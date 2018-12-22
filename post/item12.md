
## item12
### toString을 항상 재정의하라
===

- 대부분의 class 는 Object 를 상속받아 사용하는 경우가 많음  
- 로그나 System message 로 class 를 찍었을 땐 보통 Object::toString 을 수행함.  
- Integer, String 등은 변수의 값 그대로 찍히지만 사용자가 만든 class 는 모든 값을 찍어주지 않음.  


```java
@Override
public String toString() {
	return super.toString();
}

public String toString() {
	return getClass().getName() + "@" + Integer.toHexString(hashCode());
}
```

toString 을 재정의하지 않으면 다음과 같이 출력된다.  

```java
People people = new People();
people.setName("name");
people.setAge(1);

System.out.println("people = " + people);

>> people = com.nevercaution.demo.item12.People@7852e922
```

주석이나 로그에서 해당 객체를 그대로 넣는 경우엔 내가 원하는 값을 볼 수가 없다. 물론 변수 모두를 객체에서 꺼내서 로그에 써주는 방법이 있지만 코드가 길어지고, 추가되는 변수들에 대한 값을 표시해주기가 어렵다. 로그를 보면서 작업할 때 편의성을 위해 되도록 toString 을 재정의해주는것이 좋다.   

```java 
@Override
public String toString() {
	return "[People Class] name : " + name + ", age : " + age;
}

>> people = [People Class] name : name, age : 1
```

lombok 을 사용하는 경우에 `@Data` annotation 을 걸어주면 toString 에서 필요한 값들을 출력해준다.  

```java

@Data
public class People {

    private String name;
    private Integer age;
}

>> people = People(name=name, age=1)
```

- Entity 나 data 성 class 는 lombok 의 `@Data` 를 사용하거나 그러지 못할 경우엔 toString 을 재정의 해주자.  
