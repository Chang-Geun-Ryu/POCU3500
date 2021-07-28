# COMP3500 실습 8

아... 안돼! 공원에서 평화롭게 코딩을 하고 있었는데 갑자기 악마의 군주가 나타나 날 미로 속에 가뒀어요! 이 미로는 너무나 춥고 어둡네요. 무서워요 T^T. 대체 내가 어디 있는지도, 출구에서 얼마나 떨어져 있는지도 모르겠어요. 출구가 있기는 한 걸까요? 으아아.. 너무 머릿속이 복잡해요. 용사님, 제가 여기서 빠져나갈 수 있게 도와주세요!

## 1. 프로젝트를 준비한다

1. `Lab8` 폴더로 이동합니다.

2. `src/academy/pocu/comp3500/lab8/maze` 폴더 안에 `Point.java` 파일이 있는지 확인하세요. 만약 없다면 [이 페이지](https://github.com/POCU/COMP3500StarterPack/tree/main/Lab8/src/academy/pocu/comp3500/lab8/maze)에서 다운로드하세요.

3. `src/academy/pocu/comp3500/lab8` 폴더에 다음 클래스들을 추가합니다.

   ```java
   package academy.pocu.comp3500.lab8;
   
   import academy.pocu.comp3500.lab8.maze.Point;
   
   import java.util.List;
   
   public final class MazeSolver {
       public static List<Point> findPath(final char[][] maze, final Point start) {
           return null;
       }
   }
   ```

## 2. 전반적인 규칙

- 새로 추가하는 클래스들은 `academy.pocu.comp3500.lab8` 패키지에 속해야 합니다.

- ```
  Collection<E>
  ```

  나

   

  ```
  List<E>
  ```

   

  인터페이스를 구현하는 클래스 중 사용할 수 있는 클래스는 다음뿐입니다.

  - `LinkedList`
  - `ArrayList`
  - `Stack`
  - `PriorityQueue`
  - `Hashmap`

- `java.util.Arrays`, `java.util.Collections`, `java.util.stream.Stream` 패키지는 사용할 수 없습니다. 사용하면 0점이 뜨니 시도조차 하지 마세요. :)

- `System.arraycopy()` 메서드를 사용할 수 없습니다.

- `Point.java` 파일의 내용을 변경하지 마세요. 이 파일의 내용을 변경해도 빌드봇은 변경된 코드를 무시하고 채점을 진행합니다.

## 3. `MazeSolver` 클래스 구현하기

- 여러분이 해야 할 일은 출구까지의 경로를 찾는 것입니다.
- 출구는 최대 한 개가 있습니다.
- 다음의 규칙을 지켜야 합니다.
  - 벽을 통과해 이동할 수 없습니다.
  - 바로 이웃 타일로만 이동할 수 있습니다. (상하좌우 이동 가능, 대각선 이동 불가능)

### 3.1 `findPath()` 메서드를 구현한다

- 이 메서드는 다음의 인자를 받습니다.

  - 미로의 맵: `char[][] maze`
  - 시작 위치: `Point start`

- ```
  maze
  ```

  는 2D

   

  ```
  char
  ```

   

  배열로 각 요소의 값은

   

  ```
  x
  ```

  ,

   

  ```
  E
  ```

  , 빈칸(

  ```
  ' '
  ```

  ) 문자 중 하나입니다.

  - `x`: 벽
  - `E`: 출구
  - `' '`: 경로

- ```
  start
  ```

  는

   

  ```
  Point
  ```

   

  개체입니다.

  - `x` 매개변수는 `char` 배열에서 가로 위치를 나타냅니다.
  - `y` 매개변수는 `char` 배열에서 세로 위치를 나타냅니다.
  - 미로의 왼쪽 위 모서리는 `Point(0, 0)`입니다.

- 이 메서드는 출구까지 향하는 경로를 `Point`의 리스트로 반환합니다.

- 출구가 없는 경우는 빈 리스트를 반환합니다.

- 원래 `maze`의 출구까지의 경로를 올바로 반환한다면 인자로 들어온 `maze`의 내용을 변경해도 상관없습니다.

