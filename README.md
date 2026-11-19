# 📚 도서 대출 프로그램 (콘솔)

Java 21 / 순수 Java(IntelliJ) / 콘솔 입출력 / 메모리 저장소

## 실행
- IntelliJ: 폴더 열기 → `src/com/library/main/Main.java` 실행
- 터미널:
  ```bash
  javac -encoding UTF-8 -d out $(find src -name "*.java")
  java -cp out com.library.main.Main
  ```

## 패키지 구조 & 담당
| 도메인 | 패키지 | 담당 | 주요 클래스 |
|---|---|---|---|
| 메인 | `com.library.main` | TBD | `Main`, `LibraryApp`(객체 조립·메인 메뉴) |
| 책 | `com.library.book` | TBD | `Book`, `BookRepository`, `BookService`, `BookController` |
| 회원 | `com.library.member` | TBD | `Member`, `MemberRepository`, `MemberService`, `MemberController` |
| 대출 | `com.library.loan` | TBD | `Loan`, `LoanRepository`, `LoanService`, `LoanController` |
| 공통 | `com.library.common` | 공동 | `InputUtil`(Scanner는 이것만 사용) |

계층: `Controller(콘솔 메뉴)` → `Service(로직)` → `Repository(메모리 저장)`

## 협업 규칙
- **자기 도메인 패키지만 수정**합니다. 다른 도메인이 필요하면 그 도메인의 `Service` 메서드를 호출합니다.
- 대출 도메인은 `BookService`, `MemberService`를 사용하므로 두 서비스의 **public 메서드 시그니처 변경 시 팀에 공유**합니다.
- `common`, `main` 수정은 팀에 공유 후 진행합니다.
- `System.in`용 `Scanner`를 새로 만들지 말고 `InputUtil`을 사용합니다.

## 브랜치 / 커밋
- `main`: 항상 실행 가능한 상태 유지, 직접 push 금지 → PR로 머지
- 작업 브랜치: `feat/book-search`, `fix/loan-return` 처럼 `타입/도메인-내용`
- 커밋 메시지: `feat: 도서 검색 기능 추가`, `fix: 반납 시 도서 상태 미변경 수정`
  - 타입: `feat` `fix` `refactor` `docs` `chore`
