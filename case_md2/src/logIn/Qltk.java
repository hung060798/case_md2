package logIn;
import qlnv.Loi;
import qlnv.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Qltk {
    static Menu menu = new Menu();
    static Scanner sc = new Scanner(System.in);
    static File file = new File("taiKhoan.txt");
    static File file1 = new File("taiKhoanDN.txt");
    static ArrayList<TaiKhoan> list1 = new ArrayList<>();
    static ArrayList<TaiKhoan> list = new ArrayList<>();


    public static String getTK() {
        while (true) {
            try {
                System.out.println("nhập tên đăng nhập");
                String tk = sc.nextLine();
                for (TaiKhoan t : list){
                    if (t.getTk().equals(tk)){
                        throw new Loi();
                    }
                }
                if (tk.equals("")) {
                    throw new Loi();
                }
                return tk;
            } catch (Loi loi) {
                System.out.println("không được để trống hoặc bị trùng");
            }
        }
    }

    public static String getMK() {
        while(true){
        try {
            System.out.println("nhập mật khẩu");
            String mk = sc.nextLine();
            if (mk.equals("")) {
                throw new Loi();
            }
            return mk;
        } catch (Loi e) {
            System.out.println("không được để trống");
        }
    }
}


    public static void logIn() throws Exception {
        while (true) {
            try {
                System.out.println("Nhập tên đăng nhập");
                String tk = sc.nextLine();
                System.out.println("Nhập mật khẩu");
                String mk = sc.nextLine();
                boolean check = true;
                list = docTK();
                for (TaiKhoan t : list) {
                    if (t.getTk().equals(tk) && t.getMk().equals(mk)){
                        check = false;
                        list1.add(new TaiKhoan(tk, mk));
                        ghiTKDN(list1);
                        menu.Menu();
                        break;
                    }
                }
                if (check){
                    throw  new Loi();
                }
            } catch (Loi e) {
                System.out.println("sai đăng nhập");
            }
        }
    }

    public static void logOut(){
        list1.remove(0);
    }


//    public static  void ghiTK() throws IOException {
//        BufferedWriter bufferedWriter = null;
//        try {
//            FileWriter fileWriter = new FileWriter(taiKhoan);
//            bufferedWriter = new BufferedWriter(fileWriter);
//
//            for (TaiKhoan tk : taiKhoans){
//                bufferedWriter.write(tk.ghiTk());
//                bufferedWriter.newLine();
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }finally {
//            bufferedWriter.close();
//        }
//    }
//
//    public static void docTK() throws IOException {
//        BufferedReader bufferedReader = null;
//        try {
//            FileReader fileReader = new FileReader(taiKhoan);
//            bufferedReader = new BufferedReader(fileReader);
//            String line = "";
//            while ((line = bufferedReader.readLine()) != null) {
//
//                System.out.println(line);
////                String[] str = line.split(",");
////                if (str.length >= 4) {
////                    list.add(new qlnv.NhanVien(str[0], Integer.parseInt(str[1].trim()), str[2], str[3], str[4], str[5], Double.parseDouble(str[6].trim()),str[7]));
////                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            bufferedReader.close();
//        }
//    }

    static public void hThi(){
            try {
                list = docTK();
                for (TaiKhoan t : list){
                    System.out.println( "tài khoản: " +t.getTk());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    static public  void  hThiTKDN() throws Exception {
       list1 = docTKDN();
       for (TaiKhoan t :list1){
           System.out.println("tài khoản đang đăng nhập là: " +t.getTk());
       }
    }

    static public void ghiTKDN(ArrayList<TaiKhoan> tk)  {
        try {
            FileOutputStream fos = new FileOutputStream(file1);
            ObjectOutput oos = new ObjectOutputStream(fos);
            oos.writeObject(tk);
            oos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<TaiKhoan> docTKDN() {
       ArrayList<TaiKhoan> t = new ArrayList<>();
       try {
           if (!file1.exists())
               file1.createNewFile();
           ObjectInputStream ois = new ObjectInputStream(new FileInputStream("taiKhoanDN.txt"));
           t = (ArrayList<TaiKhoan>) ois.readObject();
           ois.close();
       } catch (IOException | ClassNotFoundException e) {
           System.out.println("trống");
       }
            return t;
    }

    public static void taoTk() {
        list.add( new TaiKhoan(getTK(),getMK()));
        ghiTK(list);
    }

    public static void xoaTk(){
        System.out.println("nhập tên tài khoản ");
        String nameTk = sc.nextLine();
        for (int i =0; i<list.size(); i++){
            if (list.get(i).getTk().equals(nameTk)){
                list.remove(i);
            }
        }
        ghiTK(list);
    }

    public static void ghiTK( ArrayList<TaiKhoan> tk) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tk);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<TaiKhoan> docTK() {
        ArrayList<TaiKhoan> t = new ArrayList<>();
        try {
            if (!file.exists())
                file.createNewFile();
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            t = (ArrayList<TaiKhoan>) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            System.out.println("chưa có tài khoản được tạo");
        }
        return t;
    }
}
