package com.colorwalletalert.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.colorwalletalert.adapter.CategoryIconAdapter;
import com.colorwalletalert.database.FirebaseHelper;
import com.colorwalletalert.model.Category;
import com.google.android.material.card.MaterialCardView;

public class NewCategoryActivity extends AppCompatActivity {
    static final String TAG = "NewCategoryActivity";
    private TextView mCategoryDescriptionTextView;
    private TextView mCategoryTargetTextView;
    private GridLayoutManager mLayoutManager;
    private TypedArray mCategoryIconList;
    private int categoryIconSelected = -1;
    private MaterialCardView mMessageCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);
        mCategoryDescriptionTextView = findViewById(R.id.new_category_description_text_view);
        mCategoryTargetTextView = findViewById(R.id.new_category_target_text_view);
        mCategoryIconList = getResources().obtainTypedArray(R.array.category_icons);
        mLayoutManager = new GridLayoutManager(NewCategoryActivity.this, 6);

        loadCategoryIcons();
    }

    public void addNewCategory(View v) {

        Category category =
                new Category(mCategoryDescriptionTextView.getText().toString(),
                        Float.parseFloat(mCategoryTargetTextView.getText().toString()),
                        categoryIconSelected);
        FirebaseHelper.getInstance().saveCategory(category);

        backToBoard();

    }

    public void backToBoard() {
        Context context = NewCategoryActivity.this;
        Intent intent = new Intent(context, CWABoardActivity.class);
        context.startActivity(intent);
        finish();
    }

    public void loadCategoryIcons() {


        final RecyclerView categoryIconRecyclerView = findViewById(R.id.new_category_icon_recycler_view);

        categoryIconRecyclerView.setLayoutManager(mLayoutManager);

        CategoryIconAdapter adapter = new CategoryIconAdapter(mCategoryIconList,
                new CategoryIconAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int iconId) {
                        categoryIconSelected = iconId;
                    }
                });
        categoryIconRecyclerView.setAdapter(adapter);
    }
}
