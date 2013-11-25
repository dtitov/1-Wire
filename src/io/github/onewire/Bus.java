package io.github.onewire;

import java.util.List;

/**
   Copyright 2014 Dmytro Titov

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
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
