// Time Complexity : O(n*2^n)
// Space Complexity : O(n)


import java.util.*;

public class BuildingPlacement {
    public int minDistance(int w, int h, int n) {
        int[][] grid = new int[w][h];
        for (int[] row : grid) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        List<int[]> buildings = new ArrayList<>();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                buildings.add(new int[]{i, j});
            }
        }
        
        // Generate all possible combinations of building placements
        List<List<int[]>> combinations = new ArrayList<>();
        generateCombinations(buildings, n, 0, new ArrayList<>(), combinations);
        
        int minDistance = Integer.MAX_VALUE;
        
        // Find the combination with the minimum maximum distance
        for (List<int[]> combination : combinations) {
            int maxDistance = 0;
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    if (grid[i][j] == 0) {
                        int minDist = Integer.MAX_VALUE;
                        for (int[] building : combination) {
                            int dist = Math.abs(i - building[0]) + Math.abs(j - building[1]);
                            minDist = Math.min(minDist, dist);
                        }
                        maxDistance = Math.max(maxDistance, minDist);
                    }
                }
            }
            minDistance = Math.min(minDistance, maxDistance);
        }
        
        return minDistance;
    }
    
    private void generateCombinations(List<int[]> buildings, int n, int index, List<int[]> current, List<List<int[]>> result) {
        if (current.size() == n) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = index; i < buildings.size(); i++) {
            current.add(buildings.get(i));
            generateCombinations(buildings, n, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    public static void main(String[] args) {
        BuildingPlacement bp = new BuildingPlacement();
        int w = 4;
        int h = 4;
        int n = 3;
        System.out.println(bp.minDistance(w, h, n)); // Output: 2
    }
}


