# COMP3500 과제 4

I 회사는 최근에 사활이 걸린 프로젝트를 따냈습니다. 이 프로젝트는 매우 크고 복잡한데 자칭 CEO라는 사람이 뭔 일이 있어도 이 프로젝트를 끝내라고 합니다. 안 그러면 다들 잘린다는군요. 이 회사의 수석 엔지니어인 여러분은 이 프로젝트의 계획을 수립하고 스케줄과 관련한 다양한 정보들을 CEO에게 보고해야 합니다.

유용한 팁: 이 과제는 실습 10의 확장입니다. 실습 10을 먼저 끝내면 이 과제가 조금 더 쉬울 수 있습니다. 만약 실습 10 전에 이 과제를 진행한다면 **A. 부록**을 먼저 읽어 주세요.

## 1. 프로젝트를 준비한다

1. `Assignment4` 폴더로 이동합니다.

2. `src/academy/pocu/comp3500/assignment4/project` 폴더 안에 `Task.java` 파일이 있는지 확인하고, 만약 그렇지 않다면 [이 페이지](https://github.com/POCU/COMP3500StarterPack/tree/main/Assignment4/src/academy/pocu/comp3500/assignment4/project)에서 다운로드하세요.

3. ```
   src/academy/pocu/comp3500/assignment4
   ```

    

   폴더에 다음과 같은

    

   ```
   Project.java
   ```

    

   파일을 추가합니다.

   ```java
   package academy.pocu.comp3500.assignment4;
   
   import academy.pocu.comp3500.assignment4.project.Task;
   
   public final class Project {
       public Project(final Task[] tasks) {
   
       }
   
       public int findTotalManMonths(final String task) {
           return -1;
       }
   
       public int findMinDuration(final String task) {
           return -1;
       }
   
       public int findMaxBonusCount(final String task) {
           return -1;
       }
   }
   ```

## 2. 전반적인 규칙

- 새로 추가하는 클래스들은 `academy.pocu.comp3500.assignment4` 패키지 안에 넣어 주세요.

- `academy.pocu.comp3500.assignment4.project` 안에 있는 클래스들을 변경하지 마세요

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

## 3. `Project` 클래스 구현하기

### 3.1 생성자를 구현한다

- 생성자는 다음의 인자를 받습니다.
  - 프로젝트에 있는 일감 목록: `Task[] tasks`

### 3.2 `findTotalManMonths()` 메서드를 구현한다

맨먼스(인월, Man/Month)란 어떤 일감을 한 사람이 끝내는데 들어가는 시간을 달로 표현한 수치입니다. 예를 들어 어떤 일감을 끝내려면 다섯 명이 인력이 한 달 동안 투입되어야 한다면 이 일감의 맨먼스는 5입니다.

해당 마일스톤이 끝날 때까지 완료해야 하는 모든 일감들의 맨먼스가 몇인지 찾으세요. **이때 유지보수 사이클은 최종 값 계산에 포함하지 않습니다.**

- 이 메서드는 다음의 인자를 받습니다:
  - 해당 마일스톤의 마지막 일감: `String task`
- 해당 마일스톤까지 완료해야 하는 모든 일감들의 맨먼스를 반환합니다.
- 해당 마일스톤에 끝나야 하는 일감은 모두 직간접적으로 `task`에 연결되어 있습니다.

```java
Task[] tasks = createTasks();
/*
Title    Estimate    Predecessors
A        3
B        1           A
C        2           
D        10          B, C
*/

int manMonths = project.findTotalManMonths("D"); // manMonths: 16
```

### 3.3 `findMinDuration()` 메서드를 구현한다

맨먼스는 일감을 배분하거나 퇴사자가 프로젝트 스케줄에 미치는 영향 등을 예측하는데 유용합니다. 하지만 이로부터 프로젝트 또는 마일스톤이 실제로 언제 끝나는지(예: 12월 24일)는 알긴 어렵죠.

각 일감에는 한 명의 직원만 투입할 수 있다는 가정하에 해당 마일스톤까지 완료해야 하는 모든 일감들을 끝내려면 몇 달이 걸리는지 찾으세요. **이때 유지보수 사이클은 최종 값 계산에 포함하지 않습니다.**

- 이 메서드는 다음의 인자를 받습니다.
  - 해당 마일스톤의 마지막 일감: `String task`
- 해당 마일스톤까지 완료해야 하는 모든 일감들을 끝내려면 필요한 최소 달 수를 반환합니다.
- 해당 마일스톤에 끝나야 하는 일감은 모두 직간접적으로 `task`에 연결되어 있습니다.

```java
Task[] tasks = createTasks();
/*
Title    Estimate    Predecessors
A        3
B        1           A
C        2           
D        10          B, C
*/

int minDuration = project.findMinDuration("D"); // minDuration: 14
```

### 3.4 `findMaxBonusCount()` 메서드를 구현한다

갑자기 CEO가 각 일감을 정확히 한 달만에 끝내고 싶다고 합니다. 그 대신 한 일감에 직원을 몇 명을 투입해도 상관없다고 하네요. 그리고 처음부터 끝까지 프로젝트에 참가하는 직원에게는 특별 보너스를 준다고 합니다. 이 보너스를 받을 수 있는 최대 직원 수가 몇 명인지 찾으세요.

- 이 메서드는 다음의 인자를 받습니다.
  - 프로젝트의 마지막 일감: `String task`
- 보너스를 받을 수 있는 최대 직원 수를 반환합니다.
- 해당 프로젝트에 끝나야 하는 일감은 모두 직간접적으로 `task`에 연결되어 있습니다.

```java
Task[] tasks = createTasks();
/*
Title    Estimate    Predecessors
A        3
B        1           A
C        2           
D        10          B, C
*/

int bonusCount = project.findMaxBonusCount("D"); // bonusCount: 3
```

## 4. 본인 컴퓨터에서 테스트하는 법

- `Program.java`를 아래처럼 바꾼 뒤 실행하세요.

```java
package academy.pocu.comp3500.assignment4.app;

import academy.pocu.comp3500.assignment4.Project;
import academy.pocu.comp3500.assignment4.project.Task;

public class Program {

    public static void main(String[] args) {
        Task[] tasks = createTasks();

        Project project = new Project(tasks);

        int manMonths1 = project.findTotalManMonths("ms1");
        assert (manMonths1 == 17);

        int manMonths2 = project.findTotalManMonths("ms2");
        assert (manMonths2 == 46);

        int minDuration1 = project.findMinDuration("ms1");
        assert (minDuration1 == 14);

        int minDuration2 = project.findMinDuration("ms2");
        assert (minDuration2 == 32);

        int bonusCount1 = project.findMaxBonusCount("ms1");
        assert (bonusCount1 == 6);

        int bonusCount2 = project.findMaxBonusCount("ms2");
        assert (bonusCount2 == 6);
    }

    private static Task[] createTasks() {
        Task a = new Task("A", 3);
        Task b = new Task("B", 5);
        Task c = new Task("C", 3);
        Task d = new Task("D", 2);
        Task e = new Task("E", 1);
        Task f = new Task("F", 2);
        Task g = new Task("G", 6);
        Task h = new Task("H", 8);
        Task i = new Task("I", 2);
        Task j = new Task("J", 4);
        Task k = new Task("K", 2);
        Task l = new Task("L", 8);
        Task m = new Task("M", 7);
        Task n = new Task("N", 1);
        Task o = new Task("O", 1);
        Task p = new Task("P", 6);
        Task ms1 = new Task("ms1", 6);
        Task ms2 = new Task("ms2", 8);

        c.addPredecessor(b);

        ms1.addPredecessor(a, c);

        e.addPredecessor(c);
        f.addPredecessor(g);
        g.addPredecessor(e);

        i.addPredecessor(h);
        j.addPredecessor(ms1);

        k.addPredecessor(j);
        n.addPredecessor(k);
        m.addPredecessor(n);
        l.addPredecessor(m);

        p.addPredecessor(i, j);
        o.addPredecessor(j);

        ms2.addPredecessor(o, p);

        Task[] tasks = new Task[]{
                a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, ms1, ms2
        };

        return tasks;
    }
}
```

## 5. 커밋, 푸시 그리고 빌드 요청

이건 어떻게 하는지 이제 다 아시죠? :)

