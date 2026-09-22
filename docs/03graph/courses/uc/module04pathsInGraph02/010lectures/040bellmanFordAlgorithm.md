# Bellman Ford Algorithm

## Prerequisites

* [DijkstrasAlgorithm.md](030dijkstrasAlgorithm.md)

## Concept

* Earlier, we have seen the Dijkstra's Algorithm to find the shortest path (distance).
* And there was an important invariant (or assumption).

> Once we extract the (distance to vertex) pair from the `min-heap`, it cannot have a shorter distance than it.

* Let us use it again:

![Dijkstra On Negative Weight.webp](../../../../../../assets/images/03graph/courses/uc/module04pathsInGraph02/04bellmanFordAlgorithmOfShortestPath/010dijkstraOnNegativeWeight.webp)

* As we can see in the image, the invariant (or assumption) fails when there is a negative weight on the edge.
* We might think that what is the problem if we add (-10 to B) again to the `min-heap` after we have already extracted (5 to B)?
* The problem is, if B had many outgoing edges, then adding (-10 to B) forces us (the algorithm) to inspect those outgoing edges again.
* And it breaks the promised time complexity of the algorithm.
* Because the promised time complexity does not include this case.
* So, what is the solution?

## Bellman-Ford Algorithm

* Let us take a real life example.
* The currency exchange problem is the famous one.
* So, the question is, is it possible to go through a certain currency exchange path using which we can actually end-up earning more than what we had when we started?
* For example, suppose initially we have $1,000 USD.
* And then maybe we can go through RUB, GBP, and some other currency exchange paths.
* Now, is it possible that somehow we get more than $1,000 USD when we return to USD through a particular exchange rate route?
* For example, maybe going through USD → EUR → GBP → RUB → USD gives us more USD than the direct USD → RUB → USD.
* If it is possible, then people can earn a lot of money through this process.
* All they have to do is just to find the path of this different currency exchange routes using which they can get more money than they started with.
* So, how do we model this problem as a graph problem?
* Imagine that these currencies, USD, RUB, EUR, GBP, etc. are vertices.
* When we convert one currency to another currency, it becomes a direct edge.
* For example, suppose we convert from USD to RUB, then there is an edge from USD to RUB.
* And when we convert one currency to another currency, it will have some exchange rate, and we denote this rate as the weight.
* So, for example the weight of USD → EUR might be 0.83 or something like that.
* It indicates that if we have 1 USD, then we get 0.83 EUR. 
* So now, the goal is to find such a path using which we can have maximum money.
* It means that we are interested in a path where 1 becomes more.
* For example, if the exchange rate of USD → RUB is 83, then 1 USD becomes 83 RUB.
* So, we constantly look for a path that increases the value in such a way that we end-up having maximum money using a particular route.
* We can think of the exchange rate in this way:
* If we have 1 USD, then we get 83 RUB because the edge weight is 83.
* How many RUB do we get if we have $1,000 USD?
* So, we multiply with the exchange rate (weight) of USD → RUB, which is 83.
* So, we get 1,000 USD * 83 (USD to RUB Exchange Rate) = 83,000 RUB.
* So, we want to find a path using which we can get the maximum product (multiplication) of two vertices (currencies).
* Now, this sounds a little bit strange.
* Because, we normally ask: What will be the minimum sum of weights?
* Notice: Minimum and Sum.
* Here, we ask: What will be the maximum product of weights?
* Notice: Maximum and Product.
* So, we need a way to come up with some mathematical formula.
* For example, we can convert a multiplication product into a summation formula using logarithm arithmetic.
* For example:
* $log(xy) = log x + log y$
* So, if we have the below multiplication problem:
* $4 * \frac{1}{2} = 2$, then we can convert it into a logarithmic problem using base-2 as:
* $log_2(4) + log_2(\frac{1}{2}) = 2 - 1 = 1$
* And this 1 is for $log_2(2) = 1$.
* And $log_2(2) = 1$ translates into $2^1$, which is 2.
* So this is how we can convert a multiplication problem into a summation problem.
* In other words, if we denote exchange rate as `r`, then: 
* $r_1 * r_2 * r_3 * ...* r_n$ into $log(r_1) + log(r_2) + log(r_3) +...+ log(r_n)$.
* But how do we convert a maximization problem into a minimization problem?
* So, we negate the values.
* For example:
* $log(r_1)$ becomes $- log(r_1)$, and so on...
* Now, we want minimum sum of $- log(r_n)$. 
---
* So, logarithm converts multiplication (product) into summation.
* And negation converts maximum into minimum.
---
* It means if some exchange rate (edge weight) is $r = 2$.
* Then, it becomes: $- log_2(2) = -1$.
* It means that the edge weight becomes negative.
* And we have seen it earlier that we cannot use Dijkstra's Algorithm to solve the problem where edges can have negative weight.
* Because we have seen it that for any edge, `weight >= 0` is the Dijkstra's Algorithm's core condition due to which we say that once we `poll` the item, it cannot have any shorter distance than that.
* Reference: [Dijkstras Algorithm.md](030dijkstrasAlgorithm.md)
* And we have also seen it in the beginning of this lecture, how Dijkstra's Algorithm fails if we ever get `weight < 0`.
* It means that we need a different algorithm when an edge can have negative weight.
---
* Why did we say that we need a maximum product? Product of what? Product of currencies? It was too fast for me. How did we conclude that in order to end-up having more money than we started, we need to find maximum product?
---
* 

## Time Complexity

## Space Complexity

## Next