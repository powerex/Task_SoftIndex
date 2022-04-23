package com.softindex.task.model;

import com.softindex.task.interfaces.Map;

import java.util.NoSuchElementException;

/**
 * @author <a href="https://github.com/powerex">AZbest</a>
 * @see Map
 */
public class HashMap implements Map {

    private final float THRESHOLD = 0.6f;
    private final int PROBE_BEFORE_RESIZE = 6;
    private final int STEP = 1;
    private int power2 = 3;
    private int tableLength = prevPrime((int) (1L << power2));

    private int size = 0;

    Node[] table;

    public HashMap() {
        table = new HashMap.Node[tableLength];
    }

    /**Insert value in map mapped by key
     *  @param key   key of value for search
     *  @param value  value inserted into map
     * */
    @Override
    public void put(int key, long value) {
        int hash = getHash(key);
        if (table[hash] == null) {
            table[hash] = new Node(key, value);
        } else {
            int probe = 0;
            Node current = table[hash];
            do {
                ++probe;
                if (current.key == key) {
                    current.value = value; // replace new value on old key
                    return;                // size not changed
                }
                hash += STEP;
                if (hash >= tableLength)
                    hash %= tableLength;
                current = table[hash];
            } while (probe < PROBE_BEFORE_RESIZE && current != null);
            if (probe < PROBE_BEFORE_RESIZE)
                table[hash] = new Node(key, value); //collision different value & same hash
            else {
                resize();
                put(key, value);
                return;
            }
        }
        ++size;
        if (size > tableLength * THRESHOLD)
            resize();
    }

    /**
     *
     * @param key mapped key for saving data
     * @return long value if key is present else throw exception
     * @see java.util.NoSuchElementException
     */
    @Override
    public long get(int key) {
        int hash = getHash(key);
        int probe = 1;
        while (table[hash] != null && probe < tableLength) {  // TODO improve log(N) complexity
            if (table[hash].key == key)
                return table[hash].value;
            hash += STEP;
            if (hash >= tableLength)
                hash %= tableLength;
            ++probe;
        }
        throw new NoSuchElementException("No element mapped with key: " + key);
    }

    @Override
    public int size() {
        return size;
    }

    private int prevPrime(int input) {
        int counter;
        while (true) {
            int l = (int) Math.sqrt(input);
            counter = 0;
            for (int i = 2; i <= l; i++) {
                if (input % i == 0) counter++;
            }
            if (counter == 0)
                return input;
            else {
                input--;
            }
        }
    }

    private int getHash(int key) {
        return key % tableLength;
    }

    private void resize() {
        Node[] oldTable = table;
        ++power2;
        tableLength = (power2 > 30) ? Integer.MAX_VALUE : prevPrime((int) (1L << power2));
        table = new Node[tableLength];
        size = 0;
        for (Node node: oldTable) {
            if (node != null)
                put(node.key, node.value);
        }
    }

    static final class Node {
        private int key;
        private long value;

        public Node(int key, long value) {
            this.key = key;
            this.value = value;
        }
    }
}
