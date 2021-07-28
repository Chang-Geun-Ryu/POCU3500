# COMP3500 실습 9

그리디 전략이 언제나 최적의 해법을 찾지는 못한다고 강의 중에 배웠습니다. 그러면 '대체 왜 이 기법을 사용하지?'라는 생각을 가질 수 있습니다. 하지만 실세계 문제에서 최적의 해법을 찾으려면 너무 어렵거나 엄청난 시간이 걸리는 경우들이 있습니다. 시간과 자원이 충분하지 않다면 적당히 괜찮은 해법에 만족해야 할 때도 있지요. 그리고 이러는 것이 최적의 해법을 찾는 것보다 가성비에 좋은 경우도 있답니다. 다음과 같은 상황을 고려해보세요.

1. 실제 해법을 찾는 비용이 10인데 그로부터 얻는 이득이 5 임
2. 근사 해법을 찾는 비용이 5인데 그로부터 얻는 이득이 3 임

이럴 경우 어떤 것을 선택하시겠나요? 가성비를 따지면 당연히 두 번째 옵션이겠죠? 실무에서는 생각보다 이런 선택을 할 일이 꽤 있으며, 따라서 많은 회사들이 그리디 전략을 사용해서 문제를 풀 능력이 있는 프로그래머들을 좋아한답니다.

그래서 이번 실습에서는 그리디 전략으로 풀 수 있는 문제 3개를 준비했습니다!

## 1. 프로젝트를 준비한다

1. `Lab9` 폴더로 이동합니다.

