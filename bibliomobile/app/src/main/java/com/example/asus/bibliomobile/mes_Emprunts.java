package com.example.asus.bibliomobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mes_Emprunts extends AppCompatActivity {
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
        switch(item.getItemId())
        {
            case R.id.item1:
            {homeIntent = new Intent(mes_Emprunts.this,Acceuil.class);
                homeIntent.putExtra("id",id);
                startActivity(homeIntent);}break;
            case R.id.item2:{homeIntent = new Intent(mes_Emprunts.this,Profil.class);
                homeIntent.putExtra("id",id);
                startActivity(homeIntent);}break;

            case R.id.item4:
            {homeIntent = new Intent(mes_Emprunts.this,Recherche.class);
                homeIntent.putExtra("id",id);
                startActivity(homeIntent);}
                break;

        }
return true;
    }
    Integer id; private ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_emprunts);
        Bundle extras = getIntent().getExtras();
        id =extras.getInt("id");
getemprunts(id);

    }
    public void remplirlistemprunt(List<Emprunt> c)
    {
        if(c.size()>0)
        {
            lst =  findViewById(R.id.lstrecherche);

            List list=new ArrayList();

            for(int i=0;i<c.size();i++)
            {
                list.add("code du livre :"+c.get(i).getId().getIdLivre()+" // date emprunt : "+c.get
                        (i).getId().getDateEmprunt()+" // Date de retour :"+c.get(i)
                        .getDateRetourTheorique());

            }
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(mes_Emprunts.this,
                    android.R.layout.simple_list_item_1, list);
            lst.setAdapter(adapter);

            }
        else{
            Toast.makeText(mes_Emprunts.this,"aucun résultat",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(mes_Emprunts.this,Acceuil.class);
            i.putExtra("id",id);
            startActivity(i);
        }


    }
    private API api=APIUTILS.getAPI();
    public void getemprunts(int idadh)
    {
        api.getempruntsparadherent(idadh).enqueue(new
                                                                 Callback<List<Emprunt>>()
                                                                 {@Override
                                                                 public void onResponse
                                                                         (Call<List<Emprunt>> call,
                                                                          Response<List<Emprunt>>
                                                                                  response)
                                                                 {
                                                                     if(response.isSuccessful()) {

                                                                         remplirlistemprunt(response
                                                                                 .body());

                                                                     } else
                                                                         Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                                                                 }@Override
                                                                 public void onFailure
                                                                         (Call<List<Emprunt>> call,
                                                                                       Throwable t) {
                                                                     Log.e("test","Unabele to " +
                                                                             "get the " + "list " +
                                                                             "of emprunts"); }
                                                                 });


    }
}
