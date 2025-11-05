/**
 * You are given locations of n floating points. Each floating point will cover the 
 * range max((i-locations[i]), 1) to min((i+locations[i]), n).
 * Your task is to return the minimum number of floating points required to cover the
 * complete range.
 * Sample Input 1 -
 * locations - [1, 2, 1]
 * Sample Output 1 - 1 (The 2nd floating is enough to cover the whole range)
 * Sample Input 2 - 
 * locations - [2, 0, 0, 0]
 * Sample Output 2 - 2 (1st and 4th floating point are enough to cover the whole range)
 * Sample Input 3 - 
 * locations - [3, 4, 1, 1, 0]
 * Sample Output 2 - 1 (The 2nd floating is enough to cover the whole range)
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class MinimumFloatingPointsCoverFullRange {
    public static void main(String[] args) {
       
        System.out.println(minimumFloatingPointsRequired(List.of(1, 2, 1 )));
        System.out.println(minimumFloatingPointsRequired(List.of(2, 0, 0, 0)));
        System.out.println(minimumFloatingPointsRequired(List.of(3, 4, 1, 1, 0)));
    }

    private static Integer minimumFloatingPointsRequired(List<Integer> locations) {
        
        var n = locations.size();
        var rightMost = new ArrayList<>(Collections.nCopies(n+1, 0));
        for (int i=1; i<=n; i++) {
            int left = Math.max(i-locations.get(i-1), 1);
            int right = Math.min(i+locations.get(i-1), n);
            rightMost.set(left, Math.max(rightMost.get(left), right));
        }
        int currendEndPos = 1, endPosAchievable = 1, floatingPoints = 0;
        for (int i=1; i<=n; i++) {
            if (i > currendEndPos) {
                currendEndPos = endPosAchievable;
                floatingPoints += 1;
            }
            endPosAchievable = Math.max(endPosAchievable, rightMost.get(i));
        }
        return floatingPoints;
    }
}