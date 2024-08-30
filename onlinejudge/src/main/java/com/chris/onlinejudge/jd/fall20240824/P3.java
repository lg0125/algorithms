package com.chris.onlinejudge.jd.fall20240824;

import java.util.Scanner;
import java.util.TreeSet;

/**
 有n根木棍排成一列，第i根木棍的长度为ai 。
 请你从中选出一个最长的子区间，使得区间内任意三根木棍都能构成三角形。只需要输出选出的区间端点即可

 第一行输入一个正整数n，代表木棍的数量。 n 10^6
 第二行输入n个整数，第i个整数ai代表第i根木棍的长度 ai 10^9

 输出一行两个整数，表示最长的满足条件的区间的两个端点，如果有多个满足条件的区间，输出左端点最小的区间。保证答案存在

 3
 1 2 3

 1 2

 9
 2 3 3 3 1 1 3 3 3

 1 4

 实质上要求最长区间，使得区间最小值+次小值>=最大值；
 滑动区间，每次加入下一个数，并将区间头后移直到区间合法，
 用multiset可log维护最小值，次小值，最大值。O（n log n）

 要能够组成三角形，需要满足任意两边之和大于第三边的条件。
 而如果一个连续子序列中任意三个数都满足这个条件，则需要该区间中最小的两个值的和大于最大值。该需求可以考虑使用双指针进行求解。

 从1到i枚举右指针，并维护左指针。
 左指针的变化要求指针间所有数中，最小的两个值的和要大于指针间所有数中最大的值，
 因此在将第i个数加入时，动态移动左指针以满足要求。

 在维护时，可以同时维护两个优先队列，一个大根堆一个小根堆，并不断更新最大值和最小值，次小值即可。
 */
public class P3 {

}
