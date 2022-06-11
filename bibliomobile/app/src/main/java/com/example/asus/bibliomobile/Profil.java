package com.example.asus.bibliomobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profil extends AppCompatActivity {
    TextView nom,prenom,tel,email,address,departement,filiere,grade,anneeinsc,titregrade,
    titreannee,titrefilier,titredept;
Integer id;
    private API api=APIUTILS.getAPI();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        nom=findViewById(R.id.nom);
        prenom=findViewById(R.id.prenom);
        email=findViewById(R.id.email);
        address=findViewById(R.id.address);
        departement=findViewById(R.id.departement);
        filiere=findViewById(R.id.filiere);
        titreannee=findViewById(R.id.titleanninc);
        titredept=findViewById(R.id.titledepartement);
        titrefilier=findViewById(R.id.titlefiliere);
        titregrade=findViewById(R.id.titlegrade);
        grade=findViewById(R.id.grade);
        anneeinsc=findViewById(R.id.anneinsc);
        tel=findViewById(R.id.telephone);
        Bundle extras = getIntent().getExtras();
         id =extras.getInt("id");
         getAdherent(id);

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
            {  homeIntent = new Intent(Profil.this,Acceuil.class);
                homeIntent.putExtra("id",id);
                startActivity(homeIntent);}break;

           /* case R.id.item3:
            { homeIntent = new Intent(Profil.this,mes_Emprunts.class);
                homeIntent.putExtra("id",id);
                startActivity(homeIntent);}break;*/
            case R.id.item4:
            {  homeIntent = new Intent(Profil.this,Recherche.class);
                homeIntent.putExtra("id",id);
                startActivity(homeIntent);}break;

        }
        return true;
    }
    public void remplirLayout(Adherent a)
    {
        nom.setText(a.getNom());
        prenom.setText(a.getPrenom());
        email.setText(a.getEmail());
        tel.setText(a.getTel().toString());
        address.setText(a.getAdresse());
        if(a.getEtu()==true)
        {
        anneeinsc.setText(a.getAnneeInscrip());
        filiere.setText(a.getFiliere());
        titredept.setVisibility(View.INVISIBLE);
        titregrade.setVisibility(View.INVISIBLE);
        departement.setVisibility(View.INVISIBLE);
        grade.setVisibility(View.INVISIBLE);
        }
        else
            {departement.setText(a.getDepartement());
    if(a.getGrade()!=null)

        grade.setText(a.getGrade().toString());
titrefilier.setVisibility(View.INVISIBLE);
titreannee.setVisibility(View.INVISIBLE);
        filiere.setVisibility(View.INVISIBLE);
        anneeinsc.setVisibility(View.INVISIBLE);}

    }
    public void getAdherent(int adherent)
    {
        api.getadherent(adherent).enqueue(new
                                         Callback<Adherent>() {

                                             @Override
                                             public void onResponse(Call<Adherent> call,
                                                                    Response<Adherent>
                                                                            response) {
                                                 if(response.isSuccessful()) {

                                                         remplirLayout(response.body());

                                                 } else
                                                     Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                                             }@Override
                                             public void onFailure(Call<Adherent> call,
                                                                   Throwable t) {
                                                 Log.e("test","Unabele to get the " + "list of categories"); }
                                         });


    }

}
