package com.cdeidea.pingpong;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Jugadores extends Activity implements OnClickListener {
	
	private SharedPreferences pref;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jugadores);
        
        Button BotonGuardar = (Button) findViewById(R.id.BotonGuardar);
        BotonGuardar.setOnClickListener(this);
        
        
    }

	@Override
	public void onClick(View arg0) {
		int id = arg0.getId();
		switch(id){
		case R.id.BotonGuardar:
			EditText ETexto1 = (EditText) findViewById(R.id.editText1);
			String s1 = ETexto1.getText().toString();
			EditText ETexto2 = (EditText) findViewById(R.id.editText2);
			String s2 = ETexto2.getText().toString();
			this.savePreferences(s1,s2);
			Intent intent2 = new Intent(Jugadores.this,PingPong.class );
			startActivity(intent2);
		}
	}
	
	protected void savePreferences(String s1, String s2){ 
		int mode = Activity.MODE_PRIVATE; 
		pref = getSharedPreferences("mypref", mode); 
		SharedPreferences.Editor editor = pref.edit(); 
		editor.putString("jugador1", s1); 
		editor.putString("jugador2", s2); 
		editor.commit();
	}
}
