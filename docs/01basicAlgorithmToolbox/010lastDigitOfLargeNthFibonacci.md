# Last digit of the large nth Fibonacci

* The key-lemma is we don't have to run up to the actual Fibonacci number.
* The sequence of the last digit of various Fibonacci numbers gets repeated.
* It means that we can find the last digit of the smaller variant.
* The formula is:

f(a) % m = f(b) % m; where b = a % p and p = Pissano period (length).  

* Here, `b` is a smaller variant of `a` and the last digit of both `a` and `b` are the same.
* So, we don't have to run up to `a`.
* We calculate the Pissano period, and find the last digit of the smaller variant.
* Because the last digit of both the original large number and the smaller variant are the same.