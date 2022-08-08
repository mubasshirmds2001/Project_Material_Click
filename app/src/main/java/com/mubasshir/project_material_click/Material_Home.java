package com.mubasshir.project_material_click;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Material_Home extends AppCompatActivity {
    private Button receivedMat,usedMat;
    private RecyclerView recyclerView,recyclerViewUse;
    private ImageView options;
    private DatabaseReference databaseReference;
    private DatabaseReference usedRef;
    private Adapter adapter;
    private Adapter madapter;
    private ArrayList<Materials> list = new ArrayList<Materials>();
    private ArrayList<Materials> listU = new ArrayList<Materials>();
    private Adapter uadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material__home);

        receivedMat = (Button) findViewById(R.id.btn_received);
        usedMat = (Button) findViewById(R.id.btn_used);
        recyclerView = findViewById(R.id.recyclerviewRec);
        recyclerViewUse = findViewById(R.id.recyclerviewUse);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Projects").child("-N8rDsLnJCb4p6h31qVG1").child("MaterialInfo").child("ReceivedInfo");
        usedRef = FirebaseDatabase.getInstance().getReference().child("Projects").child("-N8rDsLnJCb4p6h31qVG1").child("MaterialInfo").child("UsedInfo");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewUse.setHasFixedSize(true);
        recyclerViewUse.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        madapter = new Adapter(list, this);
        recyclerView.setAdapter(madapter);
        listU = new ArrayList<>();
        uadapter = new Adapter(listU, this);
        recyclerViewUse.setAdapter(uadapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Materials minfo = dataSnapshot.getValue(Materials.class);
                        list.add(minfo);
                    }
//                sortOrders();
                    madapter.notifyDataSetChanged();
                    Log.d("materialData", "data received successfully");
                    Log.d("materialData", list.toString());
//                madapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        usedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listU.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Materials muinfo = dataSnapshot.getValue(Materials.class);
                        listU.add(muinfo);
                    }
//                sortOrders();
                    uadapter.notifyDataSetChanged();
                    Log.d("materialData", "data received successfully");
                    Log.d("materialData", list.toString());
//                madapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        receivedMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Material_Home.this, Add_material.class);
                startActivity(intent);
            }
        });

        usedMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Material_Home.this, Used_Material.class);
                startActivity(intent);
            }
        });

    }


//    private void sortOrders() {
//        Collections.sort(list, new Comparator<Materials>() {
//            @Override
//            public int compare(Materials o1, Materials o2) {
//                return o1.getMaterialid().compareToIgnoreCase(o2.getMaterialid());
//            }
//        });
//        Collections.reverse(list);
//    }


}