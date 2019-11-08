package com.example.dulieu.ui.billfrag;

import java.io.Serializable;

public class BillModel implements Serializable {

    public String mahoadon;
    public String ngay;



    public BillModel() {
    }

    public BillModel(String mahoadon, String ngay) {
        this.mahoadon = mahoadon;
        this.ngay = ngay;

    }

    public String getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(String mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

}
