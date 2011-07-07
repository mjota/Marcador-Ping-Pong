package com.cdeidea.pingpong;

import android.app.Activity;
import android.content.Context;
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
	private TextView Texto1;
	private int NumPunt;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actjuego);
        
        Button BotonJug1 = (Button) findViewById(R.id.BotonJugador1);
        BotonJug1.setOnClickListener(this);
        
        Button BotonJug2 = (Button) findViewById(R.id.BotonJugador2);
        BotonJug2.setOnClickListener(this);
        
        restorePreferences();
        
    }

	@Override
	public void onClick(View arg0) {
		int id = arg0.getId();
		switch(id){
		case R.id.BotonJugador1:
			this.showMsg("Punto para jugador 1");
			this.Vibrar(50);
			break;
		case R.id.BotonJugador2:
			this.showMsg("Punto para jugador 2");
			this.Vibrar(50);
			break;
		}
		
	}
	
	public void showMsg(String message) {
		Toast msg = Toast.makeText(ActJuego.this, message, Toast.LENGTH_SHORT);
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
		if(NumPunt==11){
			showMsg("Ha salido 11");
		}else{
			showMsg("Ha salido 21");
		}
	}
}
