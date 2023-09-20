class Solution {
    int m;
    int t;
    int[] dp;
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        m = maxChoosableInteger;
        t = desiredTotal;
        // edge cases
        // if the sum of all the numbers is less than the desiredTotal, return false
        if (maxChoosableInteger * (maxChoosableInteger + 1) >> 1 < desiredTotal) return false;

        // dp[state] represent if the current state and current turn can win, 1 mean true, -1 means false. 
        // state is a integer represent the state, if the ith bit is 1, means the number i has been choosen, otherwise has not been choosen.
        dp = new int[1 << maxChoosableInteger];
        
        // we start a dfs from state = 0, which means no number has been choosen.
        // if the dfs return 1 means the first player to move can win, otherwise not 
        return dfs(0, 0) == 1;
    }

    private int dfs(int state, int total) {
        // base case, if dp[state] has been calculated, we return the value
        if (dp[state] != 0) return dp[state];

        // for current move, we can choose from range [1, m]
        for (int i = 1; i <= m; i++) {
            // if the current number has been used, continue;
            if (((state >> (i - 1)) & 1) == 1) continue;
            // if the current number add total is greater than t, means this state can win
            if (total + i >= t) return dp[state] = 1;
            // if the next state can not win, that means this state can win
            if (dfs(state | (1 << (i - 1)), total + i) == -1) return dp[state] = 1;
        }
        return dp[state] = -1;
    }
}