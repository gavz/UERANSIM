/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BiMap<K, V> {
    private final Map<K, V> keys;
    private final Map<V, K> values;

    public BiMap() {
        this.keys = new HashMap<>();
        this.values = new HashMap<>();
    }

    public boolean containsKey(K key) {
        return keys.containsKey(key);
    }

    public boolean containsValue(V value) {
        return values.containsKey(value);
    }

    public void put(K key, V value) {
        keys.put(key, value);
        values.put(value, key);
    }

    public K getKey(V value) {
        return values.get(value);
    }

    public V getValue(K key) {
        return keys.get(key);
    }

    public int size() {
        return keys.size();
    }

    public Set<K> keySet() {
        return keys.keySet();
    }

    public Set<V> valueSet() {
        return values.keySet();
    }
}
