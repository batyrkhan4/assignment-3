# Experimental Analysis Results

### 1. Minimum Spanning Tree (Kruskal vs Prim)
| Size | V | E | Kruskal Time (ns) | Kruskal Ops | Prim Time (ns) | Prim Ops |
|---|---|---|---|---|---|---|
| Small | 10 | 30 | 398260 | 60 | 289900 | 70 |
| Medium | 100 | 500 | 414580 | 1000 | 389180 | 1208 |
| Large | 1000 | 5000 | 2006420 | 10000 | 2483480 | 13318 |

### 2. Shortest Paths (Dijkstra)
| Size | V | E | Execution Time (ns) | Operations Count |
|---|---|---|---|---|
| Small | 10 | 30 | 477440 | 47 |
| Medium | 100 | 500 | 185080 | 780 |
| Large | 1000 | 5000 | 1940860 | 7839 |

### 3. Connectivity (Kosaraju SCC & Topological Sort)
| Size | V | E | SCC Time (ns) | TopoSort (DAG) Time (ns) |
|---|---|---|---|---|
| Small | 10 | 30 | 389680 | 243260 |
| Medium | 100 | 500 | 172320 | 72780 |
| Large | 1000 | 5000 | 1218080 | 241440 |