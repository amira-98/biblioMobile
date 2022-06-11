package com.example.asus.bibliomobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Details_livre extends AppCompatActivity {
TextView titre,categorie,auteur,disponibilité,idlivre;
Button emprunter;
    private API api=APIUTILS.getAPI();
    private Integer id;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_livre);

        Bundle extras = getIntent().getExtras();
      final int idoeuvre= extras.getInt("idoeuvre");
        id =extras.getInt("id");
        idlivre=findViewById(R.id.idlivre);
      titre=findViewById(R.id.txttitre);
      categorie=findViewById(R.id.txtcategorie);
      auteur=findViewById(R.id.txtauteur);
      disponibilité=findViewById(R.id.txtetat);
      emprunter=findViewById(R.id.emprunter);

        getOeuvre(idoeuvre);
        getLivres(idoeuvre);
        emprunter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Details_livre.this,Emprunter.class);
                i.putExtra("idoeuvre",idoeuvre);
                i.putExtra("idlivre",idliv);
                i.putExtra("idadh",id);
                startActivity(i);

            }
        });


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
        switch(item.getItemId())
        {
            case R.id.item1:
            {homeIntent = new Intent(Details_livre.this,Acceuil.class);
                homeIntent.putExtra("id",id);
                startActivity(homeIntent);}
            break;
            case R.id.item2:{
                homeIntent = new Intent(Details_livre.this,Profil.class);
                homeIntent.putExtra("id",id);
                startActivity(homeIntent);
            }
            break;
        /*    case R.id.item3:
            {
                homeIntent = new Intent(Details_livre.this,mes_Emprunts.class);
                homeIntent.putExtra("id",id);
                startActivity(homeIntent);}
            break;*/
            case R.id.item4:
            {homeIntent = new Intent(Details_livre.this,Recherche.class);
                homeIntent.putExtra("id",id);
                startActivity(homeIntent);
            }
            break;


        }
        return true;

    }
    public void remplirchamps(Oeuvre c)
        {
         titre.setText(c.getTitre());
         categorie.setText(c.getCategorie().getLibelle());
         auteur.setText(c.getAuteur().getNom()+" "+c.getAuteur().getPrenom());




    }
    public void getOeuvre(int idOeuv)
    {
        api.getOeuvre(idOeuv).enqueue(new
                                                                 Callback<Oeuvre>()
                                                                 {@Override
                                                                 public void onResponse(Call<Oeuvre> call, Response<Oeuvre> response)
                                                                 {
                                                                     if(response.isSuccessful()) {

                                                                             remplirchamps(response
                                                                                     .body());

                                                                     } else
                                                                         Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                                                                 }@Override
                                                                 public void onFailure
                                                                         (Call<Oeuvre> call,
                                                                                       Throwable t) {
                                                                     Log.e("test","Unabele to get the " + "list of categories"); }
                                                                 });


    }
    private int idliv;
    @SuppressLint("ResourceAsColor")
    public void rempliretat(List<Livre> c)
    {if(c.size()>0)
    { if(c.get(0).getEtat()== 1)
          disponibilité.setText("disponible");
      else { disponibilité.setText("en rupture de stock");
      emprunter.setEnabled(false);
        emprunter.setBackgroundColor(R.color.grey);
      }
idlivre.setText(c.get(0).getIdLivre()+"");
    }
    else {disponibilité.setText("en rupture de stock");
        emprunter.setEnabled(false);
        emprunter.setBackgroundColor(R.color.grey);}
        idliv=c.get(0).getIdLivre();


    }
    public void getLivres(int idOeuv)
    {
        api.getlivreparoeuvre(idOeuv).enqueue(new
                                              Callback<List<Livre>>()
                                              {@Override
                                              public void onResponse(Call<List<Livre>> call, Response<List<Livre>> response)
                                              {
                                                  if(response.isSuccessful()) {

                                                      rempliretat(response
                                                              .body());

                                                  } else
                                                      Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                                              }@Override
                                              public void onFailure
                                                      (Call<List<Livre>> call,
                                                       Throwable t) {
                                                  Log.e("test","Unabele to get the " + "list of categories"); }
                                              });


    }
}
