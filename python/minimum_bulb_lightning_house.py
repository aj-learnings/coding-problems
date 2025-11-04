 # You are given startTime and endTime of n houses. Each house will use a bulb from startTime 
 # till endTime. A bulb will become free and can be used by other house if its usage is finished. 
 # Also, one bulb can be used in maximum `threshold` houses.
 # Your task is to return the minimum number of bulbs required to light all the houses.
 # Sample Input 1 -
 # houses - [{1, 10}, {40, 50}, {30, 40}, {10, 20}, {20, 30}], threshold - 2
 # Sample Output 1 - 3
 # Sample Input 2 - 
 # houses - [{1, 10}, {40, 50}, {30, 40}, {10, 20}, {20, 30}], threshold - 10
 # Sample Output 2 - 1

from typing import List
import heapq

class Node(object):
    def __init__(self, time: int, bulb: int):
        self.time = time
        self.bulb = bulb

    def __lt__(self, other):
        return self.time < other.time

class House(object):
    def __init__(self, startTime: int, endTime: int):
        self.startTime = startTime
        self.endTime = endTime
    
    def __lt__(self, other):
        if self.startTime == other.startTime :
            return self.endTime < other.endTime
        return self.startTime < other.startTime

def minimumBulbsRequired(houses: List[House], threshold: int):
    n = len(houses)
    houses.sort()
    currentBulbNumer, bulbOccurences, freeBulbs = 0, [0] * (n+1), []
    heap: List[Node] = []
    for house in houses:
        while len(heap) > 0 and heap[0].time <= house.startTime :
            bulb = heap[0].bulb
            if bulbOccurences[bulb] < threshold:
                freeBulbs.append(bulb)
            heapq.heappop(heap)
        if len(freeBulbs) == 0 :
            currentBulbNumer += 1
            bulbOccurences[currentBulbNumer] += 1
            heapq.heappush(heap, Node(house.endTime, currentBulbNumer))
        else :
            bulbOccurences[freeBulbs[-1]] += 1
            heapq.heappush(heap, Node(house.endTime, freeBulbs[-1]))
            freeBulbs.pop()

    return currentBulbNumer

if __name__ == "__main__":
   
    houses = [ House(1, 10), House(40, 50), House(30, 40), House(10, 20), House(20, 30) ]
    print(minimumBulbsRequired(houses, 2))
    print(minimumBulbsRequired(houses, 10))