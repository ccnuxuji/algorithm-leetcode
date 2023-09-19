class Solution {
    public int getMoneyAmount(int n) {
        // range dynamic programming
        // dp[l][r] means the minmum money needed to guarantee a win in range [l, r]
        // 1. in range [l, r], we choose every number to calculate the dp[l][r], and we take the max one
        // 2. when we choose x in the range [l, r], we have:
        //          curr = Math.max(dp[l][x - 1], dp[x + 1][r]) + x;

        int[][] dp = new int[n + 10][n + 10];
        // first layer of loop is the lenth of the range, since for lenth of 1, we don't need to guess, the money needed is 0, we start from len of 2
        for (int len = 2; len <= n; len++) {
            for (int l = 1; l + len - 1 <= n; l++) {
                int r = l + len - 1;
                dp[l][r] = Integer.MAX_VALUE;
                for (int x = l; x <= r; x++) {
                    int curr = Math.max(dp[l][x - 1], dp[x + 1][r]) + x;
                    dp[l][r] = Math.min(dp[l][r], curr);
                }
            }
        }
        return dp[1][n];
    }
}
