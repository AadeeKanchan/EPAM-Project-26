// Problem 1: Tree of Trusted Servers
    
// A multinational organization maintains its internal communication infrastructure in the form of a tree, where each server is represented by a node, and each communication link is represented by an edge. The root server (Server 1) is the central authentication server. Every server stores a unique security key represented by an integer. During periodic audits, the organization wants to verify whether the communication path between the root server and every other server satisfies a security policy.
// A server is considered trusted if the XOR of all security keys on the path from the root server to that server is greater than or equal to a given threshold K. Your task is to determine how many servers in the network are trusted. The root server is also included in the evaluation.

// Input Format
// First line: N K
// Second line: N integers (security keys)
// Next N-1 lines: u v (tree edges)
    
// Output Format
// Print the number of trusted servers.
    
// Constraints
// 1 ≤ N ≤ 10^5
// 0 ≤ K ≤ 10^9
// 0 ≤ Key ≤ 10^9
// Input graph is a tree
    
// Sample Input
// 7 5
// 3 6 2 7 1 4 5
// 1 2
// 1 3
// 2 4
// 2 5
// 3 6
// 3 7
// Sample Output
// 4
    
// Explanation
// Compute the XOR value along the path from the root (Server 1) to every server. Count the servers whose path XOR is at least K.
    
// Example
// Input:
// 5 2
// 1 3 2 5 6
// 1 2
// 1 3
// 3 4
// 3 5

// Output:
// 3



// Solution




package Arrays_DSA_Ques;
import java.io.*;
import java.util.*;

public class EPAM {
    private static int trustedCount = 0;

    public static void main(String[] args) throws IOException {
        // Fast I/O for handling large inputs (up to N = 10^5)
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        String line = reader.readLine();
        if (line == null) return;
        st = new StringTokenizer(line);

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 1-indexed array for server keys
        int[] keys = new int[N + 1];
        st = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= N; i++) {
            keys[i] = Integer.parseInt(st.nextToken());
        }

        // Build Adjacency List for the Tree
        List<List<Integer>> adj = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
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

        // Start DFS from root (Server 1) with parent = 0 and current path XOR = keys[1]
        dfs(1, 0, keys[1], keys, adj, K);

        System.out.println(trustedCount);
    }

    private static void dfs(int u, int parent, int currentXor, int[] keys, List<List<Integer>> adj, int K) {
        // Check if the current server's path XOR meets the threshold
        if (currentXor >= K) {
            trustedCount++;
        }

        // Traverse all connected neighbors
        for (int v : adj.get(u)) {
            // Avoid moving backward to the parent node
            if (v != parent) {
                // Pass down cumulative XOR: currentXor ^ keys[v]
                dfs(v, u, currentXor ^ keys[v], keys, adj, K);
            }
        }
    }
}
