# COMP3500 실습 6

짜잔~ 제가 새로운 프로그래머 리그를 만들었어요. 이름하여 League of POCU. 토너먼트로 진행되는 이 리그에서는 각 경기마다 두 명의 프로그래머 선수가 주어진 시간 안에 코딩 문제를 풀면서 경쟁합니다. 그리고 주어진 시간이 경과하면 다음에 기초해서 각 선수의 점수를 계산합니다.

- 코드의 시간 복잡도
- 코드의 공간 복잡도
- 선수가 문제를 푸는 데 사용한 시간
- 코드의 줄 수
- 선수가 얼마나 선남선녀인지(응?)
- 등

헤헷, 마지막은 농담이고요. 어쨌든 어떤 방식인지 아시겠죠? ^^ 어쨌든 둘 중 점수가 높은 선수가 해당 경기에서 승리하는 겁니다. 그리고 승리한 경기 수에 따라 각 선수의 등급을 매기지요. 당연히 승 수가 많을수록 등급은 올라갑니다. 선수들은 언제라도 리그에 참가하거나 탈퇴할 수 있습니다.

이제 선수들이 리그에서 자신의 위치를 쉽게 확인할 수 있는 순위표를 만들려고 하는데요. 저에게 모든 선수 데이터는 있지만 그 순위표를 관리해주는 프로그램이 없네요. 이 프로그램이 다음 경기를 치를 선수들을 뽑아줄 수도 있다면 정말 좋겠어요!

## 1. 프로젝트를 준비한다

1. `Lab6` 폴더로 이동합니다.

2. `src/academy/pocu/comp3500/lab6/leagueofpocu` 폴더 안에 `Player.java` 파일이 있는지 확인하세요. 만약 없다면 [이 페이지](https://github.com/POCU/COMP3500StarterPack/tree/main/Lab6/src/academy/pocu/comp3500/lab6/leagueofpocu)에서 다운로드하세요.

3. `src/academy/pocu/comp3500/lab6` 폴더에 다음 클래스들을 추가합니다.

   ```java
   package academy.pocu.comp3500.lab6;
   
   import academy.pocu.comp3500.lab6.leagueofpocu.Player;
   
   public class League {
       public Player findMatchOrNull(final Player player) {
           return null;
       }
   
       public Player[] getTop(final int count) {
           return null;
       }
   
       public Player[] getBottom(final int count) {
           return null;
       }
   
       public boolean join(final Player player) {
           return false;
       }
   
       public boolean leave(final Player player) {
           return false;
       }
   }
   ```

## 2. `League` 클래스 구현하기

### 전반적인 규칙

- 새로 추가하는 클래스들은 `academy.pocu.comp3500.lab6` 패키지에 속해야 합니다.
- `Collection<E>`나 `List<E>` 인터페이스를 구현하는 클래스 중 사용할 수 있는 클래스는 다음뿐입니다.
  - `LinkedList`
  - `ArrayList`
  - `Stack`
  - `PriorityQueue`
  - `Hashmap`
- `java.util.Arrays`, `java.util.Collections`, `java.util.stream.Stream` 패키지는 사용할 수 없습니다. 사용하면 0점이 뜨니 시도조차 하지 마세요. :)
- `System.arraycopy()` 메서드를 사용할 수 없습니다.
- `Player.java` 파일의 내용을 변경하지 마세요. 이 파일의 내용을 변경해도 빌드봇은 변경된 코드를 무시하고 채점을 진행합니다.

### 2.1 생성자를 구현한다

- 매개변수를 받지 않는 생성자를 만드세요.
- 다음의 인자를 받는 또 다른 생성자도 만드세요.
  - 선수(플레이어) 목록: `Player[] players`
  - `players`가 등급에 따라 정렬되어 있는지를 나타내는 불리언 값: `boolean isSorted`

```java
Player player1 = new Player(1, "player1", 9);
Player player2 = new Player(2, "player2", 10);
Player player3 = new Player(3, "player3", 14);
Player player4 = new Player(4, "player4", 14);

League league1 = new League(new Player[]{player1, player2, player3, player4}, true);
League league2 = new League(new Player[]{player4, player1, player3, player2}, false);
```

### 2.2 `findMatchOrNull()` 메서드를 구현한다

선수 등급은 비슷한 등급을 가진 다른 선수와의 경기에서 이기면 올릴 수 있습니다. 여러분이 다른 선수와 경기를 원한다면 리그 관리자가 다음의 조건을 만족하는 선수를 찾아 줄 것입니다.

1. 여러분과 등급이 같은 선수
2. 그런 선수가 없다면 여러분과 가장 등급이 가까운 선수
3. 그런 선수가 여럿이 있을 경우, 여러분보다 높은 등급을 가진 선수

경기 요청은 언제라도 할 수 있습니다.

- 이 메서드는 다음의 인자를 받습니다.
  - 선수: `Player player`
- 매개변수로 지정된 `player`와 경기를 치를 상대 선수를 반환합니다.
- 만약 위 조건을 만족하는 선수가 여럿 있다면, 그중 아무나 반환합니다.
- 만약 경기를 성사시킬 선수가 없다면 `null`을 반환합니다.

```java
Player player1 = new Player(1, "player1", 9);
Player player2 = new Player(2, "player2", 10);
Player player3 = new Player(3, "player3", 14);
Player player4 = new Player(4, "player4", 14);

League league = new League(new Player[]{player1, player2, player3, player4}, true);

Player player3Match = league.findMatchOrNull(player3); // player4
Player player2Match = league.findMatchOrNull(player2); // player1
```

### 2.3 `getTop()` 메서드를 구현한다

