package com.example.homewidgettest;

import java.util.Random;
import java.util.Set;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.RemoteViews;

public class MyWidgetUpdateService extends Service {
	Handler h = null;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (h == null) {
			h = new Handler();
			h.postDelayed(widgetRunnable, 500);
		}
		return START_STICKY;
	}

	@Override
	public void onCreate() {
	}

	@Override
	public void onDestroy() {
	}

	private Runnable widgetRunnable = new Runnable() {
		public void run() {
			updateWidgets();
			h.postDelayed(widgetRunnable, 2000);
		}
	};

	void updateWidgets() {
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
				.getApplicationContext());
		Set<Integer> s = MainAppClass.getInstance().widgetsMap.
							keySet();
		 Integer []wids = s.toArray(new Integer[s.size()]);
		
		for (int widgetId : wids) {
			// create some random data
			int number = (new Random().nextInt(100));

			RemoteViews remoteViews = new RemoteViews(this
					.getApplicationContext().getPackageName(),
					R.layout.widget_layout);

			// Set the text
			remoteViews.setTextViewText(R.id.update,
					"Random: " + String.valueOf(number));

			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}

	}
}
