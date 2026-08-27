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
                if (!visited[i][j] && grid[i][j] == '1') {
                    dfs(i, j, grid, visited)
                    islands++
                }
            }
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