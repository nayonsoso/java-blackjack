# java-blackjack

### 블랙잭 미션 저장소

블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

## ♠ 구현해야 하는 기능

> 입력 예외 발생 시 `Illegalargumentexception` 을 발생시킨다.

### 📌 플레이어 등록
- [x] 게임에 참여할 사람의 이름을 입력받는다.
  - [예외처리] 이름은 빈 값을 입력하면 안된다.
  - [예외처리] 이름은 null 을 입력하면 안된다.
  - [예외처리] 이름은 중복되면 안된다.
  - [예외처리] 이름은 `딜러`와 같으면 안된다.
  - [예외처리] 게임에 참여하는 사람은 딜러 포함 8명을 넘길 수 없다.

### 📌 베팅
- [x] 플레이어들은 게임에 베팅할 금액을 입력한다.
  - [예외처리] 베팅 금액은 음수를 입력하면 안된다.
  - [예외처리] 베팅 금액은 10원 단위로 입력해야 한다.

### 📌 카드 셋팅
- [x] 게임에 참여할 사람의 목록을 입력 받으면 모든 종류의 카드를 생성한다.
- [x] 생성한 카드를 랜덤으로 섞는다.
- [x] 카드를 나누어줄 때에는 랜덤으로 섞은 카드를 뒤에서부터 하나씩 준다.

### 📌 초기 카드 뽑기
- [x] 딜러와 플레이어들에게 각각 2장의 카드를 나누어준다.
- [x] 딜러와 플레이어가 블랙잭인지 확인한다.
- [x] 딜러가 블랙잭일 경우 승패를 결정하여 게임을 종료한다.
  - [x] 딜러가 블랙잭일 경우 플레이어가 블랙잭이면 `무승부`이다.
    - [x] `무승부`일 경우 수익은 없다.
  - [x] 딜러가 블랙잭일 경우 플레이어가 블랙잭이 아니면 플레이어의 `패배`이다.
    - [x] `패배`일 경우 베팅 금액을 딜러에게 준다.
- [x] 플레이어가 블랙잭일 경우 승패를 결정한다.
  - [x] 딜러가 블랙잭이 아닌데 플레이어가 블랙잭이면 플레이어의 `승리`이다.
    - [x] 블랙잭 `승리`일 경우 딜러에게서 베팅 금액의 `1.5배`를 받는다.
- [x] 딜러가 블랙잭이 아니며 블랙잭이 아닌 플레이어가 남아있을 경우 플레이어는 카드를 뽑는다.

### 📌 플레이어 카드 뽑기
- [x] 참가자들 순서대로 카드를 더 뽑을지 확인한다.
- [x] 현재 참가자의 숫자 총 합이 21 아래이며 카드 여분이 있을 경우에 뽑을지 확인한다.
- [x] `y` 를 입력할 경우 카드를 하나 추가한다.
- [x] `n` 을 입력할 경우 다음 참가자로 넘어간다.
  - [예외처리] 이름은 빈 값을 입력하면 안된다.
  - [예외처리] 이름은 null 을 입력하면 안된다.
  - [예외처리] `y`, `n` 이외의 값을 입력하면 안된다.
- [x] 플레이어의 현재 카드를 오픈한다.
- [x] 플레이어의 카드의 총 합이 21을 넘길 경우 플레이어는 `패배`이다.
  - [x] 버스트되어 `패배`일 경우 베팅 금액을 딜러에게 준다.

### 📌 딜러 카드 뽑기
- [x] 모든 참가자를 확인한 후 딜러의 총 합이 `17` 점을 넘길 때 까지 계속해서 카드를 추가한다.
- [x] 딜러의 카드의 총 합이 21을 넘길 경우 플레이어 `승리`이다.

### 📌 승패 결정
- [x] 딜러의 턴이 종료되면 가지고 있는 모든 카드와 총 합을 오픈한다.
- [x] 딜러와 플레이어의 합이 같을 경우 `무승부`이다.
  - [x] `무승부`일 경우 수익은 없다.
- [x] 딜러의 합이 더 클 경우 플레이어의 `패배`이다.
  - [x] `패배`일 경우 베팅 금액을 딜러에게 준다.
- [x] 딜러의 합이 더 작을 경우 플레이어의 `승리`이다.
  - [x] `승리`일 경우 딜러에게서 베팅 금액만큼 받는다.

## 실행 결과
  ```
    게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
    pobi,jason
    
    pobi의 배팅 금액은?
    10000
    
    jason의 배팅 금액은?
    20000
    
    딜러와 pobi, jason에게 2장을 나누었습니다.
    딜러: 3다이아몬드
    pobi카드: 2하트, 8스페이드
    jason카드: 7클로버, K스페이드
    
    pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
    y
    pobi카드: 2하트, 8스페이드, A클로버
    pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
    n
    jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
    n
    jason카드: 7클로버, K스페이드
    
    딜러는 16이하라 한장의 카드를 더 받았습니다.
    
    딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
    pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
    jason카드: 7클로버, K스페이드 - 결과: 17
    
    ## 최종 수익
    딜러: 10000
    pobi: 10000 
    jason: -20000
  ```
