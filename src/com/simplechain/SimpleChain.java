package com.simplechain;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class SimpleChain {
    public static int difficulty = 1;
    public static ArrayList<Block> blocchain = new ArrayList<Block>();

    public static boolean isValidChain() {
        Block currBlock;
        Block prevBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0','0');

        for (int i = 1; i < blocchain.size(); i++) {
            currBlock = blocchain.get(i);
            prevBlock = blocchain.get(i-1);
            if(!currBlock.hash.equals(currBlock.calculateHash())) {
                System.out.println("Current hashes are not equal");
                return false;
            }
            if(!prevBlock.hash.equals(prevBlock.calculateHash())) {
                System.out.println("Previous hashes are not equal");
                return false;
            }
            if(!currBlock.hash.substring(0,difficulty).equals(hashTarget)) {
                System.out.println("The block isn't mined yet");
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        blocchain.add(new Block("First","0"));
        System.out.println("Mining block 1");
        blocchain.get(0).mine(difficulty);
        blocchain.add(new Block("Second",blocchain.get(blocchain.size()-1).hash));
        System.out.println("Mining block 2");
        blocchain.get(1).mine(difficulty);
        blocchain.add(new Block("Third",blocchain.get(blocchain.size()-1).hash));
        System.out.println("Mining block 3");
        blocchain.get(2).mine(difficulty);
        System.out.println("Blockchain is valid : " + isValidChain());
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blocchain);
        System.out.println("\nThe block chain :-");
        System.out.println(blockchainJson);


    }

}
