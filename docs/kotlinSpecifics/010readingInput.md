# Reading Input

<!-- TOC -->
* [Reading Input](#reading-input)
  * [Why is it better to use `BufferReader` instead of `readln()` or `scanner`?](#why-is-it-better-to-use-bufferreader-instead-of-readln-or-scanner)
    * [readln](#readln)
    * [.split(" ")](#split-)
    * [.map{ it.toInt() }](#map-ittoint-)
    * [BufferReader](#bufferreader)
    * [StringTokenizer](#stringtokenizer)
    * [toInt()](#toint)
    * [Pseudocode](#pseudocode)
    * [System.`in`](#systemin)
    * [InputStreamReader](#inputstreamreader)
<!-- TOC -->

## Why is it better to use `BufferReader` instead of `readln()` or `scanner`?

### readln

* Uses `stdin` synchronously (System call).
  * Our program runs on a user space.
  * The keyboard/input file lives on a hardware/kernel space.
  * The hardware/kernel space is managed by OS.
  * To get the data, our program asks OS, which is a system call.
  * The system call involves context switches.
  * The system stops and saves the current state of our program.
  * Goes to the hardware/kernel space and fetches the data.
  * Switches back and resumes our program.
  * These switches are expensive.
  * `readln`, and `standard Scanner` reads small.
  * It means, it causes more context switches.
  * And this happens per line, and not in bulk.
* Reads entire line. Reads bytes, and decodes them to chars.
* Creates a new string object for the whole line.

### .split(" ")

* Internally creates and uses `regEx` to find and manage spaces.
* Creates a list of string to hold (store) the results.
* One string object per token.
  * What is token here?
  * A Token is a meaningful data separated by a delimiter, like a space.
  * Give examples.
  * For example, the input `10 20 30` has 3 tokens separated by a space delimiter: `10`, `20`, and `30`.
* 1,00,000 numbers -> 1,00,000 strings.
  * What is this?
  * Explain.
  * For example, the input `10 20 30` creates 3 string objects (`10`, `20`, and `30`) and a list of string to store those created string objects.
  * If the line has 1,00,000 numbers, it creates 1,00,000 string objects.
  * That's too much.

### .map{ it.toInt() }

* Creates another list to store integers.
* Each string: Parse, validate, convert.
* Boxing and unboxing overhead.
  * What is boxing and unboxing?
  * Why does that happen?
  * How does that happen?
  * A generic list like `List<Int>` cannot hold (store) `primitive` data like raw bits (00001010).
  * They can only hold (store) objects.
  * To store the primitive data like `int`, the JVM wraps it into the `integer` object.
  * This additional process adds metadata like headers, hash codes, etc.
  * And it increases the memory usage.
  * For example, the memory usage of every 4 bytes turns into 24 bytes per number.
  * This requires additional memory and time.

### BufferReader

* Reads large blocks (8KB+).
  * Explain how with examples and comparisons.
  * Give examples.
  * The `readln` moves one brick at a time. 
  * The `BufferReader` moves multiple bricks (like a wheelbarrow) at once.
  * How? The `BufferReader` uses an internal array of size 8KB (8192 bytes).
  * When we ask for the data, it fills the entire 8KB data at once.
  * So, one system call fills 8KB data.
  * Then, the subsequent `read()` call takes bytes from this array, which is fast.
* So, we make fewer system calls.
  * One system call per 8KB data.
* Much faster than [readln](#readln).
  * Explain why and how with examples.

### StringTokenizer

* It is a cursor.
* It doesn't create a list of strings.
* It holds a long string, and an index position.
* It scans for the current index until it finds a delimiter.
* By default, it supports space, tab, and new line delimiter.
* But, we can pass a custom delimiter, too.
* No `regEx`.
  * Explain why with examples and comparisons.
* No immediate new string object creation.
  * Explain why and how with examples and comparisons.
* It only extracts what is needed, when needed.
  * Explain what is this, and how that happens with examples and comparisons.
* Parses character by character.
  * Explain how with examples and comparisons.
* No intermediate list.
  * Explain why with examples and comparisons.
* No unnecessary objects.
  * Explain why with examples and comparisons.

### toInt()

* String parsing, but creates fewer strings overall.
  * Explain why and how with examples and comparisons.

### Pseudocode

```kotlin

val reader = BufferedReader(InputStreamReader(System.`in`))
val firstLine = reader.readLine()
if (firstLine == null) return
val total = firstLine.toInt()
repeat(total) {
    val line = reader.readLine()
    if (line != null) {
        // StringTokenizer("string", "delimiters")
        val st = StringTokenizer(line)
        while (st.hasMoreTokens()) {
            val token = st.nextToken()
            val numValue = token.toInt()
            //...
        }
    }
}
```

### System.`in`

* Connects to the raw bytes from the keyboard/file.

### InputStreamReader

* Decodes raw bytes into characters.

