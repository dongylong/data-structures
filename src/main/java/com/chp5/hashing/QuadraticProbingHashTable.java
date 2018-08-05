package com.chp5.hashing;

/**
 * @author dongyl
 * @date 11:16 8/5/18
 * @project data-structures
 */
public class QuadraticProbingHashTable<T> {
    private static final int DEFAULT_TABLE_SIZE = 11;
    /**
     * the array of element
     */
    private HashEntry<T>[] array;
    /**
     * the number of occupied cells
     */
    private int currentSize;

    private static class HashEntry<T> {
        /**
         * the element
         */
        public T element;
        /**
         * false if marked deleted
         */
        public boolean isActive;

        public HashEntry(T element) {
            this(element, true);
        }

        public HashEntry(T element, boolean isActive) {
            this.element = element;
            this.isActive = isActive;
        }
    }

    /**
     * Construct the hash table
     */
    public QuadraticProbingHashTable() {
        //5.15
        this(DEFAULT_TABLE_SIZE);
    }

    /**
     * Construct the hash table
     *
     * @param size the approximate initial size
     */
    public QuadraticProbingHashTable(int size) {
        //5.15
        allocateArray(size);
        makeEmpty();
    }

    /**
     * make the hash table logically empty
     */
    public void makeEmpty() {
        //5.15
        currentSize = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    /**
     * Find an item in the hash table
     *
     * @param t the item to search for
     * @return the matching item
     */
    public boolean contains(T t) {
        //5.16
        int currentPos = findPos(t);
        return isActive(currentPos);
    }

    /**
     * Insert into the hash table. If the item is already present ,do nothing
     *
     * @param t the item to insert
     */
    public void insert(T t) {
        //5.17
        /**
         * Insert x as active
         */
        int currentPos = findPos(t);
        if (isActive(currentPos)) {
            return;
        }
        array[currentPos] = new HashEntry<T>(t, true);
        /**
         * rehash 5.5
         */
        if (currentSize > array.length / 2) {
            rehash();
        }
    }

    /**
     * remove from the hash table
     *
     * @param t the item to remove
     */
    public void remove(T t) {
        //5.17
        int currentPos = findPos(t);
        if (isActive(currentPos)) {
            array[currentPos].isActive = false;
        }
    }

    /**
     * Internal method to allocate array
     *
     * @param arraySize the size of the array
     */
    private void allocateArray(int arraySize) {
        //5.15
        array = new HashEntry[nextPrime(arraySize)];
    }

    /**
     * Return true if currentPos exitst and is active
     *
     * @param currentPos
     * @return
     */
    private boolean isActive(int currentPos) {
        //5.16
        return array[currentPos] != null && array[currentPos].isActive;
    }

    /**
     * Method that performs quadratic probing resolution in half-empty table.
     *
     * @param t the item to search for
     * @return the position where the search terminates
     */
    private int findPos(T t) {
        //5.16
        int offset = 1;
        int currentPos = myhash(t);
        while (array[currentPos] != null && !array[currentPos].element.equals(t)) {
            /**
             * compute ith probe
             */
            currentPos += offset;
            offset += 2;
            if (currentPos >= array.length) {
                currentPos -= array.length;
            }
        }
        return currentPos;
    }

    /**
     * Rehashing for quadratic probing hash table.
     */
    private void rehash() {
        //5.22
        HashEntry<T>[] oldArray = array;
        //create a new double-sized empty table
        allocateArray(nextPrime(2 * oldArray.length));
        currentSize=0;
        for (int i = 0; i < oldArray.length; i++) {
            if(oldArray[i]!=null&&oldArray[i].isActive){
                insert(oldArray[i].element);
            }
        }
    }

    private int myhash(T t) {
        //online code
        return 0;
    }

    private static int nextPrime(int n) {
        //online code
        return n;
    }

    private static boolean isPrime(int n) {
        //online code
        return false;
    }

}
