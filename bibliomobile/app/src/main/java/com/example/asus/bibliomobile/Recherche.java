package com.example.asus.bibliomobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recherche extends AppCompatActivity implements OnItemSelectedListener{
    private API api=APIUTILS.getAPI();
    private Spinner spinner_categorie,spinner_auteur;
EditText motcle;
Button btnrechercherche;
    String categorie,auteur;

Integer id;
  @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);
      Bundle extras = getIntent().getExtras();
      id =extras.getInt("id");
      spinner_categorie =  findViewById(R.id.spin_categorie);
      spinner_auteur=findViewById(R.id.spin_auteur);
      getCategories();
      getAuteurs();
      motcle=findViewById(R.id.editmotcle);
      btnrechercherche=findViewById(R.id.btnrecherche);
spinner_categorie.setOnItemSelectedListener(this);
      spinner_auteur.setOnItemSelectedListener(this);
      btnrechercherche.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
          String    motcletext=motcle.getText().toString();

         if(!motcletext.isEmpty()&&!categorie.equalsIgnoreCase("catégorie")&&!auteur
                .equalsIgnoreCase("auteur"))
         { String idcategorie=categorie.charAt(0)+"",idauteur=auteur.charAt(0)+"";

                  Intent i = new Intent(Recherche.this, Rechercheresult.class);
i.putExtra("type","all");
                  i.putExtra("idAut",idauteur);
                  i.putExtra("idCat",idcategorie);
                  i.putExtra(("mc"),motcletext);
            i.putExtra("id",id);
              startActivity(i);
         }
             else if( motcletext.isEmpty()&&categorie.equalsIgnoreCase("catégorie")&&auteur
                 .equalsIgnoreCase("auteur"))
         {

             Intent i = new Intent(Recherche.this, Rechercheresult.class);
             i.putExtra("type","any");
             i.putExtra("id",id);
             startActivity(i);

         }
         //recherche par auteur et categorie
         else if(motcletext.isEmpty()&&!categorie.equalsIgnoreCase("catégorie")&&!auteur
                 .equalsIgnoreCase("auteur")){
             String idcategorie=categorie.charAt(0)+"",idauteur=auteur.charAt(0)+"";
             Intent i = new Intent(Recherche.this, Rechercheresult.class);
             i.putExtra("type","autcat");
             i.putExtra("idAut",idauteur);
             i.putExtra("id",id);
             i.putExtra("idCat",idcategorie);
             startActivity(i);
         }
                 //recherche par auteur
         else if(motcletext.isEmpty()&&categorie.equalsIgnoreCase("catégorie")&&!auteur
                 .equalsIgnoreCase("auteur")){
             Intent i = new Intent(Recherche.this, Rechercheresult.class);
             i.putExtra("type","aut");
             String idauteur=auteur.charAt(0)+"";
             i.putExtra("idAut",idauteur);
             i.putExtra("id",id);
             startActivity(i);
         }
                 //recherche par categorie
         else if(motcletext.isEmpty()&&!categorie.equalsIgnoreCase("catégorie")&&auteur
                 .equalsIgnoreCase("auteur")){
             Intent i = new Intent(Recherche.this, Rechercheresult.class);
             String idcategorie=categorie.charAt(0)+"";
             i.putExtra("type","cat");
             i.putExtra("id",id);
             i.putExtra("idCat",idcategorie);
             startActivity(i);
         }
                 //recherche par mot clé
         else if(!motcletext.isEmpty()&&categorie.equalsIgnoreCase("catégorie")&&auteur
                 .equalsIgnoreCase("auteur")){
             Intent i = new Intent(Recherche.this, Rechercheresult.class);

             i.putExtra("type","mc");
             i.putExtra("id",id);
             i.putExtra(("mc"),motcletext);
             startActivity(i);
         }
                 //recherche par auteur et mot cle
         else if(!motcletext.isEmpty()&&categorie.equalsIgnoreCase("catégorie")&&!auteur
                 .equalsIgnoreCase("auteur")){
             Intent i = new Intent(Recherche.this, Rechercheresult.class);
             String idauteur=auteur.charAt(0)+"";
             i.putExtra("type","autmc");
             i.putExtra("idAut",idauteur);
             i.putExtra("id",id);
             i.putExtra(("mc"),motcletext);
             startActivity(i);
         }
                 //recherche par categorie et mot cle
         else if(!motcletext.isEmpty()&& !categorie.equalsIgnoreCase("catégorie")&&auteur
                 .equalsIgnoreCase("auteur")){
             Intent i = new Intent(Recherche.this, Rechercheresult.class);
             String idcategorie=categorie.charAt(0)+"";
             i.putExtra("type","catmc");
i.putExtra("id",id);
             i.putExtra("idCat",idcategorie);
             i.putExtra(("mc"),motcletext);
             startActivity(i);
         }
          }
      });
    }
    public void remplirspinnercategorie(List<Categorie> c)
    { spinner_categorie =  findViewById(R.id.spin_categorie);
        spinner_categorie.setOnItemSelectedListener(this);

        List list=new ArrayList();
        list.add("catégorie");
        for(int i=0;i<c.size();i++)
        {list.add(c.get(i).getIdCategorie()+" "+c.get(i).getLibelle());
        }


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_categorie.setAdapter(adapter);
    }

    public void remplirspinnerAuteur(List<Auteur> c)
    { spinner_auteur =  findViewById(R.id.spin_auteur);
        spinner_auteur.setOnItemSelectedListener(this);
        List list=new ArrayList();
        list.add("auteur");
        for(int i=0;i<c.size();i++)
        {
            list.add(c.get(i).getIdAuteur()+" "+c.get(i).getNom()+" "+c.get(i).getPrenom());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_auteur.setAdapter(adapter);
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
        Bundle extras = getIntent().getExtras();
        Integer id =extras.getInt("id");
        Intent homeIntent;
        switch(item.getItemId())
        {
            case R.id.item1:
            {homeIntent = new Intent(Recherche.this,Acceuil.class);
                homeIntent.putExtra("id",id);

                startActivity(homeIntent);}
            break;
            case R.id.item2:{homeIntent = new Intent(Recherche.this,Profil.class);
                homeIntent.putExtra("id",id);

                startActivity(homeIntent);}
            break;
          /*  case R.id.item3:
            {homeIntent = new Intent(Recherche.this,mes_Emprunts.class);
                homeIntent.putExtra("id",id);

                startActivity(homeIntent);}
            break;
*/

        }
        return true;

    }

 public void getCategories()
    {
        api.getCategories().enqueue(new
        Callback<List<Categorie>>() {

           @Override
            public void onResponse(Call<List<Categorie>> call, Response<List<Categorie>> response) {
            if(response.isSuccessful()) {
                if(response.body().size()> 0)
     remplirspinnercategorie(response.body());

                } else
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            }@Override
            public void onFailure(Call<List<Categorie>> call, Throwable t) {
               Log.e("test","Unabele to get the " + "list of categories"); }
               });


    }

    public void getAuteurs()
    {
        api.getAuteurs().enqueue(new
                                            Callback<List<Auteur>>() {

                                                @Override
                                                public void onResponse(Call<List<Auteur>> call,
                                                                       Response<List<Auteur>>
                                                                               response) {
                                                    if(response.isSuccessful()) {
                                                        if(response.body().size()> 0)
                                                            remplirspinnerAuteur(response.body());

                                                    } else
                                                        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                                                }@Override
                                                public void onFailure(Call<List<Auteur>> call,
                                                                      Throwable t) {
                                                    Log.e("test","Unabele to get the " + "list of categories"); }
                                            });


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        if(parent.getId()==R.id.spin_categorie){categorie=parent.getItemAtPosition(position).toString();}
        if(parent.getId()==R.id.spin_auteur){auteur=parent.getItemAtPosition(position).toString();}


        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
