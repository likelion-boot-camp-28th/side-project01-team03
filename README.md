# 📚 도서 대출 프로그램 (콘솔)

Java 21 / 순수 Java(IntelliJ) / 콘솔 입출력 / 메모리 저장소

## 실행
- IntelliJ: 폴더 열기 → `src/com/library/main/Main.java` 실행
- 터미널:
  ```bash
  javac -encoding UTF-8 -d out $(find src -name "*.java")
  java -cp out com.devin.library.main.Main
  ```

## 패키지 구조 & 담당
| 도메인 | 패키지 | 담당 | 주요 클래스 |
|---|---|---|---|
| 메인 | `com.devin.library.main` | TBD | `Main`, `LibraryApp`(객체 조립·메인 메뉴) |
| 책 | `com.devin.library.book` | TBD | `Book`, `BookRepository`, `BookServiceImpl`, `BookController` |
| 회원 | `com.devin.library.member` | TBD | `MemberDto`, `MemberRepository`, `MemberService`, `MemberController` |
| 대출 | `com.devin.library.loan` | TBD | `Loan`, `LoanRepository`, `LoanService`, `LoanController` |
| 공통 | `com.devin.library.common` | 공동 | `InputUtil`(Scanner는 이것만 사용) |

## 전체 흐름
```
Main → LibraryApp (메인 메뉴)
          ├─ 1. 도서 관리 → BookController   → BookService   → BookRepository
          ├─ 2. 회원 관리 → MemberController → MemberService → MemberRepository
          └─ 3. 대출 관리 → LoanController   → LoanService   → LoanRepository
                                                   └─ BookService, MemberService 사용
```

도메인마다 클래스 구성은 같습니다.

| 계층 | 예시 | 역할 |
|---|---|---|
| Entity | `Book`, `MemberDto`, `Loan` | 데이터를 담는 클래스 |
| Repository | `BookRepository` | `Map`에 저장, 조회, 삭제 (DB 대신 메모리) |
| Service | `BookServiceImpl` | 실제 규칙과 로직 |
| Controller | `BookController` | 콘솔 메뉴 출력, 입력받아 Service 호출 |

## 도메인 설명

### 1. 메인 (`com.devin.library.main`)
프로그램 시작점이고, 다른 도메인들을 연결합니다.
- `Main`: `main()` 메서드만 있습니다.
- `LibraryApp`: Repository → Service → Controller를 생성해 서로 연결하고, 메인 메뉴(도서, 회원, 대출, 종료)를 반복해서 보여줍니다.

**TODO**
- [ ] 시작할 때 샘플 도서와 회원 미리 넣어두기 (테스트용)
- [ ] 메뉴 출력 꾸미기
- [ ] 공통 예외 처리
- [ ] `common/InputUtil` 관리
- [ ] 통합 테스트

### 2. 책 (`com.devin.library.book`)
도서 정보를 관리합니다.
- `Book`: `id`, `title`, `author`, `available`(대출 가능 여부)
- 구현됨: 등록, 전체 목록

**TODO**
- [ ] 제목/저자로 검색
- [ ] 도서 정보 수정
- [ ] 삭제 (대출 중인 책은 삭제 막기)
- [ ] 대출 가능한 책만 보기

### 3. 회원 (`com.devin.library.member`)
회원 정보를 관리합니다.
- `MemberDto`: `id`, `name`, `phone`
- 구현됨: 가입, 전체 목록

**TODO**
- [ ] 전화번호 중복 가입 막기
- [ ] 이름으로 회원 검색
- [ ] 정보 수정
- [ ] 탈퇴

### 4. 대출 (`com.devin.library.loan`)
책과 회원을 이어서 대출과 반납 기록을 관리합니다. 로직이 가장 많은 도메인입니다.
- `Loan`: `bookId`, `memberId`, `loanDate`(대출일), `dueDate`(반납 예정일), `returnDate`(반납일, `null`이면 대출 중)
- 구현됨: 대출 내역 보기

**TODO**
- [ ] 대출: 회원과 책이 있는지 확인 → 대출 가능한 책인지 확인 → `book.setAvailable(false)` → 반납 예정일 계산(예: +14일) → 저장
- [ ] 반납: `loan.markReturned(오늘)` → `book.setAvailable(true)` → 연체 여부 출력
- [ ] 회원별 대출 내역, 현재 연체 목록
- [ ] 1인당 대출 권수 제한 (선택)

> 대출 도메인은 `bookService.findById()`, `memberService.findById()`에 의존합니다. 책/회원 담당자와 메서드 이름과 반환 타입을 먼저 맞춰두세요.

## 협업 규칙
- **자기 도메인 패키지만 수정**합니다. 다른 도메인이 필요하면 그 도메인의 `Service` 메서드를 호출합니다.
- 대출 도메인은 `BookServiceImpl`, `MemberService`를 사용하므로 두 서비스의 **public 메서드 시그니처 변경 시 팀에 공유**합니다.
- `common`, `main` 수정은 팀에 공유 후 진행합니다.
- `System.in`용 `Scanner`를 새로 만들지 말고 `InputUtil`을 사용합니다.

## 브랜치 / 커밋
- `main`: 항상 실행 가능한 상태 유지, 직접 push 금지 → PR로 머지
- 작업 브랜치: `feat/book-search`, `fix/loan-return` 처럼 `타입/도메인-내용`
- 커밋 메시지: `feat: 도서 검색 기능 추가`, `fix: 반납 시 도서 상태 미변경 수정`
  - 타입: `feat` `fix` `refactor` `docs` `chore`
