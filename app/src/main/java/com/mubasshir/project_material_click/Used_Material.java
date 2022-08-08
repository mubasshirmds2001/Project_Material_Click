package com.mubasshir.project_material_click;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Used_Material extends AppCompatActivity {
    private ImageButton imageButton;
    private Button saveused;
    private Spinner material;
    private EditText dateused,quantity;
    private DatabaseReference databaseReference;
    private int maxid=0;
    private Materials materials=new Materials();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_used__material);

        imageButton = findViewById(R.id.btn_back_list_order);
        material=findViewById(R.id.spinnerused);
        dateused=findViewById(R.id.edDateused);
        quantity=findViewById(R.id.edQuantityused);
        saveused=findViewById(R.id.btn_save_used);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Projects").child("-N8j4HIpOBh7Clqox9SY1").child("MaterialInfo").child("UsedInfo");

        imageButton.setOnClickListener(v -> finish());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid=(int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        saveused.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qty=quantity.getText().toString();
                String useddate=dateused.getText().toString();
                String usedmaterial=material.getSelectedItem().toString();

                if (qty.isEmpty()) {
                    quantity.setError("Required!");
                    quantity.requestFocus();
                    return;
                }
                if (useddate.isEmpty()) {
                    dateused.setError("Required!");
                    dateused.requestFocus();
                    return;
                }

                materials.setDate(dateused.getText().toString());
                materials.setMaterial(material.getSelectedItem().toString());
                materials.setQuantity(quantity.getText().toString());
                databaseReference.child(String.valueOf(maxid+1)).setValue(materials);
            }
        });

        ArrayAdapter<CharSequence> arrayAdapter=ArrayAdapter.createFromResource(this,R.array.materials,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        material.setAdapter(arrayAdapter);
        material.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Calendar calendar= Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                updateCalendar();
            }

            private void updateCalendar() {
                String Format = "MM/dd/yy";
                SimpleDateFormat sdf=new SimpleDateFormat(Format, Locale.US);

                dateused.setText(sdf.format(calendar.getTime()));
            }
        };

        dateused.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Used_Material.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
}