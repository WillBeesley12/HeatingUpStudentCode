import java.util.ArrayList;
/**
 * The class WeatherPatterns finds the longest span of days in which
 * each day’s temperature is higher than on the previous day in that sequence.
 *
 * @author Zach Blick
 * @author William Beesley
 */

public class WeatherPatterns {

    private static int PATHFINDER[];
    private static int NUM;
    private static ArrayList<Integer> GRAPH[];
    private static int MAP[];
    public static int longestPath(int vertex) {
        int length = 1;
        int other;
        for (int node : GRAPH[vertex]) {
            if (PATHFINDER[node] == 0) {
                other = longestPath(node);
            }
            else {
                other = PATHFINDER[node];
            }
            length = Math.max(length, other + 1);
        }
        PATHFINDER[vertex] = length;
        return length;
    }

    /**
     * Longest Warming Trend
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */

    public static int longestWarmingTrend(int[] temperatures) {
        // Initialize variables
        NUM = temperatures.length;
        // Both of these data structures use the index of the temp as the lookup value
        PATHFINDER = new int[NUM];
        GRAPH = new ArrayList[NUM];
        for (int i = 1; i <= NUM; i++) {
            GRAPH[i] = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (temperatures[j] < temperatures[i]) {
                    GRAPH[i].add(j);
                }
            }
        }
        int longest = 0;
        for (int i = 0; i < NUM; i++) {
            longest = Math.max(longest, longestPath(i));
        }
        return longest;
    }
}
