package com.colorwalletalert.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.colorwalletalert.model.Category;
import com.colorwalletalert.ui.CWABoardActivity;
import com.colorwalletalert.ui.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseCategoryAdapter extends FirebaseRecyclerAdapter<Category, FirebaseCategoryAdapter.CategoryViewHolder> {
    static final String TAG = "FirebaseCategoryAdapter";
    private OnItemClickListener listener;
    private Context context;



    public interface OnItemClickListener {
        void onItemClick(Category category);
    }


    public FirebaseCategoryAdapter (FirebaseRecyclerOptions<Category> options,
                                    Context context,
                                    OnItemClickListener listener) {
        super(options);
        this.listener = listener;
        this.context = context;
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
        TextView mCategorySuggestedTextView;
        ImageView mCategoryIconImageView;

        CategoryViewHolder(View itemView){
            super(itemView);
            mCategoryDescriptionTextView =  itemView.findViewById(R.id.category_description_text_view);
            mCategoryTargetTextView = itemView.findViewById(R.id.category_target_text_view);
            mCategorySuggestedTextView = itemView.findViewById(R.id.category_sugested_text_view);
            mCategoryIconImageView = itemView.findViewById(R.id.category_icon_image_view);
        }

        void bind(final Category category, final OnItemClickListener listener){
            mCategoryDescriptionTextView.setText(category.getDescription().toLowerCase());
            // TODO atualizar esse valor de acordo com o gasto inserido
            mCategoryTargetTextView.setText(category.getTarget().toString());
            // TODO atualizar esse valor de acordo com o gasto inserido
            mCategorySuggestedTextView.setText(
                    String.format("Suggested daily spend %s", category.getTarget().toString()));

            if (category.getIconPath() != -1) {
                    Picasso.get().load(category.getIconPath())
                            .placeholder(category.getIconPath())
                            .into(mCategoryIconImageView);
            }
        }
    }
}
