package com.cdeidea.pingpong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class PingPong extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
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
	
	private void showMsg(String message) {
		Toast msg = Toast.makeText(PingPong.this, message, Toast.LENGTH_LONG);
		msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2,msg.getYOffset() / 2);
		msg.show();
	}
}