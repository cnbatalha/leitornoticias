package com.leitorrss.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "RSS_DB";
	private static final int DATABASE_VERSION = 1;
	
	final static String URL_TB_NAME = "URL_RSS";
	final static String URL_END = "ENDERECO";
	final static String DESCRICAO = "DESCRICAO";
	final static String _ID = "_id";
	final static String[] columns = { _ID, URL_END, DESCRICAO };

	final private static String CREATE_CMD =

	"CREATE TABLE url_rss (" + _ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ URL_END + " TEXT NOT NULL,"
			+ " DESCRICAO TEXT NOT NULL)";
	
	private Context mContext;
	
	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.mContext = context;
	}	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_CMD);
	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
