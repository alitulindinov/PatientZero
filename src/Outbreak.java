
public class Outbreak {

    private static int longestResult;

    public static boolean[] cheapestTests(int n, int[][] nb, int[] cost) {
        boolean[] tested = new boolean[n];
        int[][] dp = new int[n][2];

        calculateDP(0, -1, nb, cost, dp);

        rebuildAnswer(0, -1, nb, dp, tested, dp[0][1] <= dp[0][0]);

        return tested;
    }

    private static void calculateDP(int v, int parent, int[][] nb,
                                    int[] cost, int[][] dp) {
        dp[v][1] = cost[v];
        dp[v][0] = 0;

        for (int u : nb[v]) {
            if (u == parent) {
                continue;
            }

            calculateDP(u, v, nb, cost, dp);

            dp[v][1] += Math.min(dp[u][0], dp[u][1]);
            dp[v][0] += dp[u][1];
        }
    }

    private static void rebuildAnswer(int v, int parent, int[][] nb,
                                      int[][] dp, boolean[] tested,
                                      boolean isTested) {
        tested[v] = isTested;

        for (int u : nb[v]) {
            if (u == parent) {
                continue;
            }

            boolean childTested;

            if (!isTested) {
                childTested = true;
            } else {
                childTested = dp[u][1] <= dp[u][0];
            }

            rebuildAnswer(u, v, nb, dp, tested, childTested);
        }
    }

    public static int longestChain(int n, int[][] nb) {
        if (n <= 1) {
            return 0;
        }

        longestResult = 0;
        findLongest(0, -1, nb);

        return longestResult;
    }

    private static int findLongest(int v, int parent, int[][] nb) {
        int first = 0;
        int second = 0;

        for (int u : nb[v]) {
            if (u == parent) {
                continue;
            }

            int length = findLongest(u, v, nb) + 1;

            if (length > first) {
                second = first;
                first = length;
            } else if (length > second) {
                second = length;
            }
        }

        longestResult = Math.max(longestResult, first + second);

        return first;
    }
}
