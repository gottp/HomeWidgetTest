package com.example.homewidgettest;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyWidgetProvider extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		try {
			MainAppClass myapp = MainAppClass.getInstance();
			for (int wid : appWidgetIds) {
				Integer i = wid;
				Log.e("AAA", String.valueOf(i) + "" + myapp.toString() + " " +
						myapp.widgetsMap.toString());
				myapp.widgetsMap.put(wid, true);
			}
			if (appWidgetIds.length > 0) {
				/* Start a service */
				Intent intent = new Intent(context.getApplicationContext(),
						MyWidgetUpdateService.class);
				context.startService(intent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		try {
			MainAppClass myapp = MainAppClass.getInstance();
			for (int wid : appWidgetIds) {
				Integer i = wid;
				if (myapp.widgetsMap.containsKey(i)) {
					myapp.widgetsMap.put(i, false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
