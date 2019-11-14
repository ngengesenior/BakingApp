package com.apps.ngenge.bakingapp.app_widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class ListRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new IngredientsRemoteViewsFactory(this.getApplication());
    }
}
