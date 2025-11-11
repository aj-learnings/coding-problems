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

 #include<bits/stdc++.h>
 using namespace std;

 int minimumCoverLength(vector<int> positions, int k) {

    sort(positions.begin(), positions.end());
    int result = positions.back();
    for (int i=k-1; i<(int)positions.size(); i++) {
        result = min(result, positions[i]-positions[i-k+1]+1);
    }
    return result;
 }

 int main() {

    cout << minimumCoverLength({ 8, 2, 17, 10}, 3) << "\n";
    cout << minimumCoverLength({ 11, 3, 23, 7, 12, }, 2) << "\n";
    cin.get();
    return 0;
 }