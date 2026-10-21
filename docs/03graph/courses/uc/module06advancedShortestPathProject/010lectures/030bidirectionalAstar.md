# Bidirectional A*

## Prerequisites

* [Dijkstras Algorithm.md](../../module04pathsInGraph02/010lectures/030dijkstrasAlgorithm.md)
* [Bidirectional Dijkstra.md](010bidirectionalDijkstra.md)
* [Bidirectional Astar.md](030bidirectionalAstar.md)

## Thought Process

* Let us recall the overview of the bidirectional Dijkstra's process.
* We search in two directions: Forward and backward.
* We take 2 arrays to store the distance: Forward and backward.
* We take 2 priority queues: Forward and backward.
* We store `(distance, vertex)` in each queue.
* The `distance` is the actual distance from the source vertex to the `vertex`.
* The distance associated with the polled vertex is the final.
* The vertex cannot get a smaller (shorter) distance than that.
* After that, we inspect the outgoing edges of this polled vertex.
* Each edge has two vertices: Start and end.
* The polled vertex is the start vertex (say, `u`).
* And at the end of the outgoing edge, we get the end vertex as: (to, weight).
* If the end vertex (say, `v`) that we get from the outgoing edge has a tentative distance stored in the relevant distance array of the other side, then:
* `forwardDist[u] + weight(u, v) + backwardDist[v]` = `complete path`.
* If this complete path is less than the shortest complete path found so far, then we update the global `min` or `shortestPathDistance` variable.
* It means that the global `min` is our upper bound, and it expects a lower value.
* Also, the item that we peek or poll from the queue cannot be smaller than the previous item, because we do not allow negative weights.
* It means that the item that we peek or poll from the queue is our lower bound.
* It says that "the next item cannot be smaller than me!".
* All these together, give us the stop condition:
* Stop when: `lowerBound >= upperBound`.
* I.e., stop when: `forwardPeek + backwardPeek >= min`.
* That is to say, stop when: Lowest distance in the forward search queue + the lowest distance in the backward search queue >= current global shortest complete path. 
* Because it is mathematically guaranteed that if the current `forwardPeek + backwardPeek >= min`, then no future `forwardPeek + backwardPeek` can be smaller than the current global shortest path, `min`.
* One of the critical parts that makes this guarantee possible is the fact that we store the actual distance in the priority queues.
* However, in the A* algorithm, we store:
* `f(v)`, which is `f(v) = g(v) + h(v)`.
* `f(v)` is not the actual distance.
* It is a guess, an estimated value.
* It means that we cannot conclude it definitely that adding `f(v)` of each priority queue will be the final and actual shortest distance.
* The current value of each `f(v)` prevents us from confidently knowing the true lower bound.
* Because even though each estimation is consistent, it does not have to be accurate.
* And on top of that, we have two different estimations (heuristics) for each vertex.
* Think of this like having two different thermometers with different offsets.
* We cannot conclude the final actual temperature by adding their values.
* We must first handle the offset.
* In our case, heuristics are our offsets.
* It means that we cannot add pure and plain `f(v)` to each priority queue.
* We need to modify it in such a way that can give us a helpful mathematical guarantee about the lower bound.
* So, we need to add a modified `f(v)` to each priority queue.
* How do we do that?
* What do we do?
* We start with what we have.

---

* We need `h(v)` to guide the search, but we don't want it to be the part of the calculation that defines the stop condition.
* We know that whenever we `peek` in our priority queues, each of them give us the lowest value.
* We want to use this value to define the stop condition.
* In other words, we want to use this `f(v)` to deterministically (definitely) conclude whether the next value has the possibility to provide the shortest path than the current global `shortestPath`.
* But the problem is that each `f(v)` includes `h(v)`.
* And `h(v)` prevents us from such a definite conclusion.
* So, we want to use maths to come up with a situation where when we add `f(v)` from each priority queue, their corresponding `h(v)` cancels out each other.
* For example, a normal stop condition looks like:

```kotlin

if (forwardMin + backwardMin >= shortestPath) {
    return
}

```

* Now, if the `peek` of the forward search priority queue gives us `fg(v) + x` and the `peek` of the backward search priority queue gives us `bg(v) - x`, then:

$$
\text{forwardMin + backwardMin}
$$
$$
= (fg(v) + x) + (bg(v) - x)
$$
$$
= fg(v) + bg(v)
$$

