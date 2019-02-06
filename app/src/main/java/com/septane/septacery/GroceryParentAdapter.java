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

public class GroceryParentAdapter extends FirestoreRecyclerAdapter<GroceryParent, GroceryParentAdapter.GroceryHomeHolder> {

    public GroceryParentAdapter(@NonNull FirestoreRecyclerOptions<GroceryParent> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull GroceryHomeHolder holder, int position, @NonNull GroceryParent model) {

        holder.txtGroceryTab.setText(model.getName());
        holder.txtGroceryTabDesc.setText(model.getDescription());
        holder.setPicture(model.getImage());
    }

    @NonNull
    @Override
    public GroceryHomeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grocery_parent,
        viewGroup,false);

        return new GroceryHomeHolder(v);
    }

    class GroceryHomeHolder extends RecyclerView.ViewHolder{

        TextView txtGroceryTab;
        TextView txtGroceryTabDesc;
        ImageView imgGroceryTab;

        public GroceryHomeHolder(@NonNull View itemView) {
            super(itemView);

            txtGroceryTab=itemView.findViewById(R.id.txtGroceryTab);
            txtGroceryTabDesc=itemView.findViewById(R.id.txtGroceryTabDesc);
        }

        public void setPicture(String picture) {
            imgGroceryTab = itemView.findViewById(R.id.imgGroceryTab);
            Picasso.get().load(picture).into(imgGroceryTab);
        }
    }
}
