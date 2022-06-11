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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Rechercheresult extends AppCompatActivity {
ListView lst;
TextView txt;
private Integer id;
    private API api=APIUTILS.getAPI();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechercheresult);
        Bundle extras = getIntent().getExtras();

        switch (extras.getString("type"))
        { case "all":
{   String idAut= extras.getString("idAut");

            String idCat=extras.getString("idCat");
            String mc=extras.getString("mc");
            getOeuvresall(Integer.parseInt(idAut),Integer.parseInt(idCat),mc);
}
break;
         case "any" : {getOeuvresany();}break;
            case "autcat" :{
                String idAut= extras.getString("idAut");
                String idCat=extras.getString("idCat");
                getOeuvresautcat(Integer.parseInt(idAut),Integer.parseInt(idCat));
            }break;
            case"aut":{String idAut= extras.getString("idAut");
            getOeuvresaut(Integer.parseInt(idAut));
            }break;
            case"cat":{ String idCat=extras.getString("idCat");
            getOeuvrescat(Integer.parseInt(idCat));}break;
            case"mc":{String mc=extras.getString("mc");
            getOeuvresmc(mc);}break;
            case"autmc":{String idAut= extras.getString("idAut");
                String mc=extras.getString("mc");
            getOeuvresautmc(Integer.parseInt(idAut),mc);}break;
            case"catmc":{String idCat=extras.getString("idCat");
                String mc=extras.getString("mc");
            getOeuvrescatmc(Integer.parseInt(idCat),mc);}break;

}
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
        switch(item.getItemId())
        {
            case R.id.item1:
            {homeIntent = new Intent(Rechercheresult.this,Acceuil.class);
                homeIntent.putExtra("id",id);
                startActivity(homeIntent);}
            break;
            case R.id.item2:{homeIntent = new Intent(Rechercheresult.this,Profil.class);
                homeIntent.putExtra("id",id);
                startActivity(homeIntent);}
            break;
         /*   case R.id.item3:
            {homeIntent = new Intent(Rechercheresult.this,mes_Emprunts.class);
                homeIntent.putExtra("id",id);
                startActivity(homeIntent);}
            break;*/
            case R.id.item4:
            {homeIntent = new Intent(Rechercheresult.this,Recherche.class);
                homeIntent.putExtra("id",id);
                startActivity(homeIntent);}
            break;


        }
        return true;

    }

    public void remplirlistoeuvre(List<Oeuvre> c)
    { txt=findViewById(R.id.txtres);
    if(c.size()>0)
    {  txt.setVisibility(View.INVISIBLE);
        lst =  findViewById(R.id.lstrecherche);

        List list=new ArrayList();

        for(int i=0;i<c.size();i++)
        {
            list.add(c.get(i).getIdOeuvre()+" : "+c.get(i).getTitre()+" /         "+c.get(i)
                    .getAuteur()
                    .getNom()+" "+c.get(i).getAuteur().getPrenom());
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Rechercheresult.this,
                android.R.layout.simple_list_item_1, list);lst.setAdapter(adapter);
                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                               @Override
                                               public void onItemClick(AdapterView<?> parent,
                                                                       View view, int position, long idl) {
                                                   String oeuvre=(String)lst.getItemAtPosition(position);
                                                   int idoeuvre=Integer.parseInt(oeuvre.charAt
                                                           (0)+"");
                                                   Intent i = new Intent(Rechercheresult.this,
                                                           Details_livre.class);
                                                   i.putExtra("idoeuvre",idoeuvre);
                                                   i.putExtra("id",id);
                                                   startActivity(i);

                                               }
                                           }
                );}
                else{
Toast.makeText(Rechercheresult.this,"aucun résultat",Toast.LENGTH_SHORT).show();
Intent i=new Intent(Rechercheresult.this,Recherche.class);
        i.putExtra("id",id);
startActivity(i);
    }


    }

    public void getOeuvresall(int idaut,int idcat, String mc)
    {
        api.getOeuvreparautcatmc(idaut,idcat,mc).enqueue(new
                                            Callback<List<Oeuvre>>()
                                            {@Override
                                                public void onResponse(Call<List<Oeuvre>> call, Response<List<Oeuvre>> response)
                                            {
                                                    if(response.isSuccessful()) {

                                                         remplirlistoeuvre(response.body());

                                                    } else
                                                        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                                                }@Override
                                                public void onFailure(Call<List<Oeuvre>> call,
                                                                      Throwable t) {
                                                    Log.e("test","Unabele to get the " + "list of categories"); }
                                            });


    }
    public void getOeuvresany()
    {
        api.getOeuvres().enqueue(new
                                                                 Callback<List<Oeuvre>>()
                                                                 {@Override
                                                                 public void onResponse(Call<List<Oeuvre>> call, Response<List<Oeuvre>> response)
                                                                 {
                                                                     if(response.isSuccessful()) {

                                                                             remplirlistoeuvre(response.body());

                                                                     } else
                                                                         Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                                                                 }@Override
                                                                 public void onFailure(Call<List<Oeuvre>> call,
                                                                                       Throwable t) {
                                                                     Log.e("test","Unabele to get the " + "list of categories"); }
                                                                 });


    }
    public void getOeuvresaut(int idAut)
    {
        api.getOeuvresparaut(idAut).enqueue(new
                                         Callback<List<Oeuvre>>()
                                         {@Override
                                         public void onResponse(Call<List<Oeuvre>> call, Response<List<Oeuvre>> response)
                                         {
                                             if(response.isSuccessful()) {

                                                     remplirlistoeuvre(response.body());

                                             } else
                                                 Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                                         }@Override
                                         public void onFailure(Call<List<Oeuvre>> call,
                                                               Throwable t) {
                                             Log.e("test","Unabele to get the " + "list of categories"); }
                                         });


    }
    public void getOeuvrescat(int idCat)
    {
        api.getOeuvresparcat(idCat).enqueue(new
                                                    Callback<List<Oeuvre>>()
                                                    {@Override
                                                    public void onResponse(Call<List<Oeuvre>> call, Response<List<Oeuvre>> response)
                                                    {
                                                        if(response.isSuccessful()) {

                                                                remplirlistoeuvre(response.body());

                                                        } else
                                                            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                                                    }@Override
                                                    public void onFailure(Call<List<Oeuvre>> call,
                                                                          Throwable t) {
                                                        Log.e("test","Unabele to get the " + "list of categories"); }
                                                    });


    }
    public void getOeuvresmc(String mc)
    {
        api.getOeuvresparmc(mc).enqueue(new
                                                    Callback<List<Oeuvre>>()
                                                    {@Override
                                                    public void onResponse(Call<List<Oeuvre>> call, Response<List<Oeuvre>> response)
                                                    {
                                                        if(response.isSuccessful()) {

                                                                remplirlistoeuvre(response.body());

                                                        } else
                                                            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                                                    }@Override
                                                    public void onFailure(Call<List<Oeuvre>> call,
                                                                          Throwable t) {
                                                        Log.e("test","Unabele to get the " + "list of categories"); }
                                                    });


    }
    public void getOeuvresautcat(int idAut,int idCat)
    {
        api.getOeuvreparautcat(idAut,idCat).enqueue(new
                                                Callback<List<Oeuvre>>()
                                                {@Override
                                                public void onResponse(Call<List<Oeuvre>> call, Response<List<Oeuvre>> response)
                                                {
                                                    if(response.isSuccessful()) {

                                                            remplirlistoeuvre(response.body());

                                                    } else
                                                        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                                                }@Override
                                                public void onFailure(Call<List<Oeuvre>> call,
                                                                      Throwable t) {
                                                    Log.e("test","Unabele to get the " + "list of categories"); }
                                                });


    }
    public void getOeuvresautmc(int idAut,String mc)
    {
        api.getOeuvreparautmc(idAut,mc).enqueue(new
                                                            Callback<List<Oeuvre>>()
                                                            {@Override
                                                            public void onResponse(Call<List<Oeuvre>> call, Response<List<Oeuvre>> response)
                                                            {
                                                                if(response.isSuccessful()) {

                                                                        remplirlistoeuvre(response.body());

                                                                } else
                                                                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                                                            }@Override
                                                            public void onFailure(Call<List<Oeuvre>> call,
                                                                                  Throwable t) {
                                                                Log.e("test","Unabele to get the " + "list of categories"); }
                                                            });


    }
    public void getOeuvrescatmc(int idCat,String mc)
    {
        api.getOeuvreparcatmc(idCat,mc).enqueue(new
                                                        Callback<List<Oeuvre>>()
                                                        {@Override
                                                        public void onResponse(Call<List<Oeuvre>> call, Response<List<Oeuvre>> response)
                                                        {
                                                            if(response.isSuccessful()) {

                                                                    remplirlistoeuvre(response.body());

                                                            } else
                                                                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                                                        }@Override
                                                        public void onFailure(Call<List<Oeuvre>> call,
                                                                              Throwable t) {
                                                            Log.e("test","Unabele to get the " + "list of categories"); }
                                                        });


    }
}
