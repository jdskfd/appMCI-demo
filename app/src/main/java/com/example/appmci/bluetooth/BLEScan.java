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
    @Override
    public void onCreate() {
        super.onCreate();
//        writeLine("Automate service created...");
        getBTService();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        writeLine("Automate service start...");
        if (!isBluetoothSupported()) {

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

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
//        writeLine("Automate service destroyed...");
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
//            },2000000000);
//
//            mScanning = true;
//            mBluetoothAdapter.startLeScan(mLeScanCallback);
//        } else {
//            mScanning = false;
//            mBluetoothAdapter.stopLeScan(mLeScanCallback);
//        }
//
//    }
    @Override
    public boolean stopService(Intent name) {
//        writeLine("Automate service stop...");
//        stopSelf();
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
//                    Log.d(TAG + " onLeScan: ", "Name: " + device.getName() + "Address: " + device.getAddress() + "RSSI: " + rssi + "scanRecord" +scanRecord);
                    String Test = String.valueOf(device);
//                    CheckMac(Test) == true && CheckContains(ByteArrayToIntArray(scanRecord))==true
                    if(CheckMac(Test) == true && CheckContains(ByteArrayToIntArray(scanRecord))==true) {
                        Log.d("DEBUG", "device : " + Test);
                        Log.d("DEBUG", "RSSI : " + rssi);
                        //Log.d("DEBUG", "device : " + device);
                        printScanRecord(scanRecord);
//                        Log.e("Debug","Posture:"+ Posture(ByteArrayToIntArray(scanRecord)));
//                        Log.e("Debug","Heart:"+Heart(ByteArrayToIntArray(scanRecord)));
//                        Log.e("Debug","Steps:"+Steps(ByteArrayToIntArray(scanRecord)));
                        int hr = Heart(ByteArrayToIntArray(scanRecord));
                        int steps = Steps(ByteArrayToIntArray(scanRecord));
                        int posture = Posture(ByteArrayToIntArray(scanRecord));
                        Log.e("debug", "Posture: "+posture);
                        Log.e("debug", "HR: "+hr);
                        Log.e("debug", "Steps: "+steps);
                        Log.e("debug","RSSI: " + rssi);

                        Bundle bundle = new Bundle();
                        bundle.putInt("hr", hr);
                        bundle.putInt("step", steps);
                        bundle.putInt("posture", posture);
                        bundle.putInt("rssi",rssi);
                        Intent broadIntent = new Intent("bodyTagBroadcast");
                        broadIntent.putExtras(bundle);
                        sendBroadcast(broadIntent);
                    }
                    if (device != null && device.getName() != null) {
                        if (rssi > -90 && rssi < -1) {
//                            writeLine("Automate service BLE device in range: " + device.getName() + " " + rssi);
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
//            writeLine("Automate service connection state: "+ newState);
            if (newState == android.bluetooth.BluetoothProfile.STATE_CONNECTED){
//                writeLine("Automate service connection state: STATE_CONNECTED");
                Log.v("BLEService", "BLE Connected now discover services");
                Log.v("BLEService", "BLE Connected");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        writeLine("Automate service go for discover services");
                        gatt.discoverServices();
                    }
                }).start();
            }else if (newState == android.bluetooth.BluetoothProfile.STATE_DISCONNECTED){
//                writeLine("Automate service connection state: STATE_DISCONNECTED");
                Log.v("BLEService", "BLE Disconnected");
            }
        }

        @Override
        public void onServicesDiscovered(final BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            if (status == BluetoothGatt.GATT_SUCCESS) {
//                writeLine("Automate service discover service: GATT_SUCCESS");
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
    public static int complement(int x){switch (x){
        case -128: x = 128;break;
        case -127: x = 129;break;
        case -126: x = 130;break;
        case -125: x = 131;break;
        case -124: x = 132;break;
        case -123: x = 133;break;
        case -122: x = 134;break;
        case -121: x = 135;break;
        case -120: x = 136;break;
        case -119: x = 137;break;
        case -118: x = 138;break;
        case -117: x = 139;break;
        case -116: x = 140;break;
        case -115: x = 141;break;
        case -114: x = 142;break;
        case -113: x = 143;break;
        case -112: x = 144;break;
        case -111: x = 145;break;
        case -110: x = 146;break;
        case -109: x = 147;break;
        case -108: x = 148;break;
        case -107: x = 149;break;
        case -106: x = 150;break;
        case -105: x = 151;break;
        case -104: x = 152;break;
        case -103: x = 153;break;
        case -102: x = 154;break;
        case -101: x = 155;break;
        case -100: x = 156;break;
        case -99: x= 157;break;
        case -98: x= 158;break;
        case -97: x= 159;break;
        case -96: x= 160;break;
        case -95: x= 161;break;
        case -94: x= 162;break;
        case -93: x= 163;break;
        case -92: x= 164;break;
        case -91: x= 165;break;
        case -90: x= 166;break;
        case -89: x= 167;break;
        case -88: x= 168;break;
        case -87: x= 169;break;
        case -86: x= 170;break;
        case -85: x= 171;break;
        case -84: x= 172;break;
        case -83: x= 173;break;
        case -82: x= 174;break;
        case -81: x= 175;break;
        case -80: x= 176;break;
        case -79: x= 177;break;
        case -78: x= 178;break;
        case -77: x= 179;break;
        case -76: x= 180;break;
        case -75: x= 181;break;
        case -74: x= 182;break;
        case -73: x= 183;break;
        case -72: x= 184;break;
        case -71: x= 185;break;
        case -70: x= 186;break;
        case -69: x= 187;break;
        case -68: x= 188;break;
        case -67: x= 189;break;
        case -66: x= 190;break;
        case -65: x= 191;break;
        case -64: x= 192;break;
        case -63: x= 193;break;
        case -62: x= 194;break;
        case -61: x= 195;break;
        case -60: x= 196;break;
        case -59: x= 197;break;
        case -58: x= 198;break;
        case -57: x= 199;break;
        case -56: x= 200;break;
        case -55: x= 201;break;
        case -54: x= 202;break;
        case -53: x= 203;break;
        case -52: x= 204;break;
        case -51: x= 205;break;
        case -50: x= 206;break;
        case -49: x= 207;break;
        case -48: x= 208;break;
        case -47: x= 209;break;
        case -46: x= 210;break;
        case -45: x= 211;break;
        case -44: x= 212;break;
        case -43: x= 213;break;
        case -42: x= 214;break;
        case -41: x= 215;break;
        case -40: x= 216;break;
        case -39: x= 217;break;
        case -38: x= 218;break;
        case -37: x= 219;break;
        case -36: x= 220;break;
        case -35: x= 221;break;
        case -34: x= 222;break;
        case -33: x= 223;break;
        case -32: x= 224;break;
        case -31: x= 225;break;
        case -30: x= 226;break;
        case -29: x= 227;break;
        case -28: x= 228;break;
        case -27: x= 229;break;
        case -26: x= 230;break;
        case -25: x= 231;break;
        case -24: x= 232;break;
        case -23: x= 233;break;
        case -22: x= 234;break;
        case -21: x= 235;break;
        case -20: x= 236;break;
        case -19: x= 237;break;
        case -18: x= 238;break;
        case -17: x= 239;break;
        case -16: x= 240;break;
        case -15: x= 241;break;
        case -14: x= 242;break;
        case -13: x= 243;break;
        case -12: x= 244;break;
        case -11: x= 245;break;
        case -10: x= 246;break;
        case -9: x = 247;break;
        case -8: x = 248;break;
        case -7: x = 249;break;
        case -6: x = 250;break;
        case -5: x = 251;break;
        case -4: x = 252;break;
        case -3: x = 253;break;
        case -2: x = 254;break;
        case -1: x = 255;break;
        default:
            Log.e("steps complement:","not -128 to -1");break;
    }return x; }
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
            stepsA = complement(a);
        }else{Log.e("Steps :","wrong");}
        if( b>=0 && b<=127 ){
            stepsB = b;
        }else if( b>=-128 && b<=-1 ){
            stepsB = complement(b);
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