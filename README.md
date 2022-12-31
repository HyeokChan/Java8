# JAVA8
***
### 함수형 인터페이스와 람다 표현식
#### 함수형 인터페이스와 람다 표현식 소개
1. 함수형 인터페이스 
    - 추상 메소드를 하나만 가지고 있는 인터페이스
    - SAM(Single Abastact Method) 인터페이스
    - @FunctionalInterface 어노테이슨을 가지고 있는 인터페이스

2. 람다 표현식
    - 함수형 인터페이스의 인스턴스를 만드는 방법으로 쓰일 수 있음
    - 코드 수 줄임
    - 메소드 매개변수, 리턴 타입, 변수로 만들어 사용 가능

3. 함수형 프로그래밍
    - 함수를 First class object로 사용할 수 있음
    - 순수 함수
        - 사이드 이펙트가 없음 : 함수 밖에 있는 값을 변경하지 않음
        - 상태가 없음 : 함수 밖에 있는 값을 사용하지 않음
    - 고차 함수 : 함수가 함수를 매개변수로 받을 수 있고 함수를 리턴할 수 도 있음
    - 불변성

#### 자바에서 제공하는 함수형 인터페이스
1.  Function<T,R>
    - T타입을 받아서 R타입을 리턴하는 함수 인터페이스
        - R apply(T t)
    - 함수 조합용 메소드
        - andThen
        - compose

2. BiFunction<T,U,R>
    - 두 개의 값(T,U)를 받아서 R타입을 리턴하는 함수 인터페이스
        - R apply(T t, U u)

3. Consumer<T>
    - T타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스
        - void apply(T t)
    - 함수 조합용 메소드
        -  andThen

4. Supplier<T>
    - T타입의 값을 제공하는 함수 인터페이스
        - T get()

5. Predicate<T>
    - T타입을 받아서 boolean을 리턴하는 함수 인터페이스
        - boolean test(T t)
    - 함수 조합용 메소드
        - And
        - Or
        - Negate

6. UnaryOperator<T>
    - Function<T,R>의 특수한 형태로, 입력값 하나를 받아서 동일한 타입을 리턴하는 함수 인터페이스

7. BinaryOperator<T>
    - BiFunction<T,U,R>의 특수한 형태로, 동일한 타입의 입력값 두개를 받아 리턴하는 함수 인터페이스

#### 람타 표현식
1. 람다
    - (인자리스트) -> {바디} 형태의 구조

2. 인자리스트
    - 인자가 없을 때 : ()
    - 인자가 한개일 때 : (one)
    - 인자가 여러개 일 때 : (one, two)
    - 인자의 타입은 생략 가능, 컴파일러가 추론하지만 명시할 수 있음 : (Integer one, Integer two)

3. 바디 
    - 화살표 오른쪽에 함수 본문 정의
    - 여러 줄인 경우에 {}를 사용하여 묶음
    - 한 줄인 경우 생략가능

4.  변수 캡처
    - 로컬 변수 캡처
        - final이거나 effective final인 경우에만 참조할 수 있음
        - 그렇지 않을 경우 concurrency 문제가 생길 수 있어 컴파일러가 방지함
    - effective final
        - 자바8부터 지원하는 사실상 final인 변수
        - final 키워드를 사용하지 않은 변수를 익명클래스 구현체 또는 람다에서 참조할 수 있음
    - 익명클래스 구현체와 달리 쉐도잉하지 않는다
        - 익명클래스는 새로 스콥을 만들지만, 람다는 람다를 감싸고 있는 스콥과 같다.

#### 메소드 레퍼런스
1. 람다가 하는 일이 기존 메소드 또는 생성자를 호출하는 거라면, 메소드 레퍼런스를 사용하여 매우 간결하게 표현 가능
2. 메소드 참조 방법
    - 스태틱 메소드 참조 : 타입::스태틱메소드
    - 특정 객체의 인스턴스 메소드 참조 : 객체 레퍼런스::인스턴스메소드
    - 임의 객체의 인스턴스 메소드 참조 : 타입::인스턴스메소드
    - 생성자 참조 : 타입::new

