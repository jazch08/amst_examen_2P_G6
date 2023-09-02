package com.amst.examen_amst_g6;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BateryActivity extends AppCompatActivity {
    private LineChart lineChart;
    private LineDataSet lineDataSet;

    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batery);

        lineChart = findViewById(R.id.bateryChart);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("data_sensor");

        ArrayList<Entry> lineEntries = new ArrayList<Entry>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    //System.out.println(data.getValue().toString());
                    int Batery = Integer.parseInt(data.child("batery").getValue().toString());
                    String fecha = data.child("Hora").getValue().toString();
                    System.out.println("Bateria: " + Batery + "Fecha: " + fecha);
                    lineEntries.add(new Entry((float) i,(float)Batery));
                    i++;
                }

                lineDataSet = new LineDataSet(lineEntries, "% de bateria con paso de los minutos");

                LineData lineData = new LineData();
                lineData.addDataSet(lineDataSet);
                lineChart.setData(lineData);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
}