## DAY2 - TREE (B TREE, BST)
### 공부한내용
- 이진트리, 이진탐색트리
- 이진트리순회 (in order. post order. pre order)
- 이진트리순회 (너비우선탐색-레벨 순회. 깊이우선탐색)
- 트리 높이 구하기

### 이론
#### 트리 종류
- 이진트리 : 최대 2개만의 자식을 가질수 있다.
- 포화이진트리 : 모든 레벨의 노드가 꽉차 있는 이진트리, 모든 노드의 차수가2
- 완전이진트리 : 로트노드부터 순열을 이루고 있음, 왼쪽부터 채워져있다

#### Balanced
- 높이 차를 맞추는 트리.

#### 트리탐색
- 전위 탐색(DLR) - 루트->왼쪽->오른쪽
- 중위 탐색(LDR) - 왼쪽->루트->오른쪽
- 후위 탐색(LRD) - 왼쪽->오른쪽->루트
- 레벨 탐색 - 루트노드에서 리프노드로 레벨별로 프린트

#####  레벨 탐색 순서
- (BFS)
    - 루트노드 탐색
    - L 레벨 탐색하면서 모든항목을 큐에 넣음
    - L+1 레벨 들어가서 탐색
    - 모든 레벨이 끝날떄 까지 탐색함
- (DFS)

#### 특수트리
- B 트리 : 바이너리 트리
- B-FREE : 멀티 WAY SEARCH TREE
- 이진 검색 트리
    - 삽입 : 순서에 맡에 넣음됨
    - 삭제
        - 리프노드일 경우
        - 자식을 하나 갖을 경우
        - 자식이 두개일경우 : 왼쪽의 최대항목 오른쪽에 최소항목으로 교체!
- 완전 균형 이진트리 : 높이의 차이가 0
- AVL 트리 : 높이의 차이가 1, 검색 속도를 유지한다.
    - AVL 조건 맞추기 위해 LL LR RR RL 회전을 함
- RED-BLACK 트리
- 스레드 이진 트리 : NULL 포인터를 사용하여 스택/큐가 필요없이 재귀적인 중위 순회를 빠르게 가능하게 하는 트리
- 중위 스레드 트리
- 수식트리 : 일반정수식(중위) 를 컴퓨터는 후위로 바꿈
- XOR 트리 : 부모가 자식노드를 탐색할때 XOR 연산 사용, 스레드 이진트리 처럼 스택이나 큐를 필요로 하지 않음

#### BFS DFS
- Breadth-first  Search
- Depth-first  Search

### Reference
[좋은 자바 알고리즘 구현](https://github.com/phishman3579/java-algorithms-implementation)
[DFS](https://www.hackerrank.com/challenges/pacman-dfs/submissions/game/662756)
[BFS](https://www.hackerrank.com/challenges/pacman-bfs/submissions/game/686602)
[VisuAlgo](http://www.comp.nus.edu.sg/~stevenha/visualization/)
[B+Tree](https://www.cs.usfca.edu/~galles/visualization/BTree.html)
