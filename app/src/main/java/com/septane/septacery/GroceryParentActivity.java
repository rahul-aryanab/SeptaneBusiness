package com.septane.septacery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class GroceryParentActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference groceryRef = db.collection("generalstoretab");

    private GroceryParentAdapter adapter;

    TextView txtName;
    TextView txtDescription;
    ImageView imgItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grocery);

        setUpRecyclerView();
    }

    private void setUpRecyclerView(){

        FirestoreRecyclerOptions<GroceryParent> options = new FirestoreRecyclerOptions.Builder<GroceryParent>()
                .setQuery(groceryRef, GroceryParent.class)
                .build();

        adapter = new GroceryParentAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recViewGrocery);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
