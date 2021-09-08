package com.simplechain;

import java.util.Date;
public class Block {

    public String hash;
    public String prevHash;
    private String data;
    private long timeStamp;
    private int nonce;

    public Block (String date, String prevHash) {
        this.data = data;
        this.prevHash = prevHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calculatedHash = StringUtils.applySha256(
        prevHash +
                Long.toString(timeStamp) +
                data);
        return calculatedHash;
    }

    public void mine(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0','0');
        while (!hash.substring(0,difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Mined! : " + hash);
    }

}