### 인터페이스의 변화
#### 인터페이스 기본 메소드와 스태틱 메소드
1. 기본 메소드(Default Method)
    - 인터페이스에 메소드 선언이 아니라 구현체를 제공하는 방법
    - 해당 인터페이스를 구현한 구현체를 깨트리지 않고 새 기능 추가 가능
    - 기본 메소드는 구현체가 모르게 추가된 기능으로 그만큼 리스크도 존재
    - Object가 제공하는 기능(equals, hasCode)는 기본 메소드로 제공할 수 없음
    - 본인이 수정할 수 있는 인터페이스에만 기본 메소드 제공 가능
    - 인터페이스를 상속받는 인터페이스에서 다시 추상 메소드로 변경할 수 있음
    - 인터페이스 구현체가 재정의 가능
2. 스태틱 메소드
    - 해당 타입 관련 헬터 또는 유틸리티 메소드를 제공할 때 인터페이스에 스태틱 메소드를 제공할 수 있음

#### 자바8 API의 기본 메소드와 스태틱 메소드
1. Iterator의 기본 메소드
    - forEach()
    - spliterator
2. Collection의 기본 메소드
    - stream() / parallelStream()
    - removeIf(Predicate)
    - spliterator()
3. Comparator의 기본 메소드 및 스태틱 메소드
    - reversed()
    - thenComparing
    - static reverseOrder() / naturalOrder()
    - static nullsFirst() / nullsLast()
    - static comparing()

### Stream
#### Stream 소개
1. Stream
    - 데이터를 담고 있는 저장소(컬렉션)가 아님
    - 스트림이 처리하는 데이터 소스를 변경하지 않는다
    - 무제한일 수도 있음(Short Circuit 메소드를 사용해서 제한 가능)
    - 중개 오퍼레이션은 근본적으로 Lazy 함
    - 손쉽게 병렬 처리 가능
2. 스트림 파이프라인
    - 0ㄷ 또는 다수의 중개 오퍼레이션과 한개의 종료 오퍼레이션으로 구성
    - 스트림의 데이터 소스는 오직 종료 오퍼레이션을 실행할 때에만 처리
3. 중개 오퍼레이션
    - Stream을 리턴
    - Stateless / Stateful 오퍼레이션으로 더 상세하게 구분 가능
      (대부분 Stateless 오퍼레이션, distinct/sorted처럼 이전 소스 데이터를 참조해야 하는 오퍼레이션은 Statedful 오퍼레이션)
    - filter, map, limiut, skip, sorted 등등 존재
4. 종료 오퍼레이션
    - Stream을 리턴하지 않는다
    - collect, allMatch, count, forEach, min, max 등등 존재

#### Stream API
1. 걸러내기
    - Filter(Predicate)
2. 변경하기 
    - Map(Function)
    - FlatMap(Function)
3. 생성하기
    - generate(Supplier)
    - Iterate(T seed, UnaryOperator)
4. 제한하기
    - limit(long)
    - skip(long)
5. 스트림에 있는 데이터가 특정 조건을 만족하는지 확인
    - anyMatch()
    - allMatch()
    - nonMatch()
6. 개수 세기
    - count()
7. 스트림 데이터를 하나로 뭉치기
    - reduce(identity, BiFunction)
    - collect()
    - sum()
    - max()

### Opational
#### Optional 소개
1. Optional : 오직 값 한개가 들어있을 수도 없을 수도 있는 컨테이너
2. 주의점
    - 리턴값으로만 쓰기 권장(메소드 매개변수 타입, 맵의 키 타입, 인스턴스 필드 타입으로 사용 피하기)
    - Optional을 리턴하는 메소드에서 null 리턴 피하기
    - 프리미티브 타입용 Optional이 따로 있다.
      (OptionalInt, OptionalLong)
    - Collection, Map, Stream Array, Optional은 Optional로 감싸지 말 것

#### Optional API
1. Optional 만들기
    - Optional.of()
    - Optional.ofNullable()
    - Optional.empty()
2. Optional에 값이 있는지 없는지 확인하기
    - isPresent()
    - isEmpty()
3. Optional에 있는 값 가져오기
    - get()
