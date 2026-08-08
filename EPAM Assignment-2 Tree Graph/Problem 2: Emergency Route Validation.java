//Problem 2: Emergency Route Validation
//A country's transportation department models its highway system as a connected undirected graph. Each city is represented by a vertex, while highways are represented by edges. During emergencies, rescue teams need to travel from the capital city (City 1) to all other cities. However, not every city is considered safely reachable because some routes may contain too many intermediate cities.
// A city is called efficiently reachable if the length of the shortest path from City 1 to that city is less than or equal to D roads. Determine the total number of efficiently reachable cities, including the capital. Unreachable cities are not counted.

// Input Format
// First line: N M D
// Next M lines: u v (roads)
                   
// Output Format
// Print the number of efficiently reachable cities.
  
// Constraints
// 1 ≤ N ≤ 10^5
// 0 ≤ M ≤ 2×10^5
// 0 ≤ D ≤ N
// No self-loops
  
// Sample Input
// 7 8 2
// 1 2
// 1 3
// 2 4
// 2 5
// 3 6
// 6 7
// 5 7
// 4 6
// Sample Output
// 6
  
// Explanation
// Run BFS from City 1 to compute the shortest distance to every city. Count cities whose distance is at most D.
  
// Example
// Input:
// 6 5 1
// 1 2
// 2 3
// 1 4
// 4 5
// 5 6

// Output:
// 3




//Solution_Code


package Arrays_DSA_Ques;

import java.io.*;
import java.util.*;

public class EPAM {
    public static void main(String[] args) throws IOException {
        // Fast I/O for handling large inputs (N up to 10^5, M up to 2*10^5)
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        String line = reader.readLine();
        if (line == null) return;
        st = new StringTokenizer(line);

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        // Build Adjacency List for the graph
        List<List<Integer>> adj = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            line = reader.readLine();
            while (line != null && line.trim().isEmpty()) {
                line = reader.readLine();
            }
            if (line == null) break;

            st = new StringTokenizer(line);
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Distance array initialized to -1 (unvisited)
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);

        // BFS Initialization from Capital (City 1)
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        dist[1] = 0;

        int reachableCount = 0;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            // Count cities within maximum distance threshold D
            if (dist[u] <= D) {
                reachableCount++;
            } else {
                // Since BFS visits nodes in increasing order of distance,
                // once distance exceeds D, remaining nodes in queue will also exceed D
                continue;
            }

            // Explore neighbors
            for (int v : adj.get(u)) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    queue.add(v);
                }
            }
        }

        System.out.println(reachableCount);
    }
}
