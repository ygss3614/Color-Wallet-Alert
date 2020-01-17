package com.colorwalletalert.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.colorwalletalert.adapter.CategoryAdapter;
import com.colorwalletalert.model.Category;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CWABoardActivity extends AppCompatActivity {
    static final String TAG = "CWABoardActivity";
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cwa_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        myRef = database.getReference();
        

        // call read categories method to fill CWA Board with registered categories on firebase
        readCategories();
    }

    public void writeCategory (){
        // Write a message to the database
        Category category = new Category("Casa", (float) 1000, "");
        String categoryId = "140864be-3815-11ea-a137-2e728ce88125";
        myRef.child("categories").child(categoryId).setValue(category);
    }

    /***
     * name: readCategories
     * description: read from Firebase database all registered categories
     * params:
     *
     */
    public void readCategories(){
        //TODO: read categories from firebase
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    /***
     * name: setAdapter
     * description: receive a list of items and set a adapter to recycler view
     * params: List<Categories> categoryList
     *
     */
    public void setAdapter(List<Category> categoryList){

        RecyclerView CategoryRecyclerView = findViewById(R.id.category_recycler_view);
        RecyclerView.LayoutManager mLayoutManager =
                new GridLayoutManager(CWABoardActivity.this, 2);
        CategoryRecyclerView.setLayoutManager(mLayoutManager);

        CategoryAdapter mAdapter = new CategoryAdapter(categoryList, new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Category category) {
//                Context context = CWABoardActivity.this;
//                if (category != null) {
//                    Intent intent = new Intent(context, MoviesDetailsActivity.class);
//                    intent.putExtra(MoviesDetailsActivity.EXTRA_MOVIE, movieDB);
//                    context.startActivity(intent);
//                } else {
//                    Toast.makeText(context, "movieDB object is null", Toast.LENGTH_LONG).show();
//                }
            }
        });
        CategoryRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
