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

---

* `a` is a large Fibonacci number.
* a % m = b % m; where b = a % p; where p = Pissano Period
* So, Find the pissano period, `p` for modulo 10.
* Find the reduced variant of `a`, which will be `b`, by `a % p`.
* So, `b = a % p`.
* And then find the last digit of `b` instead of `a`.

## Implementation

* [GitHub: Last digit of a large Fibonacci](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/7b74242dc1b67f9c10bdb5324f902aa46dab1d6d/src/courses/uc/course01algorithmicToolbox/module02AlgorithmicWarmUp/015lastDigitOfNthFibonacci.kt)