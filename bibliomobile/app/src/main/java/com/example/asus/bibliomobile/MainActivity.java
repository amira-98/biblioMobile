package com.example.asus.bibliomobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText email,password;
    Button btn;
    private API api=APIUTILS.getAPI();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn_login);
        email = findViewById(R.id.edit_login);
        password = findViewById(R.id.edit_motdepasse);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login=email.getText().toString();
                String pass=password.getText().toString();

                if (!TextUtils.isEmpty(login) && !TextUtils.isEmpty(pass)) {

                    login(login,pass);
                }

            }});   }

public void login(String login,String pass)
{
    api.getComptes(login,pass).enqueue(new
     Callback<List<Compte>>() {
        @Override
        public void onResponse(Call<List<Compte>> call, Response<List<Compte>> response) {
            if(response.isSuccessful()){
                if(response.body().size()>0)
                navigatetoHomeActivity(response.body());
                else  Toast.makeText(getApplicationContext(),"password or login are not correct",
                        Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Call<List<Compte>> call, Throwable t) {
Log.e("test","Unabele to login");
        }
    });
}

    public void navigatetoHomeActivity(List<Compte> c){

        Intent homeIntent = new Intent(getApplicationContext(),Acceuil.class);

homeIntent.putExtra("id",c.get(0).getAdherent().getId());

        startActivity(homeIntent);

    }
}
