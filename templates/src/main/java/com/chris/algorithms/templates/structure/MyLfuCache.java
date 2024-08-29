package com.chris.algorithms.templates.structure;


import java.util.HashMap;
import java.util.LinkedHashSet;

/**
LFU算法淘汰访问频次最低的数据，若访问频次最低的数据有多条，需要淘汰最旧的数据
 1. 使用一个HashMap存储key到val的映射，就可以快速计算get(key) => KV table
 2. 使用一个HashMap存储key到freq的映射，就可以快速操作key对应的freq => KF table
 3. 将freq最小的key删除，那就应该快速得到当前所有key最小的freq

  要求时间复杂度O(1)，则不能遍历，那就用一个变量minFreq来记录当前最小的freq
 4. 可能存在多个key拥有相同的freq,则freq对key是一对多关系，
  即一个freq对应一个key列表 => FK table
 5. freq对应的key列表是存在时序的，便于快速查找并删除最旧的key => linked list
 6. 快速删除key列表的任何一个key => hash table
 综上，FK table应该使用LinkedHashSet
 */
public class MyLfuCache {
    HashMap<Integer, Integer> key2val; // kv table key->val
    HashMap<Integer, Integer> key2freq; // kf table key->freq
    HashMap<Integer, LinkedHashSet<Integer>> freq2keys; // fk table freq->key list
    int cap;
    int min_freq;

    public MyLfuCache(int capacity) {
        key2val     = new HashMap<>();
        key2freq    = new HashMap<>();
        freq2keys   = new HashMap<>();
        cap         = capacity;
        min_freq    = 0;
    }

    public int get(int key) {
        if(!key2val.containsKey(key))
            return -1;

        increaseFreq(key);

        return key2val.get(key);
    }

    public void put(int key, int val) {
        if(cap <= 0)
            return;

        // if key exists
        if(key2val.containsKey(key)) {
            key2val.put(key, val);
            increaseFreq(key);
            return;
        }
        // if key does not exist
        if(cap <= key2val.size())
            removeMinFreqKey();

        // update kv table
        key2val.put(key, val);
        // update kf table
        key2freq.put(key, 1);
        // update fk table
        freq2keys.putIfAbsent(1, new LinkedHashSet<>());
        freq2keys.get(1).add(key);

        min_freq = 1;
    }

    private void increaseFreq(int key) {
        int freq = key2freq.get(key);

        // update kf table
        key2freq.put(key, freq+1);
        // update fk table
        freq2keys.get(freq).remove(key);
        freq2keys.putIfAbsent(freq+1, new LinkedHashSet<>());
        freq2keys.get(freq+1).add(key);

        if(freq2keys.get(freq).isEmpty()) {
            freq2keys.remove(freq);
            if(freq == min_freq)
                min_freq++;
        }
    }

    private void removeMinFreqKey() {
        LinkedHashSet<Integer> keyList = freq2keys.get(min_freq);
        int deletedKey = keyList.getFirst();

        // update fk table
        keyList.remove(deletedKey);
        if(keyList.isEmpty())
            freq2keys.remove(min_freq);
        // update kv table
        key2val.remove(deletedKey);
        // update kf table
        key2freq.remove(deletedKey);
    }
}
