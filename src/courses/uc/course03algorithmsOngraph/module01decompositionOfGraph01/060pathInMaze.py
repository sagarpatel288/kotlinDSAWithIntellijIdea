class PathInMaze:
    def __init__(self, vertices):
        # To process each neighbor of each vertex
        self.adjacencyList = [[] for _ in range(vertices)]

        self.vertices = vertices

    def addEdge(self, a, b):
        if a < 0 or a >= self.vertices or b < 0 or b >= self.vertices:
            return

        self.adjacencyList[a].append(b)
        self.adjacencyList[b].append(a)

    def hasPath(self, a, b):
        if a < 0 or a >= self.vertices or b < 0 or b >= self.vertices:
            return False

        visited = [False] * self.vertices

        return self.hasPathUsingDfs(a, b, visited)

    def hasPathUsingDfs(self, a, b, visited):
        if a == b:
            return True

        if visited[a]:
            return False

        visited[a] = True

        neighbors = self.adjacencyList[a]

        for it in neighbors:
            if not visited[it]:
                if self.hasPathUsingDfs(it, b, visited):
                    return True

        return False


def main():
    vertices, edges = map(int, input().split())

    graph = PathInMaze(vertices)

    for _ in range(edges):
        a, b = map(int, input().split())
        graph.addEdge(a - 1, b - 1)

    a, b = map(int, input().split())

    print("1" if graph.hasPath(a - 1, b - 1) else "0")


if __name__ == "__main__":
    main()