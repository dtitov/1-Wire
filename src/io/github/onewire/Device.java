package io.github.onewire;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Dmytro
 * Date: 11.11.13
 * Time: 20:56
 */
public class Device {

    private List<BitPair> address;
    private boolean status;

    public Device(String stringAddress) {
        address = new ArrayList<BitPair>();
        char[] charAddress = new char[stringAddress.length()];
        stringAddress.getChars(0, stringAddress.length(), charAddress, 0);
        for (char bit : charAddress) {
            address.add(bit == '0' ? new BitPair(0) : new BitPair(1));
        }
    }

    public List<BitPair> getAddress() {
        return address;
    }

    public int readBitA(int index) {
        return address.get(index - 1).getBitA();
    }

    public int readBitB(int index) {
        return address.get(index - 1).getBitB();
    }

    public boolean isOnline() {
        return status;
    }

    public void reset() {
        status = true;
    }

    public void exclude() {
        System.out.println("Switching off device: " + toString());
        status = false;
    }

    @Override
    public String toString() {
        String stringAddress = "";
        for (BitPair bit : address) {
            stringAddress += bit.toString();
        }
        return stringAddress;
    }

}
