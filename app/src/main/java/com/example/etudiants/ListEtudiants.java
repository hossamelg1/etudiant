package com.example.etudiants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.Toast;

import com.example.etudiants.adapter.EtudiantAdapter;
import com.example.etudiants.model.Etudiant;
import com.example.etudiants.remote.EtudiantService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListEtudiants extends AppCompatActivity {
    EtudiantService etudiantService;
    Button getAll;
    RecyclerView recyclerView;
    EtudiantAdapter etudiantAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etudiants);
        recyclerView = findViewById(R.id.recycle_view);

        Intent intent = getIntent();
        List<Etudiant> etudiants = (List<Etudiant>) intent.getSerializableExtra("data");
        etudiantAdapter = new EtudiantAdapter(this, etudiants);
        recyclerView.setAdapter(etudiantAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}