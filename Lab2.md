# COMP3500 실습 2

본 과제는 컴퓨터에서 해야 하는 과제입니다. 코드 작성이 끝났다면 실습 1에서 만들었던 깃 저장소에 커밋 및 푸시를 하고 슬랙을 통해 자동으로 채점을 받으세요.

자료구조는 대용량의 데이터를 효율적으로 저장 및 관리하는 수단을 제공합니다. 상황에 맞게 적절히 자료구조를 사용하면 알고리듬의 시간 및 공간 복잡도를 확연히 향상할 수 있지요. 본 과목에서 배우는 알고리듬들은 이미 여러분이 연결 리스트, 스택, 큐 등의 기본 자료구조를 매우 잘 알고 있다고 가정합니다. 따라서 알고리듬을 더 깊게 파기 전에 잠시 시간을 내서 흔히 사용되는 자료구조를 복습하고 처음부터 구현해보겠습니다.

## 1. 프로젝트를 준비한다

1. 실습 1을 반드시 끝내도록 하세요. 그래야만 본인의 컴퓨터에 `Lab2` 폴더가 올바르게 설정되어 있을 겁니다.

2. `Lab2` 폴더로 이동합니다.

3. `src/academy/pocu/comp3500/lab2/datastructure`에 `Node.java` 파일이 있는지 확인합니다. 그렇지 않다면 [다음 페이지](https://github.com/POCU/COMP3500StarterPack/tree/main/Lab2/src/academy/pocu/comp3500/lab2/datastructure)에서 다운로드하세요.

4. 다음의 클래스들을 `src/academy/pocu/comp3500/lab2` 폴더에 추가합니다.

   ```java
   package academy.pocu.comp3500.lab2;
   
   import academy.pocu.comp3500.lab2.datastructure.Node;
   
   public final class LinkedList {
       private LinkedList() { }
   
       public static Node append(final Node rootOrNull, final int data) {
           return null;
       }
   
       public static Node prepend(final Node rootOrNull, final int data) {
           return null;
       }
   
       public static Node insertAt(final Node rootOrNull, final int index, final int data) {
           return null;
       }
   
       public static Node removeAt(final Node rootOrNull, final int index) {
           return null;
       }
   
       public static int getIndexOf(final Node rootOrNull, final int data) {
           return -1;
       }
   
       public static Node getOrNull(final Node rootOrNull, final int index) {
           return null;
       }
   
       public static Node reverse(final Node rootOrNull) {
           return null;
       }   
   
       public static Node interleaveOrNull(final Node root0OrNull, final Node root1OrNull) {
           return null;
       }
   }
   ```

   ```java
   package academy.pocu.comp3500.lab2;
   
   import academy.pocu.comp3500.lab2.datastructure.Node;
   
   public final class Stack {
       public void push(final int data) {
   
       }
   
       public int peek() {
           return -1;
       }
   
       public int pop() {
           return -1;
       }
   
       public int getSize() {
           return -1;
       }
   }
   ```

   ```java
   package academy.pocu.comp3500.lab2;
   
   import academy.pocu.comp3500.lab2.datastructure.Node;
   
   public final class Queue {
       public void enqueue(final int data) {
   
       }
   
       public int peek() {
           return -1;
       }
   
       public int dequeue() {
           return -1;
       }
   
       public int getSize() {
           return -1;
       }
   }
   ```

## 2. 자료구조 구현하기

### 전반적인 규칙

- 여러분이 작성하는 클래스들은 반드시 `academy.pocu.comp3500.lab2` 패키지 안에 속해야 합니다.

- 정적 (`static`) 변수는 사용할 수 없습니다.

- `Collection<E>`이나 `List<E>` 인터페이스를 구현하는 클래스들은 사용할 수 없습니다. 이런 클래스의 몇 예는 다음과 같습니다.

  - `LinkedList`
  - `Stack`
  - `Queue`
  - `ArrayList`,
  - 등

  그냥 기본 데이터형 외에 Java에서 자체적으로 제공하는 자료구조를 사용하지 않으면 됩니다.

- `java.util.Arrays` 패키지를 사용할 수 없습니다. 사용하면 0점이 뜨니 시도조차 하지 마세요. ;)

