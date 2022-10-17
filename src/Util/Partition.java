package Util;

public class Partition {
    public static void partition(int[] s, int n, int k) {
        int maxN = 10;
        int maxK = 10;
        int[][] m = new int[maxN + 1][maxK + 1]; //2d array of size 11 x 11
        int[][] d = new int[maxN + 1][maxK + 1]; //2d array of size 11 x 11
        int[] p = new int[maxN + 1]; //array of size 11
        int cost; //int
        int i, j, x; //counters

        p[0] = 0;
        for (i = 1; i <= n; i++) {
            p[i] = p[i - 1] + s[i - 1];
        }
        for (i = 1; i <= n; i++) {
            m[i][i] = p[i];
        }
        for (j = 1; j <= k; j++) {
            m[1][j] = s[1];
        }
        for (i = 2; i <= n; i++) {
            for (j = 2; j <= k; j++) {
                m[i][j] = maxInt(p);
                for (x = 1; x <= (i - 1); x++) {
                    if (m[x][j - 1] >= p[i] - p[x]) {
                        cost = m[x][j - 1];
                    } else cost = p[i] - p[x];
                    if (m[i][j] > cost) {
                        m[i][j] = cost;
                        d[i][j] = x;
                    }
                }
            }
        }
        reconstructPartition(s, d, n, k);
    }

    private static void reconstructPartition(int[] s, int[][] d, int n, int k) {
        if (k == 1) {
            printBooks(s, 1, n);
        } else {
            reconstructPartition(s, d, d[n][k], k - 1);
            printBooks(s, d[n][k] + 1, n);
        }
    }

    private static void printBooks(int[] s, int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.printf(" %d ", s[i - 1]);
        }
        System.out.printf("\n");
    }

    private static int maxInt(int[] s) {
        int max = 0;
        for (int num : s) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] s = new int[]{12, 6, 7, 32, 98, 9, 2, 456, 0};
        partition(s, s.length, 3);
    }

}
