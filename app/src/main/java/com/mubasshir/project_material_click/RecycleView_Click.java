package com.mubasshir.project_material_click;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecycleView_Click extends AppCompatActivity {
    private TextView received,used;
    private Button receivedMat,usedMat;
    private ImageButton imageButton;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view__click);

        received=(TextView)findViewById(R.id.tvReceived);
        used=(TextView)findViewById(R.id.tvUsed);
        receivedMat=(Button) findViewById(R.id.btn_received);
        usedMat=(Button) findViewById(R.id.btn_used);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Projects").child("-N8j4HIpOBh7Clqox9SY1").child("MaterialInfo").child("ReceivedInfo");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String rcvd=snapshot.child("quantity").getValue(String.class);
                String usd=snapshot.child("quantity").getValue(String.class);

                received.setText(rcvd);
                used.setText(usd);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        imageButton = findViewById(R.id.btn_back_list_order);

        imageButton.setOnClickListener(v -> finish());

        Intent mIntent = getIntent();
        String materialReceived = mIntent.getStringExtra("quantity");
        String materialStock = mIntent.getStringExtra("quantity");

        received.setText(materialReceived);
        used.setText(materialStock);

        receivedMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecycleView_Click.this,Add_material.class);
                startActivity(intent);
            }
        });

        usedMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecycleView_Click.this,Used_Material.class);
                startActivity(intent);
            }
        });
    }


}