package com.sam_chordas.android.stockhawk.ui;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteModel;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;
import com.sam_chordas.android.stockhawk.databinding.ActivityStockDetailsBinding;

public class StockDetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int CURSOR_LOADER_ID = 234;
    private static final String STOCK_STATE_KEY = "stock_key";
    public static final String STOCK_SYMBOL_EXTRA = "stock_symbol_extra";
    ActivityStockDetailsBinding mStockBinding;
    QuoteModel mQuote;
    String mStockSymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null)
            this.mQuote = savedInstanceState.getParcelable(STOCK_STATE_KEY);

        this.mStockBinding = DataBindingUtil.setContentView(this, R.layout.activity_stock_details);

        if (this.mQuote != null) {
            this.mStockSymbol = mQuote.getSymbol();
            this.mStockBinding.setQuote(this.mQuote);
            this.mStockBinding.glContainer.setVisibility(View.VISIBLE);
        }else{
            this.mStockSymbol = getIntent().getStringExtra(STOCK_SYMBOL_EXTRA);
            getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mQuote == null)
            getLoaderManager().restartLoader(CURSOR_LOADER_ID, null, this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.mQuote != null)
            outState.putParcelable(STOCK_STATE_KEY, this.mQuote);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, QuoteProvider.Quotes.CONTENT_URI,
                new String[]{QuoteColumns.BIDPRICE, QuoteColumns.CHANGE, QuoteColumns.CHANGE_FOR_YEAR_HIGH,
                        QuoteColumns.CHANGE_FOR_YEAR_LOW, QuoteColumns.PERCENT_CHANGE, QuoteColumns.PREVIOUS_CLOSE,
                        QuoteColumns.SYMBOL, QuoteColumns.YEAR_RANGE},
                QuoteColumns.SYMBOL + " =?",
                new String[]{this.mStockSymbol},
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data == null || !data.moveToFirst())
            return;

        mQuote = new QuoteModel();
        mQuote.setBidPrice(data.getString(0));
        mQuote.setChange(data.getString(1));
        mQuote.setChangeForYearHigh(data.getString(2));
        mQuote.setChangeForYearLow(data.getString(3));
        mQuote.setPercentChange(data.getString(4));
        mQuote.setPreviousClose(data.getString(5));
        mQuote.setSymbol(data.getString(6));
        mQuote.setYearRange(data.getString(7));
        this.mStockBinding.setQuote(mQuote);

        this.mStockBinding.glContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
