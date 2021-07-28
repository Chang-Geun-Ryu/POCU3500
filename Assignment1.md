# COMP3500 과제 1

본 과제는 컴퓨터에서 해야 하는 과제입니다. 코드 작성이 끝났다면 실습 1에서 만들었던 깃 저장소에 커밋 및 푸시를 하고 슬랙을 통해 자동으로 채점을 받으세요.

POCU 농구연맹(PBA)이 배출한 최고의 농구 감독인 여러분은 다음 시즌에 대비해 새로운 팀을 꾸리고 있습니다. 농구에서 팀워크보다 중요한 건 없다고 생각하는 여러분은 최고의 팀워크를 가진 팀을 꾸리고 싶습니다. 그러기 위해 첫 번째 단계로 PBA 데이터베이스에서 선수 통계를 구해왔습니다. 이 통계는 다음과 같은 통계를 각 선수마다 기록하고 있습니다.

- 선수 이름
- 경기 번호 (각 경기를 식별하는 고유한 번호)
- 득점
- 슛 성공 수
- 슛 시도 수
- 어시스트 수
- 패스 수

하지만 이것은 아직 가공되지 않은 데이터여서 일단 처리를 해야 합니다. 그럼 이제 여러분의 드림팀을 만들 준비가 되었나요?

## 1. 프로젝트를 준비한다

1. 실습 1을 반드시 끝내도록 하세요. 그래야만 본인의 컴퓨터에 `Assignment1` 폴더가 올바르게 설정되어 있을 겁니다.

2. `Assignment1` 폴더로 이동합니다.

3. `src/academy/pocu/comp3500/assignment1/pba` 폴더 안에 `GameStat.java`와 `Player.java` 파일이 있는지 확인하세요. 만약 없다면 [이 페이지](https://github.com/POCU/COMP3500StarterPack/tree/main/Assignment1/src/academy/pocu/comp3500/assignment1/pba)에서 다운로드하세요.

4. `src/academy/pocu/comp3500/assignment1` 폴더에 `PocuBasketballAssociation.java` 파일을 만들고 다음의 내용을 추가합니다.

   ```java
   package academy.pocu.comp3500.assignment1;
   
   import academy.pocu.comp3500.assignment1.pba.Player;
   import academy.pocu.comp3500.assignment1.pba.GameStat;
   
   public final class PocuBasketballAssociation {
       private PocuBasketballAssociation() {
       }
   
       public static void processGameStats(final GameStat[] gameStats, final Player[] outPlayers) {
   
       }
   
       public static Player findPlayerPointsPerGame(final Player[] players, int targetPoints) {
           return null;
       }
   
       public static Player findPlayerShootingPercentage(final Player[] players, int targetShootingPercentage) {
           return null;
       }
   
       public static long find3ManDreamTeam(final Player[] players, final Player[] outPlayers, final Player[] scratch) {
           return -1;
       }
   
       public static long findDreamTeam(final Player[] players, int k, final Player[] outPlayers, final Player[] scratch) {
           return -1;
       }
   
       public static int findDreamTeamSize(final Player[] players, final Player[] scratch) {
           return -1;
       }
   }
   ```

## 2. `Assignment1` 클래스의 메서드를 구현한다

### 2.1 전반적인 규칙

- 새로 추가하는 클래스들은 `academy.pocu.comp3500.assignment1` 패키지 안에 넣어 주세요.

- 정적 (`static`) 변수는 사용할 수 없습니다.

- `Collection<E>`이나 `List<E>` 인터페이스를 구현하는 클래스들은 사용할 수 없습니다. 이런 클래스의 몇 예는 다음과 같습니다.

  - `LinkedList`
  - `Stack`
  - `Queue`
  - `ArrayList`
  - `Hashmap`
  - `HashSet`
  - 등

  그냥 기본 데이터형 그리고 `String` 외에 Java에서 자체적으로 제공하는 자료구조를 사용하지 않으면 됩니다.

- `java.util.Arrays`, `java.util.stream.Stream` 클래스를 사용할 수 없습니다. 사용하면 0점이 뜨니 시도조차 하지 마세요. ;)

- 이 과제에서는 어떤 개체의 인스턴스도 생성할 수 없습니다. 즉, `new` 키워드를 사용할 수 없습니다. 사용하면 0점이 뜨니 시도조차 하지 마세요. ;)

- `java.lang.Object.clone()` 메서드를 사용할 수 없습니다. 사용하면 0점이 뜨니 시도조차 하지 마세요. ;)

