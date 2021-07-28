# COMP3500 실습 4

해커 그룹 PoCuHacker(Positively Cute Hacker)의 리더인 여러분은 상위 10대 기업이 소유하고 있는 모든 고객의 비밀번호를 탈취해 세계 정복(?)의 꿈을 이루려고 합니다. 최근에 동료 해커가 위 대기업들의 서버에 침투해 사용자 테이블을 훔쳐왔지만 평문(plaintext)이 아닌 해시된 비밀번호만 저장되어 있네요. 여러분은 이로부터 평문인 비밀번호를 찾을 수 있을까 하여 어둠의 경로를 통해 다음과 같은 레인보우 테이블 5개를 구매했습니다.

1. CRC32
2. MD2
3. MD5
4. SHA1
5. SHA256

다른 해시 알고리듬용 레인보우 테이블은 나중에 코인이 좀 오르면 구매하기로 하고, 일단 위 레인보우 테이블을 이용해 비밀번호를 찾아내는 프로그램을 만들어 봅시다.

## 1. 프로젝트를 준비한다

1. `Lab4` 폴더로 이동합니다.

2. `src/academy/pocu/comp3500/lab4/pocuhacker`에 `RainbowTable.java`와 `User.java` 파일이 있는지 확인합니다. 그렇지 않다면 [다음 페이지](https://github.com/POCU/COMP3500StarterPack/tree/main/Lab4/src/academy/pocu/comp3500/lab4/pocuhacker)에서 다운로드하세요.

3. `src/academy/pocu/comp3500/lab4` 폴더에 다음 클래스들을 추가합니다.

   ```java
   package academy.pocu.comp3500.lab4;
   
   import academy.pocu.comp3500.lab4.pocuhacker.RainbowTable;
   import academy.pocu.comp3500.lab4.pocuhacker.User;
   
   public final class Cracker {
       public String[] run(final RainbowTable[] rainbowTables) {
           return null;
       }
   }
   ```

## 2. `Cracker` 클래스 구현하기

### 전반적인 규칙

- 여러분이 작성하는 클래스들은 반드시 `academy.pocu.comp3500.lab4` 패키지 안에 속해야 합니다.
- 정적 (`static`) 변수는 사용할 수 없습니다.
- 레인보우 테이블은 키-값 쌍의 목록을 가지고 있습니다. 키는 비밀번호 해시, 값은 평문 비밀번호입니다. (참고: 실제 레인보우 테이블은 디스크 용량을 줄이기 위해 추가적인 연산을 거침)
- 텍스트 인코딩은 UTF8를 사용하세요
- CRC32를 제외한 모든 해시는 Base64로 인코딩 되어 있습니다.
- `RainbowTable.java`과 `User.java` 파일의 내용을 변경하지 마세요. 이 파일의 내용을 변경해도 빌드봇은 변경된 코드를 무시하고 채점을 진행합니다.

### 2.1 생성자를 구현한다

- 생성자는 다음의 인자를 받습니다.
  - 사용자 테이블: `User[] userTable`
  - 내 계정의 이메일 주소: `String email`
  - 내 계정의 비밀번호(평문): `String password`
- 여러분은 예전에 각 기업에 사용자 가입을 했습니다. 따라서 내 계정의 이메일 주소와 비밀번호를 알고 있습니다.

### 2.2 `run()` 메서드를 구현한다

- 이 메서드는 다음의 인자를 받습니다.

  - 레인보우 테이블 배열: `RainbowTable[] rainbowTables`

- 평문 비밀번호 배열을 반환합니다

- ```
  rainbowTables
  ```

  은 다음의 조건을 충족합니다.

  - 배열의 길이는 언제나 5
  - rainbowTables[0]은 CRC32용 레인보우 테이블
  - rainbowTables[1]은 MD2용 레인보우 테이블
  - rainbowTables[2]은 MD5용 레인보우 테이블
  - rainbowTables[3]은 SHA1용 레인보우 테이블
  - rainbowTables[4]은 SHA256용 레인보우 테이블

- 이 메서드가 반환하는 배열의 색인 위치는 `userTable`의 색인 위치와 동일합니다. 즉, `i`에 저장된 값은 `userTable[i]`의 평문 비밀번호입니다.

- 어떤 사용자의 평문 비밀번호를 찾을 수 없는 경우 반환 배열에서 그 사용자에 해당하는 평문 비밀번호는 `null`이어야 합니다.

## 3. 본인 컴퓨터에서 테스트하는 법

- `Program.java`를 아래처럼 바꾼 뒤 실행하세요.

```java
package academy.pocu.comp3500.lab4.app;

import academy.pocu.comp3500.lab4.Cracker;
import academy.pocu.comp3500.lab4.pocuhacker.RainbowTable;
import academy.pocu.comp3500.lab4.pocuhacker.User;

import java.util.HashMap;
import java.util.Map;

public class Program {
    public static void main(String[] args) {
        HashMap<String, String> crc32Map = new HashMap<>(Map.of(
                "211534962", "0000",
                "477404077", "letmein",
                "55151997", "qwerty",
                "901924565", "password"));
        HashMap<String, String> md2Map = new HashMap<>(Map.of(
                "yiRNCBNQgQETz6+ieP/VgQ==", "0000",
                "EfIl0sd6mcLoS45wACqTUg==", "letmein",
                "wssIXCT4UJhuVfHESr5odg==", "qwerty",
                "8DiBqIxuORNfDsxg79YJuQ==", "password"));
        HashMap<String, String> md5Map = new HashMap<>(Map.of(
                "Sn0e1BRHTkAzrCnMuGU9mw==", "0000",
                "DRB9CfW75Ayt495ccenptw==", "letmein",
                "2FeO34RYzgb7xbt2pYxcpA==", "qwerty",
                "X03MO1qnZdYdgyfeuILPmQ==", "password"));
        HashMap<String, String> sha1Map = new HashMap<>(Map.of(
                "Od+lUoMxjTGv5aP/Sg4yU+IEXkM=", "0000",
                "t6h1/B6iKLkGEEG3zsS9PFKrPOM=", "letmein",
                "sbN3OgXA7QF2eHpPFXT/AHX3Uh4=", "qwerty",
                "W6ph5Mm5Pz8GgiULbPgzG37mj9g=", "password"));
        HashMap<String, String> sha256Map = new HashMap<>(Map.of(
                "mvFbM25qlhmShTffMLLmojdlafz51+dz7M7eZWBlKaA=", "0000",
                "HIv+j4AdeXRcRjHQn/82yCqjf8TM5PyUZoPXsza2MDI=", "letmein",
                "ZehL4zUy+3hMSBKWdfnv86aCsnFowOp0Syz1juAjN8U=", "qwerty",
                "XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=", "password"));

        RainbowTable[] rainbowTables = new RainbowTable[] {
                new RainbowTable(crc32Map),
                new RainbowTable(md2Map),
                new RainbowTable(md5Map),
                new RainbowTable(sha1Map),
                new RainbowTable(sha256Map),
        };

        final String email = "notahacker@not.a.hacker";
        final String password = "notahackerpassword";

        final String normalUser1 = "john.smith@te.st";
        final String normalUser2 = "hong.gil.dong@nor.mal";

        // CRC32
        {
            User[] userTable = new User[]{
                    new User("001", normalUser1, "2418662205"),
                    new User("004", email, "632000577"),
                    new User("011", normalUser2, "477404077")
            };

            Cracker cracker = new Cracker(userTable, email, password);
            String[] plainTexts = cracker.run(rainbowTables);

            assert (plainTexts[0] == null);
            assert (plainTexts[1] == null);
            assert (plainTexts[2] != null && plainTexts[2].equals("letmein"));
        }

        // MD2
        {
            User[] userTable = new User[] {
                    new User("001", normalUser1, "8DiBqIxuORNfDsxg79YJuQ=="),
                    new User("005", normalUser2, "yiRNCBNQgQETz6+ieP/VgQ=="),
                    new User("006", email, "UHkDM4kEQC1JUsXEPN3QcA==")
            };

            Cracker cracker = new Cracker(userTable, email, password);
            String[] plainTexts = cracker.run(rainbowTables);

            assert(plainTexts[0] != null && plainTexts[0].equals("password"));
            assert(plainTexts[1] != null && plainTexts[1].equals("0000"));
            assert(plainTexts[2] == null);
        }

        // MD5
        {
            User[] userTable = new User[] {
                    new User("010", email, "lQGk5Otx90KH95fKA25Aug=="),
                    new User("011", normalUser1, "2FeO34RYzgb7xbt2pYxcpA=="),
                    new User("012", normalUser2, "6v2Gb022xeiHfqvTTmmT/g==")
            };

            Cracker cracker = new Cracker(userTable, email, password);
            String[] plainTexts = cracker.run(rainbowTables);

            assert(plainTexts[0] == null);
            assert(plainTexts[1] != null && plainTexts[1].equals("qwerty"));
            assert(plainTexts[2] == null);
        }

        // SHA1
        {
            User[] userTable = new User[] {
                    new User("001", normalUser2, "Od+lUoMxjTGv5aP/Sg4yU+IEXkM="),
                    new User("002", email, "LhcvnqAh1/Tme0rYqG2R37+J8ak="),
                    new User("003", normalUser1, "IpvINWW5+7SOw7I5/cAVuc81jXc=")
            };

            Cracker cracker = new Cracker(userTable, email, password);
            String[] plainTexts = cracker.run(rainbowTables);

            assert(plainTexts[0] != null && plainTexts[0].equals("0000"));
            assert(plainTexts[1] == null);
            assert(plainTexts[2] == null);
        }

        // SHA256
        {
            User[] userTable = new User[] {
                    new User("001", email, "08WISV7yGWsQpUCXlnErNl6ledurwx7pRhPGiS3zhIA="),
                    new User("002", normalUser2, "XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg="),
                    new User("003", normalUser1, "/Z7d6Us6HBWG5GIez6AHWSJg2irdWAdXKlsO+6WnVhI=")
            };

            Cracker cracker = new Cracker(userTable, email, password);
            String[] plainTexts = cracker.run(rainbowTables);

            assert(plainTexts[0] == null);
            assert(plainTexts[1] != null && plainTexts[1].equals("password"));
            assert(plainTexts[2] == null);
        }
    }
}
```

## 4. 커밋, 푸시 그리고 빌드 요청

이건 어떻게 하는지 이제 다 아시죠? :)