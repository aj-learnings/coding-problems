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

package main;

import "sort"
import "fmt"

func main() {
	fmt.Println(minimumCoverLength([]int{ 8, 2, 17, 10 }, 3));
	fmt.Println(minimumCoverLength([]int{ 11, 3, 23, 7, 12 }, 2));
}

func minimumCoverLength(positions []int, k int) int {

	n := len(positions);
	sort.Slice(positions, func(i, j int) bool {
		return positions[i] < positions[j];
	});
	result := positions[n-1];
	for i:=k-1; i<n; i++ {
		result = min(result, positions[i]-positions[i-k+1]+1);
	}
	return result;
}