- `GameStat.java`과 `Player.java` 파일의 내용을 변경하지 마세요. 이 파일의 내용을 변경해도 빌드봇은 변경된 코드를 무시하고 채점을 진행합니다.

- 이 과제에서 100점을 받으려면 메서드들을 최적으로 구현해야 합니다. 복잡도 테스트에서 감점을 당했다면 알고리듬의 시간 복잡도를 줄일 방법을 고민해보세요.

### 2.2 `processGameStats()` 메서드를 구현한다

각 선수마다 다음의 정보가 필요합니다.

- 선수의 이름

- 경기당 평균 득점수

- 경기당 평균 어시스트수

- 경기당 평균 패스수

- 슛 성공률. 슛 성공률을 계산하는 법은 다음과 같습니다.

  ```
  슛 성공률 = 100 * [총 슛 성공수] / [총 슛 시도수]
  ```

  계산 후에 소수점 이하는 버리세요.

위 정보를 얻기 위해 `processGameStats()` 메서드를 구현하세요.

- 이 메서드는 다음과 같은 2개의 인자를 받습니다.
  - 경기 기록 배열: `GameStat[] gameStats`
  - 선수 배열. 본 메서드의 결과가 여기에 저장됨: `Player[] outPlayers`
- `outPlayers` 안에 들어있는 `Player` 개체의 정보를 올바르게 업데이트하세요. 이 메서드의 반환형은 `void`여야 합니다.
- 한 경기 기록에서 동일한 선수의 데이터가 여러 번 등장하는 경우는 없다고 가정해도 좋습니다.
- `outPlayers`의 길이는 고유한 선수의 수와 같다고 가정해도 좋습니다.
- `outPlayers`의 요소는 모두 `null`이 아니라고 가정해도 좋습니다.
- `outPlayers`의 순서는 어째도 상관없습니다.

```java
GameStat[] gameStats = new GameStat[] {
        new GameStat("Player 1", 1, 13, 5, 6, 10, 1),
        new GameStat("Player 2", 2, 5, 2, 5, 0, 10),
        new GameStat("Player 1", 3, 12, 6, 9, 8, 5),
        new GameStat("Player 3", 1, 31, 15, 40, 5, 3),
        new GameStat("Player 2", 1, 3, 1, 3, 12, 2),
        new GameStat("Player 1", 2, 11, 6, 11, 9, 3),
        new GameStat("Player 2", 3, 9, 3, 3, 1, 11),
        new GameStat("Player 3", 4, 32, 15, 51, 4, 2),
        new GameStat("Player 4", 3, 44, 24, 50, 1, 1),
        new GameStat("Player 1", 4, 11, 5, 14, 8, 3),
        new GameStat("Player 2", 4, 5, 1, 3, 1, 9),
};

Player[] players = new Player[] {
        new Player(),
        new Player(),
        new Player(),
        new Player()
};

PocuBasketballAssociation.processGameStats(gameStats, players);
/*
players: [
    { "Player 2", pointsPerGame: 5, assistsPerGame: 3, passesPerGame: 8, shootingPercentage: 50 },
    { "Player 1", pointsPerGame: 11, assistsPerGame: 8, passesPerGame: 3, shootingPercentage: 55 },
    { "Player 4", pointsPerGame: 44, assistsPerGame: 1, passesPerGame: 1, shootingPercentage: 48 },
    { "Player 3", pointsPerGame: 31, assistsPerGame: 4, passesPerGame: 2, shootingPercentage: 32 }
]
*/
```

### 2.3 `findPlayerPointsPerGame()` 메서드를 구현한다

`findPlayerPointsPerGame()` 메서드는 경기당 득점수가 인자로 전달된 값과 가장 가까운 선수를 반환합니다.

- 이 메서드는 다음과 같은 2개의 인자를 받습니다.
  - 선수 배열. 경기당 득점수의 오름차순으로 정렬되어 있음: `Player[] players`
  - 목표 득점수: `int targetPoints`
- 경기당 득점수가 `targetPoints`에 가장 가까운 선수를 반환합니다.
- 위 조건을 만족하는 선수가 여러 명이라면 경기당 득점수가 높은 선수를 반환합니다.

```java
Player[] players = new Player[] {
        new Player("Player 1", 1, 5, 1, 60),
        new Player("Player 2", 5, 2, 11, 31),
        new Player("Player 3", 7, 4, 7, 44),
        new Player("Player 4", 10, 10, 15, 25),
        new Player("Player 5", 11, 12, 6, 77),
        new Player("Player 6", 15, 0, 12, 61),
        new Player("Player 7", 16, 8, 2, 70)
};

Player player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 12); // player: Player 5

player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 5); // player: Player 2
player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 13); // player: Player 6
```