## A. 부록

프로젝트는 일감들의 집합이며, 어떤 일감은 곧바로 시작할 수 있지만 다른 일감을 끝낸 후에야 시작할 수 있는 일감도 있습니다. 이때 그 다른 일감을 선수 일감(predecessor)이라고 합니다. 아래의 간단한 프로젝트의 예를 봐주세요.

| #    | Title           | Estimate | Predecessors |
| ---- | --------------- | -------- | ------------ |
| 1    | Design UX       | 1        |              |
| 2    | Review UX       | 2        | 1            |
| 3    | Market Research | 1        |              |
| 4    | Formalize Spec  | 4        | 2, 3         |
| 5    | Implement       | 5        | 4            |
| 6    | Write Test Plan | 2        | 4            |
| 7    | Test            | 2        | 5, 6         |
| 8    | Release         | 1        | 7            |

- `Title`: 일감의 이름
- `Estimate`: 직원 한 명이 이 일감을 끝내는데 걸리는 시간을 산정한 것입니다. 여기의 수치는 달(month)이고 최댓값은 100입니다.
- `Predecessors`: 이 일감을 시작하기 전에 끝내야 하는 다른 일감들의 목록입니다.

선수 일감 관계를 이용하면 위 표를 아래와 같은 간단한 그래프로 시각화할 수 있습니다.

![프로젝트 예 1](https://github.com/POCU/COMP3500StarterPack/blob/main/Resources/Assignment4/project_graph00.png?raw=true)

노드 `s`는 프로젝트의 시작점을 표현하기 위해 별도로 추가해준 것입니다.

때로는 프로젝트에 '유지보수 사이클'이 있을 수도 있습니다. 유지보수 사이클은 그래프의 순환(cycle) 형태를 띄며, 아래 그래프에서 `k`, `l`, `m`, `n` 노드가 바로 그런 예입니다.

![프로젝트 예 2](https://github.com/POCU/COMP3500StarterPack/blob/main/Resources/Assignment4/project_graph01.png?raw=true)

유지보수 사이클의 예는 다음과 같습니다. 개발팀이 어떤 기능을 완료하고 다음 기능을 구현하기 시작합니다. 완료된 기능의 유지보수는 이제 유지보수 팀이 담당합니다. 개발팀은 계속 다른 기능을 구현하는 동안 말이지요. 다음은 유지보수 사이클에 들어가는 일감의 예입니다.

1. 버그 수집
2. 버그 수정계획 수립
3. 버그를 고침
4. 테스트
5. 새 코드 배포
6. 더 이상 유지보수가 필요 없을 때까지 1~5를 반복

유지보수 사이클은 프로젝트 그래프에서 볼 수 있는 유일한 순환(cycle)이며, 순환에서 탈출하는 변(edge)은 존재하지 않습니다. 일단 어떤 기능을 완성한 다음에는 그 기능이 없어지지 않는 한 계속 유지보수를 해야 하기 때문이죠.