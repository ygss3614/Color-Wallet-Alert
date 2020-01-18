package com.colorwalletalert.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colorwalletalert.model.Category;
import com.colorwalletalert.ui.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseCategoryAdapter extends FirebaseRecyclerAdapter<Category, FirebaseCategoryAdapter.CategoryViewHolder> {
    static final String TAG = "FirebaseCategoryAdapter";
    private OnItemClickListener listener;



    public interface OnItemClickListener {
        void onItemClick(Category category);
    }


    public FirebaseCategoryAdapter (FirebaseRecyclerOptions<Category> options,
                                    OnItemClickListener listener) {
        super(options);
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForCWABoardItem = R.layout.cwa_board_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForCWABoardItem, viewGroup, false);

        return new CategoryViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i, @NonNull Category category) {
//        categoryViewHolder.mCategoryDescriptionTextView.setText(category.getDescription());
        categoryViewHolder.bind(category, listener);
    }


    class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView mCategoryDescriptionTextView;
        TextView mCategoryTargetTextView;
        CategoryViewHolder(View itemView){
            super(itemView);
            mCategoryDescriptionTextView =  itemView.findViewById(R.id.category_description_text_view);
            mCategoryTargetTextView = itemView.findViewById(R.id.category_target_text_view);
        }

        void bind(final Category category, final OnItemClickListener listener){
            mCategoryDescriptionTextView.setText(category.getDescription());
            mCategoryTargetTextView.setText(category.getTarget().toString());


        }
    }
}
