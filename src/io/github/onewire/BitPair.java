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

public class BitPair {

    private int bitA;
    private int bitB;

    public BitPair(int bitA) {
        this.bitA = bitA;
        this.bitB = bitA == 0 ? 1 : 0;
    }

    public int getBitA() {
        return bitA;
    }

    public void setBitA(int bitA) {
        this.bitA = bitA;
    }

    public int getBitB() {
        return bitB;
    }

    public void setBitB(int bitB) {
        this.bitB = bitB;
    }

    @Override
    public String toString() {
        return String.valueOf(bitA);
    }

}
