package com.demo.feetme;

import android.app.Activity;
import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class DeviceScanActivity extends Activity {
	
	ListView listDevicesFound;
	
	BluetoothAdapter mBluetoothAdapter;
	// BluetoothAdapter?
	
	ArrayAdapter<String> btArrayAdapter;
	
	//private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_device_scan);
		

		final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
		mBluetoothAdapter = bluetoothManager.getAdapter();

		final Button btnScanDevice = (Button)findViewById(R.id.scandevice);
		btnScanDevice.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
			    btArrayAdapter.clear();
			    mBluetoothAdapter.startDiscovery();
			    //mBluetoothAdapter.startLeScan(mLeScanCallback);
			}
		});
		
		// BluetoothAdapter?
		listDevicesFound = (ListView)findViewById(R.id.devicesfound);
		btArrayAdapter = new ArrayAdapter<String>(DeviceScanActivity.this, android.R.layout.simple_list_item_1);
        listDevicesFound.setAdapter(btArrayAdapter);
        
        registerReceiver(ActionFoundReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
	
	}    
	
	
	@Override
	 protected void onRestart(){
		super.onRestart();
		//CheckBlueToothState();
	}
	
	@Override
	 protected void onStart(){
		super.onStart();
		//CheckBlueToothState();
	 }
	
	@Override
	 protected void onResume(){
		super.onResume();
		//CheckBlueToothState();
	}
	
	@Override
	 protected void onDestroy(){
		super.onDestroy();
		unregisterReceiver(ActionFoundReceiver);
	}
	
	private final BroadcastReceiver ActionFoundReceiver = new BroadcastReceiver(){
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
		    String action = intent.getAction();
		    if(BluetoothDevice.ACTION_FOUND.equals(action)) {
		    	BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		    	btArrayAdapter.add(device.getName() + "\n" + device.getAddress());
		    	btArrayAdapter.notifyDataSetChanged();
		    }
		}
	};
	
}


    

