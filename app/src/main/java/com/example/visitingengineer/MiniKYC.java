package com.example.visitingengineer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MiniKYC extends AppCompatActivity {
    EditText e1,e2,e3;
    Button btn;
    FirebaseAuth auth;
    String a1,a2,a3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_kyc);

        e1=(EditText)findViewById(R.id.editText2);
        e2=(EditText)findViewById(R.id.editText3);
        e3=(EditText)findViewById(R.id.editText4);

        btn=(Button)findViewById(R.id.button);
        a1=e1.getText().toString();
        a2=e2.getText().toString();
        a3=e3.getText().toString();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SenduserData();
                Intent intent=new Intent(MiniKYC.this,Login.class);
                startActivity(intent);
                finish();
            }
        });



    }
    private void SenduserData()
    {
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference myRef=firebaseDatabase.getReference(auth.getUid());
        UserProfile userProfile=new UserProfile(a1,a2,a3);
        myRef.setValue(userProfile);
    }
}
