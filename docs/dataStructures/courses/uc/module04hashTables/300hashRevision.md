# Hash Table Revision

* An array gives us an arbitrary item in `O(1)` if we know the index.
* If we don't know the index, it takes `O(n)` time.
* If we treat the item as an index, it takes `O(1)` time.
* Treating an item as an index is called **Direct Addressing**.
* But direct addressing takes a huge memory and can waste a huge memory.
* For example, we have two items: 0 and 100000.
* The direct addressing will require an array of size: 100000.
* It is a huge memory just to store two items!
* And it fills only two slots.
* All the other slots remain empty.
* It means that we waste a lot of memory!
* A `Hash Table` solves this issue.
* We can get any arbitrary item in almost `O(1)` time without wasting too much memory.
* If we have `n` keys, we keep the array size in such a way that $\frac n  m = 0.75$
* `m` is the array size and $m << n$.
* Then, the key that we give to the hash table goes to a hash function.
* The hash function generates a hash code using the `mod m`.
* Based on the hash code, we get an index.
* Note that the size of the hash table is less than the possible number of keys we might get.
* It means that we might get the same index for multiple keys.
* We call it collision.
* Collision increases the time of the find operation. 
* To solve that, we use either separate chaining or open addressing.
* In a separate chaining, each slot is a linked list.
* So, each slot can store more than one keys.
* However, when we try to get the value for a particular key, which value will be return?
* Because all the values are at the same index.
* How do we find which value belong to which key?
* To solve that, we store the key-value pairs in the linked list.
* But if someone knows which group of keys create collisions, we can get **Hash DoS Attack**.
* To solve that, we use two techniques: 
* Universal family of hash functions and `Treefication`.
* Instead of using the same predictable hash function, we use a universal family of hash functions.
* It is: h(x) = ((ax + b) mod p ) mod m
* Here, `a` ranges between `1` to `p`.
* So, `a` has a total of `p - 1` choices.
* And `b` ranges between `0` to `p`.
* So, `b` has a total of `p` choices.
* It generates approximately p(p - 1) possible hash functions.
* And we can't know which hash function is responsible to place an item at a particular index.
* If we try to apply the **Hash DoS Attack**, we might get a different hash function that will ultimately place the item at different index. 