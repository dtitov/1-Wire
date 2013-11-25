package io.github.onewire;

import java.util.List;

/**
 * User: Dmytro
 * Date: 11.11.13
 * Time: 21:04
 */
public class Bus {

    List<Device> devices;

    public Bus(List<Device> devices) {
        this.devices = devices;
    }

    public Device getDevice(int index) {
        return devices.get(index);
    }

    public void sendBit(int bit, int currentBit) {
        for (Device device : devices) {
            if (device.isOnline() && device.readBitA(currentBit) != bit) {
                device.exclude();
            }
        }
    }

    public int readBit(int index) {
        for (Device device : devices) {
            if (device.isOnline()) {
                return device.readBitA(index);
            }
        }
        return 1;
    }

    public boolean same(int index) {
        int bitA = 1;
        int bitB = 1;
        for (Device device : devices) {
            if (device.isOnline()) {
                if (device.readBitA(index) == 0) {
                    bitA = 0;
                }
                if (device.readBitB(index) == 0) {
                    bitB = 0;
                }
            }
        }
        return bitA == bitB;
    }

    public void reset() {
        for (Device device : devices) {
            device.reset();
        }
    }

}
