package com.chris.onlinejudge.kdxf.fall20240713;

import java.util.*;

public class P3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLong();
        }

        Map<Long, Long> dp = new HashMap<>();
        dp.put(1L, 0L);

        for (long val : a) {
            Map<Long, Long> newDp = new HashMap<>();
            List<Long> arr = new ArrayList<>();

            for (Map.Entry<Long, Long> entry : dp.entrySet()) {
                long k = entry.getKey();
                long v = entry.getValue();

                long l = val, r = val;
                long cnt = 0;

                while (l >= k) { // 除2
                    if (!newDp.containsKey(l)) {
                        newDp.put(l, v + cnt);
                        arr.add(l);
                    }
                    newDp.put(l, Math.min(newDp.get(l), v + cnt));
                    l /= 2;
                    cnt++;
                }

                cnt = 0;
                while (r < k) { // 乘2
                    r *= 2;
                    cnt++;
                }
                if (!newDp.containsKey(r)) {
                    newDp.put(r, v + cnt);
                    arr.add(r);
                }
                newDp.put(r, Math.min(newDp.get(r), v + cnt));
            }
            // 去除无效状态
            Collections.sort(arr);
            dp.clear();
            long minv = newDp.get(arr.get(0));
            dp.put(arr.get(0), minv);

            for (int i = 1; i < arr.size(); i++) {
                if (newDp.get(arr.get(i)) > minv)
                    continue;
                minv = newDp.get(arr.get(i));
                dp.put(arr.get(i), minv);
            }
        }

        long res = Long.MAX_VALUE;
        for (long v : dp.values()) {
            res = Math.min(res, v);
        }

        System.out.println(res);
    }
}
