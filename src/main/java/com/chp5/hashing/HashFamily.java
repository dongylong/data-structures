package com.chp5.hashing;

/**
 * @author dongyl
 * @date 12:40 8/5/18
 * @project data-structures
 */
public interface HashFamily<T> {
    int hash(T t,int which);
    int getNumberOfFunctions();
    void generateNewFunctions();
}
