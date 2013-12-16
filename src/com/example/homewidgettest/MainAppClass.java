package com.example.homewidgettest;

import java.util.HashMap;

import android.app.Application;

public class MainAppClass extends Application{
	
	private static MainAppClass singleton;

	/* Maps to hold widget ids */
	public HashMap<Integer, Boolean> widgetsMap = null;
	
	public static MainAppClass getInstance() {
		return singleton;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		singleton = this;
		widgetsMap = new HashMap<Integer, Boolean>();
	}

}
