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

import java.util.List;

class MaximumMovieRatingWithSubsequenceSelectionConstraint {
    public static void main(String[] args) {
        
        System.out.println(maximumMovieRatings(List.of(5, -1, -2, 3, 4)));
        System.out.println(maximumMovieRatings(List.of(-1, -2, -3, -4, -5)));
    }

    private static int maximumMovieRatings(List<Integer> ratings) {
        Integer includedMovieMaxRatingSoFar = 0, excludedMovieMaxRatingSoFar = 0;
        for(Integer rating: ratings) {
            var prevIncludedMovieMaxRatingSoFar = includedMovieMaxRatingSoFar;
            includedMovieMaxRatingSoFar = rating + Math.max(includedMovieMaxRatingSoFar, excludedMovieMaxRatingSoFar);
            excludedMovieMaxRatingSoFar = prevIncludedMovieMaxRatingSoFar;
        }
        return Math.max(includedMovieMaxRatingSoFar, excludedMovieMaxRatingSoFar);
    }
}