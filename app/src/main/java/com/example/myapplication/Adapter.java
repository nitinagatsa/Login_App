package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.PersonViewHolder> {

    private Context context;
    private ArrayList<Model.Datum> datalist;

    public Adapter(Context context, ArrayList<Model.Datum> personList) {
        this.context = context;
        this.datalist = personList;
    }

    public void setData(ArrayList<Model.Datum> personList) {
        this.datalist = personList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {

        holder.textName.setText(datalist.get(position).getName());
         holder.textAge.setText(datalist.get(position).getAge());
        holder.textCity.setText(datalist.get(position).getCity());

        Picasso.with(context).load(datalist.get(position).getProfileimg()).placeholder(R.drawable.log).fit().into(holder.image);

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textAge, textCity;
        ImageView image;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textAge = itemView.findViewById(R.id.textAge);
            textCity = itemView.findViewById(R.id.textCity);
            image = itemView.findViewById(R.id.image);
        }
    }
}
