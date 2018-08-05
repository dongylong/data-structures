package com.chp5.hashing;

import java.util.LinkedList;
import java.util.List;

/**
 * @author dongyl
 * @date 10:21 8/5/18
 * @project data-structures
 */
public class SeparateCHainingHashTable<T> {
    private static final int DEFAULT_TABLE_SIZE = 101;
    private List<T>[] theLists;
    private int currentSize;

    public SeparateCHainingHashTable() {
        //5-9
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateCHainingHashTable(int size) {
        //5-9
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<T>();
        }
    }

    public void insert(T t) {
        //5-10
        List<T> whichList = theLists[myHash(t)];
        if (!whichList.contains(t)) {
            whichList.add(t);
            if (++currentSize > theLists.length) {
                rehash();
            }
        }

    }

    public void remove(T t) {
        //5-10
        List<T> whichList = theLists[myHash(t)];
        if (whichList.contains(t)) {
            whichList.remove(t);
            currentSize--;
        }
    }

    public boolean contains(T t) {
        //5-10y
        List<T> whichList = theLists[myHash(t)];
        return whichList.contains(t);
    }

    public void makeEmpty() {
        //5-9
        for (int i = 0; i < theLists.length; i++) {
            theLists[i].clear();
            currentSize = 0;
        }
    }

    public void rehash() {
        //5-22
        List<T>[] oldLists = theLists;
        theLists = new List[nextPrime(2 * oldLists.length)];
        for (int i = 0; i < oldLists.length; i++) {
            theLists[i] = new LinkedList<T>();
        }
        //copy table over
        currentSize = 0;
        for (int i = 0; i < oldLists.length; i++) {
            for (T item : oldLists[i]) {
                insert(item);
            }
        }

    }

    public int myHash(T t) {
        //5-7
        int hashVal = t.hashCode();
        hashVal %= theLists.length;
        if (hashVal < 0) {
            hashVal += theLists.length;
        }
        return hashVal;
    }

    public static int nextPrime(int n) {
        //online code
        return n;
    }

    public static boolean isPrime(int n) {
        //online code
        return false;
    }

}
