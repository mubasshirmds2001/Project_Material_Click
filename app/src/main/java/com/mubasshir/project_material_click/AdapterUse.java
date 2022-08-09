package com.mubasshir.project_material_click;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class AdapterUse extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ArrayList<Materials> listU;
    Context context;



    public AdapterUse(ArrayList<Materials> list, Context context) {
        this.listU = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_row, parent, false);
        return new Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Materials materials = listU.get(position);
        holder.txtMaterial.setText(materials.getUsedMaterial());
        holder.txtUsed.setText(materials.getUsedQty());

    }

    @Override
    public int getItemCount() {
        return listU.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtMaterial;
        public TextView txtUsed;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaterial = (TextView) itemView.findViewById(R.id.tvMaterialU);
            txtUsed = (TextView) itemView.findViewById(R.id.tvUsed);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            listener.onItemClick(position);
//                        }
//                    }
//                }
//            });
        }
    }
}