### 2.4 `findPlayerShootingPercentage()` 메서드를 구현한다

`findPlayerShootingPercentage()` 메서드는 슛 성공률이 인자로 전달된 값과 가장 가까운 선수를 반환합니다.

- 이 메서드는 다음과 같은 2개의 인자를 받습니다.
  - 선수 배열. 슛 성공률의 오름차순으로 정렬되어 있음: `Player[] players`
  - 목표 슛 성공률: `int targetShootingPercentage`
- 슛 성공률이 `targetShootingPercentage`에 가장 가까운 선수를 반환합니다.
- 위 조건을 만족하는 선수가 여러 명이라면 슛 성공률이 높은 선수를 반환합니다.

```java
Player[] players = new Player[] {
        new Player("Player 4", 10, 10, 15, 25),
        new Player("Player 2", 5, 2, 11, 31),
        new Player("Player 3", 7, 4, 7, 44),
        new Player("Player 1", 1, 5, 1, 60),
        new Player("Player 6", 15, 0, 12, 61),
        new Player("Player 7", 16, 8, 2, 70),
        new Player("Player 5", 11, 12, 6, 77)
};

Player player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 28); // player: Player 2

player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 58); // player: Player 1
player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 72); // player: Player 7
```

### 2.5 `find3ManDreamTeam()` 메서드를 구현한다

선수 3명으로 구성된 팀을 만들고 싶다고 해봅시다. n명의 선수 중 팀워크 점수가 최고인 선수 3명을 뽑는 `find3ManDreamTeam()` 메서드를 구현하세요

어떤 팀의 팀워크 점수를 계산하는 법은 다음과 같습니다.

```
팀워크 = [팀에 속한 모든 선수의 경기당 패스수를 합한 결과] * [팀에 속한 각 선수의 경기당 어시스트수 중 최솟값]
```

- 이 메서드는 다음과 같은 2개의 인자를 받습니다.
  - 선발 가능한 선수 배열: `Player[] players`
  - 결과 배열: `Player[] outPlayers`
  - 필요에 따라 임시 저장소로 사용할 수 있는 배열. 써도 되고 안 써도 되고: `Player[] scratch`
- 3명으로 구성된 팀의 최대 팀워크 점수를 반환합니다.
- `outPlayers`를 선택된 플레이어로 업데이트합니다.
- `players`의 길이는 언제나 3보다 같거나 크다고 가정해도 좋습니다.
- `outPlayers`와 `scratch`의 길이는 언제나 3이라고 가정해도 좋습니다.

```java
Player[] players = new Player[] {
        new Player("Player 2", 5, 12, 14, 50),
        new Player("Player 6", 15, 2, 5, 40),
        new Player("Player 5", 11, 1, 11, 54),
        new Player("Player 4", 10, 3, 51, 88),
        new Player("Player 7", 16, 8, 5, 77),
        new Player("Player 1", 1, 15, 2, 22),
        new Player("Player 3", 7, 5, 8, 66)
};

Player[] outPlayers = new Player[3];
Player[] scratch = new Player[3];

long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch); // maxTeamwork: 219, outPlayers: [ Player 4, Player 2, Player 3 ]
```

### 2.6 `findDreamTeam()` 메서드를 구현한다

이번에는 3명 대신 k명으로 구성된 팀을 만들고 싶습니다. n명의 선수 중 팀워크 점수가 최고인 `k`명을 뽑는 `findDreamTeam()` 메서드를 구현하세요.

- 이 메서드는 다음과 같은 3개의 인자를 받습니다.
  - 선수 배열: `Player[] players`
  - 팀 크기: `int k`
  - 결과 배열: `Player[] outPlayers`
  - 필요에 따라 임시 저장소로 사용할 수 있는 배열. 써도 되고 안 써도 되고: `Player[] scratch`
- 팀의 최대 팀워크 점수를 반환합니다.
- `outPlayers`를 선택된 플레이어로 업데이트합니다.
- `players`의 길이는 언제나 `k`보다 같거나 크다고 가정해도 좋습니다.
- `outPlayers`와 `scratch` 길이는 언제나 `k`라고 가정해도 좋습니다.

