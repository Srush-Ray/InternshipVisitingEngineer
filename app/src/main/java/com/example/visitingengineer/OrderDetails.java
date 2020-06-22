package com.example.visitingengineer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.visitingengineer.Model.AddressModel;
import com.example.visitingengineer.Model.CustomerModels;
import com.example.visitingengineer.Model.PaymentInfoModel;
import com.example.visitingengineer.Model.ProductDetailsModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderDetails extends AppCompatActivity {

    RecyclerView rv_cust_products;

    DatabaseReference orderedData;
    ArrayList<AddressModel> addressModels;
    ArrayList<CustomerModels> customerModels;
    ArrayList<PaymentInfoModel> paymentInfoModels;
    ArrayList<ArrayList<ProductDetailsModel>> productDetailsModels;
    ArrayList<String> orderId;

    DatabaseReference orderData;
    AddressModel addressModel;
    CustomerModels customerModel;
    PaymentInfoModel paymentInfoModel;
    ProductDetailsModel productDetailsModel;
    private Dialog loading_dialog;
    private ImageView loading_gif_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        loading_dialog=new Dialog(this);
        loading_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        loading_dialog.setContentView(R.layout.loading_dialog);
        loading_gif_imageView = (ImageView) loading_dialog.findViewById(R.id.loading_gif_imageView);
        Glide.with(getApplicationContext()).load(R.drawable.loading).placeholder(R.drawable.loading).into(loading_gif_imageView);
        loading_dialog.setCanceledOnTouchOutside(false);
        loading_dialog.setCancelable(false);
        loading_dialog.show();

        addressModels = new ArrayList<>();
        customerModels = new ArrayList<>();
        paymentInfoModels = new ArrayList<>();
        productDetailsModels = new ArrayList<>();
        orderId = new ArrayList<>();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        orderData =FirebaseDatabase.getInstance().getReference("VisitingEngineer/" + uid + "/Orders");

        rv_cust_products = (RecyclerView) findViewById(R.id.rv_cust_products);
        rv_cust_products.setHasFixedSize(true);
        rv_cust_products.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    private void fetchData(){
//        orderedData.keepSynced(true);
        orderData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                loading_dialog.dismiss();
                if(dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        String orderNo = data.getKey().toString();
                        Log.d("order" , orderNo);
                        orderId.add(orderNo);
                    }

                    for (final String orderNo : orderId) {
                        final DatabaseReference orderDetails = FirebaseDatabase.getInstance().getReference("Orders/" + orderNo);
                        orderDetails.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                DataSnapshot addressSnapshot = dataSnapshot.child("addressModels");
                                Log.d("adress" , addressSnapshot.toString());
                                AddressModel addressModel = addressSnapshot.getValue(AddressModel.class);

                                DataSnapshot customerSnapshot = dataSnapshot.child("customerModels");
                                CustomerModels customerModel = customerSnapshot.getValue(CustomerModels.class);

                                DataSnapshot paymentSnapshot = dataSnapshot.child("paymentInfoModel");
                                PaymentInfoModel paymentInfoModel = paymentSnapshot.getValue(PaymentInfoModel.class);

                                DataSnapshot productSnapshot = dataSnapshot.child("productDetails");
                                Log.d("products" , productSnapshot.toString());
                                ArrayList<ProductDetailsModel> products = new ArrayList<>();

                                for (DataSnapshot data : productSnapshot.getChildren()) {
                                    products.add(data.getValue(ProductDetailsModel.class));
                                }

                                addressModels.add(addressModel);
                                customerModels.add(customerModel);
                                paymentInfoModels.add(paymentInfoModel);
                                productDetailsModels.add(products);
                                Log.d("customer", addressModels.size() + " fetching order");
                                if (addressModels.size() == orderId.size()) {
                                    loadData();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                } else{
                    Toast.makeText(getApplicationContext(),"No New Orders" , Toast.LENGTH_LONG ).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadData(){
        Log.d("customer" , "Fully Finished");

        for(ArrayList<ProductDetailsModel> products : productDetailsModels)
        {
            String s = "";
            for(int j=0;j<products.size();j++)
                s = s + " " + products.get(j).getProductName();

            Log.d("customer" , s);
        }

//        Log.d("customer size" , customerModels.size() + " " + addressModels.size() + " " + paymentInfoModels.size() + " " + productDetailsModels.size() + " " + orderId.size());
        OrderDetailsAdapter orderDetailsAdapter = new OrderDetailsAdapter(getApplicationContext(),addressModels,customerModels,paymentInfoModels,productDetailsModels,orderId);
        rv_cust_products.setAdapter(orderDetailsAdapter);
    }
}
