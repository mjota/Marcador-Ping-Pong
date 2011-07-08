package com.cdeidea.pingpong;

import android.os.Bundle;

import java.util.ArrayList;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Resultados extends ListActivity{
	private SQLiteDatabase db = null;
	private Cursor constantsCursor = null;
    int a = 2;
    ArrayAdapter<String> adapter;
    ArrayList<String> listItems = new ArrayList<String>();
    @Override 
   public void onCreate(Bundle savedInstanceState) { 
       super.onCreate(savedInstanceState); 
       setContentView(R.layout.list);
       db=(new Database(this)).getWritableDatabase();
       constantsCursor = db.rawQuery("SELECT _ID, jugador1, jugador2, puntos1, puntos2 "+ "FROM partidas", null);
       ListAdapter adapter=new SimpleCursorAdapter(this,
				R.layout.row, constantsCursor,
				new String[] {"jugador1", "puntos1" , "jugador2" , "puntos2"},
				new int[] {R.id.jug1, R.id.puntos1 , R.id.jug2 , R.id.puntos2});
       setListAdapter(adapter);
       //registerForContextMenu(getListView());
       new AddStringTask().execute();
   }
    class AddStringTask extends AsyncTask<Void, String, Void>{

		@Override
		protected Void doInBackground(Void... arg0) {
			for(String item : listItems){
				publishProgress(item);
				SystemClock.sleep(200);
			}
			return null;
		}
    	
		@Override
		protected void onProgressUpdate(String... item){
			Toast.makeText(Resultados.this, "Done!", Toast.LENGTH_SHORT).show();
		}
    }
	public void onDestroy() {
		super.onDestroy();
		db.close();
	}
}