package com.chris.algorithms.templates.structure;

public class MyHashSetV2 {
    // 由于数据范围为 0 <= key <= 10^6，我们最多需要的 int 数量不会超过 40000
    int N = 40000;
    // 建立一个 buckets 数组，数组装载的 int 类型数值
    // 先对 key 进行 key / 32，确定当前 key 所在桶的位置（大概位置）
    // 再对 key 进行 key % 32，确定当前 key 所在桶中的哪一位（精确位置）
    // 根据位运算对「精确位置」进行修改
    int[] bs = new int[N];

    public void remove(int key) {
        int bucketIdx   = key / 32;
        int bitIdx      = key % 32;
        setVal(bucketIdx, bitIdx, false);
    }

    public boolean contains(int key) {
        int bucketIdx   = key / 32;
        int bitIdx      = key % 32;
        return getVal(bucketIdx, bitIdx);
    }

    private void setVal(int bucket, int loc, boolean val) {
        int u;
        if (val) {
            u = bs[bucket] | (1 << loc);
        } else {
            u = bs[bucket] & ~(1 << loc);
        }
        bs[bucket] = u;
    }

    private boolean getVal(int bucket, int loc) {
        int u = (bs[bucket] >> loc) & 1;
        return u == 1;
    }
}
