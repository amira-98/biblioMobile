package com.example.asus.bibliomobile;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Acceuil extends AppCompatActivity {
private DrawerLayout drawer;



Integer id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        Bundle extras = getIntent().getExtras();
        id =extras.getInt("id");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        Intent homeIntent;
        Bundle extras = getIntent().getExtras();
        Integer id =extras.getInt("id");
        switch(item.getItemId())
        {
            case R.id.item2:{

                homeIntent = new Intent(Acceuil.this,Profil.class);
                homeIntent.putExtra("id",id);
                startActivity(homeIntent);}
                break;
           /* case R.id.item3:
            { homeIntent = new Intent(Acceuil.this,mes_Emprunts.class);
                homeIntent.putExtra("id",id);
                startActivity(homeIntent);}
                break;*/
            case R.id.item4:
            { homeIntent = new Intent(Acceuil.this,Recherche.class);
                homeIntent.putExtra("id",id);
                startActivity(homeIntent);}
                break;

        }
return true;
    }

}
