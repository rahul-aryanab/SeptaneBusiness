package com.septane.septacery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MilkActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference groceryRef = db.collection("groceries");

    private MilkAdapter adapter;

    CheckBox chkItem;
    EditText txtQuantity;
    TextView txtItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_milk);

        setUpRecyclerView();


        //addListenerOnCheckBox();
    }

    private void setUpRecyclerView(){

        Query query = groceryRef.whereEqualTo("category","Milk & Milk Products");

        FirestoreRecyclerOptions<Milk> options = new FirestoreRecyclerOptions.Builder<Milk>()
                .setQuery(query, Milk.class)
                .build();

        adapter = new MilkAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recViewMilk);
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

    public void addListenerOnCheckBox() {
        chkItem = (CheckBox) findViewById(R.id.chkItem);
        chkItem.setChecked(false);
        txtItem = (TextView) findViewById(R.id.txtItem);


        chkItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                txtItem.setWidth(100);
                txtQuantity.setVisibility(View.VISIBLE);
            }
        });
    }

}
