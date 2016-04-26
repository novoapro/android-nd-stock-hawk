package com.sam_chordas.android.stockhawk.widget;

import android.content.Context;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;

/**
 * novoa on 4/26/16.
 */
public class StockListProvider implements RemoteViewsService.RemoteViewsFactory {
    private Context context = null;
    private Cursor mData;

    public StockListProvider(Context context) {
        this.context = context;
        populateListItem();
    }

    private void populateListItem() {
        this.mData = this.context.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,
                new String[]{QuoteColumns._ID, QuoteColumns.SYMBOL, QuoteColumns.BIDPRICE,
                        QuoteColumns.PERCENT_CHANGE, QuoteColumns.CHANGE, QuoteColumns.ISUP},
                QuoteColumns.ISCURRENT + " = ?",
                new String[]{"1"},
                null);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return this.mData != null ? this.mData.getCount() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.list_item_quote);

        if(this.mData == null || !this.mData.moveToPosition(position))
            return null;

        remoteView.setTextViewText(R.id.stock_symbol, this.mData.getString(1));
        remoteView.setTextViewText(R.id.bid_price, this.mData.getString(2));
        remoteView.setTextViewText(R.id.change, this.mData.getString(3));

        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }
}
