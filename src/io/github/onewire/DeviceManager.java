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

import io.github.onewire.impl.Device;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeviceManager {

    public int lastDeviation;
    public int newDeviation;
    public int index;
    public int[] bitPattern;
    public List<int[]> devices;
    public Bus bus;

    public void search(String inputFileName) throws IOException {
        List<IDevice> inputDevices = new ArrayList<>();
        int addressLength = importDevicesFromFile(inputFileName, inputDevices);

        devices = new ArrayList<>();
        bitPattern = new int[addressLength];


        bus = new Bus(inputDevices);

        index = 0;
        lastDeviation = 0;

        do {
            System.out.println();
            System.out.println("*** Step " + (index + 1) + "***");
            bus.reset();

            newDeviation = 0;

            for (int i = 1; i < addressLength + 1; i++) {
                if (!bus.same(i)) {
                    bitPattern[i - 1] = bus.readBit(i);
                } else {
                    if (i == lastDeviation) {
                        bitPattern[i - 1] = 1;
                    } else {
                        if (i > lastDeviation) {
                            bitPattern[i - 1] = 0;
                            newDeviation = i;
                        } else {
                            if (bitPattern[i - 1] == 0) {
                                newDeviation = i;
                            }
                        }
                    }
                }
                bus.sendBit(bitPattern[i - 1], i);
            }
            lastDeviation = newDeviation;
            devices.add(bitPattern);
            System.out.println(Arrays.toString(bitPattern));
            index++;
        } while (lastDeviation != 0);
    }

    public int importDevicesFromFile(String fileName, List<IDevice> devices) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(fileName));

        while (in.ready()) {
            String address = in.readLine();
            IDevice device = new Device(address);
            devices.add(device);
            System.out.println(device);
        }

        in.close();

        return devices.get(0).getAddress().size();
    }

}
