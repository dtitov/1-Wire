/**
 * Copyright 2015 Dmytro Titov
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.onewire;

import java.util.List;

public class Bus {

    List<IDevice> devices;

    public Bus(List<IDevice> devices) {
        this.devices = devices;
    }

    public IDevice getDevice(int index) {
        return devices.get(index);
    }

    public void sendBit(int bit, int currentBit) {
        for (IDevice device : devices) {
            if (device.isOnline() && device.readBitA(currentBit) != bit) {
                device.exclude();
            }
        }
    }

    public int readBit(int index) {
        for (IDevice device : devices) {
            if (device.isOnline()) {
                return device.readBitA(index);
            }
        }
        return 1;
    }

    public boolean same(int index) {
        int bitA = 1;
        int bitB = 1;
        for (IDevice device : devices) {
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
        for (IDevice device : devices) {
            device.reset();
        }
    }

}