* We can see that the offset of each `peek` cancels out each other.
* And what remains is the actual values that we can confidently use for the lower bounds.
* But we still have an issue.
* The `fg(v)` represents the actual value from the source to the vertex, v for the forward search priority queue.
* The `bg(v)` represents the actual value from the vertex, v to the target for the backward search priority queue.
* As we have seen in the normal A* algorithm, a heuristic value represents the estimated remaining distance to the goal.
* In a normal A* algorithm, we use only one search direction, and hence only one goal direction: Target.
* But in a bidirectional A* algorithm, we use two searches, and hence two goals.
* The two searches we use in the bidirectional A* algorithm: Forward search and backward search.
* For the forward search, the goal is: Target.
* For the backward search, the goal is: Source.
* Two searches = Two goals = Two heuristics for each vertex.
* Forward search uses `heuristicToTarget[vertex]`, that represents the estimated remaining distance to the target.
* Backward search uses `heuristicToSource[vertex]`, that represents the estimated remaining distance to the source.
* They have different goals.
* They measure different things.
* We want them to share a common goal and measure the same thing.
* But isn't it true that if we connect them, their combined value represents the complete path? 
* That's what we did in the bidirectional Dijkstra, right?
* Yes, if it happens to store only the actual distance values!
* But didn't we say that if we cancel out `+x` and `-x`, what remains is the actual values?
* Yes, but first, we need to convert the forward search queue item `fg(v) + hs(v)` into `fg(v)+ x`. 
* And similarly, backward search queue item `bg(v) + ht(v)` into `bg(v) - x`.
* And then, we will exactly use: `forwardSearchPeek + backwardSearchPeek` confidently.
* So, how do we convert?

---

* We start with what we know.
* We know that weight is associated with an edge.
* For example, weight(u, v) represents the value for the edge(u, v), where u and v are adjacent vertices connected through an edge.
* Similarly, a heuristic value is associated with a vertex.
* For example, h(v) represents the estimated remaining distance (value) from the vertex, v to the goal (target).
* So, the replacement of h(v) should represent some value associated with the corresponding vertex, v.
* For example, it can be h'(v), q(v), or p(v), or in whichever (whatever) way we want to denote (represent) it.
* Conventionally, we start with q(v) and finalize p(v) as we will see it gradually.
* And finally, the heuristic consistency.
* The only information that we know about the heuristics is (or we expect, assume) that the heuristics are consistent, and they satisfy the below consistency condition:

$$
h(u) &le; weight(u, v) + h(v)  
$$
$$
h(u) - h(v) &le; weight(u, v)
$$

* The consistent heuristics convey that for the adjacent vertices (u, v), the difference of their individual heuristics cannot exceed their edge weight.
* For example, let us assume that moving from `u` to `v` actually costs us `5`.
* Then, the difference of their heuristics `h(u) - h(v)` cannot be more than `5`.
* So, if `h(u)` is `20` and `h(v)` is `25`, then it is consistent and admissible.
* But if `h(u)` is `20` and `h(v)` is `40`, then it is inconsistent.
* The entire approach of our bidirectional A* algorithm assumes that we get consistent and admissible (feasible) heuristics.
* It means that if the replacement of h(u) is q(u) and the replacement of h(v) is q(v), then:
* The difference q(u) - q(v) must be consistent (align) with the difference h(u) - h(v).
* The replacement values can be different. 
* But the value about how much it can change while going from u to v should align with (follow) the heuristic consistency.
* For example, the value of q(u) and q(v) can be different from h(u) and h(v) respectively.
* But their difference, q(u) - q(v) should align with (follow) the difference of h(u) - h(v).
* Using this information, we want to come up with some definite stop condition.
* It means that if we want to replace `fg(v) + hs(v)` with `fg(v) + x`, and `bg(v) + ht(v)` with `bg(v) - x`, then we must ensure that the `x` that we use aligns with $h(u) - h(v) &le; weight(u, v)$.
* In other words, if the replacement for `hs(v)`, and `ht(v)` is `x`, then their difference must never exceed the actual edge weight `weight(u, v)`.

---

* Now, the questions is, how do we accommodate and integrate this with our priority queue structure?
* Our goal is... // ToDo: Finish from here...

---

* Recall the mathematical guarantee we used in the **find min from a stack using an array in O(1)** problem:

  * Reference: [Min Stack Using Array.kt](../../../../../../src/courses/uc/course02dataStructures/module01/section02stacksAndQueues/video01Stack/03MinStackUsingArray.kt)

* Similarly, we are looking for a mathematical guarantee when we peek the `min` in the priority queues.

---

* In the A-Star algorithm, each vertex gets two heuristics due to forward and backward search.
* For example, there will be two heuristics for vertex, v as below:

$$
\text{Forward search:}\ h_{S}(v)
$$

* It conveys the estimated remaining distance from the vertex, v to the target.

$$
\text{Backward search:}\ h_{T}(v)
$$

* It conveys the estimated remaining distance from the vertex, v to the source.
* So, they measure different things.
* They represent different things.
* They give different values.
* They have different goals.
* We want to align that.
* We want them to measure and represent the same thing.
* We want them to share a common goal.
* We want to represent `hs(v)` and `ht(v)` in the common term, say `x`.
* We can try to understand this with an analogy.
* Suppose that we have someone that reports the quantity in millilitre, whereas the other person reports the same quantity in liter.
* If we want to them to take a certain action when the quantity reaches a certain level, we need some mathematical transformation of their measurement units that they both can follow.
* In short, we need to align their measurement units, they both need to use the same measurement unit, so that they both can follow the common (shared) goal.
* Similarly, in our current situation, forward search moves towards the target.
* The forward search heuristics indicate the estimated remaining distance to the target.
* The backward search moves towards the source.
* The backward search heuristics indicate the estimated remaining distance to the source.
* If we want to share a common goal with them, we need some mathematical transformation.

---

