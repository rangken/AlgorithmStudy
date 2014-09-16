## DAY3 - TREE (AVL TREE, RB TREE)
### 공부한것
1. AVL TREE - 균형 마추기
2. RED-BLACK TREE - 기본 원리 구현
3. RED-BLACK 이용한 TREEMAP Collection 구현
4. TREE 를 이용한 산술

### 더 해야할것

### 이론
#### 회전
- 왼쪽 회전
  - X 왼쪽 노드를 X 자리로 옴기고 X 를 옴겨진 노드에 오른쪽으로 옴김
>          A               B
>         / \       ->    / \
>        B   C           D   A
>       / \                 / \
>      D   E               E   C


- 오른쪽 회전
  - X 의 오른쪽 노드를 X 자리로 옴기고 X 를 옴겨진 노드에 왼쪽으로 옴김
>          A               C
>         / \       ->    / \
>        B   C           A   E
>           / \         / \
>          D   E       B   D

#### AVL
- X 의 왼쪽 자식의 왼쪽 부속트리에 노드가 삽입된 경우 (LL)
  - ROTATE_LEFT(X)
- X 의 왼쪽 자식의 오른쪽 부속트리에 도드가 삽입된 경우(LR)
  - ROTATE_RIGHT(X.LEFT) -> ROTATE_LEFT(X)
- X 의 오른쪽 자식의 왼쪽 부속트리에 노드가 삽입된 경우(RL)
  - ROTATE_LEFT(X.RIGHT) -> ROTATE_RIGHT(X)
- X 의 오른쪽 자식의 오른쪽 부속트리에 노드가 삽입된 경우(RR)
  - ROTATE_RIGHT(X)
  
>         /      /      \      \
>        /       \      /       \
>       <LL>    <LR>   <RL>     <R>
