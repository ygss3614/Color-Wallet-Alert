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
import com.colorwalletalert.utils.Utils;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


// TODO: tratar lista de categorias vazia
// TODO: transição entre as telas
// COMPLETED: adicionar subtextos
// COMPLETED: adicionar icone do app

public class CWABoardActivity extends AppCompatActivity {
    static final String TAG = "MainActivity";
    private FirebaseCategoryAdapter mCategoryAdapter;
    private  MaterialCardView mMessageCardView;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cwa_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = CWABoardActivity.this;

        ActionBar ab = getSupportActionBar();
        ab.setSubtitle("sub-title");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NewCategoryActivity.class);
                mContext.startActivity(intent);
            }
        });


        mMessageCardView = findViewById(R.id.empty_message_cardview);
        mMessageCardView.setVisibility(View.GONE);
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
        RecyclerView CategoryRecyclerView = findViewById(R.id.category_recycler_view);
        // COMPLETED calcular a quantidade de colunas no grid de acordo com o tamanho da tela
        int numberOfColumns = Utils.getInstance().calculateNoOfColumns(this, 500);
        RecyclerView.LayoutManager mLayoutManager =
                new GridLayoutManager(CWABoardActivity.this, numberOfColumns);
        CategoryRecyclerView.setLayoutManager(mLayoutManager);

        // using FirebaseRecyclerOption to load categories
        FirebaseRecyclerOptions categories = FirebaseHelper.getInstance().readCategories();

        mCategoryAdapter = new FirebaseCategoryAdapter(categories, mContext,
                new FirebaseCategoryAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Category category) {

                    Intent intent = new Intent(mContext, NewCategorySpendActivity.class);
                    intent.putExtra(NewCategorySpendActivity.EXTRA_CATEGORY, category);
                    mContext.startActivity(intent);
                }

                @Override
                public void onDetailClick(Category category) {
                    Intent intent = new Intent(mContext, CategorySpendsDetailedActivity.class);
                    intent.putExtra(CategorySpendsDetailedActivity.EXTRA_CATEGORY, category);
                    mContext.startActivity(intent);
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
