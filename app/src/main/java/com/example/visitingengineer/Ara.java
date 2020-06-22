package com.example.visitingengineer;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.visitingengineer.Model.RoomDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;


public class Ara extends AppCompatActivity {
    ArrayList<String> variantList = new ArrayList<>();
    Spinner variantSpinner;
    ArrayAdapter<String> variantArrayAdapter;
    Button active,preview;
    String variant=null,roomName=null,boardName=null,UID;
    DatabaseReference ref;
    private FirebaseAuth mAuth;
    Spinner roomSpinner,boardSpinner;
    ArrayList<String> roomList = new ArrayList<>();
    ArrayList<String> boardList = new ArrayList<>();
    ArrayAdapter<String> roomArrayAdapter;
    ArrayAdapter<String> boardArrayAdapter;
    RoomDetails details;
    String selectedRoom,selectedboard;
    private boolean isSpinnerTouchedV = false,isSpinnerTouchedR=false,isSpinnerTouchedB=false;
    private int check=0;
    AdapterView.OnItemSelectedListener listener = null;
    int indexR=0,indexB=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ara);

        variantSpinner=findViewById(R.id.variant);
        roomSpinner=(Spinner) findViewById(R.id.roomspinner);
        boardSpinner=(Spinner) findViewById(R.id.boardspinner);
        active=(Button)findViewById(R.id.active);
        preview=(Button)findViewById(R.id.preview);

        mAuth = FirebaseAuth.getInstance();
        ref=FirebaseDatabase.getInstance().getReference("ProductOperations");
        UID=mAuth.getUid();

        variantList.add("Select Variant");
        roomList.add("Select Room");
        roomList.add("Add Room");
        boardList.add("Select Board");
        boardList.add("Add Board");
        variantList=getVariantList();
        variantArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, variantList);
        variantArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        variantSpinner.setAdapter(variantArrayAdapter);
        variantSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isSpinnerTouchedV = true;
                return false;
            }
        });

        variantSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                variant = parent.getItemAtPosition(position).toString();
                if(!isSpinnerTouchedV){
                }else{
                    Toast.makeText(getApplicationContext(),variant,Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        roomArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roomList);
        roomArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomSpinner.setAdapter(roomArrayAdapter);
        roomSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                isSpinnerTouchedR=true;
                return false;
            }
        });
        roomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRoom=parent.getItemAtPosition(position).toString();
                if(!isSpinnerTouchedR){

                }else{
                    if(selectedRoom.equals("Add Room")){
                        showAlertBoxAddRoom();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        boardArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, boardList);
        boardArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boardSpinner.setAdapter(boardArrayAdapter);

        boardSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                isSpinnerTouchedB=true;
                return false;
            }
        });

        boardSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedboard=parent.getItemAtPosition(position).toString();
                if(!isSpinnerTouchedB){

                }else{
                    if(selectedboard.equals("Add Board")){
                        showAlertBoxAddBoard();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                Toast.makeText(getApplicationContext(),"Select a board",Toast.LENGTH_LONG).show();
            }
        });

        active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(variant) || TextUtils.isEmpty(selectedRoom) || TextUtils.isEmpty(selectedboard)){
                    Toast.makeText(getApplicationContext(),"Select all the details",Toast.LENGTH_LONG).show();
                }else{
                    details=new RoomDetails(roomName,boardName,variant);
//                    Intent intent=new Intent(Ara.this,RoomDevicesActivity.class);
//                    intent.putExtra("model", details);
//                    startActivity(intent);
//                    ref.child(UID).child("ARA").child(roomName).child(boardName).setValue(variant).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if(task.isSuccessful()){
//                                Toast.makeText(getApplicationContext(), "Added",Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    });
                    Log.i(selectedboard,selectedRoom);
                }


            }
        });


        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                details=new RoomDetails(roomName,boardName,variant);
                if(boardName!=null && roomName!=null && variant!=null){
                if(boardName.equals("Add Board") && boardName.equals("Select Board") && TextUtils.isEmpty(boardName)){
                    Toast.makeText(getApplicationContext(),"Select the board",Toast.LENGTH_LONG).show();
                }else if(roomName.equals("Add Room") && roomName.equals("Select Room") && TextUtils.isEmpty(roomName)){
                    Toast.makeText(getApplicationContext(),"Select the room",Toast.LENGTH_LONG).show();
                }else if(variant.equals("Select Variant") && TextUtils.isEmpty(variant)){
                    Toast.makeText(getApplicationContext(),"Select the variant",Toast.LENGTH_LONG).show();
                }else{
                    Intent intent=new Intent(Ara.this,Preview.class);
                    intent.putExtra("model", details);
                    startActivity(intent);
                }
                }else{
                    Toast.makeText(getApplicationContext(),"Select proper values",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    public ArrayList<String> getVariantList(){
        ArrayList<String>list=new ArrayList<>();
        list.add("Select Variant");
        list.add("Variant#1:D4_211");
        list.add("Variant#2:D4_310");
        list.add("Variant#3:D4_301");
        list.add("Variant#4:D4_400");
        list.add("Variant#5:D5_410");
        list.add("Variant#6:D5_401");
        list.add("Variant#7:D5_320");
        list.add("Variant#8:D5_311");
        list.add("Variant#9:D5_221");
        list.add("Variant#10:D6_510");
        list.add("Variant#11:D6_501");
        list.add("Variant#12:D6_411");
        list.add("Variant#13:D6_321");
        list.add("Variant#14:D6_420");
        list.add("Variant#15:D7_610");
        list.add("Variant#16:D7_601");
        list.add("Variant#17:D7_520");
        list.add("Variant#18:D7_511");
        list.add("Variant#19:D7_421");
        list.add("Variant#20:D7_430");
        return list;
    }
    public void showAlertBoxAddRoom() {
        LayoutInflater li = LayoutInflater.from(Ara.this);
        View promptsView = li.inflate(R.layout.addroom, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Ara.this);
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.roomnumber);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                roomName=userInput.getText().toString().trim();
                                roomList.add("Room#"+roomName);
                                roomSpinner.setSelection(roomList.indexOf("Room#"+roomName));
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void showAlertBoxAddBoard() {
        LayoutInflater li = LayoutInflater.from(Ara.this);
        View promptsView = li.inflate(R.layout.addboard, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Ara.this);
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.boardnumber);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                boardName=userInput.getText().toString().trim();
                                boardList.add("Board#"+boardName);
                                boardSpinner.setSelection(boardList.indexOf("Board#"+boardName));
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}
