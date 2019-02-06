package com.septane.septacery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class GroceryActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference groceryRef = db.collection("groceries");

    private MilkAdapter adapter;

    CheckBox chkItem;
    EditText txtQuantity;
    TextView txtItem;
    ImageView imgItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grocery);

        setUpRecyclerView();

        //addListenerOnButton();
    }

    private void setUpRecyclerView(){

        Query query = groceryRef.whereEqualTo("category","GROCERY");

        FirestoreRecyclerOptions<Milk> options = new FirestoreRecyclerOptions.Builder<Milk>()
                .setQuery(query, Milk.class)
                .build();

        adapter = new MilkAdapter(options);

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

    public void addListenerOnButton() {
        chkItem = (CheckBox) findViewById(R.id.chkItem);
        txtItem = (TextView) findViewById(R.id.txtItem);
        txtQuantity = (EditText) findViewById(R.id.txtQuantity);

        chkItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkItem.isChecked()) {
                    txtItem.setWidth(100);
                    txtQuantity.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
