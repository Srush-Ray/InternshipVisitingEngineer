package com.example.visitingengineer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visitingengineer.Model.AddressModel;
import com.example.visitingengineer.Model.CustomerModels;
import com.example.visitingengineer.Model.PaymentInfoModel;
import com.example.visitingengineer.Model.ProductDetailsModel;

import java.util.ArrayList;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.OrderViewHolder> {
    ArrayList<AddressModel> addressModels;
    ArrayList<CustomerModels> customerModels;
    ArrayList<PaymentInfoModel> paymentInfoModels;
    ArrayList<ArrayList<ProductDetailsModel>> productDetailsModels;
    ArrayList<String> orderId;
    Context context;
    String order;
    public OrderDetailsAdapter(Context context ,ArrayList<AddressModel> addressModels,ArrayList<CustomerModels> customerModels,ArrayList<PaymentInfoModel> paymentInfoModels,ArrayList<ArrayList<ProductDetailsModel>> productDetailsModels,ArrayList<String> orderId){
        this.addressModels = addressModels;
        this.customerModels = customerModels;
        this.paymentInfoModels = paymentInfoModels;
        this.productDetailsModels = productDetailsModels;
        this.orderId = orderId;
        this.context = context;
    }

//    private CompoundButton.OnCheckedChangeListener ls =(new CompoundButton.OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//            int tag = (int) buttonView.getTag();
//            Intent i = new Intent(context , OrderDetails.class);
//            context.startActivity(i);
//        }
//    });

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_item , parent , false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_item,parent,false);
        return new OrderViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, final int position) {
        Log.d("position" , position + " ");
        holder.orderId.setText("Order Id: " + orderId.get(position));
        holder.orderDate.setText("Cust Id: " + productDetailsModels.get(position).get(0).getOrderDate());
        holder.customerName.setText("Cust Name: " + customerModels.get(position).getCustomerName());
        order=orderId.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("ds",order);
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra("orderId",order);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return addressModels.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{

        TextView orderId , customerName , orderDate;
        CardView order_cart;
        Context context;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            orderId = (TextView) itemView.findViewById(R.id.order_id);
            customerName = (TextView) itemView.findViewById(R.id.customer_name);
            orderDate = (TextView) itemView.findViewById(R.id.order_date);
            order_cart = (CardView) itemView.findViewById(R.id.order_cart);
        }
    }

    public void open(int position){
        Intent intent = new Intent(context , OrderDetails.class);
        context.startActivity(intent);
    }
}
