# COMP3500 실습 3

POCU 항공 우주국(PASA)의 리드 엔지니어인 여러분은 인류 최초 화성 이주를 목표로 원정팀을 이끌고 있습니다. 여러분 팀에서 최근에 개발한 프로토타입 로켓은 3시간 만에 화성에 도착할 수 있다는군요! 하지만 이 프로토타입을 제품화하기로 결정을 내리기 전에 시험 비행을 몇 번해야 할 것 같습니다. 시험 비행 절차는 다음과 같습니다.

1. 로켓을 발사한다.

2. 로켓이 발사되자마자 시험 비행이 끝날 때까지 로켓의 메인 컴퓨터에 매 초마다의 고도를 기록한다.

   - 기록(log)은 정수형 배열이고, 색인이 경과한 시간을 나타냅니다.

     예> [ 5, 8, 10, 12, 5, 0 ]

3. 다음 세 가지 상황을 시험 비행한다.

   - POCU 우주 센터(PSC, 지구에 있음)에서 출발한 로켓이 최고 고도에 도달한 뒤 다시 지면으로 복귀
   - PSC에서 출발한 로켓이 POCU 우주정거장(지구 주위를 공전 중)에 도킹
   - POCU 우주정거장에서 출발한 로켓이 PSC로 복귀

   위 상황 중 어떤 것이라도 여러 번 테스트할 수 있습니다.

시험 비행이 끝난 뒤 그 기록을 가져다 몇 가지 정보를 찾아내 봅시다.

## 1. 프로젝트를 준비한다

1. `Lab3` 폴더로 이동합니다.

2. `src/academy/pocu/comp3500/lab3` 폴더에 다음 클래스들을 추가합니다.

   ```java
   package academy.pocu.comp3500.lab3;
   
   import java.util.ArrayList;
   
   public final class MissionControl {
       private MissionControl() {
       }
   
       public static int findMaxAltitudeTime(final int[] altitudes) {
           return -1;
       }
   
       public static ArrayList<Integer> findAltitudeTimes(final int[] altitudes, final int targetAltitude) {
           return null;
       }
   }
   ```

## 2. `MissionControl`의 메서드 구현하기

### 전반적인 규칙

- 여러분이 작성하는 클래스들은 반드시 `academy.pocu.comp3500.lab3` 패키지 안에 속해야 합니다.
- 정적 (`static`) 변수는 사용할 수 없습니다.
- `java.util.Arrays`, `java.util.stream.Stream` 패키지를 사용할 수 없습니다. 사용하면 0점이 뜨니 시도조차 하지 마세요. ;)
- 상승 중 로켓의 고도는 계속해서 증가합니다.
- 일단 하강하기 시작한 로켓의 고도는 계속해서 감소합니다.

### 2.1 `findMaxAltitudeTime()` 메서드를 구현한다

- 이 메서드는 다음의 인자를 받습니다.
  - 매 초마다 기록한 고도 목록: `int[] altitudes`
- 로켓의 고도가 최고였던 시간을 반환합니다.

```java
final int[] altitudes = new int[] { 1, 2, 3, 4, 5, 6, 7, 4, 3, 2 };

final int maxAltitudeTime = MissionControl.findMaxAltitudeTime(altitudes); // maxAltitudeTime: 6
```

### 2.2 `findAltitudeTimes()` 메서드를 구현한다

- 이 메서드는 다음의 인자를 받습니다.
  - 매 초마다 기록한 고도 목록: `int[] altitudes`
  - 목표 고도: `int targetAltitude`
- 로켓이 목표 고도에 있었을 때의 시간 목록을 반환합니다.
- 목표 고도가 기록에 없는 경우에는 그 목록에 아무것도 포함하지 마세요.

```java
final int[] altitudes = new int[] { 1, 2, 3, 4, 5, 6, 7, 4, 3, 2 };

ArrayList<Integer> bounds = MissionControl.findAltitudeTimes(altitudes, 2); // bounds: [ 1, 9 ]
bounds = MissionControl.findAltitudeTimes(altitudes, 5); // bounds: [ 4 ]
```

## 3. 본인 컴퓨터에서 테스트하는 법

- `Program.java`를 아래처럼 바꾼 뒤 실행하세요.

```java
package academy.pocu.comp3500.lab3.app;

import academy.pocu.comp3500.lab3.MissionControl;

import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        {
            final int[] altitudes = new int[] { 1, 2, 3, 4, 5, 6, 7, 4, 3, 2 };

            final int maxAltitudeTime = MissionControl.findMaxAltitudeTime(altitudes);

            assert (maxAltitudeTime == 6);
        }

        {
            final int[] altitudes = new int[] { 1, 2, 3, 4, 5, 6, 7, 4, 3, 2 };

            ArrayList<Integer> bounds = MissionControl.findAltitudeTimes(altitudes, 2);

            assert (bounds.size() == 2);

            assert (bounds.get(0) == 1);
            assert (bounds.get(1) == 9);

            bounds = MissionControl.findAltitudeTimes(altitudes, 5);

            assert (bounds.size() == 1);
            assert (bounds.get(0) == 4);
        }
    }
}
```

## 4. 커밋, 푸시 그리고 빌드 요청

이건 어떻게 하는지 이제 다 아시죠? :)