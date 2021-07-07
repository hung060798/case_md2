package qlnv;

import java.io.Serializable;

public class NhanVien implements Serializable {

    private String name;
    private int age;
    private String sdt;
    private String gender;
    private String email;
    private String diaChi;
    private double luong;
    private String trangThai;
    private int id;

    public NhanVien() {
    }

    public NhanVien(String name,int id, int age, String sdt, String gender, String email, String diaChi, double luong, String trangThai) {
        this.name = name;
        this.age = age;
        this.sdt = sdt;
        this.gender = gender;
        this.email = email;
        this.diaChi = diaChi;
        this.luong = luong;
        this.trangThai = trangThai;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSdt() {
        return sdt;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public double getLuong() {
        return luong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }



    public double doanhThu(){
        return getLuong();
    }

    @Override
    public String toString() {
        return "qlnv.NhanVien{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sdt='" + sdt + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", luong=" + luong +
                ", trangThai='" + trangThai + '\'' +
                ", id=" + id +
                '}';
    }

    public  String ghi(){
        return name + ", " + id +", " + age + ", " + sdt + ", " + gender + ", " + email + ", " + diaChi + ", " + luong + ", " + trangThai;
    }
}
