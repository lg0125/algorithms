package com.chris.onlinejudge.pdd.fall20240825;

import java.util.*;

/*
塔塔携带一件价值为x的礼物参加了一个节日派对。除多多外在场的有n个人，第i个人的当前持有的礼物价值为ai。
塔塔可以和任意当前持有礼物价值比塔塔低的人交换礼物。
请问最少经过多少次交换，可以使得n个人持有的礼物价值形成单调不减的数列a1 <= a2 <= a3 <= ... <= an。

第一行输入两个数字n和x，代表人数和塔塔最多持有的礼物价值
第二行n个数字a1, a2, ..., an,代表其他所有人最初持有的礼物价值
x ai 10^9
n 10^6

输出一个数字,代表为了达成目标最少进行的交换次数；如果无法达成目标，则输出-1

5 5
2 1 3 2 4

3

用手中的数与数组中的数交换，手中的数一定是越来越小的。
同时，要让数组中的数单调不减，首先就要让最后一个数最大，而如果最后一个数不是最大，唯一改变它的方法就是与手中的数字进行交换。
于是，手中的数就变为了最后一个数。
对于第个n-1,n-2,...个数同理。
于是，从最后一个数开始判断，如果比手中数小，那么就进行交换，同时与前面的数比较，如果逆序，则记录该逆序对。
交换后，与前后两个数进行比较，检查能否删除记录过的逆序对。如果最后没有记录中的逆序对，则说明可以实现，否则不能。
 */
public class P3 {
    static boolean isSorted(List<Integer> array, Set<Integer> unsortedIndices) {
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i) < array.get(i - 1)) {
                unsortedIndices.add(i);
            }
        }
        return unsortedIndices.isEmpty();
    }

    static void updateIndices(List<Integer> array, Set<Integer> unsortedIndices, int x, int y) {
        if (array.get(y) < array.get(x)) {
            unsortedIndices.add(y);
        } else {
            unsortedIndices.remove(y);
        }
    }

    static int minOperationsToSort(List<Integer> array, int x) {
        Set<Integer> unsortedIndices = new HashSet<>();

        if (isSorted(array, unsortedIndices)) {
            return 0;
        }

        int ans = 0;
        int n = array.size();

        for (int i = n - 1; i < array.size(); i--) {
            if (x > array.get(i)) {
                Collections.swap(array, i, array.indexOf(x));
                ans++;

                if (i != n - 1) {
                    updateIndices(array, unsortedIndices, i, i + 1);
                }
                if (i != 0) {
                    updateIndices(array, unsortedIndices, i - 1, i);
                }
            }

            if (unsortedIndices.isEmpty()) {
                return ans;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int x = scanner.nextInt();

        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            array.add(scanner.nextInt());
        }

        System.out.println(minOperationsToSort(array, x));
    }
}
