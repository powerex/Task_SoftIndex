package com.softindex.task.interfaces;

/**
 * Map interface with several common methods
 */
public interface Map {
    void put(int key, long value);
    long get(int key);
    int size();
}
