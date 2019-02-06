package com.septane.septacery;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class MilkAdapter extends FirestoreRecyclerAdapter<Milk, MilkAdapter.MilkHolder> {


    public MilkAdapter(@NonNull FirestoreRecyclerOptions<Milk> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MilkHolder holder, int position, @NonNull Milk model) {

        holder.txtItem.setText(model.getItem());
        //holder.txtCategory.setText(model.getCategory());
        holder.setPicture(model.getImage());
    }

    @NonNull
    @Override
    public MilkHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.milk,
        viewGroup,false);

        return new MilkHolder(v);
    }

    class MilkHolder extends RecyclerView.ViewHolder{

        //TextView txtCategory;
        TextView txtItem;
        ImageView imageView;

        public MilkHolder(@NonNull View itemView) {
            super(itemView);

            //txtCategory=itemView.findViewById(R.id.txtCategory);
            txtItem=itemView.findViewById(R.id.txtItem);
        }

        public void setPicture(String picture) {
            imageView = itemView.findViewById(R.id.imageView);
            Picasso.get().load(picture).into(imageView);
        }
    }
}
