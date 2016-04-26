package com.sam_chordas.android.stockhawk.service;


import android.content.Intent;
import android.widget.RemoteViewsService;

import com.sam_chordas.android.stockhawk.widget.StockListProvider;

public class WidgetDataService extends RemoteViewsService
{

    @Override
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {

        return (new StockListProvider(this.getApplicationContext()));
    }

}