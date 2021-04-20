package com.example.appmci.bluetooth;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.preference.PreferenceManager;

import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.appmci.R;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BLEScan extends Service{
    private static final String TAG = BLEScan.class.getSimpleName();
    private boolean mScanning;
    private BluetoothGatt btGatt;
    private BluetoothAdapter mBluetoothAdapter;
    private Handler mHandler;
    @Override
    public void onCreate() {
        super.onCreate();
        getBTService();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        writeLine("Automate service start...");
        if (!isBluetoothSupported()) {
            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
        }else{
            if(mBluetoothAdapter!=null && mBluetoothAdapter.isEnabled()){
                startBLEscan();
            }else{
//                stopSelf();
            }
        }
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(btGatt!=null){
            btGatt.disconnect();
            btGatt.close();
            btGatt = null;
        }
    }
    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }
    // Initializes a Bluetooth adapter.  For API level 18 and above, get a reference to
    // BluetoothAdapter through BluetoothManager.
    public BluetoothAdapter getBTService(){
        BluetoothManager btManager = (BluetoothManager) getApplicationContext().getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = (BluetoothAdapter) btManager.getAdapter();
        return mBluetoothAdapter;
    }

    public boolean isBluetoothSupported() {
        return this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
    }
    public void startBLEscan(){

        mBluetoothAdapter.startLeScan(mLeScanCallback);
    }

    public void stopBLEscan(){
        mBluetoothAdapter.stopLeScan(mLeScanCallback);
    }
    public static void enableDisableBluetooth(boolean enable){
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null) {
            if(enable) {
                bluetoothAdapter.enable();
            }else{
                bluetoothAdapter.disable();
            }
        }
    }

    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {
                @Override
                public void onLeScan(final BluetoothDevice device, final int rssi, byte[] scanRecord) {
                    String Test = String.valueOf(device);
                    if(CheckMac(Test) == true && CheckContains(ByteArrayToIntArray(scanRecord))==true) {
                        Log.d("DEBUG", "device : " + Test);
                        Log.d("DEBUG", "RSSI : " + rssi);
                        printScanRecord(scanRecord);
                        int hr = Heart(ByteArrayToIntArray(scanRecord));
                        int steps = Steps(ByteArrayToIntArray(scanRecord));
                        int posture = Posture(ByteArrayToIntArray(scanRecord));
                        Log.e("debug", "Posture: "+posture);
                        Log.e("debug", "HR: "+hr);
                        Log.e("debug", "Steps: "+steps);
                        Log.e("debug","RSSI: " + rssi);

                        Bundle bundle = new Bundle();
                        bundle.putInt("hr", hr);
                        bundle.putInt("rssi", rssi);
                        bundle.putInt("step", steps);
                        bundle.putInt("posture", posture);
                        bundle.putInt("rssi",rssi);
                        Intent broadIntent = new Intent("bodyTagBroadcast");
                        broadIntent.putExtras(bundle);
                        sendBroadcast(broadIntent);
                    }
                    if (device != null && device.getName() != null) {
                        if (rssi > -90 && rssi < -1) {
                            if (device.getName().equalsIgnoreCase("NCS_Beacon") || device.getName().equalsIgnoreCase("estimote")) {
                                new Handler(getApplicationContext().getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        btGatt = device.connectGatt(getApplicationContext(), false, bleGattCallback);
                                        Log.e(TAG, "onLeScan btGatt value returning from connectGatt " + btGatt);
                                    }
                                });
                            }
                        } else {
                            Log.v("Device Scan Activity", device.getAddress() + " " + "BT device is still too far - not connecting");
                        }
                    }
                }
            };
    BluetoothGattCallback bleGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(final BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            if (newState == android.bluetooth.BluetoothProfile.STATE_CONNECTED){
                Log.v("BLEService", "BLE Connected now discover services");
                Log.v("BLEService", "BLE Connected");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        gatt.discoverServices();
                    }
                }).start();
            }else if (newState == android.bluetooth.BluetoothProfile.STATE_DISCONNECTED){
                Log.v("BLEService", "BLE Disconnected");
            }
        }

        @Override
        public void onServicesDiscovered(final BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.v("BLEService", "BLE Services onServicesDiscovered");
                List<BluetoothGattService> services = gatt.getServices();
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic,  int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
        }
    };
    public void printScanRecord (byte[] scanRecord) {
        // Simply print all raw bytes
        try {
            String decodedRecord = new String(scanRecord,"UTF-8");
            Log.d("DEBUG", "decoded String : " + ByteArrayToString(scanRecord));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // Parse data bytes into individual records
        List<AdRecord> records = AdRecord.parseScanRecord(scanRecord);
        // Print individual records
        if (records.size() == 0) {
            Log.i("DEBUG", "Scan Record Empty");
        } else {
            //Log.i("DEBUG", "Scan Record: " + TextUtils.join(",", records));
        }
    }

    public static int Heart (int[] test)
    {
        return test[18];
    }
    public static int Posture (int[] test)
    {
        return test[17];
    }
    public static int Steps (int[] test)
    {
        int a = 0,b = 0;
        a = test[7];
        b = test[8];
        int stepsA = 0,stepsB = 0,stepsTotal = 0;
        if( a>=0 && a<=127 ){
            stepsA = a;
        }else if( a>=-128 && a<=-1 ){
//            stepsA = complement(a);
        }else{Log.e("Steps :","wrong");}
        if( b>=0 && b<=127 ){
            stepsB = b;
        }else if( b>=-128 && b<=-1 ){
//            stepsB = complement(b);
        }else{Log.e("Steps :","wrong");}
        stepsB = stepsB*255;
        stepsTotal = (stepsA+stepsB)/3;

        return stepsTotal;
    }
    public static String ByteArrayToString(byte[] ba)
    {
        StringBuilder hex = new StringBuilder(ba.length * 2);
        for (byte b : ba)
            hex.append(b + " ");
        return hex.toString();
    }
    private void writeLine(final String message) {
        Handler h = new Handler(getApplicationContext().getMainLooper());
        h.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static int[] ByteArrayToIntArray(byte[] ba )
    {
        int[] Result = new int[ba.length*2];
        for (int i = 0;i<ba.length;i++) {
            Result[i] = (int) ba[i];
        }
        return Result;
    }
    public boolean CheckMac(String test)
    {
        String compare = "D7:42:35:95:C5:79";
        if(test.equals(compare))
        {
            return true ;
        }
        else
            return false;
    }
    public boolean CheckContains(int[] test)
    {
        int[] Filter = {6,16};
        for(int i=2;i<4;i++)
        {
            if(test[i] != Filter[i-2] )
            {
                return false;
            }
        }
        return true;

    }

    public static class AdRecord {

        public AdRecord(int length, int type, byte[] data) {
            String decodedRecord = "";
            try {
                decodedRecord = new String(data,"UTF-8");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        public static List<AdRecord> parseScanRecord(byte[] scanRecord) {
            List<AdRecord> records = new ArrayList<AdRecord>();

            int index = 0;
            while (index < scanRecord.length) {
                int length = scanRecord[index++];
                if (length == 0) break;
                int type = scanRecord[index];
                if (type == 0) break;
                byte[] data = Arrays.copyOfRange(scanRecord, index+1, index+length);
                records.add(new AdRecord(length, type, data));
                index += length;
            }
            return records;
        }
    }
    static class ViewHolder {
        TextView deviceName;
        TextView deviceAddress;
    }
}