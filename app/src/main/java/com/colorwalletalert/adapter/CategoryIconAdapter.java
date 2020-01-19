package com.colorwalletalert.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.colorwalletalert.ui.R;
import com.squareup.picasso.Picasso;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


public class CategoryIconAdapter extends RecyclerView.Adapter<CategoryIconAdapter.IconViewHolder>{


    public interface OnItemClickListener {
        void onItemClick(int iconPosition);
    }

    private final TypedArray mCategoryIconList;
    private final OnItemClickListener listener;
    private ImageView categoryIconImageView;
    private Context context;



    public CategoryIconAdapter (TypedArray categoryIconList, OnItemClickListener listenerOnItemClickListener) {
        mCategoryIconList = categoryIconList;
        listener = listenerOnItemClickListener;

    }

    @Override
    public IconViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        int layoutIdForMovieItem = R.layout.category_icon_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForMovieItem, viewGroup, false);

        return new IconViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IconViewHolder iconViewHolder, int i) {
        iconViewHolder.bind(mCategoryIconList.getResourceId(i, -1), listener);
    }


    @Override
    public int getItemCount() {
        return mCategoryIconList.length();
    }


    class IconViewHolder extends RecyclerView.ViewHolder {

        IconViewHolder(View itemView){
            super(itemView);
            categoryIconImageView = itemView.findViewById(R.id.new_category_icon_image_view);
        }

        void bind(final int icon, final OnItemClickListener listener){

            Picasso.get()
                    .load(icon)
                    .error(icon)
                    .into(categoryIconImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(icon);
                    //TODO: deixar somente um selecionado
                    view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorFontPrimary));

                }
            });

        }

    }
}
