package com.softindex.task;

import com.softindex.task.interfaces.Map;
import com.softindex.task.model.HashMap;

public class Application {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put(1, 1L);
        map.put(8, 8L);
        map.put(2, 2L);
        System.out.println(map.size());
        System.out.println(map.get(2));
    }
}