* Suppose that a couple, A and B want to purchase a house.
* A wants to purchase the house near the target.
* We can think of it like the forward search.
* B wants to purchase the house near the source.
* We can think of it like the backward search.
* If they focus only on their own preference, they both end up with having two different choices.
* Let us assume that the source is at `0` and the target is at `100`.
* As A moves towards the target, their distance from the source is increased, and their distance to the target is decreased.
* Similarly, as B moves toward the source, their distance from the target is increased, and their distance to the source is decreased.
* Now, the house that is 10 km from the source, is 90 km from the target.
* If we take their total, `90 (distance from target) + 10 (distance from source)`, it becomes `100`.
* Similarly, the house that is 50 km from the source, is 50 km from the target.
* If we take their total, `50 (distance from target) + 50 (distance from source)`, it is still `100`.
* And if the house is 90 km from the source, it is 10 km from the target.
* And their total, `10 (distance from target) + 90 (distance from source)` is still `100`.
* In short, we will always get `100` if we take their total using the addition (summation).
* That's the constant heuristic.
* And a constant heuristic turns bidirectional A* into two individual Dijkstra.
* One might finish earlier than the other.
* But we don't get any benefits.
* In fact, we invest double, (two searches, two priority queues, etc.), but gets the same performance as of a normal Dijkstra.
* So, we need to change the way we are trying to align the two searches.

---

* Now, instead of taking their total, we try to take their differences.
* If the house is 10 km from the source, it is 90 km from the target.
* And their difference is `90 (distance from target) - 10 (distance from source) = 80` km.
* The value is positive.
* It means that it is near the source.
* If the house is 50 km from the source, it is 50 km from the target.
* And their difference is `50 (distance from target) - 50 (distance from source) = 0` km.
* The value is zero.
* It means the vertex is in the middle.
* If the house is 90 km from the source, it is 10 km from the target.
* And their difference is `10 (distance from target) - 90 (distance from source) = -80` km.
* The value is negative.
* It means that the vertex is near the target.
* See the pattern.
* We are getting difference answers.
* And if we notice, if the house is near the source, we get a positive value.
* And if the house is in the middle, we get zero `0`.
* And if the house is near the target, we get a negative value.
* So, the difference gives us directional information.

---

**Differences of the heuristics**

* We know that in the bidirectional search, we take two searches.
* Forward and backward.
* Let us first try to visualize and understand it.

* ![030differenceInBidirectionalAstar.webp](../../../../../../assets/images/03graph/courses/uc/module06advancedShortestPathProject/part02AstartAlgorithm/030differenceInBidirectionalAstar.webp)

* Each vertex gets two heuristics: Forward and backward.
* We denote the estimated remaining distance of the vertex, v to the target as: $h_{S}(v)$.
* Similarly, we denote the estimated remaining distance of the vertex, v to the source as: $h_{T}(v)$. 
* So, the vertex, v gets: $h_{S}(v)$ and $h_{T}(v)$.
* We denote their difference as: $q(v) = h_{T}(v) - h_{S}(v)$.
* Why difference?
* Because:
* The positive value indicates that the vertex is near the source and far away from the target.
* The negative value indicates that the vertex is far away from the source and near the target.
* If the difference is `0`, it indicates that the vertex is in the middle.
* Hence, the difference gives us directional information.
* And it aligns with our directed search.
* Think of it like:
* We have the forward search estimation.
* We have the backward search estimation.
* And then we have their difference.
* The difference is the gap.
* The gap indicates how much they disagree with each other!
* So, the gap is the difference between the estimated values of the forward search and the backward search.
* Hence, the difference between both the heuristics of the vertex, v is:

$q(v) = h_{T}(v) - h_{S}(v)$

* What if we take their sum value?
* As we can see in the image, if we take their sum value, it naturally gives the same answer.
* For example, if $h_{S}(A)$ is 2, and $h_{T}(A)$ is 10, their sum is 12.
* For another vertex, if $h_{S}(F)$ is 10, and $h_{T}(F)$ is 2, their sum is also 12.
* So, it doesn't help as much as taking their difference help.
* This is the same reason we don't take their multiplication.
* What if we take division?
* The division gives ratio, which is incompatible with graph algorithms in a sense that we expect a linear mathematical curve where graph weights represent additive, linear costs (mileage).
* For example, when moving from S to T, we want values that consistently (linearly) increases or decreases, not the values that produces curves.

---

* Using the subtraction (the difference) between the forward and backward heuristics, we get directional information.
* But we still have a problem.
* Suppose that the person A travels from the source towards the target.
* We know that as the person moves, their distance from the source increases and their distance to the target decreases.
* Initially, the person is at `0`.
* Now, suppose that the person travels 1 km from the source.
* So, we calculate it as: `99 (distance from target) - 1 (distance from source) = 98`!
* But the person has traveled only 1 km!
* The calculation or representation doubles the progress.
* It is tempting to think that if the result is as much as twice the expected value, then why not just cut the result in the half?
* If we half the result, it becomes `49`, which seems worse.
* There is still something that we need to improve!

--- 

