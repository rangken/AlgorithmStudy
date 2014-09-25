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
- 회전 정리

>         /      /      \      \
>        /       \      /       \
>       <LL>    <LR>   <RL>     <R>

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


#### Red-Black Tree
![Red-Black Tree](../Resource/img/Red-Black-Tree1.png?raw=true)
- 조건
	- 1.노드는 레드 혹은 블랙 중의 하나이다.
	- 2. 루트 노드(시작점)은 블랙이다.
	- 3.모든 leaf node는 블랙이다.
	- 4.레드 노드의 자식노드 양쪽은 언제나 모두 블랙이다. 그러므로 블랙 노드만이 레드 노드의 부모 노드가 될 수 있다.
	- 5.어떤 노드로부터 시작되어 leaf node에 도달하는 모든 경로에는 leaf node를 제외하면 모두 같은 개수의 블랙 노드가 있다.

### 심화내용
- Segment Tree
  - 특정 구간에 대한 질문을 빠르게 답하는데 사용됨
  - 최소값을 미리 구해서 특정지점부터 특정 지점까지 차를 쉽게 구함(최소)
  - 구간의 합을 미리구해서 특정지점부터 특정 지점까지 합을 쉽게 구함 (합)

- Binary Indexed Tree(or Fenwik Tree)
  - 구간에 합을 기록해 놓고 특정 노드에 값은 자식들에 합과 부모의 차로 구할수있다.
  - Segment Tree 에 이진수를 이용해 업그레이드 시킴

- Splay Tree
  - self-adjust BST
- IntervalTree
- Trie
  - 단어를 정렬된 트리에 저장시킴
- Treap
  - BST 가 밸런스를 맞추지 못하는 문제를 해결하기위해 값에 대한 랜덤 값을 주어서 균형을 마추도록 한다.
  - 삽입 삭제될때 랜덤으로정해진 priority 를 이용해 삽입 삭제가된다. 랜덤값이 잘 분포되어있으므로 어느정도 균형을 맞춘다.
- SkipList
  - 링크드 리스트 인데 n 개의 next 포인터를 가져서 빨리 찾음

### 이야기하면 좋은 내용
- Downcasting
- @SuppressWarnings("unchecked")
