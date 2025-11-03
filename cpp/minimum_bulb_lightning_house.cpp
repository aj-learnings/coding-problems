/**
 * You are given startTime and endTime of n houses. Each house will use a bulb from startTime 
 * till endTime. A bulb will become free and can be used by other house if its usage is finished. 
 * Also, one bulb can be used in maximum `threshold` houses.
 * Your task is to return the minimum number of bulbs required to light all the houses.
 * Sample Input 1 -
 * houses - [{1, 10}, {40, 50}, {30, 40}, {10, 20}, {20, 30}], threshold - 2
 * Sample Output 1 - 3
 * Sample Input 2 - 
 * houses - [{1, 10}, {40, 50}, {30, 40}, {10, 20}, {20, 30}], threshold - 10
 * Sample Output 2 - 1
 */


#include <bits/stdc++.h>
using namespace std;

class House {
public:
    int startTime;
    int endTime;
    
    House(): startTime(0), endTime(0) {}
    House(int startTime, int endTime): startTime(startTime), endTime(endTime) {}
};

class Node {
public:
    int finishedTime;
    int bulb;

    Node(): finishedTime(0), bulb(0) {}
    Node(int finishedTime, int bulb): finishedTime(finishedTime), bulb(bulb) {}
};

struct CompareNode {
    bool operator()(const Node &a, const Node &b) const {
        return a.finishedTime > b.finishedTime;
    }
};

int minimumBulbsRequired(vector<House>& houses, int threshold) {
    
    priority_queue<Node, vector<Node>, CompareNode> usedBulbsDetail;
    vector<int> bulbOccurences((int)houses.size()+1, 0);
    vector<int> freeBulbs;
    int currentBulbNumber = 0;

    sort(houses.begin(), houses.end(), [](const House &a, const House &b) {
        if (a.startTime == b.startTime) {
            return a.endTime < b.endTime;
        }
        return a.startTime < b.startTime;
    });
    
    for (auto house: houses) {
        while(!usedBulbsDetail.empty() && usedBulbsDetail.top().finishedTime <= house.startTime) {
            auto entry = usedBulbsDetail.top();
            if (bulbOccurences[entry.bulb] < threshold) {
                freeBulbs.push_back(entry.bulb);
            }
            usedBulbsDetail.pop();
        }
        if (freeBulbs.empty()) {
            bulbOccurences[++currentBulbNumber]++;
            usedBulbsDetail.push(Node(house.endTime, currentBulbNumber));
        } else {
            bulbOccurences[freeBulbs.back()]++;
            usedBulbsDetail.push(Node(house.endTime, freeBulbs.back()));
            freeBulbs.pop_back();
        }
    }

    return currentBulbNumber;
}

int main() {

    vector<House> houses { House(1, 10), House(40, 50), House(30, 40), House(10, 20), House(20, 30) };
    cout << minimumBulbsRequired(houses, 2) << "\n";
    cout << minimumBulbsRequired(houses, 10) << "\n";
    
    cin.get();
    return 0;
}
