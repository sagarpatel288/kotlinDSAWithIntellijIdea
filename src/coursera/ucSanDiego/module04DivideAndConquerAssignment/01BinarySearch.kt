package coursera.ucSanDiego.module04DivideAndConquerAssignment

/**
 * Problem Statement:
 *
 * [Reference](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/d2cdd413f20830cd8d27bd0b27aadc1a6f8598f0/res/coursera/ucSanDiego/module04DivideAndConquerAssignment/01binarySearch.png)
 *
 * Search multiple keys in a sorted sequence of keys.
 *
 * Input:
 *
 * A sorted array K of distinct integers and an array Q= [q0,...,qm−1] of integers.
 *
 * Output:
 *
 * For each qi, check whether it occurs in K.
 *
 * Input format.
 *
 * The first two lines of the input contain an integer n and
 * a sequence k0 <k1 <...<kn−1 of n distinct positive integers in increasing order.
 *
 * The next two lines contain an integer m and m positive integers q0,q1,...,qm−1.
 *
 * Output format.
 *
 * For all i from 0 to m−1, output an index 0 ≤ j ≤ n−1 such
 * that kj= qi, or −1, if there is no such index.
 *
 * Constraints:
 *
 * 1 ≤ n ≤ 3·10^4;
 * 1 ≤ m ≤ 10^5;
 * 1 ≤ ki ≤ 10^9 for all 0 ≤ i < n;
 * 1 ≤ qj ≤ 10^9 for all 0 ≤ j < m.
 *
 * Sample:
 *
 * Input:
 *
 * 5
 *
 * 1 5 8 12 13
 *
 * 5
 *
 * 8 1 23 1 11
 *
 * Output:
 *
 * 2 0 -1 0 -1
 *
 * Queries 8, 1, and 1 occur at positions 2, 0, and 0, respectively, while
 * queries 23 and 11 do not occur in the sequence of keys.
 *
 *
 */
fun main() {

}