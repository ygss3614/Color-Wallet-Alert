package com.colorwalletalert.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.colorwalletalert.adapter.FirebaseCategoryAdapter;
import com.colorwalletalert.adapter.FirebaseCategorySpendDetailedAdapter;
import com.colorwalletalert.database.FirebaseHelper;
import com.colorwalletalert.model.Category;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.Objects;

public class CategorySpendsDetailedActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY = "category";
    private Category mCategory;
    private FirebaseCategorySpendDetailedAdapter mCategorySpendDetailedAdapter;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_spends_detailed);

        mContext = CategorySpendsDetailedActivity.this;
        // get intent extra
        Intent intent = getIntent();
        mCategory = Objects.requireNonNull(intent).getParcelableExtra(EXTRA_CATEGORY);

        setAdapter();
    }


    /***
     * name: setAdapter
     * description: receive a list of items and set a adapter to recycler view
     * params: List<Categories> categoryList
     *
     */
    public void setAdapter(){
        RecyclerView CategoryRecyclerView = findViewById(R.id.category_spend_detailed_recycler_view);
        RecyclerView.LayoutManager mLayoutManager =
                new GridLayoutManager(CategorySpendsDetailedActivity.this, 1);
        CategoryRecyclerView.setLayoutManager(mLayoutManager);

        // using FirebaseRecyclerOption to load categories
        FirebaseRecyclerOptions categorySpends = FirebaseHelper.getInstance().readCategoriesSpends();
        Log.d("SPENDS", categorySpends.getSnapshots().toString());

        mCategorySpendDetailedAdapter = new FirebaseCategorySpendDetailedAdapter(categorySpends);

        CategoryRecyclerView.setAdapter(mCategorySpendDetailedAdapter);

    }

}
