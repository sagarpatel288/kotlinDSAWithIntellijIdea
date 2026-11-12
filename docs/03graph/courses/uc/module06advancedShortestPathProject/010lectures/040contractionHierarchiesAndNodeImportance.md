# Contraction hierarchies and node importance

---

**References**

* [Illustrative Contraction Hierarchies By System Underground](https://youtu.be/xe1CbmJfMcU)

---

* The main purpose of the contraction hierarchy is to preprocess and prepare the map beforehand so that finding the shortest path becomes faster.
* We need to preprocess and prepare the map beforehand for the multiple queries.
* We don't know the future source and the future target.
* And there can be different source and target for different queries.
* So, how do we preprocess and prepare the graph (map, network)?

---

* We compare contraction hierarchies with a flyover (overpass).
* What happens when we travel from our home, which is in a different state to a friend's home, which is in another state?
* We start with the local streets, then enter into a highway, then a bigger highway, maybe an interstate highway. 
* And as we reach closer to the friend's home, we enter into the smaller highway, and eventually, into the local streets.
* This is the general pattern and inspiration for the intuition.

1. Leave residential street.
2. Enter into the main road.
3. Join a state highway.
4. Join a national highway.
5. Leave the highway as we reach near the destination.
6. Travel through the local roads to reach the destination.

* The journey starts and ends on local roads.
* But the long distance journey happens on highways.
* We can classify them roughly as:

1. Local roads near the source and destination.
2. Regional roads that connect highways.
3. Highways that cover the major distance.

* We want to focus on the highway that connects the regional road that connects the local road to the source and the destination.
* But how do we apply this filter?
* Based on the importance.
* We want to focus on the local roads near the source that connect regional roads that connect highways that connect regional roads towards the target that connect the local roads near the target.
* If a local road near the source does not connect a regional road or a highway that can reach the target, it is not important to us.

---

* How do we assign the importance?
* Highways cover the most distance. So, they get the highest importance.
* Regional roads that connect highways get the middle importance.
* Local streets that connect regional roads get the lowest importance.

---

* How do we find (evaluate, calculate, determine) the importance? 

---

* Suppose that `V` represents the locations (vertices).
* And `E` represents the roads (edges) that connect these locations (vertices).
* These `V` and `E` are parts of our graph, `G`.

$$
G = (V, E)
$$

* And if `u` and `v` are two locations, then the cost of traveling from `u` to `v` is:

$$
w(u, v)
$$

* The cost can be any unit.
* Such as distance, fuel, price, toll-tax, time, etc. 

---

**Contraction Hierarchies**

* What if we preprocess the graph to find the shortest path?
* But we don’t know the source and the destination beforehand.
* Also, there can be millions of vertices.
* It is not feasible to find the shortest path for all the vertices from all the other vertices.
* The concept we use to solve the problem is called contraction hierarchies.
* We virtually remove intersections, obstacles, stops, etc.
* But we preserve the path.
* We assign a rank to each vertex.
* We record an order in which the vertices are visited.
* We do this for all the vertices, for the entire graph.
* This process is called preprocessing.

---

![010contractionHierarchies.webp](../../../../../../assets/images/03graph/courses/uc/module06advancedShortestPathProject/part03ContractionHierarchies/010contractionHierarchies.webp)

* In contraction hierarchy, we contract (remove) an unimportant vertex (like vertex B in the image) and connect its neighbors directly (like A and C in the image).
* Here, the term hierarchy represents ranking of nodes from least important to most important.
* The idea is:
* Identify the least important intersections.
* Bypass them.
* For example, the vertex B in the image.
* Do not destroy the shortest route.
* For example, $d(A, C)$ is still $5$.

---

* Which vertices do we identify as intersections?
* How do we ensure that we do not destroy the shortest route?

---

* Contraction hierarchies do not discover and identify intersections.
* It is something that we get beforehand as a part of the input.

|    Real-world element   |    Graph representation    |
|:-----------------------:|:--------------------------:|
| Road intersection       | Vertex                     |
| T-junction              | Vertex                     |
| Roundabout connection   | Vertex or several vertices |
| Road segment            | Edge                       |
| Travel time or distance | Edge weight                |
| One-way road            | Directed edge              |


* We generally create vertices at locations that matter for routing, such as:
* Road junctions
* Changes in connectivity
* Road endpoints
* Places where a route can enter or leave a road
* Places that change the route
* Etc.

* The exact graph construction is a separate preprocessing step that happens before contraction hierarchies.

---

* The goal is to prepare a preprocessed map that knows important routes (paths) vs. unimportant routes (paths).
* How do we define important vs. unimportant paths?
* How do we define ranks?
* Do we define ranks based on the distance (edge weight)? How?
* Does the higher edge weight get the higher rank?
* How do we use ranks?
* How do we treat different ranks?
* How does the map treat and use different ranks?


---

* Do we take the least important intersections, and hide them?
* Does it mean that we hide the vertices with the lowest rank?
* How do we hide/bypass/treat vertices or edge-weights with the lowest ranks?
* What if a vertex is a part of the shortest path? Do we still hide it? How? Why?
* What does it mean by building/creating a direct mathematical bridge/shortcut? How does it help? When do we do that? Why do we do that? How do we do that?
* Suppose that the original graph has edge(A, B) with weight 3, edge(B, C) with weight 2.
* Now, do we preprocess the graph such that it answers:
* "The shortest path from A to C goes via B and A to C costs 5".
* If yes, how?
* If no, then what does it answer? How?
* Can we say that the preprocessed graph (map) is a zoomed out version of the original graph (map)?
* If yes, then how does it help?
* Do ranks act as a guide for the search? How?
* How does the representation of the shortest path as upward from the source and downward to the target help? Why isn't it a linear path?

---

**Recap**

* We imagine the idea of contraction hierarchies as a flyover (overpass).
* The term “contraction” refers to the process of reducing the number of nodes in a graph.
* We hide less important nodes and edges, making the graph simpler.
* But they are still there, just hidden.
* The term “hierarchies” refers to the process of organizing nodes based on their importance.
* To organize nodes based on their importance, we use ranks.
* We use ranks to guide the search process.
* We imagine the search process as moving upwards from the source and downwards towards the target.
* Moving upwards from the source indicates moving through the higher-ranked nodes.
* Similarly, moving downwards towards the target indicates moving through the lower-ranked nodes.

--- 