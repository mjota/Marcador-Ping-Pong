package com.cdeidea.pingpong;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class PingPong extends Activity implements OnClickListener {
	
	private SharedPreferences pref;
	final CharSequence[] items = {"11 puntos", "21 puntos"};
	static final int DIALOG_EXIT_ID = 0;
	static final int DIALOG_PUNTOS = 1;
	TextView t;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ImageButton BotonJugar = (ImageButton) findViewById(R.id.imageButton1);
        BotonJugar.setOnClickListener(this);
        
        ImageButton BotonResultados = (ImageButton) findViewById(R.id.imageButton2);
        BotonResultados.setOnClickListener(this);
        
    }
    
    public void onClick(View arg0) {
		int id = arg0.getId();
		switch(id){
		case R.id.imageButton1:
			showDialog(DIALOG_PUNTOS);
			break;	
		case R.id.imageButton2:
			Intent intent2 = new Intent(this,Resultados.class );
			startActivity(intent2);
			break;
		}
    }
    
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		  	switch(item.getItemId()){
			   case R.id.MenuNombre:
				   Intent intent = new Intent(this, Jugadores.class);
				   startActivity(intent);
				   return true;
			   case R.id.MenuAcerca:
				   this.showMsg("Juego desarrollado por manuel.pol@gmail.com");
			}
		   return true;
	}
	
	public void showMsg(String message) {
		Toast msg = Toast.makeText(PingPong.this, message, Toast.LENGTH_LONG);
		msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2,msg.getYOffset() / 2);
		msg.show();
	}
	protected Dialog onCreateDialog(int id){
		Dialog dialog;
		switch(id){
		case DIALOG_EXIT_ID:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("�Est� seguro que quiere salir?")
				.setCancelable(false)
				.setPositiveButton("S�", new DialogInterface.OnClickListener(){
				  public void onClick(DialogInterface dialog, int id){
				    PingPong.this.finish();
			 	  }
				})
				.setNegativeButton("No", new DialogInterface.OnClickListener(){
				  public void onClick(DialogInterface dialog, int id){
				    dialog.cancel();
				  }
			});
			dialog = builder.create();
			break;
			case DIALOG_PUNTOS:
				AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
			    builder2.setTitle("Seleccione el número de puntos");
			    builder2.setItems(items, new DialogInterface.OnClickListener(){
			    	public void onClick(DialogInterface dialog, int item){
			    		switch(item){
			    			case 0:
			    				PingPong.this.showMsg("Partida a 11 puntos");
			    				PingPong.this.savePreferences(11);
			    				Intent intent = new Intent(PingPong.this,ActJuego.class );
			    				startActivity(intent);
			    				break;
			    			case 1:
			    				PingPong.this.showMsg("Partida a 21 puntos");
			    				PingPong.this.savePreferences(21);
			    				Intent intent2 = new Intent(PingPong.this,ActJuego.class );
			    				startActivity(intent2);
			    				break;
			    		}
			    	}
			    });
			    dialog = builder2.create();
			    break;
			default:
				dialog = null;
		}
		return dialog;
	}
	protected void savePreferences(int NumPunt){ 
		int mode = Activity.MODE_PRIVATE; 
		pref = getSharedPreferences("mypref", mode); 
		SharedPreferences.Editor editor = pref.edit(); 
		editor.putInt("numpuntos", NumPunt); 
		editor.commit();
	}
}