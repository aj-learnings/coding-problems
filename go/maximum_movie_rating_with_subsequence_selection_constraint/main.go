/**
 * You are given ratings of n movies. Rating can be negative as well as positive.
 * Your task is to maximize the ratings by selecting a subsequence of movies with the 
 * condition that not more than one movie can be skipped continuosly.
 * Sample Input 1 -
 * ratings - [5, -1, -2, 3, 4]
 * Sample Output 1 - 11 (The 3rd movie can be skipped)
 * Sample Input 2 - 
 * ratings - [-1, -2, -3, -4, -5]
 * Sample Output 2 - -6 (1st, 3rd, 5th movie can be skipped)
 */

package main;

import "fmt"

func main() {
	fmt.Println(maximumMovieRatings([]int{5, -1, -2, 3, 4}));
	fmt.Println(maximumMovieRatings([]int{-1, -2, -3, -4, -5}));
}

func maximumMovieRatings(ratings []int) int {
	includedMovieMaxRatingSoFar, excludedMovieMaxRatingSoFar := 0, 0;
	for _, rating := range ratings {
		prevIncludedMovieMaxRatingSoFar := includedMovieMaxRatingSoFar;
		includedMovieMaxRatingSoFar = rating + max(includedMovieMaxRatingSoFar, excludedMovieMaxRatingSoFar);
		excludedMovieMaxRatingSoFar = prevIncludedMovieMaxRatingSoFar;
	}
	return max(includedMovieMaxRatingSoFar, excludedMovieMaxRatingSoFar);
}