- `Node.java` 파일의 내용을 변경하지 마세요. 이 파일의 내용을 변경해도 빌드봇은 변경된 코드를 무시하고 채점을 진행합니다.

### 2.1 `LinkedList` 클래스의 메서드 구현하기

요즘 세상에서는 `LinkedList`를 개체지향(OO)적으로 만드는 경우가 많습니다. 하지만 `LinkedList`의 내부 연산은 개체지향 프로그래밍(OOP)하고는 아무 상관이 없죠. 연결 리스트의 실제 연산을 제대로 이해하기 위해 절차지향적(procedural)으로 `LinkedList`를 구현해보겠습니다. 이 실습 동안에는 함수 호출자가 노드의 소유권이 여러 연결 리스트에 속하는 일이 없도록 보장한다고 가정하셔도 좋습니다.

#### 2.1.1 `append()` 메서드를 구현한다

- ```
  append()
  ```

   

  메서드는 다음의 인자를 받습니다.

  - 루트 노드: `Node rootOrNull`
  - 연결 리스트에 추가할 데이터: `int data`

- 연결 리스트의 제일 마지막에 새 노드를 추가합니다.

- 연결 리스트의 루트 노드를 반환합니다.

```java
Node root = LinkedList.append(null, 10);

root = LinkedList.append(root, 11); // root: 10, list: 10 -> 11
root = LinkedList.append(root, 12); // root: 10, list: 10 -> 11 -> 12
```

#### 2.1.2 `prepend()` 메서드를 구현한다

- ```
  prepend()
  ```

   

  메서드는 다음의 인자를 받습니다.

  - 루트 노드: `Node rootOrNull`
  - 연결 리스트에 삽입할 데이터: `int data`

- 연결 리스트의 제일 앞에 새로운 노드를 삽입합니다.

- 연결 리스트의 루트 노드를 반환합니다.

```java
Node root = LinkedList.append(null, 10);

root = LinkedList.prepend(root, 11); // root: 11, list: 11 -> 10
root = LinkedList.prepend(root, 12); // root: 12, list: 12 -> 11 -> 10
```

#### 2.1.3 `insertAt()` 메서드를 구현한다

- ```
  insertAt()
  ```

   

  메서드는 다음의 인자를 받습니다.

  - 루트 노드: `Node rootOrNull`
  - 색인 위치: `int index`
  - 연결 리스트에 삽입할 데이터: `int data`

- `index`의 시작은 0입니다.

- 지정된 색인 위치에 새로운 노드를 삽입합니다.

- `index`가 유효한 범위 밖이라면 아무 일도 일어나지 않습니다.

- 연결 리스트의 루트를 반환합니다.

```java
Node root = LinkedList.append(null, 10);

root = LinkedList.insertAt(root, 0, 11); // root: 11, list: 11 -> 10
root = LinkedList.insertAt(root, 1, 12); // root: 11, list: 11 -> 12 -> 10
```

#### 2.1.4 `removeAt()` 메서드를 구현한다

- ```
  removeAt()
  ```

   

  메서드는 다음의 인자를 받습니다.

  - 루트 노드: `Node rootOrNull`
  - 색인 위치: `int index`

- `index`의 시작은 0입니다.

- `index`로 지정된 위치에 있는 노드를 지웁니다.

- 삭제할 수 없는 경우라면 아무 일도 일어나지 않습니다.

- 루트 노드를 반환합니다.

```java
Node root = LinkedList.append(null, 10);

root = LinkedList.append(root, 11); 
root = LinkedList.append(root, 12);
root = LinkedList.append(root, 13);

root = LinkedList.removeAt(root, 0); // root: 11, list: 11 -> 12 -> 13
root = LinkedList.removeAt(root, 1); // root: 11, list: 11 -> 13
```

#### 2.1.5 `getIndexOf()` 메서드를 구현한다

- ```
  getIndexOf()
  ```

   

  메서드는 다음의 인자를 받습니다.

  - 루트 노드: `Node rootOrNull`
  - 연결 리스트에서 찾을 데이터: `int data`

- `data`를 포함하고 있는 첫 번째 노드의 색인을 반환합니다. 색인의 시작은 0입니다.

