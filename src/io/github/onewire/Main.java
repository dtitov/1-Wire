package io.github.onewire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: Dmytro
 * Date: 11.11.13
 * Time: 21:07
 */
public class Main {

    public static int lastDeviation;
    public static int newDeviation;
    public static int index;
    public static int[] bitPattern;
    public static List<int[]> devices;
    public static Bus bus;

    public static void main(String[] args) throws IOException {
        List<Device> inputDevices = new ArrayList<Device>();
        int addressLength = importDevicesFromFile("devices.txt", inputDevices);

        devices = new ArrayList<int[]>();
        bitPattern = new int[addressLength];


        bus = new Bus(inputDevices);

        index = 0;
        lastDeviation = 0;

        do {
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

    public static int importDevicesFromFile(String fileName, List<Device> devices) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(fileName));

        while (in.ready()) {
            String address = in.readLine();
            Device device = new Device(address);
            devices.add(device);
            System.out.println(device);
        }

        in.close();

        return devices.get(0).getAddress().size();
    }

}