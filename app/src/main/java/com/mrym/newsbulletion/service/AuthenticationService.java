/*******************************************************************************
 * Copyright (c) 2012 Manning
 * See the file license.txt for copying permission.
 ******************************************************************************/
package com.mrym.newsbulletion.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.mrym.newsbulletion.authenticator.Authenticator;


public class AuthenticationService extends Service {
	private Authenticator mAuthenticator;

	@Override
	public void onCreate() {
		super.onCreate();
		mAuthenticator = new Authenticator(this);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mAuthenticator.getIBinder();
	}
}
