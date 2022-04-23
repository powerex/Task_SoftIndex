package com.softindex.task.model;

import com.softindex.task.interfaces.Map;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class HashMapTest {
    Map map;

    @Before
    public void setUp() throws Exception {
        map = new HashMap();

        map.put(1, 1L);
        map.put(8, 8L);
        map.put(2, 2L);
        map.put(3, 3L);
        map.put(4, 4L);
        map.put(5, 5L);
    }

    @Test
    public void get() {

        assertEquals("Expected 1: ", 1L, map.get(1));
        assertEquals("Expected 2: ", 2L, map.get(2));
        assertEquals("Expected 2: ", 3L, map.get(3));
        assertEquals("Expected 2: ", 4L, map.get(4));
        assertEquals("Expected 2: ", 5L, map.get(5));

        map.put(1, 10L);
        assertEquals("Expected 1: ", 10L, map.get(1));

    }

    @Test
    public void size() {
        assertEquals("Expected 6: ", 6, map.size());
        map.put(4, 40L);
        assertEquals("Expected 6: ", 6, map.size());
        map.put(4, 400L);
        assertEquals("Expected 6: ", 6, map.size());
        map.put(90, 90L);
        assertEquals("Expected 7: ", 7, map.size());
    }

    @Test
    public void multipleInsert() {
        Random random = new Random();
        for (int i=0; i<1e7; ++i) {
            map.put(random.nextInt(Integer.MAX_VALUE-1), random.nextLong());
        }
    }

    @Test
    public void multipleGet() {
        Map testMap = new HashMap();
        Random random = new Random();
        for (int i=0; i<1e7; ++i) {
            testMap.put(i, random.nextLong());
        }

        for (int i=0; i<1e7; ++i) {
            testMap.get(i);
        }
    }

}