package com.example.dulieu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.dulieu.ui.billfrag.BillModel;
import com.example.dulieu.ui.billfrag.billct.BillCTModel;
import com.example.dulieu.ui.bookfrag.BookModel;
import com.example.dulieu.ui.category.CategoryModel;
import com.example.dulieu.ui.nguoidungfrag.UserModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NhasachDatabase extends SQLiteOpenHelper {
    private static String DB_PATH = "";
    private static String DB_NAME = "dataa.db";
    private Context context;
    SQLiteDatabase sqLiteDatabase;


    public String username = "username";
    public String password = "password";
    public String hoten = "hoten";
    public String sdt = "sdt";

    public String maSach = "maSach";
    public String maTheloai = "maTheloai";
    public String tensach = "tensach";
    public String soluong = "soluong";
    public String tacgia = "tacgia";
    public String gia = "gia";

    public String matheloai = "matheloai";
    public String tentheloai = "tentheloai";
    public String mota = "mota";
    public String vitri = "vitri";

    public String mahoadon = "mahoadon";
    public String ngaymua = "ngaymua";
    public String masach = "masach";
    public String soLuong = "soluong";


    public NhasachDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, 1);

        this.context = context;

        if (android.os.Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
    }

    public void createDataBase() {
        //If the database does not exist, copy it from the assets.

        boolean mDataBaseExist = checkDataBase();
        if (!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                //Copy the database from assests
                copyDataBase();
                Log.e("abc", "createDatabase database created");
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }

    private void copyDataBase() throws IOException {
        InputStream mInput = context.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public List<UserModel> accountList() {

        List<UserModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String SQL = "SELECT * FROM user";
        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);
        cursor.moveToFirst();

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    UserModel account = new UserModel();
                    account.username = cursor.getString(cursor.getColumnIndex(username));
                    account.password = cursor.getString(cursor.getColumnIndex(password));
                    account.hoten = cursor.getString(cursor.getColumnIndex(hoten));
                    account.sdt = cursor.getString(cursor.getColumnIndex(sdt));

                    list.add(account);
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }

        return list;
    }

    public List<BookModel> dataSachList() {

        List<BookModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String SQL = "SELECT * FROM sach";
        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);
        cursor.moveToFirst();

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    BookModel dataSach = new BookModel();
                    dataSach.masach = cursor.getString(cursor.getColumnIndex(maSach));
                    dataSach.matheloai = cursor.getString(cursor.getColumnIndex(maTheloai));
                    dataSach.tensach = cursor.getString(cursor.getColumnIndex(tensach));
                    dataSach.soluong = cursor.getString(cursor.getColumnIndex(soluong));
                    dataSach.tacgia = cursor.getString(cursor.getColumnIndex(tacgia));
                    dataSach.gia = cursor.getString(cursor.getColumnIndex(gia));

                    list.add(dataSach);
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }

        return list;
    }

    public List<CategoryModel> dataSachListBook() {

        List<CategoryModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String SQL = "SELECT * FROM theloai";
        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);
        cursor.moveToFirst();

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    CategoryModel dataLoaiSachch = new CategoryModel();
                    dataLoaiSachch.matheloai = cursor.getString(cursor.getColumnIndex(matheloai));
                    dataLoaiSachch.tentheloai = cursor.getString(cursor.getColumnIndex(tentheloai));
                    dataLoaiSachch.mota = cursor.getString(cursor.getColumnIndex(mota));
                    dataLoaiSachch.vitri = cursor.getString(cursor.getColumnIndex(vitri));


                    list.add(dataLoaiSachch);
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }

        return list;
    }

    public List<BillModel> dataSachListHoaDon() {

        List<BillModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String SQL = "SELECT * FROM hoadon";
        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);
        cursor.moveToFirst();

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    BillModel dataHoaDon = new BillModel();
                    dataHoaDon.mahoadon = cursor.getString(cursor.getColumnIndex(mahoadon));
                    dataHoaDon.ngay = cursor.getString(cursor.getColumnIndex(ngaymua));


                    list.add(dataHoaDon);
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }

        return list;
    }

    public long themUser(UserModel userModel) {


        // xin quyen
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // ghep cap ten cot vs gia tri
        ContentValues contentValues = new ContentValues();
        contentValues.put(username, userModel.getUsername());
        contentValues.put(password, userModel.getPassword());
        contentValues.put(hoten, userModel.getHoten());
        contentValues.put(sdt, userModel.getSdt());

        long id = sqLiteDatabase.insert("user",
                null, contentValues);


        sqLiteDatabase.close();
        return id;

    }

    public long themBook(BookModel bookModel) {


        // xin quyen
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // ghep cap ten cot vs gia tri
        ContentValues contentValues = new ContentValues();
        contentValues.put(maSach, bookModel.getMasach());
        contentValues.put(maTheloai, bookModel.getMatheloai());
        contentValues.put(tensach, bookModel.getTensach());
        contentValues.put(soluong, bookModel.getSoluong());
        contentValues.put(tacgia, bookModel.getTacgia());
        contentValues.put(gia, bookModel.getGia());

        long id = sqLiteDatabase.insert("sach",
                null, contentValues);


        sqLiteDatabase.close();
        return id;

    }

    public long themtheloai(CategoryModel categoryModel) {


        // xin quyen
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // ghep cap ten cot vs gia tri
        ContentValues contentValues = new ContentValues();
        contentValues.put(matheloai, categoryModel.getMatheloai());
        contentValues.put(tentheloai, categoryModel.getTentheloai());
        contentValues.put(mota, categoryModel.getMota());
        contentValues.put(vitri, categoryModel.getVitri());

        long id = sqLiteDatabase.insert("theloai",
                null, contentValues);


        sqLiteDatabase.close();
        return id;

    }

    public long themBill(BillModel billModel) {


        // xin quyen
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // ghep cap ten cot vs gia tri
        ContentValues contentValues = new ContentValues();
        contentValues.put(mahoadon, billModel.getMahoadon());
        contentValues.put(ngaymua,convertDate(billModel.getNgay()) );

        long id = sqLiteDatabase.insert("hoadon",
                null, contentValues);


        sqLiteDatabase.close();
        return id;

    }

    public void deletecAcount(UserModel userModel) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("user", username + " = ?",
                new String[]{String.valueOf(userModel.getUsername())});
        sqLiteDatabase.close();
    }

    public void deletecBook(BookModel bookModel) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("sach", maSach + " = ?",
                new String[]{String.valueOf(bookModel.getMasach())});
        sqLiteDatabase.close();
    }

    public void deletecCategory(CategoryModel categoryModel) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("theloai", matheloai + " = ?",
                new String[]{String.valueOf(categoryModel.getMatheloai())});
        sqLiteDatabase.close();
    }

    public void deletecBill(BillModel billModel) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("hoadon", mahoadon + " = ?",
                new String[]{String.valueOf(billModel.getMahoadon())});
        sqLiteDatabase.close();
    }

    public int updateAccount(UserModel userModel) {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(username, userModel.getUsername());
        contentValues.put(hoten, userModel.getHoten());
        contentValues.put(sdt, userModel.getSdt());
        contentValues.put(password, userModel.getPassword());

        return sqLiteDatabase.update("user", contentValues, "username" + " = ?",
                new String[]{userModel.getUsername()});


    }

    public long updateBook(BookModel bookModel) {


        // xin quyen
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // ghep cap ten cot vs gia tri
        ContentValues contentValues = new ContentValues();
        contentValues.put(maSach, bookModel.getMasach());
        contentValues.put(maTheloai, bookModel.getMatheloai());
        contentValues.put(tensach, bookModel.getTensach());
        contentValues.put(soluong, bookModel.getSoluong());
        contentValues.put(tacgia, bookModel.getTacgia());
        contentValues.put(gia, bookModel.getGia());


        return sqLiteDatabase.update("sach", contentValues,
                "maSach" + "=?", new String[]{bookModel.getMasach()});

    }

    public int updatetheloai(CategoryModel categoryModel) {


        // xin quyen
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // ghep cap ten cot vs gia tri
        ContentValues contentValues = new ContentValues();
        contentValues.put(matheloai, categoryModel.getMatheloai());
        contentValues.put(tentheloai, categoryModel.getTentheloai());
        contentValues.put(mota, categoryModel.getMota());
        contentValues.put(vitri, categoryModel.getVitri());


        return sqLiteDatabase.update("theloai", contentValues, matheloai + "= ?", new String[]{categoryModel.getMatheloai()});


    }

    public long updateBill(BillModel billModel) {


        // xin quyen
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // ghep cap ten cot vs gia tri
        ContentValues contentValues = new ContentValues();
        contentValues.put(mahoadon, billModel.getMahoadon());
        contentValues.put(ngaymua, billModel.getNgay());


        return sqLiteDatabase.update("hoadon", contentValues, mahoadon + "=?", new String[]{billModel.getMahoadon()});


    }

    // các câu lệnh hóa đơn chi tiết
    public long themBillCT(BillCTModel billCTModel) {


        // xin quyen
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // ghep cap ten cot vs gia tri
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHoaDon", billCTModel.getMaHoaDon());
        contentValues.put("maSach", billCTModel.getMaSach());
        contentValues.put("soLuongMua", Integer.parseInt(billCTModel.getSoLuongMua()));

        long id = sqLiteDatabase.insert("BillCT",
                null, contentValues);


        sqLiteDatabase.close();
        return id;

    }

    public List<BillCTModel> dataSachListHoaDonCT(String maHoaDon) {

        List<BillCTModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String SQL = "SELECT c.masach,c.tensach,b.soLuongMua,b.soLuongMua*c.gia as tongTien, b.mahoadon,b.maBillCT FROM hoadon a INNER JOIN BillCT b on a.mahoadon=b.mahoadon and b.mahoadon='" + maHoaDon + "' INNER JOIN sach c on b.masach=c.masach";
        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);


        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {

                    list.add(new BillCTModel(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }

        return list;
    }

    public void deleteBillCT(BillCTModel billCTModel) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("BillCT", "maBillCT = ?",
                new String[]{String.valueOf(billCTModel.getMaHoaDonChiTiet())});
        sqLiteDatabase.close();
    }

    private long convertDate(String date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date1=simpleDateFormat.parse(date);
            return date1.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public String thongKeNgay(long ngay) {
        String tien="0";

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String SQL = "SELECT sum(b.soLuongMua*c.gia) FROM hoadon a INNER JOIN BillCT b on a.mahoadon=b.mahoadon and a.ngaymua= "+ngay+" INNER JOIN sach c on b.maSach=c.maSach";
        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);


        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                 tien= cursor.getString(0);
                cursor.close();
            }else {
                tien="0";
            }
        } else {
            tien="0";
        }
        return tien;

    }

    public String thongKeTuan(long ngay1,long ngay2) {
        String tien="0";

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String SQL = "SELECT sum(b.soLuongMua*c.gia) FROM hoadon a INNER JOIN BillCT b on a.mahoadon=b.mahoadon and a.ngaymua>= "+ngay1+" and a.ngaymua <= "+ngay2+" INNER JOIN sach c on b.maSach=c.maSach";
        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);


        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                tien= cursor.getString(0);
                cursor.close();
            }else {
                tien="0";
            }
        } else {
            tien="0";
        }
        return tien;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
