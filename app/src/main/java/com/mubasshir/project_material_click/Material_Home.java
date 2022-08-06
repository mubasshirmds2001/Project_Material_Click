package com.mubasshir.project_material_click;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Material_Home extends AppCompatActivity {
    Button addMaterial;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    Adapter adapter;
    Adapter madapter;
    ArrayList<Materials> list = new ArrayList<Materials>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material__home);

        addMaterial=(Button)findViewById(R.id.btn_add_material);
        recyclerView=findViewById(R.id.recyclerview);
        databaseReference = FirebaseDatabase.getInstance().getReference("Materials");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        madapter=new Adapter(list, this);
        recyclerView.setAdapter(madapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Materials minfo=dataSnapshot.getValue(Materials.class);
                    list.add(minfo);
                }
                madapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Material_Home.this,Add_material.class);
                startActivity(intent);
            }
        });


    }
}