package com.colorwalletalert.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.colorwalletalert.database.FirebaseHelper;
import com.colorwalletalert.model.Category;
import com.colorwalletalert.model.CategorySpend;
import com.colorwalletalert.ui.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseCategorySpendDetailedAdapter
        extends FirebaseRecyclerAdapter<CategorySpend,
                FirebaseCategorySpendDetailedAdapter.CategorySpendDetailedViewHolder> {

    static final String TAG = "FirebaseCategorySpendDetailedAdapter";
    private Context context;

    public FirebaseCategorySpendDetailedAdapter(FirebaseRecyclerOptions<CategorySpend> options,
                                                Context context) {
        super(options);
        this.context = context;
    }

    @NonNull
    @Override
    public CategorySpendDetailedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForCWABoardItem = R.layout.category_spend_detailed_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForCWABoardItem, viewGroup, false);

        return new CategorySpendDetailedViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(CategorySpendDetailedViewHolder categorySpendDetailedViewHolder,
                                    int i, CategorySpend categorySpend) {
        categorySpendDetailedViewHolder.bind(categorySpend);
    }


    class CategorySpendDetailedViewHolder extends RecyclerView.ViewHolder {

        TextView mCategorySpendDateTextView;
        TextView mCategorySpendValueTextView;
        TextView mCategorySpendLocationTextView;



        CategorySpendDetailedViewHolder(View itemView){
            super(itemView);
            mCategorySpendDateTextView =  itemView.findViewById(R.id.spend_date_text_view);
            mCategorySpendValueTextView = itemView.findViewById(R.id.spend_value_text_view);
            mCategorySpendLocationTextView = itemView.findViewById(R.id.spend_location_text_view);

        }

        void bind(final CategorySpend categorySpend){
            Resources resource = context.getResources();
//            mCategorySpendDateTextView.setText(categorySpend.getSpendDay());
            mCategorySpendValueTextView.setText(
                    String.format(resource.getString(R.string.category_currency),
                            categorySpend.getSpendValue().toString()));

            mCategorySpendLocationTextView.setText("location");
        }
    }
}
