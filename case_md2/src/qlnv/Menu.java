package qlnv;
import java.util.Scanner;

public class Menu {
    static Scanner sc = new Scanner(System.in);
    static QLNV qlnv = new QLNV();
    public static void Menu() throws Exception {

        while (true) {
            System.out.println("~~~~~ Menu ~~~~~");
            System.out.println("1. Thêm nhân viên.");
            System.out.println("2. Tìm kiếm nhân viên.");
            System.out.println("3. Kiểm tra trạng thái nhân viên.");
            System.out.println("4. Sửa thông tin nhân viên.");
            System.out.println("5. Thay đổi trạng thái của nhân viên.");
            System.out.println("6. Thông tin tài khoản.");
            System.out.println("7. Đăng xuất.");
            System.out.println("8. Hiển thị nhân viên");
            System.out.println("9. Sắp xếp theo tên nv");
            System.out.println("10. Hiển thị nhân viên theo trạng thái");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> qlnv.them();
                case 2 -> qlnv.search();
                case 3 -> qlnv.ktraTrangThai();
                case 4 -> qlnv.capNhat();
                case 5 -> qlnv.suaTrangThai();
                case 6 -> qlnv.hTHiTK();
                case 7 -> qlnv.logOut();
                case 8 -> qlnv.show();
                case 9 -> qlnv.sortByName();
                case 10 -> qlnv.showNvTheoTT();
                default -> System.out.println("nhập lại");
            }
        }
    }
}
