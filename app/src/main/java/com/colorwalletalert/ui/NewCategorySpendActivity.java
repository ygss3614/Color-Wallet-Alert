package com.colorwalletalert.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.colorwalletalert.database.FirebaseHelper;
import com.colorwalletalert.model.Category;
import com.colorwalletalert.model.CategorySpend;
import com.colorwalletalert.widget.CWAWidgetService;

import java.util.Objects;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class NewCategorySpendActivity extends AppCompatActivity {
    private final String TAG = "NewCategorySpendActivity";
    public static final String EXTRA_CATEGORY = "category";
    private TextView mNewCategorySpendValueTextView;
    private TextView mNewCategoryLocationValueTextView;
    private Category mCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category_spend);
        mNewCategorySpendValueTextView = findViewById(R.id.new_category_spend_value_text_view);
        mNewCategoryLocationValueTextView = findViewById(R.id.new_category_spend_location_text_view);

        Intent intent = getIntent();
        mCategory = Objects.requireNonNull(intent).getParcelableExtra(EXTRA_CATEGORY);

        //Adding subtitle
        ActionBar ab = getSupportActionBar();
        ab.setSubtitle(R.string.add_new_category_spend_subtitle);

        // update widget
        CWAWidgetService.startActionUpdateCategory(this, mCategory);

    }

    public void addNewSpend(View view) {
        CategorySpend categorySpend = new CategorySpend(mCategory.getDescription(),
                        Float.parseFloat(mNewCategorySpendValueTextView.getText().toString()),
                        mNewCategoryLocationValueTextView.getText().toString());
        FirebaseHelper.getInstance().saveCategorySpend(categorySpend);
        backToBoard();
    }

    public void backToBoard() {
        Context context = NewCategorySpendActivity.this;
        Intent intent = new Intent(context, CWABoardActivity.class);
        context.startActivity(intent);
        this.finish();
    }

}

