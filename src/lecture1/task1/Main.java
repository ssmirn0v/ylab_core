package lecture1.task1;

public class Main {


    public static void main(String[] args) {
        int n = 10;
        int m = 10;

        int[][] array = new int[n][m];
        Random random = new Random();
        int ceil = 10000;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                array[i][j] = random.nextInt(ceil);
            }
        }

        System.out.println("Settled array:");
        printMatrix(array);

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (array[i][j] > max) {
                    max = array[i][j];
                }
                if (array[i][j] < min) {
                    min = array[i][j];
                }
                sum += array[i][j];
            }
        }

        System.out.printf("%nMin: %d, Max: %d, Avg: %.2f", min, max, sum/((double) n*m));

    }

    public static void printMatrix(int[][] a) {
        int n = a.length;
        int m = a[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%6d", a[i][j]);
            }
            System.out.println();
        }
    }

    // LCG
    static class Random {

        private static final long MODULUS = 2L << 48;
        private static final long MULTIPLIER = 0x5DEECE66DL;
        private static final long INCREMENT = 11;

        private long seed;

        Random(int seed) {
            this.seed = seed;
        }

        Random() {
            this(1337);
        }

        public int nextInt(int ceil) {
            if (ceil <= 0) {
                throw new IllegalArgumentException("Ceil value should be more than zero");
            }

            long resSeed = (MULTIPLIER*seed + INCREMENT) % MODULUS;

            seed = resSeed;
            return (int) ((resSeed >>> 16) % ceil);
        }

        public int nextInt() {
            return nextInt(Integer.MAX_VALUE);
        }




    }
}
