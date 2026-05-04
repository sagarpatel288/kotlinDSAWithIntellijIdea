# TL;DR Quick Overview

## Given

* A static fixed-size array with items
* K-window size

## Find

* Maximum value for each window in O(n)

## Thought Process

* We read from the problem container and write to the solution process container.
* As we read from the problem container, we can manage windows through the indices.
* We can know when the valid window is formed, when and how to change (slide) the window, etc. using the indices.
* Now, from the solution container, we need to remove the items in two conditions:
* When we form a new window, we need to remove the old item which is not part of this new window.
* Now, to get the maximum value of a particular window from the solution container in O(1) time, we use a clever trick.
* We remove the item which cannot be the maximum item for this window.
* When we add a new item to the solution container, and we find that the previous item of the same window is smaller than this new incoming item, we remove the smaller item.
* This system ensures that the maximum item of the window stays in the front. 