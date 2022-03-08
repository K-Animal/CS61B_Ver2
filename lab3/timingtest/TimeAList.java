package timingtest;
import edu.princeton.cs.algs4.Stopwatch;


/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction(Ns, times, opCounts);
        printTimingTable(Ns, times, opCounts);
    }

    public static void timeAListConstruction(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        AList<Integer> test = new AList();
        int n;
        double timeInSeconds;

        for (int i = 1000; i <= 128000; i= i * 2) {
            Stopwatch sw = new Stopwatch();
            for (n = 0; n < i; n++) {
                test.addLast(n);
            }
            timeInSeconds = sw.elapsedTime();
            Ns.addLast(n);
            times.addLast(timeInSeconds);
            opCounts.addLast(n);
        }
    }
}
