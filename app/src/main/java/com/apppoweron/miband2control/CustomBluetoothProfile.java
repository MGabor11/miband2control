package com.apppoweron.miband2control;

import java.util.UUID;


public class CustomBluetoothProfile {

    public static class Basic {
        public static UUID service = UUID.fromString("0000fee0-0000-1000-8000-00805f9b34fb");
        public static UUID batteryCharacteristic = UUID.fromString("00000006-0000-3512-2118-0009af100700");
    }

    public static class AlertNotification {
        public static UUID service = UUID.fromString("00001811-0000-1000-8000-00805f9b34fb");
        public static UUID alertCharacteristic = UUID.fromString("00002A46-0000-1000-8000-00805f9b34fb");
    }

}
