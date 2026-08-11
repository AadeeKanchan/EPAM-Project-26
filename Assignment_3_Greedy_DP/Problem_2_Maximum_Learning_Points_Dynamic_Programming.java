// Problem 2: Maximum Learning Points (Dynamic Programming)
// A student is preparing for a programming contest. Every day, the student can solve one topic, and each topic provides a certain number of learning points. However, the student cannot solve two consecutive difficult topics because of fatigue.
// Given the learning points of each topic arranged in order, determine the maximum learning points the student can earn without selecting two consecutive topics.

// Input Format
// • First line: N (number of topics)
// • Second line: N integers representing learning points.
  
// Output Format
// Print the maximum learning points.
  
// Constraints
// 1 ≤ N ≤ 100000
// 1 ≤ Points ≤ 10000
  
// Sample Input
// 6
// 5 1 2 10 6 2
  
// Sample Output
// 17
  
// Explanation
// Choose topics with points 5, 10, and 2. The total learning points are 17. This is the maximum possible without choosing two consecutive topics.
  
// Example
// Input:
// 5
// 3 2 7 10 12

// Output:
// 22

package Assignment_3_Greedy_DP;
import java.util.*;

public class Problem_2_Maximum_Learning_Points_Dynamic_Programming {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] points = new int[N];

        for (int i = 0; i < N; i++) {
            points[i] = sc.nextInt();
        }

        if (N == 1) {
            System.out.println(points[0]);
            return;
        }

        int[] dp = new int[N];

        dp[0] = points[0];
        dp[1] = Math.max(points[0], points[1]);

        for (int i = 2; i < N; i++) {
            dp[i] = Math.max(dp[i - 1],
                    dp[i - 2] + points[i]);
        }

        System.out.println(dp[N - 1]);
    }
}