* The problem is that we did not consider and calculate the initial position properly.
* Let us retry.
* We don't just half the result.
* We half the every single step and property we consider and use.
* And we also adjust our method, perspective, and interpretation.
* Initially, the person A is at the source.
* That is, `0` km away from the source, and `100` km away from the target.
* We calculate it as: $\frac{100 - 0}{2} = 50$, as a **shared value**!
* Now, the person moves 1 km away from the source.
* So, it becomes: $\frac{99 - 1}{2} = 49$, as a **shared value**!
* For 1 km of progress, we get exactly 1 km of difference in the **shared value**!  
* Also, the difference 50 - 49 = 1 is a positive value.
* It means that the vertex is near the source.
* Similarly, if the vertex is 90 km away from the source, and 10 km away from the target:
* $\frac{10 - 90}{2} = -40$.
* The value is negative, and the difference 50 - 40 is 10.
* It means that the vertex is near the target, 10 km away from the target.
* Hence, one single value gives us two values: 
* The distance from the source as well as the distance from the target.

---

* The **shared value** is called the **potential**.
* The potential of vertex, v is $p(v)$.
* $p(v) = \frac{q(v)}{2}$. 

---

* Imagine that instead of the actual graph, we take a mathematical copy of it.
* We have some difficulties at the moment to properly measure the shortest path.
* Because each priority queue has `f(v)`, and the addition (summation) of each pure and plain `f(v)` does not give us a reliable `lower bound` for the stop condition.
* So, we take an adjusted graph.
* We will change certain things, but it is guaranteed that the optimal shortest path of the original graph will remain the same in the adjusted graph, too.
* In the adjusted graph, each vertex gets something called, potential.
* We can compare it with our actual (real) theory.
* The heuristics of the original graph are consistent.
* So, our potentials must be consistent, too.
* It means that it must have some mathematical consistency.
* We know that the replacement, the adjustment, the potential that we use must align with the original heuristic consistency.
* Suppose that we denote this potential as `p`.
* Then, we want to ensure that it aligns with:

$$
h(u) - h(v) &le; w(u, v)
$$

* So, we use:

$$
p(u) - p(v) &le; w(u, v)
$$

* The difference `p(u) - p(v)` represents the transition from one vertex (`u`) to another (`v`).
* The amount added when leaving `u` is related to the amount removed when entering or leaving the adjacent vertex, `v` so that the adjustments cancel along a complete route.
* If we rearrange:

$$
0 &le; w(u, v) + p(v) - p(u)
$$

* Which is:

$$
w(u, v) + p(v) - p(u) &ge; 0
$$

* And it satisfies the consistency and non-negative edge-weight invariant.
* With this adjustment, the edge's adjusted weight may differ from its original weight.
* We represent the edge weight of this adjusted graph, which is the mathematical copy of the original graph, as:

$$
w_{p}(u, v)
$$

* So, it becomes:

$$
w_{p}(u, v) = w(u, v) + p(v) - p(u)
$$

* Here, it is guaranteed that the adjusted edge-weight $w_{p}(u, v)$ is non-negative.
* And that the adjusted edge weight is equal to the actual edge weight, plus the difference between the potential of the destination (ending) vertex of the edge and the potential of the starting vertex of the edge.
* This means:

$$
\text{adjustment(u, v)} = p(v) - p(u)
$$

* It conveys:
* Start at `u`, whose potential is `p(u)`.
* Move to `v`, whose potential is `p(v)`.
* Adjust the edge cost (weight) by the change in the potential.
* And this is how, the adjusted edge weight becomes:

$$
w_{p}(u, v) = w(u, v) + p(v) - p(u)
$$

* There is also a critical interpretation here.

> The adjusted edge weight of the edge(u, v), where we are going from u to v, is equal to the original edge weight + the potential of the end-vertex - the potential of the start-vertex.

* What does it do? How does it work? How does it help?

---

* The reason behind this mathematical guarantee of the consistency is interesting.
* Suppose that our forward search path is: S → A → B → v,
* When we use the adjusted edge-weight formula, we get:

$$
w(S, A) + p(A) - p(S) + w(A, B) + p(B) - p(A) + w(B, v) + p(v) - p(B)
$$

$$
w_{p}(S, v) = w(S, v) + p(v) - p(S)
$$

* The critical interpretation here is:

> The adjusted weight of the forward search path is the original weight cost + the potential of the ending vertex - the potential of the source vertex. p(v) - p(S) is critical here.

* If the end-vertex is, x, then it becomes:

$$
w_{p}(S, x) = w(S, x) + p(x) - p(S)
$$

* If the end-vertex is, y, then it will be:

$$
w_{p}(S, y) = w(S, y) + p(y) - p(S)
$$

* This gives us another strong interpretation.

> p(S) is the constant that we get for any adjusted edge-weight during the forward search.

* To understand this, let us assume that we have the following values:

```markdown

2, 4, 7, 12
```


* Now, we increase each value by 2.
* So, it becomes:

```markdown

4, 6, 9, 14
```


* The interesting thing is that increasing each value by the same weight (value) does not change the difference between them.
* For example:

```markdown

(4 - 2) = (6 - 4) = 2
(7 - 4) = (9 - 6) = 3
(12 - 7) = (14 - 9) = 5
(7 - 2) = (9 - 4) = 5
(12 - 4) = (14 - 6) = 8
(12 - 2) = (14 - 4) = 10
```