4. Optional에 값이 있는 경우에 그 값으로 처리해라
    - ifPresent(Consumer)
5. Optional에 값이 있으면 가져오고 없으면 특정 값을 리턴해라
    - orElse(T)
6. Optional에 값이 있으면 가져오고 뭔가 없는 경우에 특정 처리를 해라
    - orElseGet(Supplier)
7. Optional에 값이 있으면 가져오고 없는 경우 에러를 던져라
    - orElseThrow()
8. Optional에 들어있는 값 걸러내기
    - Optional filter(Predicate)
9. Optional에 들어있는 값 변환하기
    - Optional map(Function)
    - Optional flatMap(Function)

### Completable Future
#### 자바 Concurrent 프로그래밍 소개
1. Concurrent 소프트웨어 : 동시에 여러 작업을 할 수 있는 소프트웨어
2. 자바에서 지원하는 concurrent 프로그래밍
    - 멀티프로세싱
    - 멀티쓰레드
3. 자바 멀티쓰레드 프로그래밍
    - Thread
    ~~~
    public static void main(String[] args) {
        HelloThread helloThread = new HelloThread();
        helloThread.start();
        System.out.println("hello : " + Thread.currentThread().getName());
    }
    static class HelloThread extends Thread {
        @Override
        public void run() {
            System.out.println("world : " + Thread.currentThread().getName());
    } }
    ~~~
    - Runnable
    ~~~
    Thread thread = new Thread(() -> System.out.println("world : " + Thread.currentThread().getName()));
    thread.start();
    System.out.println("hello : " + Thread.currentThread().getName());
    ~~~
4. 쓰레드 주요 기능
    - sleep : 다른 쓰레드가 처리할 수 있도록 기회를 주지만 락을 놔주진 않음
    - interrupt : 다른 쓰레드를 깨워서 interruptedException을 발생시킴
    - join : 다른 쓰레드가 끝날 때까지 기다림

#### Executors
1. 고수준 Concurrency 프로그래밍
    - 쓰레드를 만들고 관리하는 작업을 애플리케이션에서 분리
    - 해당 기능을 Executors에게 위임
2. Executors가 하는 일
    - 쓰레드 만들기 : 애플리케이션이 사용할 쓰레드 풀을 만들어 관리
    - 쓰레드 관리 : 쓰레드 생명주기를 관리
    - 작업처리 및 실행 : 쓰레드로 실행할 작업을 제공할 수 있는 API를 제공
3. 주요 인터페이스
    - Executor : execute(Runnable)
    - ExecutorService : Executor를 상속받은 인터페이스로, Callable도 실행할 수 있으며 Executor를 종료 시키거나, 여러 Callable을 동시에 실행하는 등의 기능 제공
    - SheduledExecutorService ; ExecutorService를 상속받은 인터페이스로 특정 시간 이후에 또는 주기적으로 작업을 실행할 수 있음
4. ExecutorService로 작업 실행하기
    ~~~
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.submit(() -> {
        System.out.println("Hello :" + Thread.currentThread().getName());
    });
    ~~~
5. ExecutorService로 멈추기
    ~~~
    executorService.shutdown(); // 처리중인 작업 기다렸다가 종료
    executorService.shutdownNow(); // 당장 종료
    ~~~

#### Callable과 Future
1. Callable : Runnable과 유사하지만 작업의 결과를 받을 수 있음
2. Future : 비동기적인 작업의 현재 상태를 조회하거나 결과를 가져올 수 있음
3. 결과 가져오기 : get()
    ~~~
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<String> helloFuture = executorService.submit(() -> {
        Thread.sleep(2000L);
        return "Callable";
    });
    System.out.println("Hello");
    String result = helloFuture.get();
    System.out.println(result);
    executorService.shutdown();
    ~~~
    - 블록킹 콜
    - 타임아웃 설정 가능
4. 작업 상태 확인하기 : isDone()
5. 작업 취소하기 : cancel()
6. 여러작업 동시에 실행하기 : invokeAll()
7. 여러 작업중에 하나라도 먼저 응답이 오면 끝내기 : invokeAny()

***
