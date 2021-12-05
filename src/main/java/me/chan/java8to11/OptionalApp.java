package me.chan.java8to11;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalApp {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        /*
        get을 사용하기 위해 ifPresent 체크가 필요하다.
        OnlineClass onlineClass = optional.get();
        System.out.println("onlineClass = " + onlineClass.getTitle());*/

        optional.ifPresent(oc -> {
            System.out.println("oc = " + oc.getTitle());
        });

        /*
        OnlineClass onlineClass = optional.orElse(이미 생성된 객체 사용);
        */

        // 동적으로 생성 시에는 orElseGet
        OnlineClass onlineClass = optional.orElseGet(OptionalApp::createNewClasses);
        System.out.println("onlineClass = " + onlineClass.getTitle());

        //supplier 통해 에러 던짐
        /*OnlineClass onlineClass = optional.orElseThrow(() -> {
            return new IllegalArgumentException();
        });*/

        // filter 사용
        Optional<OnlineClass> onlineClass1 = optional.
                filter(oc -> !oc.isClosed());
        System.out.println("onlineClass1 = " + onlineClass1);

        // map 사용
        Optional<String> s = optional.map(oc -> oc.getTitle());

        // Optional인 타입을 꺼내려면 2번 작업이 필요하다
        // 이런 경우 flatMap을 사용하면 간편하다.
        // stream의 flatMap과는 다르다.
        Optional<Optional<Progress>> progress1 = optional.map(oc -> oc.getProgress());
        Optional<Progress> progress2 = progress1.orElseThrow();

        Optional<Progress> progress = optional.flatMap(OnlineClass::getProgress);
    }

    private static OnlineClass createNewClasses() {
        System.out.println("OptionalApp.createNewClasses");
        return new OnlineClass(10, "New class", false);
    }

}
