package com.colorwalletalert.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.colorwalletalert.database.FirebaseHelper;
import com.colorwalletalert.model.Category;

import androidx.annotation.Nullable;

public class CWAWidgetService extends IntentService {

    public static final String ACTION_UPDATE_CATEGORY =
            "update_category";
    public static final String EXTRA_CATEGORY = "CATEGORY";


    public CWAWidgetService() {
        super("CWAService");
    }


    public static void startActionUpdateCategory(Context context, Category category){
        Log.d("WIDGET", category.getDescription());
        Intent intent = new Intent(context, CWAWidgetService.class);
        intent.setAction(ACTION_UPDATE_CATEGORY);
        intent.putExtra(EXTRA_CATEGORY, category);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if  (intent != null){
            final String action = intent.getAction();
            if (ACTION_UPDATE_CATEGORY.equals(action)){
                final Category category = intent.getParcelableExtra(EXTRA_CATEGORY);
                handleActionUpdateRecipeWidgets(category);
            }
        }
    }

    private void handleActionUpdateRecipeWidgets(Category category){
        //TODO: selecionar a categoria que mais esta no vermelho
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetsIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, CWAWidget.class));
        CWAWidget.updateCWAWidgets(this, appWidgetManager, category, appWidgetsIds);
    }
}