```java
Player[] players = new Player[] {
        new Player("Player 2", 5, 5, 17, 50),
        new Player("Player 6", 15, 4, 10, 40),
        new Player("Player 5", 11, 3, 25, 54),
        new Player("Player 4", 10, 9, 1, 88),
        new Player("Player 7", 16, 7, 5, 77),
        new Player("Player 1", 1, 2, 8, 22),
        new Player("Player 9", 42, 15, 4, 56),
        new Player("Player 8", 33, 11, 3, 72),
};

int k = 4;
Player[] outPlayers = new Player[4];
Player[] scratch = new Player[k];

long maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, k, outPlayers, scratch); // maxTeamwork: 171, outPlayers: [ Player 6, Player 5, Player 2, Player 7 ]
```

### 2.7 `findDreamTeamSize()` 메서드를 구현한다

이제는 미리 생각해둔 팀 크기가 없는 상황에서 팀워크 점수가 최고인 팀을 찾고 싶다고 해봅시다. 최고의 팀워크 점수를 성취하려면 필요한 선수 수를 찾는 `findDreamTeamSize()` 메서드를 구현하세요.

- 이 메서드는 다음의 인자를 받습니다.
  - 선수 배열: `Player[] players`
  - 필요에 따라 임시 저장소로 사용할 수 있는 배열. 써도 되고 안 써도 되고: `Player[] scratch`
- 최고의 팀워크 점수를 성취하려면 필요한 선수 수를 반환합니다.
- `scratch` 길이는 언제나 `players`의 길이와 같다고 가정해도 좋습니다.

```java
Player[] players = new Player[] {
        new Player("Player 1", 2, 5, 10, 78),
        new Player("Player 2", 10, 4, 5, 66),
        new Player("Player 3", 3, 3, 2, 22),
        new Player("Player 4", 1, 9, 8, 12),
        new Player("Player 5", 11, 1, 12, 26),
        new Player("Player 6", 7, 2, 10, 15),
        new Player("Player 7", 8, 15, 3, 11),
        new Player("Player 8", 5, 7, 13, 5),
        new Player("Player 9", 8, 2, 7, 67),
        new Player("Player 10", 1, 11, 0, 29),
        new Player("Player 11", 2, 6, 9, 88)
};

Player[] scratch = new Player[players.length];

int k = PocuBasketballAssociation.findDreamTeamSize(players, scratch); // k: 6
```

## 3. 본인 컴퓨터에서 테스트하는 법

- `Program.java`를 아래처럼 바꾼 뒤 실행하세요.

