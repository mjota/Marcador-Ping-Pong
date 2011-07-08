package com.cdeidea.pingpong;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
	
	private static final String NOMBRE = "Database";
	public static final String JUGADOR1 = "jugador1";
	public static final String JUGADOR2  = "jugador2";
	public static final String JUG1PUN = "puntos1";
	public static final String JUG2PUN = "puntos2";

	public Database(Context context) {
		super(context, NOMBRE, null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL("CREATE TABLE partidas (_id INTEGER PRIMARY KEY AUTOINCREMENT, jugador1 TEXT, jugador2 TEXT, puntos1 REAL, puntos2 REAL);");
		db.execSQL("CREATE TABLE jugadores (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT);");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		android.util.Log.w("Partidas", "Upgrading database, which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS partidas");
		onCreate(db);
		
	}

}
