# TL;DR Quick Overview

## Given

* A static fixed-size array with items
* K-window size

## Find

* Maximum value for each window in O(n)

## Thought Process

* We read from the problem container and write to the solution container.
* As we read from the problem container, we can manage windows through the indices.
* We can know when the valid window is formed, when and how to change (slide) the window, etc. using the indices.
* Now, from the solution container, we need to remove the items in two conditions:
* When we form a new window, we need to remove the old item that is not part of this new window.
* And we remove the item that cannot be the maximum item for this window.
* The reason we remove the item that cannot be the maximum for this window is to get the actual (true) maximum of the window from the front in O(1).
* When we add a new item to the solution container, and we find that the previous item of the same window is smaller than this new upcoming item, we remove the smaller item.
* This system ensures that the maximum item of the window stays in the front. 