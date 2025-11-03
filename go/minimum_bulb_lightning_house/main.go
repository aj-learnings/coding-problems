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

package main;

import "container/heap"
import "sort"
import "fmt"

func main() {
	fmt.Println(minimumBulbsRequired([]House{ {1, 10}, {40, 50}, {30, 40}, {10, 20}, {20, 30} }, 2));
	fmt.Println(minimumBulbsRequired([]House{ {1, 10}, {40, 50}, {30, 40}, {10, 20}, {20, 30} }, 10));
}

func minimumBulbsRequired(houses []House, threshold int) int {
	n := len(houses);
	usedBulbsDetail := &PriorityQueue{};
    heap.Init(usedBulbsDetail);
	bulbOccurences := make([]int, n+1);
	var freeBulbs []int;
	currentBulbNumber := 0;

	sort.Slice(houses, func(i, j int) bool {
        if houses[i].startTime == houses[j].startTime {
            return houses[i].endTime < houses[j].endTime
        }
        return houses[i].startTime < houses[j].startTime
    });

	for _, house := range houses {
		for usedBulbsDetail.Len() > 0 && (*usedBulbsDetail)[0].finishedTime <= house.startTime {
			entry := heap.Pop(usedBulbsDetail).(Node);
			if bulbOccurences[entry.bulb] < threshold {
				freeBulbs = append(freeBulbs, entry.bulb);
			}
		}
		if (len(freeBulbs) == 0) {
			currentBulbNumber += 1;
			bulbOccurences[currentBulbNumber]++;
			heap.Push(usedBulbsDetail, Node{
				finishedTime: house.endTime,
				bulb:         currentBulbNumber,
			});
		} else {
			bulbOccurences[freeBulbs[len(freeBulbs)-1]] += 1;
			heap.Push(usedBulbsDetail, Node{
				finishedTime: house.endTime,
				bulb:         freeBulbs[len(freeBulbs)-1],
			});
			freeBulbs = freeBulbs[:len(freeBulbs)-1]
		}
	}
	return currentBulbNumber;
}

type House struct {
	startTime int
	endTime int
}

type Node struct {
    finishedTime int
    bulb         int
}

type PriorityQueue []Node

func (pq PriorityQueue) Len() int { return len(pq) }
func (pq PriorityQueue) Less(i, j int) bool {
    return pq[i].finishedTime < pq[j].finishedTime // min-heap
}
func (pq PriorityQueue) Swap(i, j int) { pq[i], pq[j] = pq[j], pq[i] }

func (pq *PriorityQueue) Push(x any) {
    *pq = append(*pq, x.(Node))
}

func (pq *PriorityQueue) Pop() any {
    old := *pq
    n := len(old)
    item := old[n-1]
    *pq = old[0 : n-1]
    return item
}


