import java.util.Scanner;

public class Main {
    private static int[] price;
    private static int[][] friends;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        price = new int[n];
        for (int i = 0; i < n; i++) {
            price[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        friends = new int[m][2];
        for (int i = 0; i < m; i++) {
            friends[i][0] = scanner.nextInt();
            friends[i][1] = scanner.nextInt();
        }
        scanner.close();
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            minCost = Math.min(minCost, findNext(0, new int[n], i));
        }
        System.out.println(minCost);
    }

    private static int findNext(int cost, int[] route, int j) {
        int minCost = Integer.MAX_VALUE;
        cost += price[j];
        route[j] = 1;
        int[] local_route = new int[route.length];
        for (int i = 0; i < route.length; i++) {
            if (route[i] == 1) {
                local_route[i] = 1;
                for (int[] friend : friends) {
                    if (friend[0] == i)
                        local_route[friend[1]] = 1;
                    else if (friend[1] == i)
                        local_route[friend[0]] = 1;
                }
            }
        }
        for (int k : local_route) {
            if (k == 0) {
                for (int i = 0; i < route.length; i++) {
                    if (route[i] == 0) {
                        int[] mid_route = new int[route.length];
                        System.arraycopy(route, 0, mid_route, 0, route.length);
                        minCost = Math.min(findNext(cost, mid_route, i), minCost);
                    }
                }
                return minCost == Integer.MAX_VALUE ? cost : minCost;
            }
        }
        return cost;
    }
}
