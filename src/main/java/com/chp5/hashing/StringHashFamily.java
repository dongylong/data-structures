package com.chp5.hashing;

import java.util.Random;

/**
 * @author dongyl
 * @date 12:40 8/5/18
 * @project data-structures
 */
public class StringHashFamily<T> implements HashFamily<String>{
    private final int [ ] MULTIPLIERS;
    private final Random r = new Random();

    public StringHashFamily(int d) {
        MULTIPLIERS = new int[d];
        generateNewFunctions();
    }

    public int hash(String s, int which) {
        final int multiplier = MULTIPLIERS[which];
        int hashVal =0;
        for (int i = 0; i < s.length(); i++) {
            hashVal=multiplier*hashVal+s.charAt(i);
        }
        return hashVal;
    }

    public int getNumberOfFunctions() {
        return MULTIPLIERS.length;
    }

    public void generateNewFunctions() {
        for (int i = 0; i < MULTIPLIERS.length; i++) {
            MULTIPLIERS[i] =r.nextInt();
        }
    }
}
