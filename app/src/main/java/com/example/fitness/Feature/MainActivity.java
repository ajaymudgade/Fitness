package com.example.fitness.Feature;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.fitness.Database.DbManager;
import com.example.fitness.R;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerMain;

    String [] spinnerList = new String[]{"Food Tracker", "workout Tracker", "Sleep Tracker", "Water Tracker", "User Profile"};
    DbManager dbManger;
    ListView lv1, lv2, lv3, lv4, lv5;


    /*

      Database --> remaining workout, sleep , water query implementation

      Feature --> adding data

      UI --> All UI remaining

     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerMain = findViewById(R.id.spinnerMain);
        lv1 = findViewById(R.id.lv1);
        lv2 = findViewById(R.id.lv2);
        lv3 = findViewById(R.id.lv3);
        lv4 = findViewById(R.id.lv4);
        lv5 = findViewById(R.id.lv5);

        dbManger = new DbManager();

        /*

        md jamal =  https://youtu.be/wbsWVtCdiW4
        Android coding = https://youtu.be/lQIoxBq10xA

        IT's Logical = https://youtu.be/prfbLXnt8gU

        only Database created

        now you have to manage UI (How to show data)
        1) how to get data and add data and display in UI
        2) use spinner
        3) data and time code (LingoForce)

        */

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMain.setAdapter(arrayAdapter);

        spinnerMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}