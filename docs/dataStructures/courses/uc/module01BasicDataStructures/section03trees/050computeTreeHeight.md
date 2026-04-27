
* We can compute the height of a tree using the BFS-level-by-level traversal.
* What is the BFS (level-by-level) traversal? What do we do in it? How?
* We start with the first level (root).
* We finish the entire level. 
* We cover all the nodes of the current level.
* As we cover each node, we keep adding the children of each node.
* That's how we prepare the next level to travel.
* So that once we finish the current level, we can travel the next level.
* We repeat the process for each level.
* Every time we change the level, every time we go to the next level, we increase the height by 1.
* To travel level-by-level, we need parent-children information.
* We have the parent information, up front and ready to use.
* We use this parent information to catch and build the parent-children information.