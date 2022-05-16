package com.example.dnt_qlvt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DNT_Database extends SQLiteOpenHelper {
    private static final String  DATABASE_NAME =  "QLVT.db";
    private static final String TABLE_NAME = "tb_vattu";
    private static final String ID = "ma";
    private static final String NAME = "ten";
    private static final String LOAI = "loai";
    private static final String NAM = "nam";
    private static final String HANG = "hang";
    private static final String DONGIA = "dongia";
    private static final String SOLUONG = "soluong";
    private static int VERSION = 1;
    private Context context;
    public DNT_Database(Context context) {
        super(context, DATABASE_NAME, null , VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE table "+TABLE_NAME+"("
                +ID+" Text,"
                +NAME+" Text,"
                +LOAI+" Text,"
                +NAM+" integer,"
                +HANG+" Text,"
                +DONGIA+" integer,"
                +SOLUONG+" integer)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+DATABASE_NAME);

        onCreate(sqLiteDatabase);
    }
    public void insert(DNT_VATTU vattu){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, vattu.getMaMT());
        values.put(NAME, vattu.getTenMt());
        values.put(LOAI, vattu.getLoaiMt());
        values.put(NAM, vattu.getNamSx());
        values.put(HANG, vattu.getHsx());
        values.put(DONGIA, vattu.getDg());
        values.put(SOLUONG, vattu.getSl());
        sqLiteDatabase.insert(TABLE_NAME,null,values);
        sqLiteDatabase.close();

    }
    public int update (DNT_VATTU vattu){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, vattu.getMaMT());
        values.put(NAME, vattu.getTenMt());
        values.put(LOAI, vattu.getLoaiMt());
        values.put(NAM, vattu.getNamSx());
        values.put(HANG, vattu.getHsx());
        values.put(DONGIA, vattu.getDg());
        values.put(SOLUONG, vattu.getSl());
        int result = sqLiteDatabase.update(TABLE_NAME,values,ID +"=?",new String[]{String.valueOf(vattu.getMaMT())});
        sqLiteDatabase.close();
        return result;
    }
    public int delete (String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int result = sqLiteDatabase.delete(TABLE_NAME,ID+"=?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return result;
    }
    public List<DNT_VATTU> getAll(){
        List<DNT_VATTU> vatTuList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String sql = "select * from "+TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                DNT_VATTU vattu = new DNT_VATTU();
                vattu.setMaMT(cursor.getString(0));
                vattu.setTenMt(cursor.getString(1));
                vattu.setLoaiMt(cursor.getString(2));
                vattu.setNamSx(cursor.getInt(3));
                vattu.setHsx(cursor.getString(4));
                vattu.setDg(cursor.getInt(5));
                vattu.setSl(cursor.getInt(6));
                vatTuList.add(vattu);
            } while (cursor.moveToNext());
        }

        return vatTuList;
    }
    public DNT_VATTU findById(String id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,new String[]{ID,NAME,LOAI,NAM,HANG,DONGIA,SOLUONG}, ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null){
            try {
                cursor.moveToFirst();
                DNT_VATTU vattu = new DNT_VATTU();
                vattu.setMaMT(cursor.getString(0));
                vattu.setTenMt(cursor.getString(1));
                vattu.setLoaiMt(cursor.getString(2));
                vattu.setNamSx(cursor.getInt(3));
                vattu.setHsx(cursor.getString(4));
                vattu.setDg(cursor.getInt(5));
                vattu.setSl(cursor.getInt(6));
                cursor.close();
                sqLiteDatabase.close();
                return vattu;
            }
            catch (Exception e){
                return null;
            }
        }
        return null;
    }
}
