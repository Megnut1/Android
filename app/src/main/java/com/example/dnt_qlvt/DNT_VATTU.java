package com.example.dnt_qlvt;

public class DNT_VATTU {
    private String maMT,tenMt,loaiMt,hsx;
    private int namSx,dg,sl;

    public String getMaMT() {
        return maMT;
    }

    public void setMaMT(String maMT) {
        this.maMT = maMT;
    }

    public String getTenMt() {
        return tenMt;
    }

    public void setTenMt(String tenMt) {
        this.tenMt = tenMt;
    }

    public String getLoaiMt() {
        return loaiMt;
    }

    public void setLoaiMt(String loaiMt) {
        this.loaiMt = loaiMt;
    }

    public String getHsx() {
        return hsx;
    }

    public void setHsx(String hsx) {
        this.hsx = hsx;
    }

    public int getNamSx() {
        return namSx;
    }

    public void setNamSx(int namSx) {
        this.namSx = namSx;
    }

    public int getDg() {
        return dg;
    }

    public void setDg(int dg) {
        this.dg = dg;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public DNT_VATTU(String maMT, String tenMt, String loaiMt, String hsx, int namSx, int dg, int sl) {
        this.maMT = maMT;
        this.tenMt = tenMt;
        this.loaiMt = loaiMt;
        this.hsx = hsx;
        this.namSx = namSx;
        this.dg = dg;
        this.sl = sl;
    }

    public DNT_VATTU(String tenMt, String loaiMt, String hsx, int namSx, int dg, int sl) {
        this.tenMt = tenMt;
        this.loaiMt = loaiMt;
        this.hsx = hsx;
        this.namSx = namSx;
        this.dg = dg;
        this.sl = sl;
    }

    public DNT_VATTU() {
    }
}
