package qlnv;

import logIn.Client;
import logIn.Qltk;
import qlnv.*;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QLNV {
    static Scanner sc =  new Scanner(System.in);
    static File nhanVien = new File("nhanVien.txt");
    static ArrayList<NhanVien> list = new ArrayList<>();

//    static {
//        try {
//            list = docFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private static EmailRegex emailRegex;
    private static SdtRegex sdtRegex;

    public void them( ) throws IOException {
            System.out.println("1.thêm nhân viên fulltime");
            System.out.println("2.thêm nhân viên parttime");
            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                NhanVien nv = taoNv("full");
                list.add(nv);
            } else {
                NhanVien nv = taoNv("part");
                list.add(nv);
        }
            ghiFile();
    }

    public void search(){
        list =docFile();
        System.out.println("nhập tên cần tìm kiếm thông tin");
        String name = sc.nextLine();
        for (NhanVien nv : list){
            if(nv.getName().equals(name)){
                System.out.println(nv);
            }
        }
    }

    public void capNhat() throws IOException {
        list =docFile();
        System.out.println("nhập id của nhân viên cần sửa");
        int id = Integer.parseInt(sc.nextLine());
        for (int i = 0; i<list.size(); i++){
            if (list.get(i).getId() == id){
                if (list.get(i) instanceof NvFull){
                    list.set(i, taoNv("full"));
                }else list.set(i, taoNv("part"));
            }
        }
        ghiFile();
    }

    public void ktraTrangThai(){
        list = docFile();
        System.out.println("nhập tên nhân viên muốn kiểm tra trạng thái");
        String name = sc.nextLine();
        for (NhanVien nv : list){
            if (nv.getName().equals(name)){
                System.out.println("trạng thái: "+ nv.getTrangThai());
            }
        }
    }

    public void suaTrangThai() throws IOException {
        System.out.println("nhập tên nhân viên muốn thay đổi trạng thái");
        String name = sc.nextLine();
        for (NhanVien nv : list){
            if (nv.getName().equals(name)){
                nv.setTrangThai(getTrangThai());
                System.out.println(nv);
            }
        }
        ghiFile();
    }

    public void showNvTheoTT(){
        System.out.println("nhập trạng thái");
        String trangThai = sc.nextLine();
        for (NhanVien nv : list){
            if(nv.getTrangThai().equals(trangThai)){
                System.out.println(nv.ghi());
            }
        }
    }

    public void sortByName() throws IOException {
        SortByName sortByName = new SortByName();
        list.sort(sortByName);
        ghiFile();
    }

    public void  hTHiTK() throws Exception {
        Qltk.hThiTKDN();
    }

    public void show(){
        for (NhanVien nv : list) {
            System.out.println(nv);
        }
    }

    public int getId() {
        while (true) {
            try {
                System.out.println("nhập id");
                int id = Integer.parseInt(sc.nextLine());
                list = docFile();
                for (NhanVien nv : list) {
                    if (nv.getId() == id) {
                        throw new Loi();
                    }
                }
                return id;
            } catch (Loi e) {
                System.out.println("id bị trùng");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        while (true) {
            try {
                System.out.println("nhập tên");
                String name = sc.nextLine();
                if (name.equals("")) {
                    throw new Loi();
                }
                return name;
            }catch (Loi e){
                System.out.println("tên không được để trống");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public String getSDT() {
        while (true) {
            boolean flag;
            do {
                System.out.println("nhập số điện thoại(0XXXXXXXXX)");
                String sdt = sc.nextLine();
                sdtRegex = new SdtRegex();
                flag = sdtRegex.validate(sdt);
                if (flag){
                    return sdt;
                } else System.out.println("số điện thoại chưa hợp lệ");
            } while (!flag);
        }
    }

    public String getDiaChi(){
        System.out.println("nhập địa chỉ");
        return sc.nextLine();
    }

    public String getTrangThai(){
        while (true){
            try {
                System.out.println("nhập trạng thái( true or false)");
                String trangThai = sc.nextLine();
                if (trangThai.equals("true")||trangThai.equals("false")){
                    return trangThai;
                } else throw new Loi();
            }
            catch (Loi e){
                System.out.println("trạng thái là true hoặc false");
            }
        }
    }

    public double getTime(){
        System.out.println("nhập số giờ");
        return Double.parseDouble(sc.nextLine());
    }

    public String getEmail() {
        while (true) {
            try {
                boolean flag;
                do {
                    System.out.println("nhập email");
                    String email = sc.nextLine();
                    emailRegex = new EmailRegex();
                    flag = emailRegex.validate(email);
                    if (flag) {
                        list = docFile();
                        for (NhanVien nv : list) {
                            if (nv.getEmail().equals(email)) {
                                throw new Loi();
                            }
                        }
                        return email;
                    } else System.out.println("email chưa hợp lệ");
                } while (!flag);
            } catch (Loi e) {
                System.out.println(" email bị trùng");
            }
        }
    }

    public String gioiTinh(){
        while (true){
            try {
                System.out.println("nhập giới tính( nam hoặc nu )");
                String gender = sc.nextLine();
                if (gender.equals("nam")||gender.equals("nu")){
                    return gender;
                } else throw new Loi();

            }
            catch (Loi e){
                System.out.println("nam hoặc nữ");
            }
        }
    }

    public int age(){
        while (true) {
            try {
                System.out.println("nhập tuổi");
                int age = Integer.parseInt(sc.nextLine());
                if (age < 18) {
                    throw new Loi();
                } else {
                    return age;
                }
            } catch (Loi e) {
                System.out.println("chưa đủ tuổi lao động");
            } catch (Exception e) {
                System.out.println("tuổi là số");
            }
        }
    }

    public double luong(){
        while (true){
            try {
                System.out.println("nhập lương");
                return Double.parseDouble(sc.nextLine());

            }
            catch (Exception e){
                System.out.println("nhập số");
            }
        }
    }

    public static  void ghiFile() throws IOException {
        BufferedWriter bufferedWriter = null;
        try {
            FileWriter fileWriter = new FileWriter("nhanVien.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            list = docFile();
            for (NhanVien nv : list){
                bufferedWriter.write(nv.ghi());
                bufferedWriter.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            bufferedWriter.close();
        }
    }

    public static ArrayList<NhanVien> docFile()  {
        ArrayList<NhanVien> list1 = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader("nhanVien.txt");
            bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {

//                System.out.println(line);
                String[] str = line.split(",");
                if (str.length >= 9) {
                   //String name,int id, int age, String sdt, String gender, String email, String diaChi, double luong, String trangThai
                    list1.add(new qlnv.NhanVien(str[0], Integer.parseInt(str[1].trim()), Integer.parseInt(str[2].trim()), str[3], str[4], str[5],str[6], Double.parseDouble(str[7].trim()),str[8]));
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("danh sách trống");
        }
        return  list1;
    }

    public NhanVien taoNv(String kieuNv){
        if (kieuNv.equals("full")) {
            return new NvFull(getName(), getId(), age(), getSDT(), gioiTinh(), getEmail(), getDiaChi(), luong(), getTrangThai() );
        } else {
            return new NvPartime(getName(), getId(), age(), getSDT(), gioiTinh(), getEmail(), getDiaChi(), luong(), getTrangThai(), getTime());
        }
    }

    public void logOut() throws Exception {
        Qltk.logOut();
        Client.subMenu();
    }
}
