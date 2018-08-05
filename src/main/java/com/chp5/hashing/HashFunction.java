package com.chp5.hashing;

/**
 * @author dongyl
 * @date 10:31 8/5/18
 * @project data-structures
 */
public class HashFunction {
    public static int easyHash(String key,int tableSize){
        int hashVal = 0;
        for (int i = 0; i < key.length(); i++) {
            hashVal+=key.charAt(i);
        }
        return hashVal%tableSize;
    }
    public static int hash(String key,int tableSize){
        int hashVal = 0;
        for (int i = 0; i < key.length(); i++) {
            hashVal= 37*hashVal +key.charAt(i);
            hashVal %=tableSize;
            if(hashVal<0){
                hashVal+=tableSize;
            }
        }
        return hashVal;
    }
}
