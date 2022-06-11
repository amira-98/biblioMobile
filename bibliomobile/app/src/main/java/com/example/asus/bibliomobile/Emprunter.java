package com.example.asus.bibliomobile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Emprunter extends AppCompatActivity {
TextView idadh,idliv,datetheorique;
EditText dateemprunt;
Button valider;
    private API api=APIUTILS.getAPI();
int idOeuv,idLiv,idAdh;
    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprunter);
        idadh=findViewById(R.id.idadherent);
        idliv=findViewById(R.id.idlivre);

        dateemprunt=findViewById(R.id.dateemprunt);
      final   DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabel();
            }

        };
        dateemprunt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Emprunter.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
valider=findViewById(R.id.valider);
        Bundle extras = getIntent().getExtras();
        idOeuv=extras.getInt("idoeuvre");
        idLiv=extras.getInt("idlivre");
        idAdh=extras.getInt("idadh");
        idadh.setText(idAdh+"");
        idliv.setText(idLiv+"");
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmprunt(idAdh,idLiv,dateemprunt.getText().toString());
            }
        });


    }
    public void returntoacceuil(){
        Intent i=new Intent(Emprunter.this,Acceuil.class);
    i.putExtra("id",idAdh);
    startActivity(i);
    }
    public void addEmprunt(int idAdh,int idLiv,String date)
    {
        api.addEmprunt(idAdh,idLiv,date).enqueue(new
                                                      Callback<Emprunt>()
                                                      {@Override
                                                      public void onResponse(Call<Emprunt> call,
                                                                             Response<Emprunt> response)
                                                      {
                                                          if(response.isSuccessful())

                                                              returntoacceuil();

                                                           else
                                                              Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                                                      }@Override
                                                      public void onFailure
                                                              (Call<Emprunt> call,
                                                               Throwable t) {
                                                          Log.e("test","Unabele to get the " + "list of categories"); }
                                                      });


    }
    private void updateLabel() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);

        dateemprunt.setText(sdf.format(myCalendar.getTime()));
    }
}
