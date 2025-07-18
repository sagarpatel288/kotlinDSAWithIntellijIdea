# References / Resources

* [Reducible: Dynamic Arrays](https://youtu.be/5AllG-i_yto?si=SnwlIy0MStKN3CTD)
* [Reducible: Amortized Analysis](https://youtu.be/Ij7NQ-0mIVA?si=O5O1xvr6XrKuXc2N)
* [122 Videos](https://youtu.be/L8cXZ_4RHt8?si=SpCTF8SHVxBzo2ci)

## What is a dynamic array? How a dynamic array is different from a fixed size static array?

* A fixed size static array has a fixed size capacity.
* We cannot add more items to it than its maximum capacity.
* However, unlike a fixed size static array, a dynamic array can resize itself. 
* It can grow as we add more items, and it can shrink as we remove items.
* Kotlin `ArrayList` is an example of a `Dynamic Array`.

## What is the time complexity of adding more items to a dynamic array?

* For a dynamic array, `Amortized time complexity` of adding a new item is `O(1)`.
* Worst-case time complexity, is `O(n)`.

## What is the difference between the `Amortized` and `Worst-Case` Time Complexity of a dynamic array?

* `Amortized Time Complexity` is an `Average Time Complexity`.
* In a dynamic array, we double the size of the array when the array hits the maximum capacity.
* Doubling the size is a worst-case, which takes `O(n)` time.
* However, we don't have to double the size frequently.
* So, if we split the cost across worst-case and best-case scenarios, on average, we get `O(1)` time complexity.
* So, the `Amortized` time complexity is less than the expensive operations, and more than the cheap operations.
* The purpose is to shift our focus from the worst-case analysis to average time analysis, 
and give a more realistic analysis.
* 

## How do we resize the array?

* As soon as the array reaches maximum capacity, we create a new array twice the size of the previous array.
* We copy all the items from the previous array and insert each item into the newly created array.
* Similarly, as soon as the number of items becomes less than half the size of the array, we shrink the array 
by creating a new array based on the number of items.
* The purpose is to limit the resize operations in such a way that we get an amortized time complexity of `O(1)`.

## Why do we double the size of an array? Isn't it inefficient? Don't we waste too much space also?

* Other schemes, such as resizing the array by one or n more blocks of memory, take `O(n^2)` time.
* On the other hand, doubling the array size takes `O(n)` in the worst case, which happens very infrequently.
* So, the average time, known as the amortized time of doubling the array, is `O(1)` only.
* For space, the maximum unused allocated memory would be `O(2N)`, and if we drop the constant, it becomes `O(n)`.
* Also, we shrink the array size as soon as we find that the number of items is less than half the size of the array.

## What is the space complexity of a dynamic array?

* For the best-case, it is `O(n)`, where `n` is the number of items.
* For the worst-case, it is `O(2n)`. However, we drop the constants for the complexity analysis.
* So, overall, it is `O(n)` only.