2. `src/academy/pocu/comp3500/lab9/data` 폴더 안에 `Task.java`와 `VideoClip.java` 파일이 있는지 확인하세요. 만약 없다면 [이 페이지](https://github.com/POCU/COMP3500StarterPack/tree/main/Lab9/src/academy/pocu/comp3500/lab9/data)에서 다운로드하세요.

3. `src/academy/pocu/comp3500/lab9` 폴더에 다음 클래스들을 추가합니다.

   ```java
   package academy.pocu.comp3500.lab9;
   
   public class PyramidBuilder {
       public static int findMaxHeight(final int[] widths, int statue) {
           return 0;
       }
   }
   ```

   ```java
   package academy.pocu.comp3500.lab9;
   
   import academy.pocu.comp3500.lab9.data.Task;
   
   public class ProfitCalculator {
       public static int findMaxProfit(final Task[] tasks, final int[] skillLevels) {
           return 0;
       }
   }
   ```

   ```java
   package academy.pocu.comp3500.lab9;
   
   import academy.pocu.comp3500.lab9.data.VideoClip;
   
   public class CodingMan {
       public static int findMinClipsCount(final VideoClip[] clips, int time) {
           return -1;
       }
   }
   ```

## 2. 전반적인 규칙

- 새로 추가하는 클래스들은 `academy.pocu.comp3500.lab9` 패키지에 속해야 합니다.

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

- `java.util.Arrays`에서 사용할 수 있는 것은 정렬 메서드뿐입니다.

- `System.arraycopy()` 메서드를 사용할 수 없습니다.

- `Task.java`, `VideoClip.java` 파일의 내용을 변경하지 마세요. 이 파일의 내용을 변경해도 빌드봇은 변경된 코드를 무시하고 채점을 진행합니다.

## 3. `PyramidBuilder` 클래스 구현하기

전지전능한 파라오가 자신을 신격화시키기 위해 대피라미드를 쌓기로 결정했습니다. 국내 최고의 석공들을 불러 피라미드 건축에 들어갈 돌 덩어리를 준비시켰으나, 어디선가 의사소통이 꼬여서 높이는 다 같은데 너비가 제각각인 돌 덩어리들이 나와 버렸습니다. 화가 난 파라오는 국내 최고의 수학자인 당신을 불러 이 문제 있는 돌들로 쌓을 수 있는 피라미드의 최대 높이를 찾으라고 명령했습니다.

다음은 피라미드를 쌓는 규칙입니다.

1. i 번째 레벨에 쌓는 돌 수는 i+1 번째 레벨에 쌓는 돌 수 보다 많아야 합니다.
2. i 번째 레벨에서 가장 작은 돌의 너비는 i+1 번째 레벨에 있는 가장 큰 돌의 너비 이상이어야 합니다.
3. 피라미드의 가장 윗 레벨에는 파라오의 순금 동상을 놓습니다.
   - 이 동상의 너비는 메서드의 인자로 제공됩니다.
   - 위의 규칙 #1은 동상에도 적용됩니다.
   - 규칙 #2는 동상에 적용되지 않지만, 동상 아래 레벨의 너비의 총합은 동상의 너비를 **초과**해야 합니다.

### 3.1 `findMaxHeight()` 메서드를 구현한다

- 이 메서드는 다음의 인자를 받습니다.
  - 피라미드를 쌓는 데 사용하는 돌의 너비들: `int[] widths`
  - 가장 위에 놓는 동상의 너비: `int statue`
- 피라미드의 최고 높이를 반환합니다. 이때, 동상의 높이는 포함하지 않습니다.
- 각 돌의 높이는 1입니다.

```java
int pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{5}, 10); // 0
pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{6, 8}, 10); // 1
pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{3, 3, 4, 4, 30, 12, 10, 10, 6}, 5); // 3
```

## 4. `ProfitCalculator` 클래스 구현하기

그린치는 돈을 더 벌 궁리만 하는 욕심 많은 부자입니다. 그린치는 직원들에게 어떻게 일감을 분배해야 최대 수익을 낼 수 있는지 고민 중입니다. 그의 앞에는 일감 목록이 있는데 각 일감마다 난이도와 그로부터 나오는 수익도 적혀있습니다. 각 직원은 일감 하나만을 완료할 수 있지만 그 직원의 실력 수준이 최소 일감의 난이도만큼은 되어야 그 일을 할 수 있습니다. 참고로 한 일감은 여러 직원에게 할당될 수 있고, 이럴 경우 그 일은 여러 번 완료되게 됩니다.

그린치가 최대 수익을 낼 수 있게 도와주세요.

### 4.1 `findMaxProfit()` 메서드를 구현한다

- 이 메서드는 다음의 인자를 받습니다.
  - 일감 목록: `Task[] task`
  - 직원들의 실력 수준. `skillLevels[i]`은 i 번째 직원의 실력 수준: `int[] skillLevels`
- 그린치가 달성할 수 있는 최고 수익을 반환합니다.

```java
Task[] tasks = new Task[]{
        new Task(20, 30),
        new Task(30, 40),
        new Task(10, 35)
};

int profit = ProfitCalculator.findMaxProfit(tasks, new int[]{10}); // 35
profit = ProfitCalculator.findMaxProfit(tasks, new int[]{20, 25}); // 70
profit = ProfitCalculator.findMaxProfit(tasks, new int[]{40, 15, 5}); // 75
```

## 5. `CodingMan` 클래스 구현하기

여러분은 '코딩 맨'이라는 TV 예능 프로의 PD입니다. 이 프로는 녹화 방송이긴 하지만 처음부터 끝까지 시간을 보여주는 '리얼타임 진행'으로 라이브 같은 긴박함을 강조하는 프로입니다. 물론 그러기 위해서 동시에 여러 카메라로 촬영을 한 뒤, 편집 중에 적절하게 화면을 전환해주죠.

그런데 문제가 생겼습니다. 내일이 방송일인데 비디오 편집자의 컴퓨터가 고장이 나서 처음부터 다시 편집을 해야 합니다. 시간이 모자란 관계로 화면 전환 편집도 최대한 안 하기로 했습니다. 게다가 편집자의 컴퓨터가 고장 날 때 원본 비디오 클립 파일 중의 일부도 유실되었다고 합니다. 자칫하면 전체 방송시간을 채우지 못 할 수도 있겠네요.

각 비디오 클립에는 녹화 시작시간과 종료시간 정보도 같이 들어 있으며, 이걸 원하는 대로 더 작은 비디오 세그먼트로 자를 수 있습니다. 예를 들어 다음과 같은 비디오 클립이 있다고 합시다.

```
시작 시간 = 10
종료 시간 = 20
```

그러면 이걸 다음과 같은 세그먼트로 자를 수 있습니다.

```
1. 시작 시간 = 10
   종료 시간 = 12

2. 시작 시간 = 12 
   종료 시간 = 17

3. 시작 시간 = 17
   종료 시간 = 20
```

이제 방송 시간을 다 채우려면 필요한 최소 비디오 클립 수를 찾아보세요.

### 5.1 `findMinClipsCount()` 메서드를 구현한다

- 이 메서드는 다음의 인자를 받습니다.
  - 원본 비디오 클립 목록: `VideoClip[] clips`
  - 방송 분량: `int time`
- 방송 분량(0부터 `time`까지)을 채우기 위해 필요한 최소 클립 수
- 방송 분량을 채울 수 없다면 -1을 반환합니다.

```java
VideoClip[] clips = new VideoClip[]{
        new VideoClip(0, 15),
        new VideoClip(10, 20),
        new VideoClip(30, 35)
};

int count = CodingMan.findMinClipsCount(clips, 10); // 1
count = CodingMan.findMinClipsCount(clips, 20); // 2
count = CodingMan.findMinClipsCount(clips, 25); // -1
count = CodingMan.findMinClipsCount(clips, 35); // -1
```

## 6. 본인 컴퓨터에서 테스트하는 법

- `Program.java`를 아래처럼 바꾼 뒤 실행하세요.

```java
package academy.pocu.comp3500.lab9.app;

import academy.pocu.comp3500.lab9.CodingMan;
import academy.pocu.comp3500.lab9.ProfitCalculator;
import academy.pocu.comp3500.lab9.PyramidBuilder;
import academy.pocu.comp3500.lab9.data.Task;
import academy.pocu.comp3500.lab9.data.VideoClip;

public class Program {

    public static void main(String[] args) {

        // PyramidBuilder
        int pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{3}, 2);

        assert (pyramidHeight == 0);

        pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{5, 5}, 10);

        assert (pyramidHeight == 0);

        pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{5, 5}, 9);

        assert (pyramidHeight == 1);

        pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{5, 4, 6}, 8);

        assert (pyramidHeight == 1);

        pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{5, 6, 8, 10, 12, 16, 16}, 17);

        assert (pyramidHeight == 2);

        pyramidHeight = PyramidBuilder.findMaxHeight(new int[]{60, 40, 20, 16, 16, 12, 10, 8, 6, 5}, 10);

        assert (pyramidHeight == 3);

        // ProfitCalculator
        Task[] tasks = new Task[]{
                new Task(20, 30),
        };
        int[] skillLevels = new int[]{20};

        int profit = ProfitCalculator.findMaxProfit(tasks, skillLevels);

        assert (profit == 30);

        tasks = new Task[]{
                new Task(20, 30),
        };
        skillLevels = new int[]{10};

        profit = ProfitCalculator.findMaxProfit(tasks, skillLevels);

        assert (profit == 0);

        tasks = new Task[]{
                new Task(20, 50),
                new Task(20, 40)
        };
        skillLevels = new int[]{25};

        profit = ProfitCalculator.findMaxProfit(tasks, skillLevels);

        assert (profit == 50);

        tasks = new Task[]{
                new Task(20, 40),
                new Task(30, 40),
                new Task(50, 25),
                new Task(60, 45)
        };
        skillLevels = new int[]{10, 20, 35, 70, 45};

        profit = ProfitCalculator.findMaxProfit(tasks, skillLevels);

        assert (profit == 165);

        // CodingMan
        VideoClip[] clips = new VideoClip[]{
                new VideoClip(0, 10),
        };
        int airTime = 10;

        int count = CodingMan.findMinClipsCount(clips, airTime);

        assert (count == 1);

        clips = new VideoClip[]{
                new VideoClip(30, 60),
                new VideoClip(0, 20)
        };
        airTime = 60;

        count = CodingMan.findMinClipsCount(clips, airTime);

        assert (count == -1);

        clips = new VideoClip[]{
                new VideoClip(0, 5),
                new VideoClip(0, 20),
                new VideoClip(5, 30),
                new VideoClip(25, 35),
                new VideoClip(35, 70),
                new VideoClip(50, 75)
        };
        airTime = 60;

        count = CodingMan.findMinClipsCount(clips, airTime);

        assert (count == 4);
    }
}
```

## 7. 커밋, 푸시 그리고 빌드 요청

이건 어떻게 하는지 이제 다 아시죠? :)