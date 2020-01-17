package com.colorwalletalert.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colorwalletalert.model.Category;
import com.colorwalletalert.ui.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private final List<Category> mCategoryList;
    private final OnItemClickListener listener;
    private TextView mCategoryDescriptionTextView;
    private TextView mCategoryTargetTextView;


    public interface OnItemClickListener {
        void onItemClick(Category category);
    }

    public CategoryAdapter (List<Category> categoryList, OnItemClickListener listenerOnItemClickListener) {
        mCategoryList = categoryList;
        listener = listenerOnItemClickListener;
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
    public void onBindViewHolder(@NonNull CategoryViewHolder movieViewHolder, int i) {
        movieViewHolder.bind(mCategoryList.get(i), listener);
    }


    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }


    class CategoryViewHolder extends RecyclerView.ViewHolder {

        CategoryViewHolder(View itemView){
            super(itemView);
            mCategoryDescriptionTextView =  itemView.findViewById(R.id.category_description_text_view);
            mCategoryTargetTextView = itemView.findViewById(R.id.category_target_text_view);
        }

        void bind(final Category category, final OnItemClickListener listener){
            mCategoryDescriptionTextView.setText(category.getDescription());
            mCategoryTargetTextView.setText(category.getTarget().toString());
//            Picasso.get()
//                    .load(movieDB.getPosterPath())
//                    .error(R.mipmap.ic_launcher)
//                    .into(listItemMoviePoster);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onItemClick(movieDB);
//                }
//            });

        }
    }
}
