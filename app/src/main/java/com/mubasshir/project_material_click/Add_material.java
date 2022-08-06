package com.mubasshir.project_material_click;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Add_material extends AppCompatActivity {
    EditText partyname,date,quantity,urate;
    Spinner material;
    TextView amount;
    Button save;
//    Button back;
    Calendar calendar;
    FirebaseDatabase database;
    DatabaseReference reference;
    int maxid=0;
    material_info modelclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material);

        partyname=findViewById(R.id.edParty_name);
        date=findViewById(R.id.edDate);
        material=findViewById(R.id.spinner);
        quantity=findViewById(R.id.edQuantity);
        urate=findViewById(R.id.edUnitrate);
        amount=findViewById(R.id.tvAmount);
        save=findViewById(R.id.btn_save);
//        back=findViewById(R.id.btn_back);

//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Add_material.this,Material_fragment.class);
//                startActivity(intent);
//            }
//        });


        TextWatcher textWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!quantity.getText().toString().equals("") && !urate.getText().toString().equals("")){
                    int templ= Integer.parseInt(quantity.getText().toString());
                    int temp2= Integer.parseInt(urate.getText().toString());
                    amount.setText(String.valueOf(templ * temp2));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        quantity.addTextChangedListener(textWatcher);
        urate.addTextChangedListener(textWatcher);

        modelclass=new material_info();

        reference = FirebaseDatabase.getInstance().getReference("Materials");
//        materialreference = reference.child(FirebaseAuth.getInstance().getUid());

        reference.addValueEventListener(new ValueEventListener() {
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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String partynm=partyname.getText().toString();
                String purchaseddate=date.getText().toString();
                String purchasedmaterial=material.getSelectedItem().toString();
                String purchasedquantity=quantity.getText().toString();
                String unitrate=urate.getText().toString();
                String totalamount=amount.getText().toString();

                Toast.makeText(Add_material.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();


                if (partynm.isEmpty()) {
                    partyname.setError("Required!");
                    partyname.requestFocus();
                    return;
                }

                if (purchaseddate.isEmpty()) {
                    date.setError("Required!");
                    date.requestFocus();
                    return;
                }

                if (purchasedquantity.isEmpty()) {
                    quantity.setError("Required!");
                    quantity.requestFocus();
                    return;
                }

                if (unitrate.isEmpty()) {
                    urate.setError("Required!");
                    urate.requestFocus();
                    return;
                }


                modelclass.setParty(partyname.getText().toString());
                modelclass.setDate(date.getText().toString());
                modelclass.setMaterial(material.getSelectedItem().toString());
                modelclass.setQuantity(quantity.getText().toString());
                modelclass.setRate(urate.getText().toString());
                modelclass.setAmount(amount.getText().toString());

                reference.child(String.valueOf(maxid+1)).setValue(modelclass);
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

                date.setText(sdf.format(calendar.getTime()));
            }
        };

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Add_material.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }


}