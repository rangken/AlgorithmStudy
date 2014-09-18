## DAY3 - TREE (AVL TREE, RB TREE)
### 공부한것
1. AVL TREE - 균형 마추기
2. RED-BLACK TREE - 기본 원리 구현
3. RED-BLACK 이용한 TREEMAP Collection 구현
4. TREE 를 이용한 산술

### TODO
- B+ TREE
- B* TREE

### 이론
#### 회전
- 왼쪽 회전
  - X 의 오른쪽 노드를 X 자리로 옴기고 X 를 옴겨진 노드에 왼쪽으로 옴김

>          A               C
>         / \       ->    / \
>        B   C           A   E
>           / \         / \
>          D   E       B   D


- 오른쪽 회전
  - X 왼쪽 노드를 X 자리로 옴기고 X 를 옴겨진 노드에 오른쪽으로 옴김

>          A               B
>         / \       ->    / \
>        B   C           D   A
>       / \                 / \
>      D   E               E   C


![트리 회전](../Resource/img/Tree-Rotate.png?raw=true)


#### AVL
- X 의 왼쪽 자식의 왼쪽 부속트리에 노드가 삽입된 경우 (LL)
  - ROTATE_LEFT(X)
![AVL 회전 LL](../Resource/img/Tree-Rotate-LL.png?raw=true)

- X 의 왼쪽 자식의 오른쪽 부속트리에 도드가 삽입된 경우(LR)
  - ROTATE_LEFT(X.LEFT) -> ROTATE_RIGHT(X)
![AVL 회전 LR](../Resource/img/Tree-Rotate-LR.png?raw=true)

- X 의 오른쪽 자식의 왼쪽 부속트리에 노드가 삽입된 경우(RL)
  - ROTATE_RIGHT(X.RIGHT) -> ROTATE_LEFT(X)
![AVL 회전 RL](../Resource/img/Tree-Rotate-RL.png?raw=true)

- X 의 오른쪽 자식의 오른쪽 부속트리에 노드가 삽입된 경우(RR)
  - ROTATE_RIGHT(X)
![AVL 회전 RR](../Resource/img/Tree-Rotate-RR.png?raw=true)

>         /      /      \      \
>        /       \      /       \
>       <LL>    <LR>   <RL>     <R>


#### Red-Black Tree
![Red-Black Tree](../Resource/img/Red-Black-Tree1.png?raw=true)

### 심화내용
- Segment Tree
  - 특정 구간에 대한 질문을 빠르게 답하는데 사용됨
  - 배열에 구간의 합을 미리구해서 특정지점부터 특정 지점까지 합을 쉽게 구함
- Binary Indexed Tree(or Fenwik Tree)
  - Segment Tree 에 이진수를 이용해 업그레이드 시킴
- Splay Tree
  - self-adjust BST
- IntervalTree
- Trie
  - String 을 정렬해서 트리에 저장
### 이야기하면 좋은 내용
- Downcasting
- @SuppressWarnings("unchecked")
