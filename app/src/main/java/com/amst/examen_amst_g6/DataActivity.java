package com.amst.examen_amst_g6;

import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    LinearLayout containerData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("data_sensor");
        containerData = findViewById(R.id.containerData);


        leerDatos();


    }

    private void leerDatos() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    //System.out.println(data.getValue().toString());
                    String fuego = data.child("fuego").getValue().toString();
                    String fecha = data.child("Hora").getValue().toString();
                    String alarma = data.child("alarma").getValue().toString();

                    LinearLayout datos = new LinearLayout(getApplicationContext());
                    datos.setOrientation(LinearLayout.HORIZONTAL);

                    TextView fuegoTxt = new TextView(getApplicationContext());
                    fuegoTxt.setText(fuego);
                    fuegoTxt.setTextSize(15);
                    fuegoTxt.setPadding(90,0,170,20);

                    TextView fechaTxt = new TextView(getApplicationContext());
                    fechaTxt.setText(fecha);
                    fechaTxt.setTextSize(15);
                    fechaTxt.setPadding(0,0,180,20);

                    TextView alarmaTxt = new TextView(getApplicationContext());
                    alarmaTxt.setText(alarma);
                    alarmaTxt.setTextSize(15);
                    alarmaTxt.setPadding(0,0,0,5);

                    datos.addView(fuegoTxt);
                    datos.addView(fechaTxt);
                    datos.addView(alarmaTxt);

                    containerData.addView(datos);

                    System.out.println("Fuego: " + fuego + " - Fecha: " + fecha);
                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


}