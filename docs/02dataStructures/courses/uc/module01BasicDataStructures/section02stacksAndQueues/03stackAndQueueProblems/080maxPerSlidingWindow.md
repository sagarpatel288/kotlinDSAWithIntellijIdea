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
* Notice that we don't remove, or skip the upcoming item ever, even if it is smaller than the previous, pre-existing item of the solution container.
* Because we might get the future window that starts from this now-seemed so-called small item where the rest of the future items of the remaining window can be even smaller than that!
* And the reason we remove (drop) the previous (pre-existing) item from the container when it is smaller than the upcoming item is that what else we do with that smaller item!
* I mean, we already know that it cannot to be the maximum.
* We can get the maximum of the current window at the front only if we remove (drop) the smaller items that block it.
* So remember, we remove the past items, and not the upcoming items.
* New resources replaces the old resources.

**We use a deque. So, from where do we remove the items from the solution container? From the front or the back? Why?**

* We remove the past (previous) smaller items from the back.
* Because it is possible that the first and front item is the maximum, followed by a smaller item, and then the third item is greater than the second item but smaller than the first item.
* So in this case, we know that the second item is of no use, it is not the maximum.
* But we can't remove it from the first, otherwise we might lose the possible max of the window.
* So, we remove the definite small item from the last before we push the upcoming item from the back.
* That's why we remove the small items from the back.

**Side-effects (Intention)**

* When we remove the smaller items from the back, we maintain a monotonic decreasing order in the queue.
* Because we know that the item before the upcoming item or the last pushed item cannot be smaller than the item we pushed last.
* Because we already remove such items.
* It means that it must be equivalent or greater than the upcoming item we pushed last.
* So this is how the system maintains the monotonic decreasing order.
* Now, this is not the side effect.
* This is the intention to keep the max item to the front of the queue so that we can get it in O(1).
* So maybe we can call it a side effect disguised in the intention.
* The main point is, this is the intentional system design.

**How do we get to know that we have already scanned the window, and now it is time to get the maximum from the front?**

* Using the index.
* All the indices less than the `current index - window size + 1` are out of current window.

**Quick Visual**

```markdown

                    Problem Container    
                                         
                                         
                +-----|-----|-----|-----|
                |     |     |     |     |
    Indices     |  0  |  1  |  2  |  3  |
                |     |     |     |     |
                +-----|-----|-----|-----|
                |     |     |     |     |
    Elements    |  7  |  4  |  0  |  5  |
                |     |     |     |     |
                +-----|-----|-----|-----|
                                         
                                         
Window size = 3    Solution Container    
                                         
                                         
                +-----|-----|-----+      
                |     |     |     |      
      Queue     |  7  |  4  |  0  |      
                |     |     |     |      
                +-----|-----|-----+      


```

* Now, as soon as we try to add `index i = 3 (element value is 5)`, we remove all the smaller past items.
* Remember that the new resources replace the old resources.

```markdown

                    Problem Container    
                                         
                                         
                +-----|-----|-----|-----|
                |     |     |     |     |
    Indices     |  0  |  1  |  2  |  3  |
                |     |     |     |     |
                +-----|-----|-----|-----|
                |     |     |     |     |
    Elements    |  7  |  4  |  0  |  5  |
                |     |     |     |     |
                +-----|-----|-----|-----|
                                         
                                         
Window size = 3    Solution Container    
                                         
                                         
                +-----|-----|-----+      
                |     |     |     |      
      Queue     |  7  |     |     |      
                |     |     |     |      
                +-----|-----|-----+      

```

* We removed `0` and then `4` because they were smaller than the upcoming item `5`. 
* And we also remove all the old items who have been slid out of the current window.
* So, it becomes:

```markdown

                    Problem Container    
                                         
                                         
                +-----|-----|-----|-----|
                |     |     |     |     |
    Indices     |  0  |  1  |  2  |  3  |
                |     |     |     |     |
                +-----|-----|-----|-----|
                |     |     |     |     |
    Elements    |  7  |  4  |  0  |  5  |
                |     |     |     |     |
                +-----|-----|-----|-----|
                                         
                                         
Window size = 3    Solution Container    
                                         
                                         
                +-----|-----|-----+      
                |     |     |     |      
      Queue     |     |     |     |      
                |     |     |     |      
                +-----|-----|-----+      


```

* Then, we add the upcoming item.

```markdown

                    Problem Container    
                                         
                                         
                +-----|-----|-----|-----|
                |     |     |     |     |
    Indices     |  0  |  1  |  2  |  3  |
                |     |     |     |     |
                +-----|-----|-----|-----|
                |     |     |     |     |
    Elements    |  7  |  4  |  0  |  5  |
                |     |     |     |     |
                +-----|-----|-----|-----|
                                         
                                         
Window size = 3    Solution Container    
                                         
                                         
                +-----|-----|-----+      
                |     |     |     |      
      Queue     |  5  |     |     |      
                |     |     |     |      
                +-----|-----|-----+

```

* This system ensures that the front first item is the maximum of the window.  

## Implementation

* [GitHub: Max per sliding window .kt](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/5ef3ac50713bbe94b673a1562c5d08f6b7d8edd8/src/courses/uc/course02dataStructures/module01/section04assignmentProblems/05maximumInSlidingWindow.kt)
* [Local: Max per sliding window .kt](src/courses/uc/course02dataStructures/module01/section04assignmentProblems/05maximumInSlidingWindow.kt)