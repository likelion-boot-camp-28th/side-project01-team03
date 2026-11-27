# 📚 도서 대출 프로그램 (콘솔)

Java 21 / 순수 Java(IntelliJ) / 콘솔 입출력

회원가입과 로그인, 도서 등록·검색·삭제, 도서 대출·반납·연체 조회를 콘솔 메뉴에서 할 수 있는 프로그램입니다.

## 실행
- IntelliJ: 프로젝트 루트 폴더 열기 → `src/com/devin/library/main/Main.java` 실행
- 터미널 (프로젝트 루트에서 실행):
  ```bash
  javac -encoding UTF-8 -d out $(find src -name "*.java")
  java -cp out com.devin.library.main.Main
  ```

> 도서 데이터 파일 경로가 `src/com/devin/library/data/books.txt`(상대 경로)라서, **작업 디렉터리를 프로젝트 루트로 두고 실행**해야 도서 목록을 불러옵니다.

## 주요 기능

| 메뉴 | 기능 | 비고 |
|---|---|---|
| 1. 회원관리 | 회원 가입, 로그인 | 아이디 입력 직후 중복확인, 로그인 중 재로그인 차단 |
| 2. 도서관리 | 도서 등록, 목록, 검색(제목/저자), 삭제 | `books.txt` 파일에 저장, 대출 중인 책은 삭제 불가 |
| 3. 대출 관리 | 도서 대출, 반납, 전체 대출 목록, 내 대출 내역, 연체 목록 | **로그인해야 사용 가능**, 대출 기간 14일 |

로그인하면 메인 메뉴 제목에 `(아이디 님 이용중)`이 표시되고, 회원 메뉴에 다시 들어가도 로그인 상태가 유지됩니다.

## 패키지 구조

```
src/com/devin/library
├─ main      Main, LibraryApp
├─ common    InputUtil, LibraryException
├─ data      books.txt
├─ member
│  ├─ controller   MemberController
│  ├─ model
│  │  ├─ dto         MemberDto
│  │  ├─ repository  MemberRepository
│  │  └─ service     MemberService, MemberServiceImpl
│  └─ view         MemberView
├─ book
│  ├─ controller   BookController
│  ├─ model
│  │  ├─ dto         Bookdto
│  │  └─ service     BookService, BookServiceImpl
│  └─ view         View
└─ loan
   ├─ controller   LoanController
   ├─ model
   │  ├─ dto         LoanDto
   │  ├─ repository  LoanRepository
   │  └─ service     LoanService
   └─ view         LoanView
```

도메인은 MVC 구조로 나뉘어 있습니다.

| 계층 | 역할 |
|---|---|
| View | 메뉴 출력, `InputUtil`로 입력받기 |
| Controller | View 입력을 Service에 넘기고, `LibraryException`을 잡아 결과 메시지 출력 |
| Service | 중복 검사, 대출 가능 여부 판단 같은 규칙과 로직 |
| Repository | 데이터 저장, 조회 (회원/대출은 `Map`, 도서는 Service가 `List` + 파일로 직접 관리) |
| DTO | 데이터를 담는 클래스 |

## 전체 흐름

```
Main → LibraryApp (메인 메뉴, 로그인 ID 보관)
         ├─ 1. 회원관리 → MemberController ⇄ MemberView
         │                  └─ MemberServiceImpl → MemberRepository (HashMap)
         │                  └─ 로그인 성공 시 아이디를 LibraryApp에 반환
         ├─ 2. 도서관리 → View → BookController → BookServiceImpl ⇄ books.txt
         └─ 3. 대출 관리 → LoanView(loginMemberId) → LoanController
                            ├─ LoanService → LoanRepository (LinkedHashMap)
                            └─ BookService (도서 조회, 대출 상태 변경)
```

- `BookService`는 `LibraryApp`에서 **한 번만 생성**해 도서 관리와 대출 관리에 함께 주입합니다. 그래서 대출/반납 시 바꾼 도서 상태가 도서 목록에도 바로 반영됩니다.
- `LibraryApp.run()`은 `LibraryException`, 입력 종료(`NoSuchElementException`), 그 밖의 `RuntimeException`을 잡아 프로그램이 갑자기 끝나지 않게 합니다.

## 도메인 설명

### 1. 메인 (`main`)
- `Main`: `LibraryApp.run()`만 호출합니다.
- `LibraryApp`: 각 도메인 객체를 생성해 연결하고, 메인 메뉴(회원관리, 도서관리, 대출 관리, 종료)를 반복해서 보여줍니다. 로그인한 회원 ID(`loginMemberId`)를 들고 있다가 대출 메뉴에 넘겨줍니다.

