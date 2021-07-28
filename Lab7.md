# COMP3500 실습 7

은하 제국은 자신의 기지였던 '어둠의 별'을 파괴한 반란군에게 보복을 하려 합니다. 반란군의 마지막 한 명까지 찾아내어 완벽히 소탕하란 황제폐하의 명령을 받은 은하 제국 비밀 정보부(SIS)는 반란군 함대의 위치를 추적하기 위해 노력하던 중 반란군의 은닉장소를 드러낼지도 모르는 초공간 무전 통신을 가로챘습니다.

그런데 한 가지 문제가 있군요. 메시지가 암호화되어 있습니다. 반란군은 스카이워크(Skyewalk)라는 암호 기법을 사용하는데 이 기법의 동작법은 다음과 같습니다.

1. 평문에 있는 각 단어를 부호어(code word)로 매핑한다. 부호어 매핑 규칙은 '비밀의 책'에 정의되어 있음.
2. 매핑된 각 부호어마다 그 부호어를 구성하는 문자들의 위치를 섞는다. 위치를 섞는 정확한 규칙은 알려져 있지 않음.

예를 들어 `I am an engineer`라는 평문이 있고, 비밀의 책이 정의한 매핑이 다음과 같다고 해봅시다.

```
I => dark
am => side
an => of
engineer => force
```

그러면 암호문은 다음과 같을 수 있습니다.

```
1. 평문의 각 단어를 부호어로 매핑한 결과
    
   dark side of force

2. 각 부호어마다 문자들의 위치를 섞은 결과

   rkad edis of rocfe

암호문 = rkad edis of rocfe
```

반란군의 무전 통신 메시지를 깨는 임무가 여러분의 팀에 떨어졌습니다. 다행히도 SIS가 반란군의 전초기지에서 '비밀의 책'을 획득했다는군요. 이 책을 이용해서 어떤 암호문에 사용된 부호어를 찾는 게 여러분이 해야 하는 일입니다. 좀 더 자세히 말하면 암호문에 있는 한 단어를 인자로 받아 그에 대응할 수 있는 모든 부호어를 찾아 반환하는 프로그램을 만들면 됩니다. 이 일을 성공적으로 마무리하면 진급과 휴가를 제공한다고 하니 제국에 평화와 자유, 정의와 안전을 가져다 줍시다!

황제폐하 만세!

## 1. 프로젝트를 준비한다

1. `Lab7` 폴더로 이동합니다.

2. `src/academy/pocu/comp3500/lab7` 폴더에 다음 클래스들을 추가합니다.

   ```java
   package academy.pocu.comp3500.lab7;
   
   public class Decryptor {
       public Decryptor(final String[] codeWords) {
       }
   
       public String[] findCandidates(final String word) {
           return new String[]{};
       }
   }    
   ```

## 2. 전반적인 규칙

- 새로 추가하는 클래스들은 `academy.pocu.comp3500.lab7` 패키지에 속해야 합니다.

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

## 3. `Decryptor` 클래스 구현하기

### 3.1 생성자를 구현한다

- 생성자는 다음의 인자를 받습니다.
  - '비밀의 책' 안에 있는 모든 부호어: `String[] codeWords`

```java
String[] codeWords = new String[]{"cat", "CATS", "AcTS", "SCAN", "acre", "aNTS"};
        
Decryptor decryptor = new Decryptor(codeWords);
```

### 3.2 `findCandidates()` 메서드를 구현한다

- 본 메서드는 다음의 인자를 받습니다.
  - 암호문의 한 단어: `String word`
- 모든 후보 부호어를 담고 있는 배열을 반환합니다. 모든 후보 부호어는 소문자로 반환됩니다.
- 후보 부호어는 대소문자 구분하지 않고 찾습니다.

```java
String[] codeWords = new String[]{"cat", "CATS", "AcTS", "SCAN", "acre", "aNTS"};

Decryptor decryptor = new Decryptor(codeWords);

String[] candidates = decryptor.findCandidates("dog");  // []
candidates = decryptor.findCandidates("cat");  // ["cat"]
candidates = decryptor.findCandidates("cats");  // ["cats", "acts"]
candidates = decryptor.findCandidates("cAsT");  // ["cats", "acts"]
```

## 4. 본인 컴퓨터에서 테스트하는 법

- `Program.java`를 아래처럼 바꾼 뒤 실행하세요.

```java
package academy.pocu.comp3500.lab7.app;

import academy.pocu.comp3500.lab7.Decryptor;

public class Program {

    public static void main(String[] args) {
        String[] codeWords = new String[]{"cat", "CATS", "AcTS", "SCAN", "acre", "aNTS"};

        Decryptor decryptor = new Decryptor(codeWords);

        String[] candidates = decryptor.findCandidates("cat");

        assert (candidates.length == 1);
        assert (candidates[0].equals("cat"));

        candidates = decryptor.findCandidates("race");

        assert (candidates.length == 1);
        assert (candidates[0].equals("acre"));

        candidates = decryptor.findCandidates("ca");

        assert (candidates.length == 0);

        candidates = decryptor.findCandidates("span");

        assert (candidates.length == 0);

        candidates = decryptor.findCandidates("ACT");

        assert (candidates.length == 1);
        assert (candidates[0].equals("cat"));

        candidates = decryptor.findCandidates("cats");

        assert (candidates.length == 2);
        assert (candidates[0].equals("cats") || candidates[0].equals("acts"));
        assert (candidates[1].equals("cats") || candidates[1].equals("acts"));

        candidates = decryptor.findCandidates("SCAt");

        assert (candidates.length == 2);
        assert (candidates[0].equals("cats") || candidates[0].equals("acts"));
        assert (candidates[1].equals("cats") || candidates[1].equals("acts"));
    }
}
```

## 5. 커밋, 푸시 그리고 빌드 요청

이건 어떻게 하는지 이제 다 아시죠? :)