* Now, the critical part: 
* If we remove the constant value that we added to each value, it does not change their relative value and order to each other.
* For example,

```markdown

(4 - 2) = (6 - 4) = 2 - 2 = 0
(7 - 4) = (9 - 6) = 3 - 2 = 1
(12 - 7) = (14 - 9) = 5 - 2 = 3
(7 - 2) = (9 - 4) = 5 - 2 = 3
(12 - 4) = (14 - 6) = 8 - 2 = 6
(12 - 2) = (14 - 4) = 10 - 2 = 8
```

* In the forward search, $p(S)$ is the value that we always subtract.
* If we ignore it, it does not change the relative value and order.
* For example, suppose that $p(S) = 5$.
* Then:

| Vertex | $G_{f}(v)$ | $p(v)$ | $G'_{f}(v)=G_{f}(v)+p(v)-5$ |
|:------:|:----------:|:------:|:---------------------------:|
|   A    |     10     |   3    |              8              |
|   B    |     7      |   4    |              6              |
|   C    |     12     |   1    |              8              |

* So, the formula for the forward search becomes:

$$
w_{p}(S, v) = w(S, v) + p(v)  
$$

* Similarly, suppose that the backward search path is: T → x → y → v,
* Then, according to the adjusted edge-weight formula, in the original graph, we get:

$$
w(v, y) + p(y) - p(v) + w(y, x) + p(x) - p(y) + w(x, T) + p(T) - p(x)
$$

$$
w_{p}(v, T) = w(v, T) + p(T) - p(v)
$$

* Similar to the forward search, we always get $p(T)$ in the backward search.
* In the backward search, $p(T)$ is the value that we always add.
* If we ignore it, it does not change the relative value and order.
* For example, suppose that $p(T) = 8$.
* Then:

| Vertex | $G_{b}(v)$ | $p(v)$ | $G'_{b}(v) = G_{b}(v) + 8 - p(v)$ | $G_{b}(v)-p(v)$ |
|:------:|:----------:|:------:|:---------------------------------:|:---------------:|
|   A    |     10     |   3    |                15                 |        7        |
|   B    |     7      |   4    |                11                 |        3        |
|   C    |     12     |   1    |                19                 |       11        |

* So, the formula for the backward search becomes:

$$
w_{p}(v, T) = w(v, T) - p(v)
$$

* This is how we get `+x` for the forward search and `-x` for the backward search.
* Now, if we add forward search and backward search, it becomes:

$$
w_{p}(S, v) + w_{p}(v, T) = w(S, v) + p(v) + w(v, T) - p(v)
$$

$$
w_{p}(S, v) + w_{p}(v, T) = w(S, v) + w(v, T)
$$

* This is how the potentials cancel each other out.
* The magical part is, almost all the potentials cancel out, and what remains is the actual edge weight + the difference between the start-vertex's potential and the end-vertex's potential!
* This is how we get the actual values from `forward search priority + backward search priority` that we can use confidently as a lower bound!
* And this is the result of the adjusted graph.
* 
---

* So, we can say that the value of the shortest path is the actual value + the difference between the end-vertex's potential and the start-vertex's potential.
* The shortest path itself of the original graph and the shortest path in the adjusted graph remain the same.
* Changing their numerical values consistently does not change the actual shortest path.
* But we cannot adjust the graph by any random, arbitrary values.
* We want to ensure that it does not produce negative edge weight.
* Because our algorithm does not work reliably when an edge can have a negative weight.
* So, how do we ensure that?
* Our potential formula is:

$$
w_{p}(S, T) = w(S, T) + p(T) - p(S)
$$

* We can re-write it in the general form to apply it on any vertex as below:

$$
w_{p}(u, v) = w(u, v) + p(v) - p(u)
$$

* To ensure that $w_{p}(u, v)$ is non-negative, it must be true that: 

$$
w_{p}(u, v) &ge; 0
$$

* We can substitute it as:

$$
w(u, v) + p(v) - p(u) &ge; 0
$$

$$
|\ p(u) - p(v)\ | &le; w(u, v)
$$

* It means that the change in the potential while going from `u` to `v` can be up to (at most) $w(u, v)$.
* It aligns with our heuristic consistency.
* They both have the same form.
* We know the heuristic consistency, which is as below:

$$
h(u) &le; weight(u, v) + h(v)
$$

$$
h(u) - h(v) &le; weight(u, v)
$$


* How do we integrate this insight and information?
* We talked about the potential consistency and rules.
* But what will be the potential for each vertex?
* What will be the value of this `p`?

---

* Now, we are trying to connect and relate heuristics, potentials, their individual differences, etc., together to... (complete this.)
* We know that the potential must satisfy the below expression:

$$
|\ p(u) - p(v)\ | &le; w(u, v)
$$

* And we also know the heuristic consistency as below:

$$
|\ h_{S}(u) - h_{S}(v)\ | &le; w(u, v)
$$

* And it is true for the backward search, too.

$$
|\ h_{T}(u) - h_{T}(v)\ | &le; w(u, v)
$$

* And we know that:

$$
q(v) = h_{T}(v) - h_{S}(v)
$$

* Now, we want to know how much change we can get in the potentials while going from `u` to `v` so that (complete this... why are we trying to know this?)
* So, it becomes:

$$
q(u) - q(v) = |\ (h_{T}(u) - h_{S}(u)) - (h_{T}(v) - h_{S}(v))\ |  
$$

$$
= |\ (h_{T}(u) - h_{T}(v))\ | + |\ (h_{S}(u) - h_{S}(v))\ |  
$$

$$
&le; 2\ weight(u, v)
$$

$$
q(u) - q(v) &le; 2\ weight(u, v)
$$

* It means that the change (difference) in the $q$ while going from `u` to `v` be up to (at most) $2\ weight(u, v)$.
* And earlier, we have derived that the change (difference) in the potential, $p$ while going from `u` to `v` can be up to (at most) $\ weight(u, v)$ only.
* If we directly use $q$ as the potential, we might get the change up to (at most) $2\ weight(u, v)$.
* But we have seen it earlier that to ensure the non-negative edge-weight, this change in the potential must be at most $weight(u, v)$.
* So, we use $\frac{q}{2}$ as the potential instead of $q$.
* That makes this change in the potential at most $weight(u, v)$.
* Hence, the potential for each vertex becomes $p = \frac{q}{2}$.
* It means that the potential of vertex, v is $p(v) = \frac{q(v)}{2}$.
* And now, if we replace $q(v)$ with its actual value (definition), we get our final potential function for the vertex, v as:

$$
p(v) = \frac{h_{T}(v) - h_{S}(v)}{2}
$$

* This is the exact value of our potential `p(v)` that we are going to use.
* Where and how do we use this potential?

---

**The seesaw analogy.**

* Suppose that side A is higher than side B.
* In other words, side B has more weight than side A.
* So, side B is near the ground and side A is far away from the ground.
* If we want to balance them both, what do we need to do?
* We add some weight on side A and remove some weight from side B.
* Similarly, to balance the things, we will add positive potential to the forward search.
* And we will add negative potential to the backward search.

---

* We have the final potential value to replace the heuristics:
* Recall the potential formula with the adjusted weight:

$$
w_{p}(S, T) = w(S, T) + p(T) - p(S)
$$

* What does it indicate?
* 

---

* 

---

**TL;DR**

* Each priority gets: `f(v) = g(v) + h(v)`, where `g(v)` is the actual distance value, and `h(v)` is an estimated remaining distance value from the vertex.
* So, we cannot use the existing structure of `f(v)` to confidently provide the lower bound.
* Hence, we want to replace `h(v)` of each `f(v)` with `x` in such a way that when we add `f(v)` of each priority queue, the `x` of each `f(v)` cancels each other out.
* So that what remains are the actual values that we can use to confidently provide the lower bound.
* Now, if we want to replace `h(v)` with `x`, then this `x` must follow the same consistency and invariants that `h(v)` follows.
* `h(u) <= w(u, v) + h(v)`.
* `h(u) - h(v) <= w(u, v)`.
* The heuristic consistency says that the difference between the heuristic values of two adjacent vertices `(u, v)` must be at most `weight(u, v)`.
* It means that the difference of the replacement should also be at most `weight(u, v)` for the `edge(u, v)`.
* Suppose that we denote this replacement term as, `q`.
* Now, we take `q = h(u) - h(v)`.
* Here, `h(u)` represents the heuristic value of the vertex, `u`.
* And similarly, `h(v)` represents the heuristic value of the vertex, `v`.
* So, `q` represents the difference between the heuristic values of the adjacent vertices `(u, v)` for the `edge(u, v)`.
* In a bidirectional A* search, each vertex gets two heuristics.
* So, the vertex, `u` gets: `hf(u)` and `hb(u)`.
* Similarly, the vertex, `v` gets: `hf(v)` and `hb(v)`.
* So, their difference becomes: `{ hf(u) + hb(u) } - { hf(v) + hb(v) }`.
* For a bidirectional (undirected) graph: `| { hf(u) + hb(u) } | - | { hf(v) + hb(v) } |`.
* Which is: `| hf(u) - hf(v) | + | hb(u) - hb(v) |`.
* And as per the heuristic consistency: `<= weight(u, v) + weight(u, v)`
* Which is: `2weight(u, v)`.
* So, this is a mismatch with our consistency invariant.
* Because the difference must be at most `weight(u, v)`.
* So, instead of taking `q`, we take `q/2`.
* Finally, the replacement becomes: `p = q/2`.
* We call it, potential.
* If we substitute `q` with its actual value, it becomes:
* $p = \frac{hb - hf}{2}$.
* So, for any vertex `u`, its potential will be: $p(u) = \frac{hb(u) - hf(u)}{2}$.
* We can represent the backward search heuristic value of `u` as $h_T(u)$ instead of $hb(u)$.
* Similarly, we can represent the forward search heuristic value of `u` as $h_S(u)$ instead of $hf(u)$.
* But the underlying meaning remains the same.
* So, finally, instead of `h`, we add this potential value, `p` to each priority queue.
* And we use `+p` for the forward search priority queue, and `-p` for the backward search priority queue.
* So that when we `peek`, and perform `forwardMin + backwardMin`, they cancel each other out.
* So, finally, we replace `g(v) + h(v)` with `fg(v) + p` for the forward search, and `bg(v) - p` for the backward search priority queue.
* So, for the forward search priority queue, it becomes:

$$
fg(v) + \frac{h_T(v) - h_S(v)}{2}
$$

* And for the backward search priority queue, it becomes:

$$
bg(v) - \frac{h_T(v) - h_S(v)}{2}
$$

* When we `peek`, and perform `forwardMin + backwardMin`, it becomes:

$$
fg(v) + bg(v)
$$

* Which is the value that we can confidently use for the lower bound.

---

* The value that we add to the forward search priority queue is:

$$
f(v) = fg(v) + \frac{h_{T}(v) - h_{S}(v)}{2}
$$


* And the value that we add to the backward search priority queue is:

$$
b(v) = bg(v) - \frac{h_{T}(v) - h_{S}(v)}{2}
$$

* We perform their summation to get the lower bound.

$$
f(v) + b(v) 
$$

* If it is greater than or equal to the global shortest complete path, we return (exit).

```kotlin

if (f(v) + b(v) >= min) {
    return    
}

```

* How do we set the global shortest complete path?
* When we inspect the outgoing edges of the polled vertex, we get a complete path.
* If the polled vertex is `u`, and the outgoing edge is edge(u, v), then:

$$
\text{Complete path} = \text{forwardSearchDist[u] + weight(u, v) + backwardSearchDist[v]}
$$

* If this complete path is smaller than the current global shortest path distance, then we update (replace) the global shortest path with this complete path value. 

---

* The potential part of the equation is fine, but it can introduce fraction because of the division by 2.
* If we want non-decimal (whole number, whole integer) value, then we multiply by 2:

$$
2f(v) = 2fg(v) + 2 * \frac{h_{T}(v) - h_{S}(v)}{2} = 2fg(v) + (h_{T}(v) - h_{S}(v)) 
$$

* And

$$
2b(v) = 2bg(v) - 2 * \frac{h_{T}(v) - h_{S}(v)}{2} = 2bg(v) - (h_{T}(v) - h_{S}(v))
$$

* It means that we would add as much as twice the actual value to each priority queue.
* It means that when we perform their addition for the lower bound, `f(v) + b(v)`, it is as much as twice the actual value.
* So, to make the comparison fair, when we compare this with the upper bound (the global shortest path distance value), we also multiply the upper bound by 2.

$$
\text{ lower bound} >= 2 * \text{ upper bound}
$$

```kotlin

if (lowerbound >= (2 * upperBound)) {
    return
}

```

//ToDo: 

* I am unsure about this order, but we need to check.
* I believe `+p` and `-p` proof comes before `<= 2weight(u, v)` problem.
* Because when we apply the heuristic difference consistency to some path, (S, T), we observe and prove that.
* We observe and prove that for the forward search, `p(S)` is constant, and we can ignore it.
* So, for the forward search, the formula becomes: `fg(v) + p(v)`.
* Similarly, we observe and prove that for the backward search, `p(T)` is constant, and we can ignore it.
* So, for the backward search, the formula becomes: `bg(v) - p(v)`.
* But then we observe another problem.

---

## Steps

**Input:**

* We get total vertices, total edges, and for each edge: from, to, weight.
* For each vertex: Heuristic value from source and to target.
* Source and target.

**Output**

* The shortest path distance from the source to the target.

**Steps**

* Build the graph using the adjacency list.
* Why? Because bidirectional A* also uses the core logic of Dijkstra.
* 2 searches: Forward and backward.
* 2 arrays to store the distance: forwardDist and backwardDist.
* 2 arrays to store the parents: forwardParents and backwardParents.
* 2 priority queues to explore the graph.
* Each priority queue stores: f(v), g(v), vertex.
* f(v) represents the adjustment.
* For the forward search priority queue, fs(v) = 2fg(v) + p(v)
* For the backward search priority queue, bs(v) = 2bg(v) - p(v)
* g(v) is the actual value.
* And potential p(v) = heuristicsToTarget(v) - heuristicsFromSource(v).
* We start with the source.
* The actual distance value from the source to the source is 0.
* So, fs(source) = p for the source.
* Similarly, for the backward search priority queue, bs(source) = -p.
* Now, as long as none of the queues are empty, we repeat.
* We peek through each queue.
* If (forwardMin + backwardMin) >= 2 * bestDistance, we return the result.
* Otherwise, we proceed with the queue that has a smaller size.
* We poll, and inspect outgoing edges.
* We attempt edge relaxation.
* For every successful edge relaxation:
* We update the corresponding priority queue, and parents.

---

## Questions

* What is the ultimate goal of these complicated process, formulas, transformation, replacement, etc.? Why is it more complex than the bidirectional Dijkstra?
  * The bidirectional Dijkstra uses actual values.
  * Each priority queue contains only the actual values, `g(v)`.
  * The A-Star (A*) algorithm is a guided search.
  * It uses heuristics (estimated values) along with the actual values.
  * Each priority queue contains `f(v) = g(v) + h(v)`, where `h(v)` is the estimated value.
  * When the actual value is mixed with the estimated values, it becomes difficult to provide the stop condition confidently.
  * We want to filter out those heuristics and retain only the actual values when we perform summation (addition) of each priority queue's `peek` values.
  * So that we can confidently provide the stop condition.
  * 