```java
package academy.pocu.comp3500.assignment1.app;

import academy.pocu.comp3500.assignment1.PocuBasketballAssociation;
import academy.pocu.comp3500.assignment1.pba.Player;
import academy.pocu.comp3500.assignment1.pba.GameStat;

public class Program {

    public static void main(String[] args) {
        {
            GameStat[] gameStats = new GameStat[] {
                    new GameStat("Player 1", 1, 13, 5, 6, 10, 1),
                    new GameStat("Player 2", 2, 5, 2, 5, 0, 10),
                    new GameStat("Player 1", 3, 12, 6, 9, 8, 5),
                    new GameStat("Player 3", 1, 31, 15, 40, 5, 3),
                    new GameStat("Player 2", 1, 3, 1, 3, 12, 2),
                    new GameStat("Player 1", 2, 11, 6, 11, 9, 3),
                    new GameStat("Player 2", 3, 9, 3, 3, 1, 11),
                    new GameStat("Player 3", 4, 32, 15, 51, 4, 2),
                    new GameStat("Player 4", 3, 44, 24, 50, 1, 1),
                    new GameStat("Player 1", 4, 11, 5, 14, 8, 3),
                    new GameStat("Player 2", 4, 5, 1, 3, 1, 9),
            };

            Player[] players = new Player[] {
                    new Player(),
                    new Player(),
                    new Player(),
                    new Player()
            };

            PocuBasketballAssociation.processGameStats(gameStats, players);

            Player player = getPlayerOrNull(players, "Player 1");
            assert (player != null);
            assert (player.getPointsPerGame() == 11);
            assert (player.getAssistsPerGame() == 8);
            assert (player.getPassesPerGame() == 3);
            assert (player.getShootingPercentage() == 55);

            player = getPlayerOrNull(players, "Player 2");
            assert (player != null);
            assert (player.getPointsPerGame() == 5);
            assert (player.getAssistsPerGame() == 3);
            assert (player.getPassesPerGame() == 8);
            assert (player.getShootingPercentage() == 50);

            player = getPlayerOrNull(players, "Player 3");
            assert (player != null);
            assert (player.getPointsPerGame() == 31);
            assert (player.getAssistsPerGame() == 4);
            assert (player.getPassesPerGame() == 2);
            assert (player.getShootingPercentage() == 32);

            player = getPlayerOrNull(players, "Player 4");
            assert (player != null);
            assert (player.getPointsPerGame() == 44);
            assert (player.getAssistsPerGame() == 1);
            assert (player.getPassesPerGame() == 1);
            assert (player.getShootingPercentage() == 48);
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 1", 1, 5, 1, 60),
                    new Player("Player 2", 5, 2, 11, 31),
                    new Player("Player 3", 7, 4, 7, 44),
                    new Player("Player 4", 10, 10, 15, 25),
                    new Player("Player 5", 11, 12, 6, 77),
                    new Player("Player 6", 15, 0, 12, 61),
                    new Player("Player 7", 16, 8, 2, 70)
            };

            Player player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 12);
            assert (player.getName().equals("Player 5"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 5);
            assert (player.getName().equals("Player 2"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 13);
            assert (player.getName().equals("Player 6"));
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 4", 10, 10, 15, 25),
                    new Player("Player 2", 5, 2, 11, 31),
                    new Player("Player 3", 7, 4, 7, 44),
                    new Player("Player 1", 1, 5, 1, 60),
                    new Player("Player 6", 15, 0, 12, 61),
                    new Player("Player 7", 16, 8, 2, 70),
                    new Player("Player 5", 11, 12, 6, 77)
            };

            Player player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 28);
            assert (player.getName().equals("Player 2"));

            player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 58);
            assert (player.getName().equals("Player 1"));

            player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 72);
            assert (player.getName().equals("Player 7"));
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 2", 5, 12, 14, 50),
                    new Player("Player 6", 15, 2, 5, 40),
                    new Player("Player 5", 11, 1, 11, 54),
                    new Player("Player 4", 10, 3, 51, 88),
                    new Player("Player 7", 16, 8, 5, 77),
                    new Player("Player 1", 1, 15, 2, 22),
                    new Player("Player 3", 7, 5, 8, 66)
            };

            Player[] outPlayers = new Player[3];
            Player[] scratch = new Player[3];

            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);

            assert (maxTeamwork == 219);

            Player player = getPlayerOrNull(players, "Player 4");
            assert (player != null);

            player = getPlayerOrNull(players, "Player 2");
            assert (player != null);

            player = getPlayerOrNull(players, "Player 3");
            assert (player != null);
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 2", 5, 5, 17, 50),
                    new Player("Player 6", 15, 4, 10, 40),
                    new Player("Player 5", 11, 3, 25, 54),
                    new Player("Player 4", 10, 9, 1, 88),
                    new Player("Player 7", 16, 7, 5, 77),
                    new Player("Player 1", 1, 2, 8, 22),
                    new Player("Player 9", 42, 15, 4, 56),
                    new Player("Player 8", 33, 11, 3, 72),
            };

            final int TEAM_SIZE = 4;

            Player[] outPlayers = new Player[TEAM_SIZE];
            Player[] scratch = new Player[TEAM_SIZE];

            long maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, TEAM_SIZE, outPlayers, scratch);

            assert (maxTeamwork == 171);

            Player player = getPlayerOrNull(players, "Player 5");
            assert (player != null);

            player = getPlayerOrNull(players, "Player 6");
            assert (player != null);

            player = getPlayerOrNull(players, "Player 2");
            assert (player != null);

            player = getPlayerOrNull(players, "Player 7");
            assert (player != null);
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 1", 2, 5, 10, 78),
                    new Player("Player 2", 10, 4, 5, 66),
                    new Player("Player 3", 3, 3, 2, 22),
                    new Player("Player 4", 1, 9, 8, 12),
                    new Player("Player 5", 11, 1, 12, 26),
                    new Player("Player 6", 7, 2, 10, 15),
                    new Player("Player 7", 8, 15, 3, 11),
                    new Player("Player 8", 5, 7, 13, 5),
                    new Player("Player 9", 8, 2, 7, 67),
                    new Player("Player 10", 1, 11, 0, 29),
                    new Player("Player 11", 2, 6, 9, 88)
            };

            Player[] tempPlayers = new Player[players.length];

            int k = PocuBasketballAssociation.findDreamTeamSize(players, tempPlayers);

            assert (k == 6);
        }
    }

    private static Player getPlayerOrNull(final Player[] players, final String id) {
        for (Player player : players) {
            if (player.getName().equals(id)) {
                return player;
            }
        }

        return null;
    }
}
```

## 4. 커밋, 푸시 그리고 빌드 요청

이건 어떻게 하는지 이제 다 아시죠? :)