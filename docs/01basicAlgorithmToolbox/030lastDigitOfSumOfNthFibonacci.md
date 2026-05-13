# Last digit of sum of Nth Fibonacci

* Sum of nth Fibonacci means:

F(0) + F(1) + F(2) + .. + F(n)

* But in reality, we don't have to sum up each Fibonacci.
* We have a formula:

F(n + 2) - 1

* So, instead of doing `{ F(0) + F(1) + F(2) + .. + F(n) } % 10`, we do `{ F(n + 2) - 1 } % 10`.
* Here, we can take `F(n + 2) = a` and `1 = b`.
* Now, according to the modulo arithmetic, `(a - b) % 10 = { (a % 10) - (b % 10) } % 10`.
* And instead of `F(n + 2) % 10`, we can take `F(reducedN) % 10` using the Pisano Period.

---

* So first, we find the last digit of `a = n + 2` using the Pisano period.
* For the Pisano period, we use `mod = 10`.
* Once we get the Pisano period `p`, we find `b = a % p`.
* Then, we find the last digit of `b`.

---

* Similarly, Partial sum of (n, m) Fibonacci:

$$F(n_0) + F(n_1) + F(n_2) + .. + F(m)$$

* But in reality, we don't have to sum up each Fibonacci from n to m.
* We have a formula:

F(n + 2) - F(m + 1)

---

**How?**

![050partial_sum_of_fibonacci_start_end.png](../../assets/images/algorithmToolbox/module02AlgorithmWarmUp/050partial_sum_of_fibonacci_start_end.png)

---

**How to remember?**

* When the game `starts`, I get `1` additional perk.
* And in the `end`, I get `2` additional perks.
* Then, I need to `return` the `difference`.
* We subtract from a bigger number.
* Being in the game till the `end` is a `big` thing.
* So, it becomes: `(end + 2) - (start + 1)`.

**End** gets two (`2` additional perks for being in the game till the `end`),  
**Start** gets one (`1` additional perk for showing the courage to `start` the game),  
**Subtract** the pair and the math is done!  
Divide by ten to keep it small,  
The last digit is the only call!


* A hotel manager gives us a discount of `+ 1` when we start the booking (start + 1), but charges us `+ 2` when we end the booking (end + 2)!
* We realize the whole scene when we end the booking.
* So, the total becomes: `(end + 2) - (start + 1)`
* A love story that `starts with 1` and `ends 2`.
* To get `+2` things, we need to remove `+1` thing.

---

* Similarly, Sum of square of nth Fibonacci:

$$F_0^2 +F_1^2 +···+F_n^2$$

* But in reality, we don't have to square and sum up each Fibonacci.
* We have a formula:

F(n) * F(n + 1)

