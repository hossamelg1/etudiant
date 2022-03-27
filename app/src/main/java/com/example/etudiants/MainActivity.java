package com.example.etudiants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.etudiants.model.Etudiant;
import com.example.etudiants.remote.ApiUtils;
import com.example.etudiants.remote.EtudiantService;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EtudiantService etudiantService;
    private EditText nom;
    private EditText prenom;
    private Spinner ville;
    private RadioButton m;
    private RadioButton f;
    private Button add, getAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAll = (Button) findViewById(R.id.getAll);
        nom = (EditText) findViewById(R.id.nom);
        prenom = (EditText) findViewById(R.id.prenom);
        ville = (Spinner) findViewById(R.id.ville);
        add = (Button) findViewById(R.id.add);
        f = (RadioButton) findViewById(R.id.f);
        m = (RadioButton) findViewById(R.id.m);
        setTitle("Etudiant");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etudiantService = ApiUtils.getUserService();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Etudiant etudiant = new Etudiant();
                etudiant.setNom(nom.getText().toString());
                etudiant.setPrenom(prenom.getText().toString());
                etudiant.setVille(ville.getSelectedItem().toString());
                String sexe ="";
                if(m.isChecked()== true){
                    sexe = "home";
                }
                if(f.isChecked() == true){
                    sexe = "femme";
                }
                etudiant.setSexe(sexe);
                addUser(etudiant);
                nom.setText("");
                prenom.setText("");
                f.setChecked(false);
                m.setChecked(false);
            }
        });
        getAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListEtudiants.class);
                getEtudiants();
            }
        });
    }
    public void addUser(Etudiant etudiant){
        Call<Etudiant> call = etudiantService.addUser(etudiant);
        call.enqueue(new Callback<Etudiant>() {
            @Override
            public void onResponse(Call<Etudiant> call, Response<Etudiant> response) {
                if(response.isSuccessful())
                    Toast.makeText(MainActivity.this, "Etudiant created successfully!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Etudiant> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
    public List<Etudiant> getEtudiants(){
        Call<List<Etudiant>> call = etudiantService.getEtudiants();
        call.enqueue(new Callback<List<Etudiant>>() {
            @Override
            public void onResponse(Call<List<Etudiant>> call, Response<List<Etudiant>> response) {

                List<Etudiant> etudiants = (List<Etudiant>) response.body();
                Intent intent = new Intent(getApplicationContext(), ListEtudiants.class);
                intent.putExtra("data", (Serializable) etudiants);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<Etudiant>> call, Throwable t) {
                String errorMessage ="Something wrong please try again later";
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}