- 연결 리스트 안에 그 데이터가 없다면 -1을 반환합니다.

```java
Node root = LinkedList.append(null, 10);

root = LinkedList.append(root, 11); 

int index = LinkedList.getIndexOf(root, 10); // index: 0
index = LinkedList.getIndexOf(root, 11); // index: 1
```

#### 2.1.6 `getOrNull()` 메서드를 구현한다

- ```
  getOrNull()
  ```

   

  메서드는 다음의 인자를 받습니다.

  - 루트 노드: `Node rootOrNull`
  - 색인 위치: `int index`

- `index`의 시작은 0입니다.

- `index`로 지정된 위치에 있는 노드를 반환합니다.

- 입력값이 유효하지 않다면 `null`을 반환합니다.

```java
Node root = LinkedList.append(null, 10);

root = LinkedList.append(root, 11);

Node node = LinkedList.getOrNull(root, 0); // node: 10
node = LinkedList.getOrNull(root, 1); // node: 11
```

#### 2.1.7 `reverse()` 메서드를 구현한다

- ```
  reverse()
  ```

   

  메서드는 다음의 인자를 받습니다.

  - 루트 노드: `Node rootOrNull`

- 연결 리스트를 뒤집습니다.

- 루트 노드를 반환합니다.

- 인자로 들어온 연결 리스트는 이 함수 실행 후에 유효한 상태가 아니게 됩니다 (즉, 더 이상 사용불가)

```java
Node root = LinkedList.append(null, 10);

root = LinkedList.append(root, 11);
root = LinkedList.append(root, 12);
root = LinkedList.append(root, 13);
root = LinkedList.append(root, 14);

root = LinkedList.reverse(root); // root: 14, list: 14 -> 13 -> 12 -> 11 -> 10
```

#### 2.1.8 `interleaveOrNull()` 메서드를 구현한다

- ```
  interleaveOrNull()
  ```

   

  메서드는 다음의 인자를 받습니다.

  - 첫 번째 연결 리스트의 루트: `Node root0OrNull`
  - 두 번째 연결 리스트의 루트: `Node root1OrNull`

- 두 연결 리스트를 하나로 합칩니다. 이때, 두 연결 리스트에서 노드를 처음부터 하나씩 번갈아 가면서 취하는 방법을 사용합니다.

- 새 루트는 첫 번째 연결 리스트의 루트가 되는 게 디폴트입니다.

- 새 루트를 반환합니다.

- 인자로 들어온 연결 리스트는 이 함수 실행 후에 유효한 상태가 아니게 됩니다 (즉, 더 이상 사용불가)

```java
Node root1 = LinkedList.append(null, 10);

root1 = LinkedList.append(root1, 11);
root1 = LinkedList.append(root1, 12);

Node root2 = new Node(13);

root2 = LinkedList.append(root2, 14);
root2 = LinkedList.append(root2, 15);

Node newRoot = LinkedList.interleaveOrNull(root1, root2); // newRoot: 10, list: 10 -> 13 -> 11 -> 14 -> 12 -> 15
```

### 2.2 `Stack` 클래스 구현하기

스택을 구현할 때는 일반적으로 원형 버퍼(circular buffer)를 사용합니다. 하지만 이 실습에서는 좀 다르게 해 볼까요? 2.1 섹션에서 구현한 연결 리스트를 사용하여 `Stack`을 구현하세요.

#### 2.2.1 생성자를 구현한다

- 본 클래스에는 매개변수를 받지 않는 생성자가 있어야 합니다.

#### 2.2.2 `push()` 메서드를 구현한다

- ```
  push()
  ```

   

  메서드는 다음의 인자를 받습니다.

  - push 할 데이터: `int data`

- 스택에 `data`를 push 합니다.

- 아무것도 반환하지 않습니다.

```java
Stack stack = new Stack();

stack.push(20); // stack: 20
stack.push(21); // stack: 21
                          20
```

#### 2.2.3 `peek()` 메서드를 구현한다

- `peek()` 메서드는 어떤 인자도 받지 않습니다.
- 스택의 가장 위(top)에 위치한 데이터를 반환합니다.
- 스택이 비어있을 때 `peek()` 메서드를 호출하는 일은 없다고 가정해도 좋습니다.

