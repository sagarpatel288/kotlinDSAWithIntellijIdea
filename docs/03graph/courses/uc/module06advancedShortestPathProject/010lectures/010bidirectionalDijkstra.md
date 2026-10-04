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
* To be continued...