- 힌트: 이미 거쳐 간 위치로 다시 이동하는 것을 막기 위해 추가 공간을 사용해야 할 수도 있습니다.

```java
char[][] maze = new char[][]{
        {'x', 'x', 'x', 'x', 'x'},
        {'x', ' ', ' ', ' ', 'x'},
        {'x', ' ', 'x', 'E', 'x'},
        {'x', 'x', 'x', 'x', 'x'}
};

List<Point> result = MazeSolver.findPath(maze, new Point(1, 2)); 
// [Point(1, 2), Point(1, 1), Point(2, 1), Point(3, 1), Point(3, 2)]
```

## 4. 본인 컴퓨터에서 테스트하는 법

- `Program.java`를 아래처럼 바꾼 뒤 실행하세요.

```java
package academy.pocu.comp3500.lab8.app;

import academy.pocu.comp3500.lab8.MazeSolver;
import academy.pocu.comp3500.lab8.maze.Point;

import java.util.List;

public class Program {

    public static void main(String[] args) {

        char[][] maze3x3 = new char[][]{
                {'x', 'x', 'x'},
                {'x', 'E', 'x'},
                {'x', 'x', 'x'}
        };

        List<Point> result = MazeSolver.findPath(maze3x3, new Point(1, 1));

        assert (result.size() == 1);
        assert (result.get(0).getX() == 1 && result.get(0).getY() == 1);

        char[][] maze3x5 = new char[][]{
                {'x', 'x', 'x'},
                {'x', ' ', 'x'},
                {'x', ' ', 'x'},
                {'x', 'E', 'x'},
                {'x', 'x', 'x'}
        };

        result = MazeSolver.findPath(maze3x5, new Point(1, 1));

        assert (result.size() == 3);
        assert (result.get(0).getX() == 1 && result.get(0).getY() == 1);
        assert (result.get(1).getX() == 1 && result.get(1).getY() == 2);
        assert (result.get(2).getX() == 1 && result.get(2).getY() == 3);

        char[][] maze7x5 = new char[][]{
                {'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'x', 'E', 'x', ' ', ' ', ' ', 'x'},
                {'x', ' ', 'x', ' ', 'x', ' ', 'x'},
                {'x', ' ', ' ', ' ', 'x', ' ', 'x'},
                {'x', 'x', 'x', 'x', 'x', 'x', 'x'}
        };

        result = MazeSolver.findPath(maze7x5, new Point(5, 3));

        assert (result.size() == 11);
        assert (result.get(0).getX() == 5 && result.get(0).getY() == 3);
        assert (result.get(1).getX() == 5 && result.get(1).getY() == 2);
        assert (result.get(2).getX() == 5 && result.get(2).getY() == 1);
        assert (result.get(3).getX() == 4 && result.get(3).getY() == 1);
        assert (result.get(4).getX() == 3 && result.get(4).getY() == 1);
        assert (result.get(5).getX() == 3 && result.get(5).getY() == 2);
        assert (result.get(6).getX() == 3 && result.get(6).getY() == 3);
        assert (result.get(7).getX() == 2 && result.get(7).getY() == 3);
        assert (result.get(8).getX() == 1 && result.get(8).getY() == 3);
        assert (result.get(9).getX() == 1 && result.get(9).getY() == 2);
        assert (result.get(10).getX() == 1 && result.get(10).getY() == 1);

        char[][] maze8x6 = new char[][]{
                {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'x', 'x', ' ', 'x', ' ', 'E', ' ', 'x'},
                {'x', 'x', ' ', 'x', ' ', 'x', 'x', 'x'},
                {'x', ' ', ' ', ' ', ' ', 'x', ' ', 'x'},
                {'x', 'x', ' ', 'x', ' ', ' ', ' ', 'x'},
                {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}
        };

        result = MazeSolver.findPath(maze8x6, new Point(2, 2));

        assert (result.size() == 7);
        assert (result.get(0).getX() == 2 && result.get(0).getY() == 2);
        assert (result.get(6).getX() == 5 && result.get(6).getY() == 1);
    }
}
```

## 5 커밋, 푸시 그리고 빌드 요청

이건 어떻게 하는지 이제 다 아시죠? :)