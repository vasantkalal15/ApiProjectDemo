package com.example.apiprojectdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.CustomViewHolder> {
    private List<Postpijo> datalist;
    private  Context context;

    public Adapter(List<Postpijo> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }



    public class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvbody;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle= itemView.findViewById(R.id.tvTitle);
            tvbody = itemView.findViewById(R.id.tvBody);
        }



        }




    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.desing,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tvTitle.setText(datalist.get(position).getTitle());
        holder.tvbody.setText(datalist.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }



}