* Why do we take something called, potential instead of the existing heuristics?
  * We can call anything, not necessarily the potential.
  * In fact, what we call potential is a special combination of the existing heuristics only.
  * Why this arrangement? Because we want to replace the existing heuristics in such a way that when we perform addition of each `peek` item, the heuristics cancel each other out, and what remains should be the factual data.
  * Why do we want to filter the heuristics and retain only the factual data?
  * So that we can provide a confident stop condition.
  * But we cannot take any arbitrary replacement.
  * The replacement must follow the existing heuristic consistency and invariants.
  *  
* What is the existing heuristic consistency and invariants?
  * If `u` and `v` are two adjacent vertices, directly connected via an edge(u, v), then the difference between their heuristics `h(u) - h(v)` must be less than or equal to (at most) the `weight(u, v)`.
  * The shortest path of the existing graph should be the shortest path in the adjusted graph.
  * Also, we must ensure that there will be no negative-edge-weight in the adjusted graph.
  * 
* Why did we take the difference of the heuristics, `q = hf - hb`? What does it indicate? How does it help?
  * We want to replace the heuristics in such a way that when we perform the summation (addition) of each priority queue's `peek` item, only the actual (factual) values remain, and the estimated values cancel each other out.
  * So that we can provide the stop condition confidently.
  * For that, we need to replace the heuristics with some values.
  * But we cannot replace the heuristics with any random, arbitrary values.
  * There are five reasons behind taking the difference of the heuristics. 
  * First, to understand how much change is allowed for the replacement of the heuristics.
  * The replacement of the heuristics must follow the consistency and invariants of the heuristics and the algorithm.
  * If we replace the heuristics with some potential, the difference between the potentials must align (follow) the current heuristic consistency and invariants.
  * Second, the difference gives us directional information.
  * Third, it helps us balance the priority queues.
  * Fourth, it becomes a reliable shared measurement. 
  * Fifth, all these together, help us to provide the stop condition confidently.
  * 
* Why didn't we take addition, multiplication, division, or something else? What is the problem with that?
  * If we take addition of the heuristics, we end up with getting the constant values throughout the exploration.
  * For example, suppose that the distance between the source and the target, is 100 km.
  * If the vertex is 10 km away from the source, it is 90 km away from the target.
  * Their addition gives us 100 km.
  * If the vertex is 20 km away from the source, it is 80 km away from the target.
  * Their addition gives us 100 km.
  * The constant values cause bidirectional A* to behave as a normal Dijkstra.
  * We lose the benefit of guided (directed) search.
  * This is true for the multiplication, too.
  * For example, we get the same result for 10 * 90 or 90 * 10.
  * Division provides fractional values.
  * It produces curves.
  * Subtraction provides linear values.
  * The algorithm and the context are suitable, and efficient with the linear progress.
  * For example, if we go from one place to another place, either it increases or decreases the distance.
  * We expect linear progress.
  * Curves make it more complex.
  * 
* Does the same approach work when heuristics are inconsistent?
  * If the heuristics do not produce negative edge-weight, then we re-open the closed vertex as soon as we find that a shorter complete path can go through it.
  * It explores slightly more vertices than the consistent heuristics.
  * However, if the heuristics introduce negative edge-weight, then the algorithm breaks.
  * The algorithm works only when there is no negative edge-weight.
  * 
* What do we mean by "balance the forward and the backward search around the same potential?"
  * If the distance between the source and the target, is 100 km, then we get two different values for any particular location of the vertex.
  * For example, if the vertex is 10 km away from the source, then it is 90 km away from the target.
  * Here, 10 and 90 are two different values.
  * We are trying to make it one.
  * For example, initially, for the vertex at source, it becomes:
  * {Distance from the target - distance from the source} / 2 = {100 - 0} / 2 = 50.
  * Now, for the vertex that is 10 km away from the source, and 90 km away from the target:
  * {Distance from the target - distance from the source} / 2 = {90 - 10} / 2 = 40.
  * Here, the difference between 50 and 40 is 10.
  * Also, 40 is a positive value.
  * So, it indicates that the vertex is 10 km away from the source.
  * If it was a negative value, then it would indicate that the vertex is 10 km away from the target.
  * So, we use the single value to decide the location of the vertex.
  * A single value provides two information:
  * The distance from the source as well as the distance from the target.
  * We treat this single value as `+x` for the forward search and `-x` for the backward search.
  * That's the part of balancing each priority queue.
  * So that when we perform addition of their `peek`, they cancel each other out.
  * When they cancel each other out, what remains is the factual data.
  * Another meaning of **balancing the search**, is that we explore the priority queue with fewer vertices.
  * So that we can finish the exploration fast, and come to the conclusion faster.
  * So that we don't consume more CPU memory and time.
  * That's the part of optimization.
  * 
* Why do we add the positive potential to the forward queue and the same negative potential to the backward queue? What does it indicate? What does it do? How does it help?
  * So that they cancel each other out when we perform addition of their `peek` values.
  * When they cancel each other out, what remains is the factual data.
  * And because of the factual data, we can provide the stop condition confidently.
  * 
---