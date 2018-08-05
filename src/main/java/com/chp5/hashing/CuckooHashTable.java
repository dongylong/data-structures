package com.chp5.hashing;

import java.util.Random;

/**
 * @author dongyl
 * @date 12:41 8/5/18
 * @project data-structures
 */
public class CuckooHashTable<T> {
    private static final double MAX_LOAD = 0.4;
    private static final int ALLOWED_REHASHES = 1;
    private static final int DEFAULT_TABLE_SIZE = 101;
    private final HashFamily<? super T> hashFunctions;
    private final int numHashFunctions;
    private T[] array;
    private int currentSize;
    private int rehashes = 0;
    private Random r = new Random();

    /**
     * Consruct the hash table.
     *
     * @param hf the hash family
     */
    public CuckooHashTable(HashFamily<? super T> hf) {
        //5.38
        this(hf, DEFAULT_TABLE_SIZE);
    }

    public CuckooHashTable(HashFamily<? super T> hf, int size) {
        //5.38
        allocateArray(nextPrime(size));
        doClear();
        hashFunctions = hf;
        numHashFunctions = hf.getNumberOfFunctions();
    }

    private int nextPrime(int size) {
        return 0;
    }

    public void makeEmpty() {
        doClear();
    }

    /**
     * find an item in the hash table.
     *
     * @param t the item to search for.
     * @return true if item is found
     */
    public boolean contains(T t) {
        //5.40
        return findPos(t) != -1;
    }

    /**
     * Compute hte hash code for x using specified hash function
     *
     * @param t     the item
     * @param which the hash funtion
     * @return the hash code
     */
    public int myHash(T t, int which) {
        //5.39
        int hashVal = hashFunctions.hash(t, which);
        hashVal %= array.length;
        if (hashVal < 0) {
            hashVal += array.length;
        }
        return hashVal;
    }

    /**
     * Method that searches all hash function places.
     *
     * @param t the item to search for.
     * @return the position where the search terminates,or -1 if not found.
     */
    public int findPos(T t) {
        //5.39
        for (int i = 0; i < numHashFunctions; i++) {
            int pos = myHash(t, i);
            if (array[pos] != null && array[pos].equals(t)) {
                return pos;
            }
        }
        return -1;
    }

    /**
     * remove from hash table
     *
     * @param t the item to remove
     * @return true if item was found and removed
     */
    public boolean remove(T t) {
        //5.41
        int pos = findPos(t);
        if (pos != -1) {
            array[pos] = null;
            currentSize--;
        }
        return pos != -1;
    }

    /**
     * insert into the hash table .if the item is already present,return false
     *
     * @param t the item to insert
     * @return
     */
    public boolean insert(T t) {
        //5.42
        if (contains(t)) {
            return false;
        }
        if (currentSize >= array.length * MAX_LOAD) {
            expand();
        }
        return insertHelper1(t);
    }

    private boolean insertHelper1(T t) {
        final int COUNT_LIMIT = 100;
        while (true) {
            int lastPos = -1;
            int pos;
            for (int count = 0; count < COUNT_LIMIT; count++) {
                for (int i = 0; i < numHashFunctions; i++) {
                    pos = myHash(t, i);
                    if (array[pos] == null) {
                        array[pos] = t;
                        currentSize++;
                        return true;
                    }
                }
                //none of the spots are available.Evict out a random one
                int i = 0;
                do {
                    pos = myHash(t, r.nextInt(numHashFunctions));
                } while (pos == lastPos && i++ < 5);
                T tmp = array[lastPos = pos];
                array[pos] = t;
                t = tmp;
            }
            if (++rehashes > ALLOWED_REHASHES) {
                expand();
                rehashes = 0;
            } else {
                rehash();
            }
        }
    }

    private void expand() {
        //5.44
        rehash((int) (array.length/MAX_LOAD));
    }

    private void rehash(int newLength) {
        T[] oldArray = array;
        allocateArray((nextPrime(newLength)));
        currentSize=0;
        for(T str:oldArray){
            insert(str);
        }
    }

    private void rehash() {
        //5.44
        hashFunctions.generateNewFunctions();
        rehash(array.length);
    }

    private void doClear() {
        //5.38
        currentSize = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    private void allocateArray(int arraySize) {
        array = (T[]) new Object[arraySize];
    }

}
