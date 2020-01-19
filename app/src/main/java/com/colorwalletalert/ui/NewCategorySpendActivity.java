package com.colorwalletalert.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.colorwalletalert.database.FirebaseHelper;
import com.colorwalletalert.model.Category;
import com.colorwalletalert.model.CategorySpend;

import java.util.Objects;

public class NewCategorySpendActivity extends AppCompatActivity {
    private final String TAG = "NewCategorySpendActivity";
    private TextView mNewCategorySpendValueTextView;
    private Category mCategory;
    public static final String EXTRA_CATEGORY = "category";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category_spend);
        mNewCategorySpendValueTextView = findViewById(R.id.new_category_spend_value_text_view);
        Intent intent = getIntent();
        mCategory = Objects.requireNonNull(intent).getParcelableExtra(EXTRA_CATEGORY);

    }

    public void addNewSpend(View view){
        CategorySpend categorySpend =
                new CategorySpend(mCategory,
                        Float.parseFloat(mNewCategorySpendValueTextView.getText().toString()));
        FirebaseHelper.getInstance().saveCategorySpend(categorySpend);
        backToBoard();

    }

    public void backToBoard(){
        Context context = NewCategorySpendActivity.this;
        Intent intent = new Intent(context, CWABoardActivity.class);
        context.startActivity(intent);
    }

}

