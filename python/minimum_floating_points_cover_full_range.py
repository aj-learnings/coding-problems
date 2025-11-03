 # You are given locations of n floating points. Each floating point will cover the 
 # range max((i-locations[i]), 1) to min((i+locations[i]), n).
 # Your task is to return the minimum number of floating points required to cover the
 # complete range.
 # Sample Input 1 -
 # locations - [1, 2, 1]
 # Sample Output 1 - 1 (The 2nd floating is enough to cover the whole range)
 # Sample Input 2 - 
 # locations - [2, 0, 0, 0]
 # Sample Output 2 - 2 (1st and 4th floating point are enough to cover the whole range)
 # Sample Input 3 - 
 # locations - [3, 4, 1, 1, 0]
 # Sample Output 2 - 1 (The 2nd floating is enough to cover the whole range)

def minimumFloatingPointsRequired(locations):
    n = len(locations)
    rightMost = [0] * (n+1)
    for i in range(1,n+1):
        start = max(i-locations[i-1], 1)
        end = min(i+locations[i-1], n)
        rightMost[start] = max(rightMost[start], end)
    currentEndPos , endPosAchievable, floatingPoints = 1, 1, 0
    for i in range (1, n+1):
        if i > currentEndPos:
            currentEndPos = endPosAchievable
            floatingPoints += 1
        endPosAchievable = max(endPosAchievable, rightMost[i])
    return floatingPoints + (1 if endPosAchievable < n else 0)

if __name__ == "__main__":

    print(minimumFloatingPointsRequired([ 1, 2, 1 ]))
    print(minimumFloatingPointsRequired([  2, 0, 0, 0 ]))
    print(minimumFloatingPointsRequired([ 3, 4, 1, 1, 0 ]))