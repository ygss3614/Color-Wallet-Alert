package com.colorwalletalert.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.colorwalletalert.adapter.FirebaseCategorySpendDetailedAdapter;
import com.colorwalletalert.database.FirebaseHelper;
import com.colorwalletalert.model.Category;
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class CategorySpendsDetailedActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY = "category";
    private Category mCategory;
    private FirebaseCategorySpendDetailedAdapter mCategorySpendDetailedAdapter;

    TextView mCategoryDescriptionTextView;
    TextView mCategoryAvailableAmountTextView;
    TextView mCategorySuggestedTextView;
    ImageView mCategoryIconImageView;
    TextView mCategorySpendTotalValueTextView;
    ImageView mCategorySpendsImageView;
    MaterialCardView mCategoryCardView;

    Context mContext;


    // TODO: mensagem de que não tem gastos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_spends_detailed);

        mContext = CategorySpendsDetailedActivity.this;
        // get intent extra
        Intent intent = getIntent();
        mCategory = Objects.requireNonNull(intent).getParcelableExtra(EXTRA_CATEGORY);
        fillCategoryCard(mCategory);
        setAdapter(mCategory);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // required to work
        mCategorySpendDetailedAdapter.startListening();

    }

    public void fillCategoryCard(Category category){
        mCategoryDescriptionTextView =  findViewById(R.id.category_description_text_view);
        mCategoryAvailableAmountTextView = findViewById(R.id.category_available_amount_text_view);
        mCategorySuggestedTextView = findViewById(R.id.category_sugested_text_view);
        mCategoryIconImageView = findViewById(R.id.category_icon_image_view);
        mCategorySpendsImageView = findViewById(R.id.category_detail_spends_image_view);
        mCategoryCardView = findViewById(R.id.category_card_view);
        mCategorySpendTotalValueTextView = findViewById(R.id.category_total_spends_value_text_view);

        mCategorySpendsImageView.setVisibility(View.INVISIBLE);

        //get total spend and update category
        FirebaseHelper.getInstance().getCategorySpendToUpdate(category);
        mCategoryCardView.setBackgroundColor(getColor(category.getCardBackgroundColor()));
        mCategoryDescriptionTextView.setText(category.getDescription().toLowerCase());
        mCategoryAvailableAmountTextView.setText(
                String.format(getString(R.string.category_currency),
                        category.getAvailableAmount().toString()));
        mCategorySuggestedTextView.setText(
                String.format(getString(R.string.category_suggested_daily_spend),
                        category.getSuggestedDailySpend()));
        mCategorySpendTotalValueTextView.setText(
                String.format(getString(R.string.category_currency),
                        category.getSpend().toString()));
        if (category.getIconPath() != -1) {
            Picasso.get().load(category.getIconPath())
                    .placeholder(category.getIconPath())
                    .into(mCategoryIconImageView);
        }
    }


    /***
     * name: setAdapter
     * description: receive a list of items and set a adapter to recycler view
     * params: List<Categories> categoryList
     *
     */
    public void setAdapter(Category category){
        RecyclerView CategoryRecyclerView = findViewById(R.id.category_spend_detailed_recycler_view);

      

        RecyclerView.LayoutManager mLayoutManager =
                new GridLayoutManager(CategorySpendsDetailedActivity.this, 1);
        CategoryRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration mDividerItemDecoration = 
                new DividerItemDecoration(CategoryRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        CategoryRecyclerView.addItemDecoration(mDividerItemDecoration);
        // using FirebaseRecyclerOption to load categories
        FirebaseRecyclerOptions categorySpends = FirebaseHelper.getInstance()
                .readCategoriesSpends(category);


        mCategorySpendDetailedAdapter =
                new FirebaseCategorySpendDetailedAdapter(categorySpends, mContext);

        CategoryRecyclerView.setAdapter(mCategorySpendDetailedAdapter);

    }

}
