# COMP3500 과제 3

강의에서 미니맥스를 사용하여 게임 AI를 구현하는 법을 배워봤으니 실제로 AI를 만들어 봅시다. 각자 체스 봇을 하나씩 만들어서 서로 경쟁하게 만들면 될 것 같네요!

체스 AI는 1950년부터 존재해왔습니다. 초창기 AI는 별로였지만 수십 년간 발전을 거듭해온 결과 이제는 프로 체스 플레이어들도 이기기 힘든 AI가 등장했죠. 물론 강의에서 배운 미니맥스 만으로는 이 정도 수준의 AI를 만들 수 없습니다. 하지만 취미로 체스를 플레이하는 사람들에 대적할만한 AI는 만들 수 있죠.

체스 AI를 만드는 방법은 여러 가지가 있습니다. 공격에만 치중하는 알고리듬도 있을 수 있고, 방어를 더 중요시하는 알고리듬도 있을 수 있죠. 혹은 이 둘을 적당히 섞어놓은 알고리듬도 있을 겁니다. 딱히 정답이 없으니 잘 생각해서 여러분만의 AI를 만들어 보세요!

## 1. 프로젝트를 준비한다

1. `Assignment3` 폴더로 이동합니다.

2. `src/academy/pocu/comp3500/assignment3/chess` 폴더 안에 다음의 클래스들이 있는지 확인하고, 만약 그렇지 않다면 [이 페이지](https://github.com/POCU/COMP3500StarterPack/tree/main/Assignment3/src/academy/pocu/comp3500/assignment3/chess)에서 다운로드하세요.

   - `PlayerBase.java`
   - `Move.java`

3. `src/academy/pocu/comp3500/assignment3/app` 폴더 안에 다음의 클래스들이 있는지 확인하고, 만약 그렇지 않다면 [이 페이지](https://github.com/POCU/COMP3500StarterPack/tree/main/Assignment3/src/academy/pocu/comp3500/assignment3/app)에서 다운로드하세요.

   - `Game.java`
   - `KeyboardPlayer.java`

4. `src/academy/pocu/comp3500/assignment3` 폴더에 다음과 같은 `Player.java` 파일을 추가합니다.

   ```java
   package academy.pocu.comp3500.assignment3;
   
   public class Player {
   
   }
   ```

## 2. 전반적인 규칙

- 새로 추가하는 클래스들은 `academy.pocu.comp3500.assignment3` 패키지 안에 넣어 주세요.

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

- `java.util.Arrays`, `java.util.Collections`, `java.util.stream.Stream`, `java.lang.Thread` 패키지는 사용할 수 없습니다.

- `System.arraycopy()`, `java.lang.Object.clone()` 메서드를 사용할 수 없습니다.

- `src/academy/pocu/comp3500/assignment3/chess` 패키지 안에 있는 파일들을 변경하지 마세요. 설사 변경해도 빌드봇은 변경된 코드를 무시하고 채점을 진행합니다.

- 파일 시스템에 파일을 작성하거나 읽을 수 없습니다.

- 여러분이 작성하는 AI의 기본은 미니맥스 알고리듬이어야 합니다.

- 여러분이 작성하는 코드는 반드시 스레드를 하나만 사용해야 합니다.

- 체스 프로그램은 힙 메모리가 넉넉하지 않은 환경에서 실행됩니다. 따라서 메모이제이션처럼 메모리를 많이 사용하는 최적화 기법을 사용하지 마세요.

- 소문자는 하얀색 피스, 대문자는 검은색 피스를 의미합니다.

## 3. 과제 3에서 사용하는 체스 규칙

- 체스 피스(piece)의 이동은 [기본 행마법](https://ko.wikipedia.org/wiki/체스_규칙#기본_행마법)을 따릅니다.
- 다음 특수 행마법은 허용하지 않습니다.
  - [앙파상](https://ko.wikipedia.org/wiki/앙파상)
  - [프로모션](https://ko.wikipedia.org/wiki/프로모션_(체스))
  - [캐슬링](https://ko.wikipedia.org/wiki/캐슬링)
- 킹도 잡힐 수 있는 피스로 간주하며 체크메이트에 관한 특별한 규칙은 적용하지 않습니다.

## 4. `Player` 클래스 구현하기

### 4.1 생성자를 구현한다

- 생성자는 다음의 인자를 받습니다.
  - 체스 플레이어의 색상을 나타내는 불리언 값: `boolean isWhite`
  - 다음 이동할 피스를 결정하는데 사용할 수 있는 최대 시간: `int maxMoveTimeMilliseconds`
- 이 클래스는 반드시 `PlayerBase` 클래스를 상속해야 합니다.

### 4.2 `getNextMove()` 메서드를 구현한다

- `getNextMove()` 메서드는 두 가지 버전이 있습니다.

  1. 매개변수로 `char[][] board`만 받는 버전
  2. 매개변수로 `char[][] board`와 `Move opponentMove`를 받는 버전

  1번은 첫 수에서만 호출되는 메서드입니다. 그다음 수부터는 2번이 호출됩니다.

- 이 메서드는 다음의 인자를 받습니다.

  - 현재 체스판 상태: `char[][] board`
  - 상대방의 마지막 수: `Move opponentMove`

- 다음에 플레이할 수(move)를 반환합니다.

- 유효하지 않은 수를 반환하면 곧바로 경기에서 패합니다.

- 생성자의 인자로 전달된 `maxMoveTimeMilliseconds` 시간 안에 다음 수를 반환해야 합니다.

```java
final char[][] board = new char[8][8];

// ...

final int MAX_MOVE_TIME_MILLISECONDS = 1000;

Player player = new Player(true, MAX_MOVE_TIME_MILLISECONDS);

Move move = player.getNextMove(board);
move = player.getNextMove(board, move);
```

## 5. 본인 컴퓨터에서 테스트하는 법

`academy.pocu.comp3500.assignment3.app` 패키지 안에 `Game` 클래스가 들어있습니다. 이 클래스는 `nextTurn()`을 호출할 때마다 다음과 같은 결과를 콘솔 창에 출력합니다.

```
Turn: 9 (W)
Captured: ppB

  abcdefgh
 +--------+
0|RN KQBNR|
1|P PPPPPP|
2| P      |
3|        |
4|p       |
5| p      |
6|  pp pbp|
7|rnbkq nr|
 +--------+

Move: b (f7) -> (g6)
```

- Line 1: 턴 번호와 플레이어의 색상
- Line 2: 잡힌 체스 피스의 목록. 아직 잡힌 피스가 없을 경우 출력하지 않음
- Line 4 - 14: 이동을 마친 뒤 체스보드
- Line 16: 플레이어가 선택한 수

### `Program.java`

`Program.java`에서 다음 코드를 실행하면 50 수 까지 체스게임을 플레이 할 수 있습니다. `Player`와 `Game` 생성자가 모두 `MAX_MOVE_TIME_MILLISECONDS`를 인자로 받는 게 보이죠? 이건 여러분의 AI가 유효한 수를 계산하기 위해 사용할 수 있는 최대 시간입니다.

```java
package academy.pocu.comp3500.assignment3.app;

import academy.pocu.comp3500.assignment3.Player;
import academy.pocu.comp3500.assignment3.chess.PlayerBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Program {

    public static void main(String[] args) {
        final boolean IS_AUTO_PLAY = true; // true 라면 주기적으로 자동으로 다음 턴이 진행됨; false 라면 Enter/Return 키를 누를 때 진행됨
        final boolean IS_WHITE_KEYBOARD_PLAYER = false; // true 라면 하얀색 플레이어의 수를 콘솔에 입력해야 함
        final boolean IS_BLACK_KEYBOARD_PLAYER = false; // true 라면 검은색 플레이어의 수를 콘솔에 입력해야 함

        final int MAX_MOVE_TIME_MILLISECONDS = 1000; // Player 가 턴마다 수를 결정하는 데에 주어진 시간
        final long AUTO_PLAY_TURN_DURATION_IN_MILLISECONDS = 1000; // Autoplay 중 턴마다 일시중지 되는 기간

        PlayerBase whitePlayer;
        PlayerBase blackPlayer;

        if (IS_WHITE_KEYBOARD_PLAYER) {
            whitePlayer = new KeyboardPlayer(true);
        } else {
            whitePlayer = new Player(true, MAX_MOVE_TIME_MILLISECONDS);
        }
        if (IS_BLACK_KEYBOARD_PLAYER) {
            blackPlayer = new KeyboardPlayer(false);
        } else {
            blackPlayer = new Player(false, MAX_MOVE_TIME_MILLISECONDS);
        }

        Game game = new Game(whitePlayer, blackPlayer, MAX_MOVE_TIME_MILLISECONDS);

        System.out.println("Let the game begin!");
        System.out.println(game.toString());

        for (int i = 0; i < 50; ++i) {
            if (game.isNextTurnWhite() && IS_BLACK_KEYBOARD_PLAYER
                    || !game.isNextTurnWhite() && IS_WHITE_KEYBOARD_PLAYER) {
                if (IS_AUTO_PLAY) {
                    pause(AUTO_PLAY_TURN_DURATION_IN_MILLISECONDS);
                } else {
                    continueOnEnter();
                }
            }

            game.nextTurn();

            clearConsole();
            System.out.println(game.toString());

            if (game.isGameOver()) {
                break;
            }
        }
    }

    public static void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void continueOnEnter() {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Press enter to continue:");
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

위의 코드가 출력하는 결과의 예는 다음과 같습니다.

```
Turn: 0

  abcdefgh
 +--------+
0|RNBKQBNR|
1|PPPPPPPP|
2|        |
3|        |
4|        |
5|        |
6|pppppppp|
7|rnbkqbnr|
 +--------+





Turn: 1 (W)

  abcdefgh
 +--------+
0|RNBKQBNR|
1|PPPPPPPP|
2|        |
3|        |
4|        |
5|p       |
6| ppppppp|
7|rnbkqbnr|
 +--------+

Move: p (a6) -> (a5)




Turn: 2 (B)

  abcdefgh
 +--------+
0|RNBKQBNR|
1|P PPPPPP|
2| P      |
3|        |
4|        |
5|p       |
6| ppppppp|
7|rnbkqbnr|
 +--------+

Move: P (b1) -> (b2)



...



Turn: 9 (W)
Captured: ppB

  abcdefgh
 +--------+
0|RN KQBNR|
1|P PPPPPP|
2| P      |
3|        |
4|p       |
5| p      |
6|  pp pbp|
7|rnbkq nr|
 +--------+

Move: b (f7) -> (g6)
```

### 커맨드 라인

경기를 "애니메이션" 형태로 보고 싶다면, 컴파일 된 코드를 커맨드 라인 콘솔에서 돌려야 합니다.

- IntelliJ IDEA 로 컴파일 했다면, 다음 커맨드를 입력하세요: `java -classpath .\out\production\Assignment3 academy.pocu.comp3500.assignment3.app.Program`
- 다른 방법으로 컴파일 했다면, `-classpath` 값을 컴파일 된 파일들이 있는 위치로 바꿔서 돌리세요.
- 프로젝트 루트에서 돌리세요: `C:\<your>\<repo>\<directory>\Assignment3`

## 6. 커밋, 푸시 그리고 빌드 요청

이건 어떻게 하는지 이제 다 아시죠? :)

## 7. 토너먼트 참가

과제 마감 후 마지막으로 제출된 AI를 모아 토너먼트를 진행합니다.

하지만 중간 점검을 위해 매주 한 번씩 모의 토너먼트를 진행한 뒤, 그 결과를 공유합니다. 첫 모의 토너먼트는 9주 차가 끝난 뒤 실행하며, 해당 주차 마지막 날 자정(GMT 기준)까지 제출된 AI만 토너먼트에 참여합니다.

토너먼트 방식은 피파 월드컵과 매우 비슷합니다. 조별로 플레이어를 배정해 라운드 로빈(round-robin) 방식으로 조별 리그를 진행 한 뒤, 각 조의 상위 플레이어 두 명이 결선 토너먼트에 진출합니다. 결선 토너먼트는 녹아웃(knockout) 방식으로 진행 되며 각 경기의 승자는 다음 라운드에 진출합니다. 결승전에서 이기는 플레이어가 최종 승자입니다.

토너먼트 결과는 과제 점수에 영향을 미치지 않습니다. 하지만 토너먼트에서 좋은 성적을 올리면 업적을 받을 수 있습니다!

### 7.1 최소 요구사항

다음의 최소 요건을 충족한 AI만 토너먼트에 참가할 수 있습니다.

- 반드시 컴파일 되어야 함
- 빌드봇 테스트에서 100점을 받았어야 함

### 7.2 토너먼트 방식

토너먼트는 크게 조별 리그와 결선 토너먼트 단계로 나뉩니다.

#### 7.2.1 조별 리그

- 한 조 마다 4명의 플레이어를 배치합니다. (토너먼트 참가 인원에 따라 3명으로 구성된 조가 있을 수도 있음)
- 각 플레이어는 본인이 속한 조의 모든 플레이어를 상대로 경기를 치릅니다. (보통 각 플레이어마다 3 경기, 3명인 조는 2 경기)
- 각 조에서 가장 많이 승리한 두 플레이어가 결선 토너먼트(혹은 토너먼트 결정전)에 진출합니다. 만약 승 수가 같아 우위를 가리지 못할 경우 다음 순서에 따라 진출자를 결정합니다.
  1. 조별 리그 경기에서 획득한 포획 점수(7.3에서 설명)가 높은 순
  2. 여전히 우위를 가릴 수 없다면 조별 리그 경기에서 사용한 시간이 적은 순
  3. 여전히 우위를 가릴 수 없다면 과제를 먼저 제출한 순

#### 7.2.2 토너먼트 결정전

- 만약 결선 토너먼트에 진출한 플레이어의 수가 2의 거듭제곱(예: 8, 16, 32 등)이 아닌 경우, 과제를 늦게 제출한 플레이어들을 모아 토너먼트 결정전을 진행합니다.
- 각 플레이어는 다른 한 명의 플레이어와 경기를 치르며, 여기서 이긴 플레이어가 결선 토너먼트에 진출합니다.

#### 7.2.3 결선 토너먼트

- 매 라운드(예: 16강전, 8강전)마다 플레이어는 다른 플레이어와 한 번의 경기를 치르며, 그 경기의 승자가 다음 라운드에 진출합니다.
- 참가 인원에 따라 시작 라운드는 달라질 수 있습니다.
- 결선 토너먼트는 4강전을 마친 뒤, 3위 결정전, 결승전까지 치르면 끝입니다.

### 7.3 경기 규칙

경기 규칙은 다음과 같습니다.

- 토너먼트 경기에서 이기는 가장 확실한 방법은 상대방의 킹 피스를 잡는 것입니다.
- 경기는 다음 조건 중 하나를 만족하면 끝이 납니다.
  1. 킹 피스가 잡힘
  2. 한 플레이어가 유효하지 않은 수(예: null, 움직일 수 없는 곳으로 피스를 이동하려 함)를 반환했음. 이 플레이어가 경기의 패자가 됩니다.
  3. 한 플레이어가 주어진 시간 안에 다음 수를 반환하지 않았음. 이 플레이어가 경기의 패자가 됩니다
  4. 지난 50수 동안 잡힌 피스가 없고 어떤 폰 피스도 움직이지 않은 경우(50수 규칙). 일반 체스 게임에서는 이 경우 서로 비긴 것으로 보지만 본 토너먼트에서는 아래의 규칙에 따라 승자를 선택합니다.

50수 규칙에 따라 경기가 끝난 경우 다음의 규칙에 따라 승자를 결정합니다.

1. 각 플레이어가 잡은 피스에 따라 계산한 점수(포획 점수)가 높은 플레이어가 승리

   ```
   (10 * 잡은 폰 수) +
   (30 * 잡은 나이트 수) +
   (30 * 잡은 비숍 수) +
   (50 * 잡은 룩 수) +
   (90 * 잡은 퀸 수)
   ```

2. 두 플레이어의 점수가 같은 경우, 시간을 적게 소모한 선수가 승리 (1/10초 단위)

3. 두 플레이어의 소모 시간도 같은 경우, 블랙을 잡은 플레이어의 승

### 7.4 토너먼트 결과

- 토너먼트 결과는 별도로 공유해드립니다. (모의 토너먼트 결과도 포함)
- 플레이어 이름은 POCU 아카데미에 등록된 여러분의 닉네임과 프로필 URL로 표시됩니다. 이 정보는 로그인 후 `프로필 보기` 페이지에서 변경할 수 있습니다.