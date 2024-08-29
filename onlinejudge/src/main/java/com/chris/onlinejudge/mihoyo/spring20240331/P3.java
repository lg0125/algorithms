package com.chris.onlinejudge.mihoyo.spring20240331;

import java.util.*;

/*
米小游不喜欢掣电树，因为击败掣电树需要很长时间，而且掣电树的伤害很高。
掣电树上有n个节点，每个节点都用一个符号'+'或'-'表示该节点是正极节点还是负极节点。如果米小游从正极节点走到负极节点或从负极节点走到正极节点时，都会受到1点雷属性伤害。
米小游想知道她走过所有路径后会受到的总伤害是多少。所谓“所有路径”，即任意两个节点存在—条唯—路径，米小游需要计算共C(n,2)条路径的总伤害。
注意:从u到v和从v到u视为同—条路径

第—行输入—个整数n
第二行输入—个长度为n的仅由'+'和'-'组成的字符串
接下来n―1行，每行输入两个整数u,v, 表示节点u和节点v之间有—条边

输出—个整数表示米小游受到的总伤害

3
++-
1 2
1 3

2

路径1-2(2-1)不会受到伤害。路径1-3(3-1)会受到1点伤害
路径2-1-3 (3-1-2)会受到1点伤害。因此答案为2

本题采用换根dp的想法：
 首先，统计每个节点所在子树的所有节点数目，保存在 nums[i] 中；
 然后，以1为根节点，统计节点1到其余节点的路径的总伤害，注意，如果某个边的伤害为1，那么需要乘以它所在子树的节点数；
 最后，换根dp，从根节点向孩子节点移动时，如果这条边没有造成伤害，说明子节点所有路径的伤害和父节点相同；否则，伤害会增加 -ans[child] + (n - ans[child])，第一项代表子节点到其子树的其他节点路径，第二项代表子节点到父节点另一侧子树的所有节点路径。
 最终，除以2即可，因为每个节点的路径有n-1条，计算了n次，除以2。
*/
public class P3 {
    private static int n;

    private static char[] s;

    private static Map<Integer, List<Integer>> g;

    private static int[] nums, ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        s = new char[n+1];
        System.arraycopy(
                sc.next().toCharArray(), 0,
                s, 1,
                n
        );

        g = new HashMap<>();
        for(int i = 0; i < n-1; ++i) {
            int u = sc.nextInt(), v = sc.nextInt();

            g.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            g.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        nums    = new int[n+1];
        ans     = new int[n+1];

        ans[1]  = dfs(1, 0);

        helper(1, 0);

        long sum = 0;
        for(int e : ans)
            sum += e;
        System.out.println(sum / 2);
    }

    private static int dfs(int u, int fa) {
        nums[u] = 1;

        int res = 0;
        for(int v : g.get(u)) {
            if(v == fa)
                continue;
            res         += dfs(v, u);
            nums[u]     += nums[v];
            if(s[u] != s[v])
                res     += nums[v];
        }
        return res;
    }

    private static void helper(int u, int fa) {
        for(int v : g.get(u)) {
            if(v == fa)
                continue;

            if(s[u] == s[v])
                ans[v] = ans[u];
            else
                ans[v] = n - 2 * nums[v] + ans[u];

            helper(v, u);
        }
    }
}
