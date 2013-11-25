package io.github.onewire;

import java.util.ArrayList;
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
