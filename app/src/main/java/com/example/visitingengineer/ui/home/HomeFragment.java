package com.example.visitingengineer.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visitingengineer.Ara;
import com.example.visitingengineer.HomePage;
import com.example.visitingengineer.OrderDetails;
import com.example.visitingengineer.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final List<String> list=new ArrayList<>();
        list.add(getString(R.string.recProduct));
        list.add(getString(R.string.custService));
        list.add(getString(R.string.replaceProd));
        list.add(getString(R.string.reqNewProd));

        RecyclerView recyclerView=root.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                list.get(position);
                if(list.get(position).equals(getString(R.string.recProduct))) {
                    Toast.makeText(getActivity(), getString(R.string.recProduct), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext() , OrderDetails.class);
                    startActivity(intent);
                }
                else if(list.get(position).equals(getString(R.string.custService))){
                    Toast.makeText(getActivity(),getString(R.string.custService),Toast.LENGTH_SHORT).show();
                }else if(list.get(position).equals(getString(R.string.replaceProd))){
                    Toast.makeText(getActivity(),getString(R.string.replaceProd),Toast.LENGTH_SHORT).show();
                }else if(list.get(position).equals(getString(R.string.reqNewProd))){
//                    Toast.makeText(getActivity(),getString(R.string.reqNewProd),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext() , HomePage.class);
                    startActivity(intent);
                }

            }
        });

        return root;
    }
    private class RecyclerViewHolder extends RecyclerView.ViewHolder{
    private CardView mCardview;
    private TextView mTextView;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public  RecyclerViewHolder(LayoutInflater inflater, ViewGroup container, final OnItemClickListener listener){
            super(inflater.inflate(R.layout.card_view,container,false));
            mCardview=itemView.findViewById(R.id.card_container);
            mTextView=itemView.findViewById(R.id.cardText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        if(listener!=null){
                            int pos=getAdapterPosition();
                            if(pos!=RecyclerView.NO_POSITION){
                                listener.onItemClick(pos);
                            }
                        }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{
       private List<String> mlist;
       public OnItemClickListener  mlistener;

       public void setOnItemClickListener(OnItemClickListener listener){
           mlistener=listener;
       }

    public RecyclerViewAdapter(List<String>list){
        this.mlist=list;
    }
        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            return new RecyclerViewHolder(inflater,parent,mlistener);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            holder.mTextView.setText((mlist.get(position)));
        }

        @Override
        public int getItemCount() {
            return mlist.size();
        }


    }
}