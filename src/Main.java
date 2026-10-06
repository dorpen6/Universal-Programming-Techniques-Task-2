public class Main {

    public static long measureExt() throws InterruptedException {
        ExtThread thread = new ExtThread();

        thread.start();
        thread.join();

        return thread.getTime();
    }

    public static long measureRun() throws InterruptedException {
        RunTask task = new RunTask();
        Thread thread = new Thread(task);

        thread.start();
        thread.join();

        return task.getTime();
    }

    public static double average(long[] values) {
        long sum = 0;

        for (long value : values) {
            sum += value;
        }

        return (double) sum / values.length;
    }

    public static double median(long[] values) {
        long[] copy = values.clone();

        java.util.Arrays.sort(copy);

        int middle = copy.length / 2;

        if (copy.length % 2 == 0) {
            return (copy[middle - 1] + copy[middle]) / 2.0;
        }

        return copy[middle];
    }

    public static void main(String[] args) throws InterruptedException {

        final int WARMUP = 5000;
        final int N = 25000;

        // Warm-up
        for (int i = 0; i < WARMUP; i++) {
            measureExt();
            measureRun();
        }

        // Arrays for measurements
        long[] extTimes = new long[N];
        long[] runTimes = new long[N];

        // Actual measurements
        for (int i = 0; i < N; i++) {
            extTimes[i] = measureExt();
            runTimes[i] = measureRun();
        }

        // Results
        System.out.println("ExtThread average: " + average(extTimes) + " ns");
        System.out.println("ExtThread median: " + median(extTimes) + " ns");

        System.out.println();

        System.out.println("Runnable average: " + average(runTimes) + " ns");
        System.out.println("Runnable median: " + median(runTimes) + " ns");
    }
}