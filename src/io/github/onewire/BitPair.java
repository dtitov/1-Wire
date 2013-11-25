package io.github.onewire;

/**
 * User: Dmytro
 * Date: 11.11.13
 * Time: 20:53
 */
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
