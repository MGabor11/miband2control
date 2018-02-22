package com.apppoweron.miband2control.service.gattattributes;

import java.util.UUID;

/**
 * This object contains the data for connection
 */
public class MiBandServiceObject {

    private MiBandServices serviceType;
    private UUID service;
    private UUID characteristic;
    private UUID descriptor;

    private MiBandServiceObject(MiBandServiceObjectBuilder builder) {
        this.serviceType = builder.serviceType;
        this.service = builder.service;
        this.characteristic = builder.characteristic;
        this.descriptor = builder.descriptor;
    }

    public MiBandServices getServiceType() {
        return serviceType;
    }

    public UUID getService() {
        return service;
    }

    public UUID getCharacteristic() {
        return characteristic;
    }

    public UUID getDescriptor() {
        return descriptor;
    }

    public static class MiBandServiceObjectBuilder {
        private MiBandServices serviceType;
        private UUID service;
        private UUID characteristic;
        private UUID descriptor;


        public MiBandServiceObjectBuilder(MiBandServices serviceType, String service, String characteristic) {
            this.serviceType = serviceType;
            this.service = UUID.fromString(service);
            this.characteristic = UUID.fromString(characteristic);
        }

        public MiBandServiceObjectBuilder descriptor(String descriptor) {
            this.descriptor = UUID.fromString(descriptor);
            return this;
        }

        public MiBandServiceObject build() {
            return new MiBandServiceObject(this);
        }
    }

}
