
 # You are given ratings of n movies. Rating can be negative as well as positive.
 # Your task is to maximize the ratings by selecting a subsequence of movies with the 
 # condition that not more than one movie can be skipped continuosly.
 # Sample Input 1 -
 # ratings - [5, -1, -2, 3, 4]
 # Sample Output 1 - 11 (The 3rd movie can be skipped)
 # Sample Input 2 - 
 # ratings - [-1, -2, -3, -4, -5]
 # Sample Output 2 - -6 (1st, 3rd, 5th movie can be skipped)

def maximumMovieRatings(ratings):
    includedMovieMaxRatingSoFar, excludedMovieMaxRatingSoFar = 0, 0
    for rating in ratings:
        prevIncludedMovieMaxRatingSoFar = includedMovieMaxRatingSoFar
        includedMovieMaxRatingSoFar = rating + max(includedMovieMaxRatingSoFar, excludedMovieMaxRatingSoFar)
        excludedMovieMaxRatingSoFar = prevIncludedMovieMaxRatingSoFar
    return max(includedMovieMaxRatingSoFar, excludedMovieMaxRatingSoFar)

if __name__ == "__main__":
   
   print(maximumMovieRatings([ 5, -1, -3, 3, 4 ]))
   print(maximumMovieRatings([ -1, -2, -3, -4, -5 ]))
