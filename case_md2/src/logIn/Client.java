package logIn;
import java.util.Scanner;

public class Client {
    static Scanner sc = new Scanner(System.in);
    static Qltk qltk = new Qltk();

    public static void main(String[] args) throws Exception {
        subMenu();
    }

        public static void subMenu() throws Exception {
        while (true) {
            System.out.println("1.Đăng nhập");
            System.out.println("2.Đăng kí");
            System.out.println("3.Exit");
            System.out.println("4.hiển thị tài khoản");
            System.out.println("5.xóa tài khoản");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> qltk.logIn();
                case 2 -> qltk.taoTk();
                case 3 -> System.exit(0);
                case 4 -> qltk.hThi();
                case 5 -> qltk.xoaTk();
                default -> System.out.println("nhập lại");
            }
        }
    }
}


