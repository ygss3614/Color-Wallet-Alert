package com.colorwalletalert.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.colorwalletalert.adapter.FirebaseCategoryAdapter;
import com.colorwalletalert.database.FirebaseHelper;
import com.colorwalletalert.model.Category;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class CWABoardActivity extends AppCompatActivity {
    static final String TAG = "MainActivity";
    private FirebaseCategoryAdapter mCategoryAdapter;


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
                Context context = CWABoardActivity.this;
                Intent intent = new Intent(context, NewCategoryActivity.class);
                context.startActivity(intent);
            }
        });
        // TODO: tratar lista de categorias vazia
        setAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // required to work
        mCategoryAdapter.startListening();

    }


    /***
     * name: setAdapter
     * description: receive a list of items and set a adapter to recycler view
     * params: List<Categories> categoryList
     *
     */
    public void setAdapter(){
        Context context = CWABoardActivity.this;
        RecyclerView CategoryRecyclerView = findViewById(R.id.category_recycler_view);
        // TODO calcular a quantidade de colunas no grid de acordo com o tamanho da tela
        RecyclerView.LayoutManager mLayoutManager =
                new GridLayoutManager(CWABoardActivity.this, 1);
        CategoryRecyclerView.setLayoutManager(mLayoutManager);

        // using FirebaseRecyclerOption to load categories
        FirebaseRecyclerOptions categories = FirebaseHelper.getInstance().readCategories();

        mCategoryAdapter = new FirebaseCategoryAdapter(categories, context,
                new FirebaseCategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Category category) {

            }
        });

        CategoryRecyclerView.setAdapter(mCategoryAdapter);

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
