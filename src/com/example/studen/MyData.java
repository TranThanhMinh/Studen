package com.example.studen;

import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Telephony.Sms.Conversations;
import android.util.Log;

public class MyData {

	private static final String Database_Name="Student";
	private static final int Datdbase_Vesion=1;
	private static final String Database_Table="HocSinh";
	private static final String Columstt="Stt";
	private static final String Columname="Name";
	private static final String Columdiem="Diem";
	private static Context context;
	private static SQLiteDatabase mDB;
	private static OpenHelper openhelper;
	
	public MyData(Context context){
		MyData.context=context;
	}
	public MyData open() throws SQLException{
		openhelper=new OpenHelper(context);
		mDB=openhelper.getWritableDatabase();
		return this;
		
	}
	public void close(){
		openhelper.close();
	}
	public long Insert(String stt,String ten,String diem){
		ContentValues value=new ContentValues();
		value.put(Columstt, stt);
		value.put(Columname, ten);
		value.put(Columdiem, diem);
		return mDB.insert(Database_Table, null, value);
		
	}
	public long Update(String stt,String ten,String diem ){
		ContentValues value=new ContentValues();
		value.put(Columname,ten);
		value.put(Columdiem,diem);
		return mDB.update(Database_Table, value, Columstt+"='"+stt+"'", null);
		
	}
	public Cursor getAllhs(){
		Cursor cursor=mDB.query(Database_Table, new String[]{Columstt,"Name",Columdiem},null, null, null,null,null);
		return cursor;
	}
	public boolean delete(String stt){
		
		
		return mDB.delete(Database_Table,Columstt + "='" + stt + "'", null)>0;
	}
	private static class OpenHelper extends SQLiteOpenHelper{

		public OpenHelper(Context context) {
			super(context, Database_Name, null, Datdbase_Vesion);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE "
			+ Database_Table
			+ "("+ Columstt+ " NVARCHAR ," + Columname + " NVARCHAR," +  Columdiem + " NVARCHAR"+ ")");
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXIST" + Database_Table);
			onCreate(db);
		}
		
	}
}
