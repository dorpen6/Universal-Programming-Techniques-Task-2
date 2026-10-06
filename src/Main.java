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

    public static void main(String[] args) throws InterruptedException {

        final int WARMUP = 5000;
        final int N = 50000;

        for (int i = 0; i < WARMUP; i++) {
            measureExt();
            measureRun();
        }

        long[] extTimes = new long[N];
        long[] runTimes = new long[N];

        for (int i = 0; i < N; i++) {
            extTimes[i] = measureExt();
            runTimes[i] = measureRun();
        }
    }
}