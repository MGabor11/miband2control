package com.apppoweron.miband2control.service.gattattributes;

public class GattAttributeProvider {

    private MiBandGattAttributes miBandGattAttributes;

    public GattAttributeProvider() {
        this.miBandGattAttributes = new MiBandGattAttributes();
    }

    public MiBandServiceObject getServiceObj(MiBandServices service) {
        MiBandServiceObject.MiBandServiceObjectBuilder builder = new MiBandServiceObject.MiBandServiceObjectBuilder(service,
                getService(service),
                getCharacteristic(service));

        String descriptor = getDescriptor(service);
        if (descriptor != null) {
            builder.descriptor(descriptor);
        }
        return builder.build();
    }

    private String getService(MiBandServices service) {
        return getAttributeValueOfService(service, BLEAttributeTypes.SERVICE);
    }

    private String getCharacteristic(MiBandServices service) {
        return getAttributeValueOfService(service, BLEAttributeTypes.CHARACTERISTIC);
    }

    private String getDescriptor(MiBandServices service) {
        return getAttributeValueOfService(service, BLEAttributeTypes.DESCRIPTOR);
    }

    private String getAttributeValueOfService(MiBandServices service, BLEAttributeTypes attributeType) {
        return miBandGattAttributes.getBLEAttribute(service, attributeType);
    }

}
