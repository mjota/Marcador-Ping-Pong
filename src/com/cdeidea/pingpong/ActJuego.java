package com.cdeidea.pingpong;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActJuego extends Activity implements OnClickListener{
	private SharedPreferences pref;
	private int NumPunt;
	private int n1 , n2 = 0;
	private TextView TextoJug1, TextoJug2;
	Button BotonJug1, BotonJug2;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actjuego);
        
        BotonJug1 = (Button) findViewById(R.id.BotonJugador1);
        BotonJug1.setOnClickListener(this);
        
        BotonJug2 = (Button) findViewById(R.id.BotonJugador2);
        BotonJug2.setOnClickListener(this);
        
        TextoJug1 = (TextView) findViewById(R.id.TextJug1);
        TextoJug2 = (TextView) findViewById(R.id.TextJug2);
        
        restorePreferences();
        
        this.setTitle("Partida en juego a " + Integer.toString(NumPunt) + " puntos");
        
    }

	public void onClick(View arg0) {
		int id = arg0.getId();
		switch(id){
		case R.id.BotonJugador1:
			this.Vibrar(50);
			n1++;
			BotonJug1.setText(Integer.toString(n1));
			Juego();
			break;
		case R.id.BotonJugador2:
			this.Vibrar(50);
			n2++;
			BotonJug2.setText(Integer.toString(n2));
			Juego();
			break;
		}
		
	}
	
	public void Juego(){
		if ((n1>=NumPunt) && (n1>=n2+2) ){
			showMsg("Ha ganado " + TextoJug1.getText());
			Vibrar(3000);
			Intent intent2 = new Intent(ActJuego.this,PingPong.class );
			startActivity(intent2);
		} 
		if ((n2>=NumPunt) && (n2>=n1+2)){
			showMsg("Ha ganado " + TextoJug2.getText());
			Vibrar(3000);
			Intent intent2 = new Intent(ActJuego.this,PingPong.class );
			startActivity(intent2);
		}
	}
	
	public void showMsg(String message) {
		Toast msg = Toast.makeText(ActJuego.this, message, Toast.LENGTH_LONG);
		msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2,msg.getYOffset() / 2);
		msg.show();
	}
	
	protected void Vibrar(int dv){
		String vs = Context.VIBRATOR_SERVICE;
		Vibrator vibrator = (Vibrator) getSystemService(vs);
		vibrator.vibrate(dv);
	}
	
	protected void restorePreferences(){ 
		int mode = Activity.MODE_PRIVATE; 
		pref = getSharedPreferences("mypref", mode); 
		NumPunt = pref.getInt("numpuntos", 11);
		TextoJug1.setText(pref.getString("jugador1", "Jugador 1"));
		TextoJug2.setText(pref.getString("jugador2", "Jugador 2"));
	}
}