```java
Stack stack = new Stack();

stack.push(20); // stack: 20

int data = stack.peek(); // data: 20, stack: 20

stack.push(21); // stack: 20, 21

data = stack.peek(); // data: 21, stack: 21
                     //                  20
```

#### 2.2.4 `pop()` 메서드를 구현한다

- `pop()` 메서드는 어떤 인자도 받지 않습니다.
- 스택의 가장 위에 위치한 데이터를 제거한 뒤 반환합니다.
- 스택이 비어있을 때 `pop()` 메서드를 호출하는 일은 없다고 가정해도 좋습니다.

```java
Stack stack = new Stack();

stack.push(20);
stack.push(21); // stack: 21
                //        20

int data = stack.pop(); // data: 21, stack: 20
```

#### 2.2.5 `getSize()` 메서드를 구현한다

- `getSize()` 메서드는 어떤 인자도 받지 않습니다.
- 스택의 크기를 반환합니다.

```java
Stack stack = new Stack();

stack.push(20);
stack.push(21);

int size = stack.getSize(); // 2
```

### 2.3 `Queue` 클래스 구현하기

큐를 구현할 때는 일반적으로 원형 버퍼(circular buffer)를 사용합니다. 하지만 `Stack`에서 그랬듯이 여기서도 연결 리스트를 사용하겠습니다. 2.1 섹션에서 구현한 연결 리스트를 사용하여 `Queue`를 구현하세요.

#### 2.3.1 생성자를 구현한다

- 본 클래스에는 매개변수를 받지 않는 생성자가 있어야 합니다.

#### 2.3.2 `enqueue()` 메서드를 구현한다

- ```
  enqueue()
  ```

   

  메서드는 다음의 인자를 받습니다.

  - 큐에 넣을 데이터: `int data`

- 큐에 `data` 데이터를 넣습니다(enqueue).

- 아무것도 반환하지 않습니다.

```java
Queue queue = new Queue();

queue.enqueue(20); // queue: 20
queue.enqueue(21); // queue: 20, 21
```

#### 2.3.3 `peek()` 메서드를 구현한다

- `peek()` 메서드는 어떤 인자도 받지 않습니다.
- 큐의 가장 앞(front)에 있는 데이터를 반환합니다.
- 큐가 비어있을 때 `peek()` 메서드를 호출하는 일은 없다고 가정해도 좋습니다.

```java
Queue queue = new Queue();

queue.enqueue(20);
queue.enqueue(21); // queue: 20, 21

int data = queue.peek(); // data: 20, queue: 20, 21
```

#### 2.3.4 `dequeue()` 메서드를 구현한다

- `dequeue()` 메서드는 어떤 인자도 받지 않습니다.
- 큐의 가장 앞에 위치한 데이터 제거한 뒤, 반환합니다.
- 큐가 비어있을 때 `dequeue()` 메서드를 호출하는 일은 없다고 가정해도 좋습니다.

```java
Queue<Integer> queue = new Queue();

queue.enqueue(20);
queue.enqueue(21); // queue: 20, 21

int data = queue.dequeue(); // data: 20, queue: 21
```

#### 2.3.5 `getSize()` 메서드를 구현한다

- `getSize()` 메서드는 어떤 인자도 받지 않습니다.
- 큐의 크기를 반환합니다.

```java
Queue queue = new Queue();

queue.enqueue(20);
queue.enqueue(21);

int size = queue.getSize(); // 2
```

## 3. 본인 컴퓨터에서 테스트하는 법

- `Program.java`를 아래처럼 바꾼 뒤 실행하세요.

