package com.apppoweron.miband2control.service.gattattributes;

import java.util.HashMap;

public class MiBandGattAttributes {

    private HashMap<MiBandServices, String> services = new HashMap<>();
    private HashMap<MiBandServices, String> characteristics = new HashMap<>();
    private HashMap<MiBandServices, String> descriptors = new HashMap<>();

    private static final String HEART_RATE_MEASUREMENT = "00002a37-0000-1000-8000-00805f9b34fb";
    public static final String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";


    public MiBandGattAttributes() {
        fillAttributeMap();
    }

    private void fillAttributeMap() {
        // MiBand2 Services.
        services.put(MiBandServices.BATTERY_LEVEL_CHECK, "0000fee0-0000-1000-8000-00805f9b34fb");
        services.put(MiBandServices.CUSTOM_NOTIFICATION_SENDING, "00001811-0000-1000-8000-00805f9b34fb");

        // MiBand2 Characteristics.
        characteristics.put(MiBandServices.BATTERY_LEVEL_CHECK, "00000006-0000-3512-2118-0009af100700");
        characteristics.put(MiBandServices.CUSTOM_NOTIFICATION_SENDING, "00002A46-0000-1000-8000-00805f9b34fb");

        // MiBand2 Descriptors
    }

    public String getBLEAttribute(MiBandServices serviceType, BLEAttributeTypes attributeType) {
        switch (attributeType) {
            case SERVICE:
                return services.get(serviceType);
            case CHARACTERISTIC:
                return characteristics.get(serviceType);
            default:
                return descriptors.get(serviceType);
        }
    }

}
