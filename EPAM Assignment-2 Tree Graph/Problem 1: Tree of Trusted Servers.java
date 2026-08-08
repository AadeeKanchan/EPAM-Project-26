package Arrays_DSA_Ques;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

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
