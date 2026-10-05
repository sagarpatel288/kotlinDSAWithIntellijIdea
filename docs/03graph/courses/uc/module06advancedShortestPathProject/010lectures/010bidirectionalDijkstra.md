# Bidirectional Dijkstra

* Assume that there are two friends: A and B.
* They want to meet.
* We have three options: 
  * A travels towards B.
  * B travels towards A.
  * Both travel towards each other.
* Now, it is natural that if they both travel towards each other, they will meet faster.
* If we total the work done by A + the work done by B, it is almost similar to the cost if either only A had traveled towards B or B had traveled towards A.
* But then why do we say that when both travel towards each other, it is faster compared to when only one of them travel towards the other?
* Because when they both travel towards each other, the travel space that each one covers is reduced by almost half the total space.
* This is the general intuition behind Bidirectional Dijkstra.
---
* We saw the idea (intuition) that if both the source and the target travel towards each other, it improves the performance.
* In the practice, we don't travel simultaneously.
* But it doesn't change the fact that we still reduce the search space.
* And we can still get some benefits.
* To understand, we take an example of a circle.
---

![010bidirectionalDijkstra.webp](../../../../../../assets/images/03graph/courses/uc/module06advancedShortestPathProject/part01BidirectionalDijkstra/010bidirectionalDijkstra.webp)

* We can see that Bidirectional Dijkstra is almost twice the faster than the normal Dijkstra.
* But there is even more practical insights.
---
* Six Handshakes

![020sixHandshakes.webp](../../../../../../assets/images/03graph/courses/uc/module06advancedShortestPathProject/part01BidirectionalDijkstra/020sixHandshakes.webp)

* We are going to see a significant difference in the performance between a simple Dijkstra's Algorithm Vs. the Bidirectional Dijkstra's Algorithm.
* Assume that in a social network, a person knows 100 people, and each one of them knows another 100 people, and each one of them knows another 100 people, and so on...
* We can represent it in the form of circles. 
* We start with one person, and we draw a circle around the person to indicate the direct connection between that person and the 100 people.
* And because each one of this 100 people knows another 100 people, we draw another circle around those 100 people and so on...
* The two closest circles indicate the direct connections.
* Otherwise, every time we go beyond the direct connections, it is like crossing a level.
* Each level grows by 100 times.
* So, in the first level, we have 100 people ($100^1$).
* In the second level, we get 100 * 100 people ($100^2$).
* In the third level, we get 100 * 100 * 100 people ($100^3$).
* And so on...
* In the sixth level, we get ($100^6$) = 1 Trillion people.
---
* 
* Now, assume that we want to find a target person from the last level.
* The conceptual work for the normal Dijkstra's Algorithm would be to visit approximately $100^6 = 10^{12}$ people.
* On the other hand, if we use the Bidirectional Dijkstra, it becomes $100^3 + 100^3 = 2 * 10^6$.
* This difference is enormous.
---
* But how do we travel backward from the target vertex T to the source vertex S?
* 


## Questions

* Why is it so that Bidirectional Dijkstra is even faster in the case of social network than compared to the road network?
* Why is this applicable to undirected and non-negative weighted graph only?
* How is it possible that C1 has radius 2r, but its area is 4 pie r square instead of 2 pie r square?
* 