### 2. 공통 (`common`)
- `InputUtil`: 프로그램 전체에서 쓰는 `Scanner` 하나를 감싼 입력 유틸입니다.
  - `readLine`, `readNonBlank`(빈 값 재입력), `readInt`, `readLong`(숫자 아니면 재입력), `readYesNo`
- `LibraryException`: 모든 도메인이 쓰는 공통 예외입니다. 사용자에게 보여줄 메시지를 담아 던지고 Controller에서 출력합니다.

### 3. 회원 (`member`)
- `MemberDto`: `id`, `pw`
- **회원 가입**: 아이디 입력 → 바로 중복확인(`duplicateCheck`) → 비밀번호 입력 → 저장
- **로그인**: 아이디/비밀번호가 맞으면 아이디를 돌려줍니다 (비밀번호는 밖으로 넘기지 않음). 틀리면 "아이디 또는 비밀번호가 다릅니다" 출력
- 이미 로그인된 상태에서는 다시 로그인할 수 없습니다.
- 저장소: `MemberRepository` (`HashMap<String, MemberDto>`, 메모리)

### 4. 도서 (`book`)
- `Bookdto`: `id`, `tilte`(제목), `author`, `borrowed`(대출 여부)
- **등록**: 번호 자동 부여 (기존 데이터의 마지막 번호 + 1)
- **목록 / 검색**: 제목 또는 저자에 검색어가 포함된 책 조회
- **삭제**: 없는 번호거나 대출 중인 책이면 삭제하지 않음
- 저장소: `BookServiceImpl`이 시작할 때 `data/books.txt`를 읽고, 등록·삭제할 때마다 파일에 다시 씁니다.
  - 파일 형식: `번호,제목,저자,대출여부` (예: `1,자바의 정석,남궁성,false`)

### 5. 대출 (`loan`)
- `LoanDto`: `loanId`, `memberId`, `bookId`, `bookName`, `bookAuthor`, `loanDate`(대출일), `dueDate`(반납 예정일), `returnDate`(반납일, `null`이면 대출 중)
- **대출**: 도서 번호 확인 → 이미 대출 중이면 거절 → 반납 예정일 = 오늘 + 14일 → 저장 → 도서 상태 `borrowed = true`
- **반납**: 내 미반납 목록을 먼저 보여주고 대출 ID 입력 → 존재 여부 / 본인 대출인지 / 이미 반납했는지 확인 → 반납일 기록 → 도서 상태 `borrowed = false`
- **전체 대출 목록 / 내 대출 내역 / 연체 목록**: 연체 = 미반납이면서 반납 예정일이 오늘보다 이전인 대출
- 저장소: `LoanRepository` (`LinkedHashMap<Long, LoanDto>`, 메모리, 등록 순서 유지)

## 알아둘 점 / TODO
- [ ] 회원·대출 정보는 메모리에만 저장되어 프로그램을 끄면 사라집니다.
- [ ] 대출/반납으로 바뀐 도서 상태(`borrowed`)는 `books.txt`에 저장되지 않습니다. (등록·삭제 때만 파일 저장)
- [ ] 로그아웃, 회원 정보 수정/탈퇴 미구현
- [ ] 도서 정보 수정, 대출 가능한 책만 보기 미구현
- [ ] 도서 등록 시 빈 제목/저자 입력 검사 (`readNonBlank` 사용)
- [ ] 1인당 대출 권수 제한 (선택)

## 협업 규칙
- **자기 도메인 패키지만 수정**합니다. 다른 도메인이 필요하면 그 도메인의 `Service` 메서드를 호출합니다.
- 대출 도메인은 `BookService`를 사용하므로 **public 메서드 시그니처를 바꿀 때는 팀에 공유**합니다.
- `common`, `main` 수정은 팀에 공유 후 진행합니다.
- `System.in`용 `Scanner`를 새로 만들지 말고 `InputUtil`을 사용합니다.
- 사용자에게 보여줄 오류는 `LibraryException`으로 던지고 Controller에서 메시지를 출력합니다.

## 브랜치 / 커밋
- `develop`: 기본 브랜치, 직접 push 금지 → 작업 브랜치에서 PR로 머지
- 작업 브랜치: `feat/member-login-service`, `fix/loan-login-check` 처럼 `타입/도메인-내용`
- 커밋 메시지: `feat: 로그인 컨트롤러 구현`, `fix: 비로그인 시 대출 메뉴 진입 차단`
  - 타입: `feat` `fix` `refactor` `docs` `chore`