- 이 메서드는 다음의 인자를 받습니다.
  - 선수 수: `int count`
- 리그에서 상위 `count` 명의 선수 목록을 반환합니다. 그 목록은 등급의 내림차순으로 정렬되어 있어야 합니다.

```java
Player player1 = new Player(1, "player1", 12);
Player player2 = new Player(2, "player2", 17);
Player player3 = new Player(3, "player3", 12);
Player player4 = new Player(4, "player4", 18);
Player player5 = new Player(5, "player5", 10);

League league = new League(new Player[]{player1, player2, player3, player4, player5}, false);

Player[] topPlayers = league.getTop(3); // player4, player2, player1 or player4, player2, player3
```

### 2.4 `getBottom()` 메서드를 구현한다

- 이 메서드는 다음의 인자를 받습니다.
  - 선수 수: `int count`
- 리그에서 하위 `count` 명의 선수 목록을 반환합니다. 그 목록은 등급의 오름차순으로 정렬되어 있어야 합니다.

```java
Player player1 = new Player(1, "player1", 12);
Player player2 = new Player(2, "player2", 17);
Player player3 = new Player(3, "player3", 12);
Player player4 = new Player(4, "player4", 18);
Player player5 = new Player(5, "player5", 10);

League league = new League(new Player[]{player1, player2, player3, player4, player5}, false);

Player[] bottomPlayers = league.getBottom(3); // player5, player1, player3 or player5, player3, player1
```

### 2.5 `join()` 메서드를 구현한다

- 이 메서드는 다음의 인자를 받습니다.
  - 리그에 참여할 새로운 선수: `Player player`
- 이미 리그에 참여 중인 선수가 아니라면 그 선수를 리그에 추가합니다.
- 선수를 성공적으로 추가했다면 `true`를 반환합니다.
- ID가 동일한데 등급이 다른 선수가 `join()`의 인자로 들어오는 일은 없다고 가정해도 좋습니다.

```java
Player player1 = new Player(1, "player1", 12);
Player player2 = new Player(2, "player2", 15);

League league = new League(new Player[]{player1, player2}, true);

Player newPlayer = new Player(3, "player3", 13);

boolean success = league.join(newPlayer); // true
success = league.join(newPlayer); // false
success = league.join(player2); // false
```

### 2.6 `leave()` 메서드를 구현한다

- 이 메서드는 다음의 인자를 받습니다.
  - 리그를 떠날 선수: `Player player`
- 리그에 그 선수가 참여하고 있다면 그 선수를 리그에서 제거합니다.
- 선수를 성공적으로 제거했다면 `true`를 반환합니다.
- ID가 동일한데 등급이 다른 선수가 `leave()`의 인자로 들어오는 일은 없다고 가정해도 좋습니다.

```java
Player player1 = new Player(1, "player1", 12);
Player player2 = new Player(2, "player2", 13);
Player player3 = new Player(3, "player3", 15);

League league = new League(new Player[]{player1, player2, player3}, true);

boolean success = league.leave(player1); // true
success = league.leave(player2); // true
success = league.leave(player1); // false
```

## 3. 본인 컴퓨터에서 테스트하는 법

- `Program.java`를 아래처럼 바꾼 뒤 실행하세요.

```java
package academy.pocu.comp3500.lab6.app;

import academy.pocu.comp3500.lab6.League;
import academy.pocu.comp3500.lab6.leagueofpocu.Player;

public class Program {

    public static void main(String[] args) {
        // Constructors
        League emptyLeague = new League();

        Player[] emptyLeaguePlayers = emptyLeague.getTop(10);

        assert (emptyLeaguePlayers.length == 0);

        Player player1 = new Player(1, "player1", 4);
        Player player2 = new Player(2, "player2", 6);
        Player player3 = new Player(3, "player3", 6);
        Player player4 = new Player(4, "player4", 7);
        Player player5 = new Player(5, "player5", 10);
        Player player6 = new Player(6, "player6", 12);

        League league1 = new League(new Player[]{player1, player2, player3, player4, player5, player6}, true);
        League league2 = new League(new Player[]{player6, player4, player1, player2, player5, player3}, false);

        // findMatchOrNull()
        Player match = league1.findMatchOrNull(player2);
        assert (match.getId() == player3.getId());

        match = league1.findMatchOrNull(player4);
        assert (match.getId() == player2.getId() || match.getId() == player3.getId());

        match = league1.findMatchOrNull(player5);
        assert (match.getId() == player6.getId());

        // getTop(), getBottom()
        Player[] topPlayers = league2.getTop(3);

        assert (topPlayers[0].getId() == player6.getId());
        assert (topPlayers[1].getId() == player5.getId());
        assert (topPlayers[2].getId() == player4.getId());

        Player[] bottomPlayers = league2.getBottom(3);

        assert (bottomPlayers[0].getId() == player1.getId());
        assert ((bottomPlayers[1].getId() == player2.getId() && bottomPlayers[2].getId() == player3.getId())
                || (bottomPlayers[1].getId() == player3.getId() && bottomPlayers[2].getId() == player2.getId()));

        // join()
        boolean joinSuccess = league1.join(new Player(7, "player7", 9));
        assert (joinSuccess);

        joinSuccess = league1.join(new Player(1, "player1", 4));
        assert (!joinSuccess);

        // leave()
        boolean leaveSuccess = league1.leave(new Player(5, "player5", 10));
        assert (leaveSuccess);

        leaveSuccess = league1.leave(new Player(5, "player5", 10));
        assert (!leaveSuccess);
    }
}
```

## 4. 커밋, 푸시 그리고 빌드 요청

이건 어떻게 하는지 이제 다 아시죠? :)