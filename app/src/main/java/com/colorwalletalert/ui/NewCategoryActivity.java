package com.colorwalletalert.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.colorwalletalert.database.FirebaseHelper;
import com.colorwalletalert.model.Category;

public class NewCategoryActivity extends AppCompatActivity {

    public TextView mCategoryDescriptionTextView;
    public TextView mCategoryTargetTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);
        mCategoryDescriptionTextView = findViewById(R.id.new_category_description_text_view);
        mCategoryTargetTextView = findViewById(R.id.new_category_target_text_view);
    }

    public void addNewCategory(View v){
        Category category =
                new Category(mCategoryDescriptionTextView.getText().toString(),
                        Float.parseFloat(mCategoryTargetTextView.getText().toString()),
                             "");
        FirebaseHelper.getInstance().saveCleaning(category);

        backToBoard();

    }

    public void backToBoard(){
        Context context = NewCategoryActivity.this;
        Intent intent = new Intent(context, CWABoardActivity.class);
        context.startActivity(intent);
    }
}
