package com.mubasshir.project_material_click;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<Materials> list;
    Context context;
    DatabaseReference materialRef;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public Adapter(ArrayList<Materials> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_row, parent, false);
        return new Adapter.ViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Materials materials = list.get(position);

        holder.txtMaterial.setText(materials.getMaterial());
        holder.txtPurchased.setText(materials.getQuantity());
        holder.txtStock.setText(materials.getQuantity());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtPartyname;
        public TextView txtDate;
        public TextView txtMaterial;
        public TextView txtPurchased;
        public TextView txtStock;
        public TextView txtUnitRate;
        public TextView txtAmount;
        public ImageView options;
        ImageView optionMenuButton;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

//            txtPartyname=(TextView)itemView.findViewById(R.id.tvMaterial);
//            txtDate=(TextView)itemView.findViewById(R.id.tvDate);
            txtMaterial = (TextView) itemView.findViewById(R.id.tvMaterial);
            txtPurchased = (TextView) itemView.findViewById(R.id.tvPurchased);
//            txtUnitRate=(TextView)itemView.findViewById(R.id.tvMaterial);
            txtStock = (TextView) itemView.findViewById(R.id.tvStock);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            Materials selectedMaterial = list.get(position);
            String materialReceived = selectedMaterial.getQuantity();
            String materialStock = selectedMaterial.getStock();

            Intent intent = new Intent(context, RecycleView_Click.class);
            intent.putExtra("materialReceived", materialReceived);
            intent.putExtra("materialStock", materialStock);
            context.startActivity(intent);
        }
    }
}
