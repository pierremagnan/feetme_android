package com.demo.feetme;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
	
	
	
	public TextView stateBluetooth;
	
	public BluetoothAdapter mBluetoothAdapter;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button click=(Button)findViewById(R.id.button1);       
        click.setOnClickListener(new View.OnClickListener() {                                            
            public void onClick(View v) {      
            	Intent launchactivity= new Intent(MainActivity.this, DeviceScanActivity.class);                               
            	startActivity(launchactivity);                          
            }
        });       
		
		final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
		mBluetoothAdapter = bluetoothManager.getAdapter();
		
		stateBluetooth = (TextView)findViewById(R.id.bluetoothstate);
		
		if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
		    Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
		    finish();
		}else{
			Toast.makeText(this, R.string.ble_supported, Toast.LENGTH_SHORT).show();
		}
		CheckBlueToothState();
		//Intent startdevicescan = new Intent(this, DeviceScanActivity.class);
        //startActivity(startdevicescan);
		

	    
	}

	private static final int REQUEST_ENABLE_BT = 1;
	
	//public void openNewActivity(View view) {
		// Do something in response to button
		//Intent intent = new Intent(MainActivity.this, DeviceScanActivity.class);
		//startActivity(intent);
	//}


	private void CheckBlueToothState(){
		if (mBluetoothAdapter == null){
	        stateBluetooth.setText("Bluetooth NOT supported");
	       }else{
	        if (mBluetoothAdapter.isEnabled()){
	         if(mBluetoothAdapter.isDiscovering()){
	          stateBluetooth.setText("Bluetooth is currently in device discovery process.");
	         }else{
	          stateBluetooth.setText("Bluetooth is Enabled.");
	         }
	        }else{
	         stateBluetooth.setText("Bluetooth is NOT Enabled!");
	         Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
	        }
	       }
    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  //TODO Auto-generated method stub
	  if(requestCode == REQUEST_ENABLE_BT){
	      CheckBlueToothState();
	  }	  
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
		CheckBlueToothState();
	}
	
	@Override
	 protected void onDestroy(){
		super.onDestroy();
	}
}
