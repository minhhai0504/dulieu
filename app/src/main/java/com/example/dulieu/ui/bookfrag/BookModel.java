package com.example.dulieu.ui.bookfrag;

public class BookModel {
    public String masach;
    public String matheloai;
    public String tensach;
    public String soluong;
    public String tacgia;
    public String gia;

    public BookModel() {
    }

    public BookModel(String masach, String matheloai, String tensach, String soluong, String tacgia, String gia) {
        this.masach = masach;
        this.matheloai = matheloai;
        this.tensach = tensach;
        this.soluong = soluong;
        this.tacgia = tacgia;
        this.gia = gia;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public String getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(String matheloai) {
        this.matheloai = matheloai;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
