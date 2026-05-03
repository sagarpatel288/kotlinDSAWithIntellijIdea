## References / Prerequisites

* [GitHub: Min Stack Documentation](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/6b715e4732375bc54cdbfd10e871bf3591125cba/docs/dataStructures/courses/uc/module01BasicDataStructures/section02stacksAndQueues/01video01stacks/050minStack.md)
* [Local: Min Stack Documentation](docs/dataStructures/courses/uc/module01BasicDataStructures/section02stacksAndQueues/01video01stacks/050minStack.md)

**When do we encode?**

```kotlin

if (incoming > current) {
    encode
}

```

* So, we know that the `incoming` value is greater than the `current` value.

**What is the encoded formula?**

```kotlin

val encoded = 2 * incoming - current
// Simplification
val encoded = incoming + (incoming - current)
// Denote "incoming - current" as "gap"
// And this "gap" is a positive number.
// Because, we are subtracting a lower value from the higher value.
val encoded = incoming + gap
```

* Here, we are adding some positive value `gap` to the `incoming` value which is already greater than the `current` value.
* So, it is obvious that the `encoded` value is greater than the `incoming` value by some `gap` value.
* That's how we are sure that the `encoded` value will always be greater than the current max value.



