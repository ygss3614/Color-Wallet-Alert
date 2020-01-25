package com.colorwalletalert.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.RemoteViews;

import com.colorwalletalert.model.Category;
import com.colorwalletalert.ui.CWABoardActivity;
import com.colorwalletalert.ui.CategorySpendsDetailedActivity;
import com.colorwalletalert.ui.R;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

/**
 * Implementation of App Widget functionality.
 */
public class CWAWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                Category category, int appWidgetId) {

        // set the click handler to open the correct activity
        Intent intent;
        Resources resource = context.getResources();
        if(category == null){
            intent = new Intent(context, CWABoardActivity.class);
        }else{
            intent = new Intent(context, CategorySpendsDetailedActivity.class);
            intent.putExtra(CategorySpendsDetailedActivity.EXTRA_CATEGORY, category);
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 , intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.cwawidget);

        if (category == null){
            views.setTextViewText(R.id.category_description_widget_text_view,
                    "click here to \n select one category");


        }else{
            views.setTextViewText(R.id.category_description_widget_text_view,
                    category.getDescription());
            views.setTextViewText(R.id.category_available_amount_widget_text_view,
                    String.format(resource.getString(R.string.category_currency),
                            String.valueOf(category.getAvailableAmount())));
            if (category.getAvailableAmount() < 0) {
                views.setTextViewText(R.id.category_sugested_widget_text_view,
                        resource.getString(R.string.stop_spend_message));
            }else{
                views.setTextViewText(R.id.category_sugested_widget_text_view,
                        String.format(resource.getString(R.string.category_suggested_daily_spend),
                                category.getSuggestedDailySpend().toString()));
            }

            views.setInt(R.id.widget_linear_layout, "setBackgroundResource",
                    category.getCardBackgroundColor());
        }

        views.setOnClickPendingIntent(R.id.widget_linear_layout, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateCWAWidgets(Context context, AppWidgetManager appWidgetManager,
                                           Category category, int[] appWidgetIds){
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, category, appWidgetId);
        }
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, null, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

