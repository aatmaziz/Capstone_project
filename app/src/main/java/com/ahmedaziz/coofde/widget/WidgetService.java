package com.ahmedaziz.coofde.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.ahmedaziz.coofde.DataBase.ColumnsDB;
import com.ahmedaziz.coofde.DataBase.Provider;
import com.akhooo.coofde.R;

/**
 * Created by Ahmed Aziz on 9/22/2017.
 */

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new CoofdeRemoteViewsFactory(this.getApplicationContext(), intent);
    }

    class CoofdeRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{
        private Context mContext;
        private int mAppWidgetId;
        private Cursor data =null;
        CoofdeRemoteViewsFactory(Context context, Intent intent){
            mContext = context;
            mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);

        }

        @Override
        public void onDataSetChanged() {
            if(data != null){
                data.close();
            }
            final long identityToken = Binder.clearCallingIdentity();
            data = getContentResolver().query(Provider.Coofde.CONTENT_URI, null, null, null, null);
            Binder.restoreCallingIdentity(identityToken);
        }

        @Override
        public int getCount() {
            return data == null ? 0 : data.getCount();
        }

        @Override
        public void onCreate() {

        }

        @Override
        public RemoteViews getViewAt(int position) {
            if(position == AdapterView.INVALID_POSITION || data == null
                    || !data.moveToPosition(position)){
                Log.d("LOG", "LOg:----------------");
                return null;
            }
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(mContext);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(mContext, getClass()));
            RemoteViews rv = new RemoteViews(getPackageName(), R.layout.appwidget_list_item);
            rv.setTextViewText(R.id.appwidget_store_title, data.getString(data.getColumnIndexOrThrow(ColumnsDB.STORE)));
            rv.setTextViewText(R.id.appwidget_offer_type, data.getString(data.getColumnIndexOrThrow(ColumnsDB.TYPE)));
            rv.setTextViewText(R.id.appwidget_coofde_title, data.getString(data.getColumnIndexOrThrow(ColumnsDB.TITLE)));

            Bundle extras = new Bundle();
            extras.putInt(WidgetProvider.ITEM_POSITION, position);
            Intent intent = new Intent();
            intent.putExtras(extras);
            rv.setOnClickFillInIntent(R.id.appwidget_item, intent);

            return rv;
        }

        @Override
        public void onDestroy() {
            if(data != null){
                data.close();
                data = null;
            }
        }

        @Override
        public RemoteViews getLoadingView() {
            return new RemoteViews(getPackageName(), R.layout.appwidget_list_item);
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            if(data.moveToPosition(position))
                return data.getLong(1);
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
