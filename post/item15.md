## item15
### 클래스와 멤버의 접근권한을 최소화하라

- 좋은 컴포넌트와 나쁜 컴포넌트의 가장 큰 차이점은 클래스 내부 데이터와 구현체를 외부로 부터 얼마나 잘 숨겼느냐이다.  
- 오직 api 로만 통신하고 내부 동작은 신경 쓰지 않아야 하는데, 이를 정보 은닉(캡슐화) 라고 한다.  
- 정보은닉의 핵심은 자바의 접근제한자를 잘 활용하는 것이다. 즉, 모든 클래스와 멤버의 접근성을 가능한 한 좁혀야 한다는 뜻.  

#### 정보 은닉의 장점
- 시스템 개발 속도 향상. (여러 컴포넌트를 병렬로 개발가능해서)  
- 시스템 관리 비용을 낮춤. (파악과 디버깅이 빠르다)  
- 성능 향상은 없지만 성능 최적화를 할 수 있음.  
- 재사용성을 높임.  
- 큰 시스템을 제작하는 난이도를 낮춤. 

#### java 의 멤버 접근 제한자
- private : 멤버를 선언한 톱레벨 클래스에서만 접근 가능  
- package-private : 멤버가 소속된 `패키지` 안의 클래스에서 접근 가능 (default 이고 interface 는 기본적으로 public)  
- protected : package-private 을 포함하고 하위클래스에서도 제한적으로 접근이 가능함  
- public : 전체공개  

```java
package com.nevercaution.demo.item15.first;

public class First {

    public void callPublic() {
        System.out.println("First.callPublic");
    }

    protected void callProtected() {
        System.out.println("First.callProtected");
    }

    void callPackagePrivate() {
        System.out.println("First.callPackagePrivate");
    }

    private void callPrivate() {
        System.out.println("First.callPrivate");
    }
}

--- 

package com.nevercaution.demo.item15.first;

public class First2 {

    public static void main(String[] args) {
        First first = new First();

        first.callPublic();
        first.callPackagePrivate();
        first.callProtected();
        first.callPrivate();  // error
    }
}

---

package com.nevercaution.demo.item15;

import com.nevercaution.demo.item15.first.First;

public class MainClass {

    public static void main(String[] args) {
        First first = new First();

        first.callPublic();
        first.callPackagePrivate(); // error
        first.callProtected();  // error
        first.callPrivate(); // error
    }
}

```

#### 클래스의 접근성을 좁히지 못하는 제약
- 하위 클래스에서 상위 클래스의 메서드를 재정의할 때는 접근 수준을 상위 클래스보다 좁힐 수 없다. 
- 이유는 상위 클래스의 인스턴스는 하위 클래스의 인스턴스로 대체해 사용할 수 있어야 하는 제약 때문  

```java
public class Second {
    void callMethod() {
        System.out.println("Second.callMethod");
    }
}

public class Second2 extends Second {
    private void callMethod() {
        System.out.println("Second2.callMethod override");
    }
}

> compile error
error: callMethod() in Second2 cannot override callMethod() in Second
attempting to assign weaker access privileges; was package
```

#### public 클래스의 인스턴스는 되도록 public 이 아니어야 한다.
- 값을 제한할 수 없게 되고 일반적으로 thread-safe 하지 않는다.
- 특정 값을 표현하고자 할 때는 public static final 로 묶어주자. (다만 list 나 변경가능한 변수를 사용하면 안된다.)  

```
// 이러면 안된다. 
public static final Thing[] VALUES = {...};
```

- 이를 해결하기 위해서는 불변 객체를 private static final 로 선언하고 public static final 에서 불변 객체를 반환하는 방법과, clone 하는 방법이 있다.  

```
private static final Thing[] PRIVATE_VALUES = {...};
publis static final List<Thing> VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));

public static final Thing[] values() {
  return PRIVATE_VALUES.clone();
}
```

