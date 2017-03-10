package com.example.wifitest;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Switch toggle = (Switch) findViewById(R.id.wifi_switch);

		ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (activeNetwork != null && activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) { // connected to the internet
			if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI && activeNetwork.isConnected()) {
				// connected to wifi
				toggle.callOnClick();
				toggle.setChecked(true);

				Toast.makeText(getApplicationContext(), "Wi-Fi on!", Toast.LENGTH_LONG).show();
			} else{
				Toast.makeText(getApplicationContext(), "Wi-Fi off!", Toast.LENGTH_LONG).show();

			}
		} else {
			// not connected to the internet

			toggleWiFi(true);
			toggle.setChecked(true);
			toggle.callOnClick();
		}
		toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					toggleWiFi(true);
					Toast.makeText(getApplicationContext(), "Wi-Fi Enabled!", Toast.LENGTH_LONG).show();
				} else {
					toggleWiFi(false);
					Toast.makeText(getApplicationContext(), "Wi-Fi Disabled!", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	public void toggleWiFi(boolean status) {
		WifiManager wifiManager = (WifiManager) this
				.getSystemService(Context.WIFI_SERVICE);
		if (status == true && !wifiManager.isWifiEnabled()) {
			wifiManager.setWifiEnabled(true);
		} else if (status == false && wifiManager.isWifiEnabled()) {
			wifiManager.setWifiEnabled(false);
		}
	}
	  
}
