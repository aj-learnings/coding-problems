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

#include <bits/stdc++.h>
using namespace std;

int minimumFloatingPointsRequired(vector<int> locations) {

    int n = (int)locations.size();
    vector<int> rightMost(n+1, 0);
    for (int i=1; i<=n; i++) {
        int start = max(i-locations[i-1], 1), end = min(i+locations[i-1], n);
        rightMost[start] = max(rightMost[start], end);
    }

    int currentEndPos = 1, endPosAchievable = 1, floatingPoints = 0;
    for (int i=1; i<=n; i++) {
        if (i > currentEndPos) {
            currentEndPos = endPosAchievable;
            floatingPoints += 1;
        }
        endPosAchievable = max(endPosAchievable, rightMost[i]);
    }
    return floatingPoints + (endPosAchievable < n);
}

int main () {

    cout << minimumFloatingPointsRequired({ 1, 2, 1 }) << "\n";
    cout << minimumFloatingPointsRequired({ 2, 0, 0, 0}) << "\n";
    cout << minimumFloatingPointsRequired({ 3, 4, 1, 1, 0}) << "\n";

    cin.get();
    return 0;
}