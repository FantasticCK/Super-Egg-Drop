package com.CK;

public class Main {

    public static void main(String[] args) {
        new Solution().superEggDrop(2, 12);

    }
}

class Solution {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        for (int i = 0; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (i == 1) {
                    dp[i][j] = j;
                    continue;
                }

                if (j == 1) {
                    dp[i][j] = 1;
                    continue;
                }

                int low = 1, high = j;
                while (low + 1 < high) {
                    int mid = (low + high) / 2;
                    int midLow = dp[i - 1][mid - 1];
                    int midHigh = dp[i][j - mid];
                    if (midLow == midHigh) {
                        low = high = mid;
                    } else if (midLow < midHigh) {
                        low = mid;
                    } else
                        high = mid;
                }
                dp[i][j] = Math.min(
                        Math.max(1 + dp[i - 1][low - 1], 1 + dp[i][j - low]),
                        Math.max(1 + dp[i - 1][high - 1], 1 + dp[i][j - high])
                );
            }
        }
        return dp[K][N];
    }
}


class Solution {
    public int superEggDrop(int K, int N) {
        int dp[] = new int[K + 1], m = 0;
        for (m = 0; dp[K] < N; ++m)
            for (int k = K; k > 0; --k)
                dp[k] += dp[k - 1] + 1;
        return m;
    }
}

class Solution {
    public int superEggDrop(int K, int N) {
        int lo = 1, hi = N;
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (f(mi, K, N) < N)
                lo = mi + 1;
            else
                hi = mi;
        }

        return lo;
    }

    public int f(int x, int K, int N) {
        int ans = 0, r = 1;
        for (int i = 1; i <= K; ++i) {
            r *= x-i+1;
            r /= i;
            ans += r;
            if (ans >= N) break;
        }
        return ans;
    }
}