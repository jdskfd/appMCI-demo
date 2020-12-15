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
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.preference.PreferenceManager;

import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmci.FragmentHome;
import com.example.appmci.R;

/**Both RX and RSSI (Received Signal Strength Indication) are indications of the power level being received
 * by an antenna
 * The difference between RX and RSSI is that RX is measured in milliWatts (mW) or decibel-milliwatts (dBm)
 * whereas RSSI is a signal strength percentage—the higher the RSSI number, the stronger the signal
 *
 */

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BLEScan extends Service{
    private static final String TAG = BLEScan.class.getSimpleName();
    private boolean mScanning;
    private BluetoothGatt btGatt;
    private BluetoothAdapter mBluetoothAdapter;
    private Handler mHandler;
//    private final MyIBinder myIBinder = new MyIBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        writeLine("Automate service created...");
        getBTService();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        writeLine("Automate service start...");
        if (!isBluetoothSupported()) {
            writeLine("1");

            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
//            stopSelf();
        }else{
            if(mBluetoothAdapter!=null && mBluetoothAdapter.isEnabled()){


                startBLEscan();
            }else{


//                stopSelf();
            }

        }
        return START_STICKY;
    }
//    public class MyIBinder extends Binder {
//        public Service getService() {
//            FragmentHome.vh.sendMessage(FragmentHome.createMessage(
//                    FragmentHome.UPDATE_VIEW,
//                    "BindServiceWithIBinder.MyIBinder.getService()"));
//            return FragmentHome.this;
//        }
//    }

    @Override
    public IBinder onBind(Intent intent) {
//        FragmentHome.vh.sendMessage(FragmentHome.createMessage(FragmentHome.UPDATE_VIEW, "HI its Binder"));
        return null;
    }

    @Override
    public void onDestroy() {
        writeLine("Automate service destroyed...");
//        stopBLEscan();
        super.onDestroy();

        if(btGatt!=null){
            btGatt.disconnect();
            btGatt.close();
            btGatt = null;
        }
    }
//    private void scanLeDevice(final boolean enable) {
//        if (enable) {
//            // Stops scanning after a pre-defined scan period.
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    mScanning = false;
//                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
//
//                }
//            },1000000000);
//
//            mScanning = true;
//            mBluetoothAdapter.startLeScan(mLeScanCallback);
//        } else {
//            mScanning = false;
//            mBluetoothAdapter.stopLeScan(mLeScanCallback);
//        }
//
//    }
//    @Override
//    public boolean stopService(Intent name) {
//        writeLine("Automate service stop...");
//        stopSelf();
//        return super.stopService(name);
//    }

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



    /**
     *

     */
//    public void scanLeDevice(final boolean enable) {
//        if (enable) {
//            startBLEscan();
//        } else {
//            stopBLEscan();
//        }
//    }

    public void startBLEscan(){
        writeLine("3");
        mBluetoothAdapter.startLeScan(mLeScanCallback);
    }

//    public void stopBLEscan(){
//        mBluetoothAdapter.stopLeScan(mLeScanCallback);
//    }
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
                  //  Log.d(TAG + " onLeScan: ", "Name: " + device.getName() + "Address: " + device.getAddress() + "RSSI: " + rssi + "scanRecord" +scanRecord);
                    String devicename = String.valueOf(device);
                    if(CheckMac(devicename)==true
                    ) {
                        Log.d("DEBUG", "device : " + devicename);
                        Log.d("DEBUG", "RSSI : " + rssi);
                        //Log.d("DEBUG", "device : " + device);
                        printScanRecord(scanRecord);
                    }

                    if (device != null && device.getName() != null) {
                        if (rssi > -90 && rssi < -1) {
                            writeLine("Automate service BLE device in range: " + device.getName() + " " + rssi);
                            if (device.getName().equalsIgnoreCase("NCS_Beacon") || device.getName().equalsIgnoreCase("estimote")) {
                                //This Main looper thread is main for connect gatt, don't remove it
                                // Although you need to pass an appropriate context getApplicationContext(),
                                //Here if you use Looper.getMainLooper() it will stop getting callback and give internal exception fail to register //callback
                                new Handler(getApplicationContext().getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        btGatt = device.connectGatt(getApplicationContext(), false, bleGattCallback);
                                        Log.e(TAG, "onLeScan btGatt value returning from connectGatt " + btGatt);
                                    }
                                });
                            }
//                            stopBLEscan();
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
            writeLine("Automate service connection state: "+ newState);
            if (newState == android.bluetooth.BluetoothProfile.STATE_CONNECTED){
                writeLine("Automate service connection state: STATE_CONNECTED");
                Log.v("BLEService", "BLE Connected now discover services");
                Log.v("BLEService", "BLE Connected");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        writeLine("Automate service go for discover services");
                        gatt.discoverServices();
                    }
                }).start();
            }else if (newState == android.bluetooth.BluetoothProfile.STATE_DISCONNECTED){
                writeLine("Automate service connection state: STATE_DISCONNECTED");
                Log.v("BLEService", "BLE Disconnected");
            }
        }

        @Override
        public void onServicesDiscovered(final BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            if (status == BluetoothGatt.GATT_SUCCESS) {
                writeLine("Automate service discover service: GATT_SUCCESS");
                Log.v("BLEService", "BLE Services onServicesDiscovered");
                //Get service
                List<BluetoothGattService> services = gatt.getServices();
                //  writeLine("Automate service discover service imei: " +imei);
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
    public static String ByteArrayToString(byte[] ba)
    {
        StringBuilder hex = new StringBuilder(ba.length * 2);
        for (byte b : ba)
            hex.append(b + " ");
        return hex.toString();
    }
    private void writeLine(final String message) {
        Handler h = new Handler(getApplicationContext().getMainLooper());
        // Although you need to pass an appropriate context
        h.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static int[] ByteArrayToIntArray(byte[] ba )
    {
        //StringBuilder tmp = new StringBuilder();
        int[] Result = new int[ba.length*2];
        for (int i = 0;i<ba.length;i++) {
            Result[i] = (int) ba[i];
            //tmp.append(Result[i]).append(" ");
        }
        return Result;
    }
    public boolean CheckMac(String test)
    {
        String compare = "CF:C0:BF:F9:9C:90";
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

        // ...

        public static List<AdRecord> parseScanRecord(byte[] scanRecord) {
            List<AdRecord> records = new ArrayList<AdRecord>();

            int index = 0;
            while (index < scanRecord.length) {
                int length = scanRecord[index++];
                //Done once we run out of records
                if (length == 0) break;

                int type = scanRecord[index];
                //Done if our record isn't a valid type
                if (type == 0) break;

                byte[] data = Arrays.copyOfRange(scanRecord, index+1, index+length);

                records.add(new AdRecord(length, type, data));
                //Advance
                index += length;
            }

            return records;
        }

        // ...
    }
    static class ViewHolder {
        TextView deviceName;
        TextView deviceAddress;
    }
}