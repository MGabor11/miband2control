package com.apppoweron.miband2control.service;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Set;

public class BTCommunicationService extends Service {

    private static final String TAG = "BTCommunicationService";

    private static final int STATE_DISCONNECTED = 0;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_CONNECTED = 2;

    public final static String ACTION_GATT_CONNECTED =
            "com.example.bluetooth.le.ACTION_GATT_CONNECTED";
    public final static String ACTION_GATT_DISCONNECTED =
            "com.example.bluetooth.le.ACTION_GATT_DISCONNECTED";
    public final static String ACTION_GATT_SERVICES_DISCOVERED =
            "com.example.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED";
    public final static String ACTION_DATA_AVAILABLE =
            "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
    public final static String EXTRA_DATA =
            "com.example.bluetooth.le.EXTRA_DATA";


    private final IBinder mBinder = new LocalBinder();

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothGatt mBluetoothGatt;
    private BluetoothDevice mBluetoothDevice;

    public class LocalBinder extends Binder {
        BTCommunicationService getService() {
            return BTCommunicationService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // After using a given device, you should make sure that BluetoothGatt.close() is called
        // such that resources are cleaned up properly.  In this particular example, close() is
        // invoked when the UI is disconnected from the Service.
        close();
        return super.onUnbind(intent);
    }

    boolean isListeningHeartRate = false;

    //First it should check the bounded devices
    boolean getBoundedDevice() {
        Set<BluetoothDevice> boundedDevice = mBluetoothAdapter.getBondedDevices();
        for (BluetoothDevice bd : boundedDevice) {
            if (bd.getName().contains("MI Band 2")) {
                //If it found by it's name it should connect to this device
                startConnectingToDevice(bd.getAddress());
                return true;
            }
        }
        return false;
    }

    private boolean startConnectingToDevice(String address) {
        mBluetoothDevice = mBluetoothAdapter.getRemoteDevice(address);
        if (mBluetoothDevice != null) {
            mBluetoothGatt = mBluetoothDevice.connectGatt(this, true, mGattCallback);
            return mBluetoothGatt != null;
        }
        return false;
    }

    void stateConnected() {
        mBluetoothGatt.discoverServices();
    }

    void stateDisconnected() {
        mBluetoothGatt.disconnect();
    }

    public void getBatteryStatus() {

       /* BluetoothGattCharacteristic bchar = mBluetoothGatt.getService(CustomBluetoothProfile.Basic.service)
                .getCharacteristic(CustomBluetoothProfile.Basic.batteryCharacteristic);
        if (!mBluetoothGatt.readCharacteristic(bchar)) {

        }*/

    }

    void sendMessage() {
        /*String text = "Test message";
        Log.i("Mi Band 2", "sendNewAlert(): " + text);
        BluetoothGattCharacteristic characteristic = mBluetoothGatt.getService(CustomBluetoothProfile.AlertNotification.service)
                .getCharacteristic(CustomBluetoothProfile.AlertNotification.alertCharacteristic);*/
        // Message format here: https://www.bluetooth.com/specifications/gatt/viewer?attributeXmlFile=org.bluetooth.characteristic.new_alert.xml
        // Message (characteristic value) format:
        // first byte
        //   0x01 - as email
        //   0x03 - as phone
        //   0x05 - as message
        // second byte
        //   count of messages (random number - not showing on display for mi band 2)
        // rest bytes
        //   content of message
        //DeviceHelper deviceHelper = getDeviceHelper(mBluetoothLeService.getDevice());
        //characteristic.setValue(new byte[]{0x01, 0x01});
        /*characteristic.setValue(ArrayUtils.addAll(new byte[]{0x05, 0x01}, Junidecode.unidecode(text).getBytes()));
        mBluetoothGatt.writeCharacteristic(characteristic);*/
    }

    void startVibrate() {
        /*BluetoothGattCharacteristic bchar = bluetoothGatt.getService(CustomBluetoothProfile.AlertNotification.service)
                .getCharacteristic(CustomBluetoothProfile.AlertNotification.alertCharacteristic);
        bchar.setValue(new byte[]{2});
        if (!bluetoothGatt.writeCharacteristic(bchar)) {
            Toast.makeText(this, "Failed start vibrate", Toast.LENGTH_SHORT).show();
        }*/

       /* mBluetoothLeService.getCharacteristic(
                BluetoothLeProfiles.AlertNotification.service,
                BluetoothLeProfiles.AlertNotification.newAlert);*/

    }

    void stopVibrate() {
       /* BluetoothGattCharacteristic bchar = mBluetoothGatt.getService(CustomBluetoothProfile.AlertNotification.service)
                .getCharacteristic(CustomBluetoothProfile.AlertNotification.alertCharacteristic);
        bchar.setValue(new byte[]{0});
        if (!mBluetoothGatt.writeCharacteristic(bchar)) {
            //Toast.makeText(getActivity(), "Failed stop vibrate", Toast.LENGTH_SHORT).show();
        }*/
    }

    final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);

            if (newState == BluetoothProfile.STATE_CONNECTED) {
                stateConnected();
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                stateDisconnected();
            }

        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);

        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
            byte[] data = characteristic.getValue();

        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
            Log.v("test", "onCharacteristicWrite");
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
            byte[] data = characteristic.getValue();
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorRead(gatt, descriptor, status);

        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorWrite(gatt, descriptor, status);

        }

        @Override
        public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
            super.onReliableWriteCompleted(gatt, status);

        }

        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            super.onReadRemoteRssi(gatt, rssi, status);

        }

        @Override
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            super.onMtuChanged(gatt, mtu, status);

        }

    };

    /**
     * After using a given BLE device, the app must call this method to ensure resources are
     * released properly.
     */
    private void close() {
        if (mBluetoothGatt == null) {
            return;
        }
        mBluetoothGatt.close();
        mBluetoothGatt = null;
    }

    public boolean initialize() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Log.e(TAG, "Unable to obtain a BluetoothAdapter.");
            return false;
        }

        return true;
    }

    /**
     * Disconnects an existing connection or cancel a pending connection. The disconnection result
     * is reported asynchronously through the
     * {@code BluetoothGattCallback#onConnectionStateChange(android.bluetooth.BluetoothGatt, int, int)}
     * callback.
     */
    public void disconnect() {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.w(TAG, "BluetoothAdapter not initialized");
            return;
        }
        mBluetoothGatt.disconnect();
    }


}
