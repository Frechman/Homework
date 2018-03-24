package ru.sberbank.homework.gavrilov.generics;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<E> implements CountMap<E> {
    private Map<E, Integer> countMap = new HashMap();

    @Override
    public void add(E e) {
        if (countMap.containsKey(e)) {
            countMap.put(e, countMap.get(e) + 1);
        } else {
            countMap.put(e, 1);
        }
    }

    @Override
    public int getCount(E e) {
        return countMap.getOrDefault(e, 0);
    }

    @Override
    public int remove(E e) {
        return countMap.remove(e);
    }

    @Override
    public int size() {
        return countMap.size();
    }

    @Override
    public void addAll(CountMap<E> source) {
        for (E e : source.toMap().keySet()) {
            if (countMap.replace(e, countMap.get(e) + source.getCount(e)) == null) {
                add(e);
            }
        }
    }

    @Override
    public Map<E, Integer> toMap() {
        return countMap;
    }

    @Override
    public void toMap(Map<E, Integer> destination) {
        destination.putAll(countMap);
    }
}