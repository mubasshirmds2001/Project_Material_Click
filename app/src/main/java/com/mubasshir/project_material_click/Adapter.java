package com.mubasshir.project_material_click;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<Materials> list;
    Context context;



    public Adapter(ArrayList<Materials> list, Context context) {
        this.list = list;
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
        Materials materials = list.get(position);

        holder.txtMaterial.setText(materials.getMaterial());
        holder.txtPurchased.setText(materials.getQuantity());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtPartyname;
        public TextView txtDate;
        public TextView txtMaterial;
        public TextView txtPurchased;
        public TextView txtUsed;
        public TextView txtUnitRate;
        public TextView txtAmount;
        public ImageView options;
        ImageView optionMenuButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            txtPartyname=(TextView)itemView.findViewById(R.id.tvMaterial);
//            txtDate=(TextView)itemView.findViewById(R.id.tvDate);
            txtMaterial = (TextView) itemView.findViewById(R.id.tvMaterial);
            txtPurchased = (TextView) itemView.findViewById(R.id.tvPurchased);
//            txtUnitRate=(TextView)itemView.findViewById(R.id.tvMaterial);


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
