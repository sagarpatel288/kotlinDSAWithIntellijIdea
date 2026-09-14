# Fastest Route (Foundation of Dijkstra's Algorithm)

* So far, from the previous lectures, we have learned to find the shortest path from A to B.
* However, we defined the shortest path in terms of edges.
* But there can be a condition where a direct path is actually longer than the subroutines.
* For example:

* ![Fastest Path Vs. Edges.webp](../../../../../../assets/images/03graph/courses/uc/module04pathsInGraph02/01fastestRoute/010fastestPath.webp)

* It means that it is not necessary that the shortest path is the fastest path if the definition of the shortest path still holds, which depends on the number of edges.
* However, it can be the other way around as well.
* So, it is also possible that the path with a fewer edges is indeed the shortest path.

* ![Fastest Path 02.webp](../../../../../../assets/images/03graph/courses/uc/module04pathsInGraph02/01fastestRoute/020fastestPath02.webp)

* So, whether the path is the shortest or not really depends on the weight of the edges.
* Here, the weight is in the sense of some value.
* It can represent distance, time, or anything.
* All the edges represent the same measurement unit or measurement family or context.
* And each value is positive.
* For example, we might want to convey how much time we will take to travel from A to B or what will be the total distance between A and B.
* So, we don't want these values to be negative.
* This is known as the case where edges have non-negative weights (values).
* Sometimes we use these terms interchangeably: weights, length, distance, values.
---
* It means that we need to know the edge weight to determine the shortest path.
* For example:

* ![Fastest Path Known-Unknown 01.webp](../../../../../../assets/images/03graph/courses/uc/module04pathsInGraph02/01fastestRoute/030fastestPath03.webp)

* As shown in the image above, we cannot determine if the distance between C to B is the shortest distance.
* Because we don't know the total distance of the subroutines for which we don't know the weights yet.
* For example, if the distance between C to D is 2 Km and D to B is also 2 Km, then this subroutine, from C to B via D is actually the shortest path.
* But it could be the case that the distance between C to D is 3 Km and D to B is also 3 Km, then the distance from C to B is indeed the shortest path.
* It means that we need to know the weight of the edges to determine the shortest path.
---
* However, we don't always need to know the weight of all the edges to determine the shortest path.

* ![Fastest Path Known-Unknown 01.webp](../../../../../../assets/images/03graph/courses/uc/module04pathsInGraph02/01fastestRoute/030fastestPath03.webp)

* For example, we know that the distance between A to B is 1 Km. 
* Now, even though we don't know the distance between C → D → B, (which are the parts of the subroutine A → C → D → B), we are sure that it cannot be shorter than the direct path A → B.
* And we could say it deterministic because we know the distance between A and C is already higher than the direct distance between A and B.
* It means that any subroutine that starts from A and reaches B via C must be longer than the direct path from A to B.
* This highlights the importance of knowing the adjacent vertices.
* So, knowing the adjacent distance is still helpful.