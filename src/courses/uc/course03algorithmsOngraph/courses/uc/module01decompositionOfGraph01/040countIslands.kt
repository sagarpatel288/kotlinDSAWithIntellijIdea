package courses.uc.course03algorithmsOngraph.courses.uc.module01decompositionOfGraph01

/**
 * # Reference
 *
 * * [Count islands](https://github.com/sagarpatel288/kotlinDSAWithIntellijIdea/blob/c985c009ef3e60eadecad784b50f976636dc5d13/docs/03graph/courses/uc/module01decompositionOfGraph01/032numberOfIslands.md)
 *
 * # Time Complexity
 *
 * * `O(rows * columns)`
 *
 * # Space Complexity
 *
 * * `O(rows * columns)`
 */
class CountIslands {

    fun countIslands(grid: Array<CharArray>): Int {
        if (grid.isEmpty()) return 0
        // grid.size gives rows and grid[0].size gives columns
        val visited = Array(grid.size) { BooleanArray(grid[0].size) { false } }
        var islands = 0
        for (i in 0..<grid.size) {
            if (grid[i].isEmpty()) continue
            for (j in 0..<grid[0].size) {
                // Only an unvisited island can pass through this `if` condition.
                if (!visited[i][j] && grid[i][j] == '1') {
                    dfs(i, j, grid, visited)
                    // Whenever we pass a vertex to the `dfs` function,
                    // it covers all the vertices of that particular component.
                    // So, this must be an isolated, independent, individual, different component.
                    // This counts. We processed a component. It counts.
                    // Every time we process a component, we increase the count.
                    // Caution! Possible point of mistake!
                    // It is within the `if` block.
                    islands++
                }
                // We don't increase the count for every `j`.
                // We increase the count only within the `if` block, where we process each island.
            }
            // We don't increase the count for every `i`.
            // We increase the count only within the `if` block, where we process each island.
        }
        return islands
    }

    private fun dfs(i: Int, j: Int, grid: Array<CharArray>, visited: Array<BooleanArray>) {
        if (i !in 0..<grid.size || j !in 0..<(grid[0].size) || grid[i][j] == '0' || visited[i][j]) {
            return
        }
        visited[i][j] = true
        dfs(i - 1, j, grid, visited) // top
        dfs(i, j + 1, grid, visited) // right
        dfs(i + 1, j, grid, visited) // bottom
        dfs(i, j - 1, grid, visited) // left
    }
}