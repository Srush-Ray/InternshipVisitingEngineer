package com.example.visitingengineer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.visitingengineer.Model.RoomDetails;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Preview extends AppCompatActivity {
    RoomDetails details;
    LinearLayout layout;
    LinkedHashMap<String,Integer>list=new LinkedHashMap<>();
    String number;
    Spinner onoffspinner;
    ArrayAdapter<String> onoffAdapter;
    ArrayList<String> onofflist = new ArrayList<>();
    boolean isSpinnerTouchedR=false;
    String onoffSelected;
    public Preview() {
        list.put("Variant#1:D4_211",211);
        list.put("Variant#2:D4_310",310);
        list.put("Variant#3:D4_301",301);
        list.put("Variant#4:D4_400",400);
        list.put("Variant#5:D5_410",410);
        list.put("Variant#6:D5_401",401);
        list.put("Variant#7:D5_320",320);
        list.put("Variant#8:D5_311",311);
        list.put("Variant#9:D5_221",221);
        list.put("Variant#10:D6_510",510);
        list.put("Variant#11:D6_501",501);
        list.put("Variant#12:D6_411",411);
        list.put("Variant#13:D6_321",321);
        list.put("Variant#14:D6_420",420);
        list.put("Variant#15:D7_610",610);
        list.put("Variant#16:D7_601",601);
        list.put("Variant#17:D7_520",520);
        list.put("Variant#18:D7_511",511);
        list.put("Variant#19:D7_421",421);
        list.put("Variant#20:D7_430",430);

        onofflist.add("Select ON/OFF Device");
        onofflist.add("LED");
        onofflist.add("LED");
        onofflist.add("LED");
        onofflist.add("LED");
        onofflist.add("LED");
        onofflist.add("LED");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        details=(RoomDetails)getIntent().getSerializableExtra("model");
        layout = findViewById(R.id.layout);

        number=Integer.toString(getDeviceVariant(details.getVariant()));

        String[] digits2 = number.split("(?<=.)");

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        int k=Integer.parseInt(digits2[0]);
        for (int i = 0; i < k; i++) {
            TextView textView = new TextView(this);
            textView.setText("Device " + String.valueOf(i));
            Spinner spinner=new Spinner(this);

            onoffAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, onofflist);
            onoffAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(onoffAdapter);
            spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    isSpinnerTouchedR=true;
                    return false;
                }
            });
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    onoffSelected=parent.getItemAtPosition(position).toString();
                    if(!isSpinnerTouchedR){

                    }else{
                        Toast.makeText(getApplicationContext(),onoffSelected,Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onNothingSelected(AdapterView <?> parent) {
                }
            });
            layout.addView(spinner);
            layout.addView(textView);
            textView.setLayoutParams(params);
            textView.setLayoutParams(params);
        }

    }
    private Integer getDeviceVariant(String variant){
        Integer i=000;
            i=list.get(variant);
        return i;
    }
}
