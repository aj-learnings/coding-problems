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

package main;

import "fmt"

func main() {
	fmt.Println(minimumFloatingPointsRequired([]int{ 1, 2, 1 }));
	fmt.Println(minimumFloatingPointsRequired([]int{ 2, 0, 0, 0 }));
	fmt.Println(minimumFloatingPointsRequired([]int{ 3, 4, 1, 1, 0 }));
}

func minimumFloatingPointsRequired(locations []int) int {
	n := len(locations);
	rightMost := make([]int, n+1);
	for i := 1; i <= n; i++ {
        start, end := max(i-locations[i-1], 1), min(i+locations[i-1], n);
		rightMost[start] = max(rightMost[start], end);
    }

	currentEndPos, endPosAchievable, floatingPoints := 1, 1, 0;
	for i := 1; i <= n; i++ {
        if (i > currentEndPos) {
            currentEndPos = endPosAchievable;
            floatingPoints += 1;
        }
        endPosAchievable = max(endPosAchievable, rightMost[i]);
    }
	if endPosAchievable < n {
    	return floatingPoints + 1
	}
	return floatingPoints
}