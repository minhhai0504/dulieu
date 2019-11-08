package com.example.dulieu.ui.billfrag.billct;

public class BillCTModel {
    private  String maSach,tieuDe,soLuongMua,tongTien,maHoaDon,maHoaDonChiTiet;

    public BillCTModel(String maSach, String tieuDe, String soLuongMua, String tongTien, String maHoaDon, String maHoaDonChiTiet) {
        this.maSach = maSach;
        this.tieuDe = tieuDe;
        this.soLuongMua = soLuongMua;
        this.tongTien = tongTien;
        this.maHoaDon = maHoaDon;
        this.maHoaDonChiTiet = maHoaDonChiTiet;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(String soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaHoaDonChiTiet() {
        return maHoaDonChiTiet;
    }

    public void setMaHoaDonChiTiet(String maHoaDonChiTiet) {
        this.maHoaDonChiTiet = maHoaDonChiTiet;
    }
}
