package com.example.visitingengineer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.visitingengineer.Model.AddressModel;
import com.example.visitingengineer.Model.CustomerModels;
import com.example.visitingengineer.Model.PaymentInfoModel;
import com.example.visitingengineer.Model.ProductDetailsModel;
import com.example.visitingengineer.Model.ProductModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductDetails extends AppCompatActivity {
    public Toolbar toolbar;
    String orderId;
    Spinner productSpinner,variantSpinner;
    ArrayList<String> productList = new ArrayList<>();
    ArrayList<String> variantList = new ArrayList<>();
    private DatabaseReference productRef;
    ArrayAdapter<String> productArrayAdapter;
    ArrayAdapter<String> variantArrayAdapter;
    String tutorialsName;
    private Dialog loading_dialog;
    private ImageView loading_gif_imageView;
    ProductModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        loading_dialog=new Dialog(this);
        loading_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        loading_dialog.setContentView(R.layout.loading_dialog);
        loading_gif_imageView = (ImageView) loading_dialog.findViewById(R.id.loading_gif_imageView);
        Glide.with(getApplicationContext()).load(R.drawable.loading).placeholder(R.drawable.loading).into(loading_gif_imageView);
        loading_dialog.setCanceledOnTouchOutside(false);
        loading_dialog.setCancelable(false);
        loading_dialog.show();

        setTitle("Product Details");
        Intent intent = new Intent();
        orderId = intent.getStringExtra("orderId");

        productSpinner = findViewById(R.id.product);
        variantSpinner=findViewById(R.id.variant);

        productList.add("ARA");
        productRef= FirebaseDatabase.getInstance().getReference("Products");
        productRef.keepSynced(true);
        productRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                loading_dialog.dismiss();
                if(dataSnapshot.exists()){
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                         model = ds.getValue(ProductModel.class);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                loading_dialog.dismiss();
            }
        });

        productArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, productList);
        variantArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, variantList);

        productArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        variantArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        productSpinner.setAdapter(productArrayAdapter);
        productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        variantSpinner.setAdapter(variantArrayAdapter);
        variantSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
    }
}
