package com.example.etudiants.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.etudiants.R;
import com.example.etudiants.model.Etudiant;

import java.util.List;

public class EtudiantAdapter extends RecyclerView.Adapter<EtudiantAdapter.EtudiantViewHolder> {
    private static final String TAG = "EtudiantAdapter";
    private List<Etudiant> etudiants;
    private Context context;
    public EtudiantAdapter(Context context, List<Etudiant> etudiants) {
        this.etudiants = etudiants;
        this.context = context;
    }

    @NonNull
    @Override
    public EtudiantAdapter.EtudiantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(this.context).inflate(R.layout.etudiant_item,
                parent, false);
        return new EtudiantViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EtudiantViewHolder holder, int i) {
        Glide.with(context)
                .asBitmap()
                .apply(new RequestOptions().override(100, 100));
        holder.lname.setText("  "+etudiants.get(i).getNom().toUpperCase()+" "+etudiants.get(i).getPrenom().toUpperCase());
        holder.city.setText("  "+etudiants.get(i).getVille().toUpperCase());
        holder.sex.setText("  "+etudiants.get(i).getSexe().toUpperCase());
    }


    @Override
    public int getItemCount() {
        return etudiants.size();
    }
    public class EtudiantViewHolder extends RecyclerView.ViewHolder{
        TextView fname, lname, city, sex;

        public EtudiantViewHolder(@NonNull View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            city = itemView.findViewById(R.id.city);
            sex = itemView.findViewById(R.id.sex);
        }
    }
}
