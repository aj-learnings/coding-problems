/**
 * You are given positions of n cars in a line in parking. In one postion, atmost one car
 * can be present. Also you are given an integer K. You need to find the minimum length of 
 * a cloth such that at least K cars in the parking gets covered with that cloth.
 * Sample Input 1 -
 * position - [8, 2, 17, 10], k = 3
 * Sample Output 1 - 9 (We can put the cloth over Car 1,2 and 4)
 * Sample Input 2 - 
 * positions - [11, 3, 23, 7, 12], k = 2
 * Sample Output 2 - 2 (We can put the cloth over car 1 and 5)
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class MinimumClothLengthToCoverParking {
    public static void main(String[] args) {
        
        System.out.println(minimumCoverLength(new ArrayList<>(List.of(8, 2, 17, 10)), 3));
        System.out.println(minimumCoverLength(new ArrayList<>(List.of(11, 3, 23, 7, 12)), 2));
    }

    private static int minimumCoverLength(List<Integer> positions, int k) {
        int n = positions.size();
        Collections.sort(positions);
        int result = positions.get(n-1);
        for (int i=k-1; i<n; i++) {
            result = Math.min(result, positions.get(i)-positions.get(i-k+1)+1);
        }
        return result;
    }
}