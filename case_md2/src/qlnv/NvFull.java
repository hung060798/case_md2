package qlnv;

import qlnv.NhanVien;

public class NvFull extends NhanVien {

    public NvFull() {
    }

    public NvFull(String name, int id, int age, String sdt, String gender, String email, String diaChi, double luong, String trangThai) {
        super(name, id, age, sdt, gender, email, diaChi, luong, trangThai);
    }

    @Override
    public double doanhThu(){
        return super.getLuong()*8;
    }
}
