package com.chp5.hashing;

/**
 * @author dongyl
 * @date 10:38 8/5/18
 * @project data-structures
 * 5-8
 */
public class Employee {
    private String name;
    private String salary;
    private int seniority;
    @Override
    public boolean equals(Object rth){
        return rth instanceof Employee && name.equals(((Employee) rth).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
    // Additional fields and methods
}
