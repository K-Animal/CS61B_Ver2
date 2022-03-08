package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        AList<Integer> Ns = new AList();
        AList<Double> times = new AList();
        AList<Integer> opCounts = new AList();
        timeGetLast(Ns, times, opCounts);
        printTimingTable(Ns, times, opCounts);
    }

    public static void timeGetLast(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        // TODO: YOUR CODE HERE
        int n, m;
        double timeInSeconds;
        for (int i = 1000; i <= 64000; i =i * 2) {
            SLList<Integer> test = new SLList();
            for (n = 0; n < i; n++) {
                test.addFirst(n);
            }
            Stopwatch sw = new Stopwatch();
            for (m = 0; m <10000; m++){
                test.getLast();
            }
            timeInSeconds = sw.elapsedTime();
            Ns.addLast(n);
            times.addLast(timeInSeconds);
            opCounts.addLast(m);
        }
    }
}
