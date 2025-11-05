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

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.PriorityQueue;

class House {
    int startTime;
    int endTime;

    House(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

class Node {
    int time;
    int bulbNumber;

    Node(int time, int bulbNumber) {
        this.time = time;
        this.bulbNumber = bulbNumber;
    }
};

class MinimumBulbLightningHouse {

    public static void main(String[] args) {

        var houses = new ArrayList<House>( 
            List.of(
                new House(1, 10), 
                new House(40, 50), 
                new House(30, 40), 
                new House(10, 20),
                new House(20, 30) 
            )
        );
        System.out.println(minimumBulbsRequired(houses, 2));
        System.out.println(minimumBulbsRequired(houses, 10));
    }

    private static int minimumBulbsRequired(List<House> houses, int threshold) {
        houses.sort((a, b) -> {
            if (a.startTime == b.startTime) {
                return Integer.compare(a.endTime, b.endTime);
            }
            return Integer.compare(a.startTime, b.startTime);
        });

        var n = houses.size();
        List<Integer> bulbOccurence = new ArrayList<>(Collections.nCopies(n+1, 0));
        var currentBulbNumber = 0;
        Deque<Integer> freeBulbs = new ArrayDeque<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.time, b.time)
        );

        for (House house: houses) {
            while (!pq.isEmpty() && pq.peek().time <= house.startTime) {
                if (bulbOccurence.get(pq.peek().bulbNumber) < threshold) {
                    freeBulbs.push(pq.peek().bulbNumber);
                }
                pq.poll();
            }
            if (freeBulbs.isEmpty()) {
                currentBulbNumber += 1;
                bulbOccurence.set(currentBulbNumber, bulbOccurence.get(currentBulbNumber)+1);
                pq.offer(new Node(house.endTime, currentBulbNumber));
            } else {
                bulbOccurence.set(freeBulbs.peek(), bulbOccurence.get(freeBulbs.peek())+1);
                pq.offer(new Node(house.endTime, freeBulbs.peek()));
                freeBulbs.pop();
            }
        }

        return currentBulbNumber;
    }
}