```java
package academy.pocu.comp3500.lab2.app;

import academy.pocu.comp3500.lab2.LinkedList;
import academy.pocu.comp3500.lab2.datastructure.Node;
import academy.pocu.comp3500.lab2.Queue;
import academy.pocu.comp3500.lab2.Stack;

public class Program {

    public static void main(String[] args) {
        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);
            root = LinkedList.append(root, 12);

            assert (root.getData() == 10);

            Node next = root.getNextOrNull();

            assert (next.getData() == 11);

            next = next.getNextOrNull();

            assert (next.getData() == 12);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.prepend(root, 11);

            assert (root.getData() == 11);

            root = LinkedList.prepend(root, 12);

            assert (root.getData() == 12);

            Node next = root.getNextOrNull();

            assert (next.getData() == 11);

            next = next.getNextOrNull();

            assert (next.getData() == 10);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.insertAt(root, 0, 11);

            assert (root.getData() == 11);

            root = LinkedList.insertAt(root, 1, 12);

            assert (root.getData() == 11);

            Node next = root.getNextOrNull();

            assert (next.getData() == 12);

            next = next.getNextOrNull();

            assert (next.getData() == 10);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);
            root = LinkedList.append(root, 12);
            root = LinkedList.append(root, 13);

            root = LinkedList.removeAt(root, 0);

            assert (root.getData() == 11);

            root = LinkedList.removeAt(root, 1);

            assert (root.getData() == 11);

            Node next = root.getNextOrNull();

            assert (next.getData() == 13);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);

            int index = LinkedList.getIndexOf(root, 10);

            assert (index == 0);

            index = LinkedList.getIndexOf(root, 11);

            assert (index == 1);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);

            Node node = LinkedList.getOrNull(root, 0);

            assert (node.getData() == 10);

            node = LinkedList.getOrNull(root, 1);

            assert (node.getData() == 11);
        }

        {
            Node root1 = LinkedList.append(null, 10);

            root1 = LinkedList.append(root1, 11);
            root1 = LinkedList.append(root1, 12);

            Node root2 = LinkedList.append(null, 13);

            root2 = LinkedList.append(root2, 14);
            root2 = LinkedList.append(root2, 15);

            Node newRoot = LinkedList.interleaveOrNull(root1, root2); // newRoot: 10, list: 10 -> 13 -> 11 -> 14 -> 12 -> 15

            assert (newRoot.getData() == 10);

            Node next = newRoot.getNextOrNull();

            assert (next.getData() == 13);

            next = next.getNextOrNull();

            assert (next.getData() == 11);

            next = next.getNextOrNull();

            assert (next.getData() == 14);

            next = next.getNextOrNull();

            assert (next.getData() == 12);

            next = next.getNextOrNull();

            assert (next.getData() == 15);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);
            root = LinkedList.append(root, 12);
            root = LinkedList.append(root, 13);
            root = LinkedList.append(root, 14);

            root = LinkedList.reverse(root); // root: 14, list: 14 -> 13 -> 12 -> 11 -> 10

            assert (root.getData() == 14);

            Node next = root.getNextOrNull();

            assert (next.getData() == 13);

            next = next.getNextOrNull();

            assert (next.getData() == 12);

            next = next.getNextOrNull();

            assert (next.getData() == 11);

            next = next.getNextOrNull();

            assert (next.getData() == 10);
        }

        {
            Stack stack = new Stack();

            stack.push(20);
            stack.push(21); // stack: 21
                            //        20

            int data = stack.pop();

            assert (data == 21);

            data = stack.pop();

            assert (data == 20);
        }

        {
            Stack stack = new Stack();

            stack.push(20); // stack: 20

            assert (stack.peek() == 20);

            stack.push(21); // stack: 21
                            //        20

            assert (stack.peek() == 21);
        }

        {
            Stack stack  = new Stack();

            stack.push(20);
            stack.push(21);

            assert (stack.getSize() == 2);
        }

        {
            Queue queue = new Queue();

            queue.enqueue(20);

            assert (queue.peek() == 20);

            queue.enqueue(21);

            assert (queue.peek() == 20);
        }

        {
            Queue queue = new Queue();

            queue.enqueue(20);
            queue.enqueue(21);

            int data = queue.dequeue();

            assert (data == 20);

            data = queue.dequeue();

            assert (data == 21);
        }

        {
            Queue queue = new Queue();

            queue.enqueue(20);
            queue.enqueue(21);

            assert (queue.getSize() == 2);
        }
    }
}
```

## 4. 커밋, 푸시 그리고 빌드 요청

이건 어떻게 하는지 이제 다 아시죠? :)