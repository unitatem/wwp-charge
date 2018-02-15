package solver;
import java.util.Random;
import solver.Point;
import solver.GeneticAlgorithm;

public class General {

    // tu uwzglednic zrownoleglanie -> kazdy procesor przeprowadza próbę

    private static float[][] getDist(Point[] points) {
        float[][] dist = new float[points.length][points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                dist[i][j] = distance(points[i], points[j]);
            }
        }
        return dist;
    }

    private static float distance(Point p1, Point p2) {
        return (float) Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }
    public static void main(String[] args) {
        Point[] points = new Point[30];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point();
            points[i].x = new Random().nextInt(200);
            points[i].y = new Random().nextInt(200);
        }
        //RandomLocations randList = new RandomLocations();

        int[] best;
    GeneticAlgorithm ga = GeneticAlgorithm.getInstance();

        ga.setMaxGeneration(1000);
        ga.setAutoNextGeneration(true);
        best = ga.tsp(getDist(points));
        System.out.print("best path:");
        for(int i = 0; i < best.length; i++) {
        System.out.print(best[i] + " ");
    }
        System.out.println();


    }}

