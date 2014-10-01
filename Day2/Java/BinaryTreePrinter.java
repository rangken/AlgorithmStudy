public class BinaryTreePrinter{
  static public <T> void printPreOrder(BinaryTree.Node<T> node){
    if(node == null){
      return;
    }
    node.visit();
    System.out.print(" ");
    printPreOrder(node.left);
    printPreOrder(node.right);
  }

  static public <T> void printInOrder(BinaryTree.Node<T> node){
    if(node == null){
      return;
    }
    printInOrder(node.left);
    node.visit();
    System.out.print(" ");
    printInOrder(node.right);
  }

  static public <T> void printPostOrder(BinaryTree.Node<T> node){
    if(node == null){
      return;
    }
    printPostOrder(node.left);
    printPostOrder(node.right);
    node.visit();
    System.out.print(" ");
  }

  static public <T>  void printLevelOrder(BinaryTree.Node<T> node){
    // 레벨 별로 순회 하는 방법
    java.util.Queue<BinaryTree.Node<T>> queue = new java.util.LinkedList<BinaryTree.Node<T>>();
    queue.offer(node);
    while(queue.peek() != null){
        BinaryTree.Node<T> t = queue.poll();
        t.visit();
        System.out.print(" ");
        if(t.left != null){
            queue.offer(t.left);
        }
        if(t.right != null){
            queue.offer(t.right);
        }
    }
  }

  static public <T> void printBFS(BinaryTree.Node<T> node){
    java.util.Stack<BinaryTree.Node<T>> stack = new java.util.Stack<BinaryTree.Node<T>>();
    stack.push(node);
    while(!stack.isEmpty()){
        BinaryTree.Node<T> t = stack.pop();
        t.visit();
        System.out.print(" ");
        if(t.right != null){
            stack.push(t.right);
        }
        if(t.left != null){
            stack.push(t.left);
        }

    }
  }
  static public <T> void printInternal(java.util.List<BinaryTree.Node<T>> nodes,int level, int maxLevel){
    if (nodes.isEmpty() || isAllElementsNull(nodes))
      return;
    int floor = maxLevel - level;
    int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
    int firstSpaces = (int) Math.pow(2, (floor)) - 1;
    int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

    printWhitespaces(firstSpaces);

    java.util.List<BinaryTree.Node<T>> newNodes = new java.util.ArrayList<BinaryTree.Node<T>>();
    for (BinaryTree.Node<T> node : nodes) {
        if (node != null) {
            System.out.print(node.element);
            newNodes.add(node.left);
            newNodes.add(node.right);
        } else {
            newNodes.add(null);
            newNodes.add(null);
            System.out.print(" ");
        }

        printWhitespaces(betweenSpaces);
    }
    System.out.println("");

    for (int i = 1; i <= endgeLines; i++) {
        for (int j = 0; j < nodes.size(); j++) {
            printWhitespaces(firstSpaces - i);
            if (nodes.get(j) == null) {
                printWhitespaces(endgeLines + endgeLines + i + 1);
                continue;
            }

            if (nodes.get(j).left != null)
                System.out.print("/");
            else
                printWhitespaces(1);

            printWhitespaces(i + i - 1);

            if (nodes.get(j).right != null)
                System.out.print("\\");
            else
                printWhitespaces(1);

            printWhitespaces(endgeLines + endgeLines - i);
        }

        System.out.println("");
    }

    printInternal(newNodes, level + 1, maxLevel);
  }
  static public <T> void print(BinaryTree.Node<T> node){
    int maxLevel = getMaxLevel(node);
    java.util.LinkedList<BinaryTree.Node<T>> rootList = new java.util.LinkedList<BinaryTree.Node<T>>();
    rootList.add(node);
    printInternal(rootList,1,maxLevel);
  }

  static public <T> void printTest(BinaryTree.Node<T> node){
    java.util.LinkedList<BinaryTree.Node<T>> curLevel = new java.util.LinkedList<BinaryTree.Node<T>>();
    java.util.LinkedList<BinaryTree.Node<T>> nextLevel = curLevel;

    StringBuilder sb = new StringBuilder();
    curLevel.add(node);
    sb.append(node.element + "\n");

    while(nextLevel.size() > 0){
        nextLevel = new java.util.LinkedList<BinaryTree.Node<T>>();
        for (int i = 0; i < curLevel.size(); i++){
            BinaryTree.Node<T> cur = curLevel.get(i);

            if (cur.left != null) {
                nextLevel.add(cur.left);
                sb.append(cur.left.element + " ");
            }
            if (cur.right != null) {
                nextLevel.add(cur.right);
                sb.append(cur.right.element + " ");
            }
        }
        if (nextLevel.size() > 0) {
            sb.append("\n");
            curLevel = nextLevel;
        }
    }
    System.out.println(sb.toString());
  }
  private static <T> boolean isAllElementsNull(java.util.List<T> list) {
      for (Object object : list) {
          if (object != null)
              return false;
      }
      return true;
  }
  private static <T> void printWhitespaces(int count) {
      for (int i = 0; i < count; i++)
          System.out.print(" ");
  }

  protected static <T> int getMaxLevel(BinaryTree.Node<T> node){
    if(node == null)
        return 0;
    return java.lang.Math.max(getMaxLevel(node.left), getMaxLevel(node.right)) + 1;
  }
}
