# Spring Boot, Gradle, YAML 설정이 Java 코드에 미치는 영향

이 문서는 Spring Boot 프로젝트에서 Gradle, `application.yaml` 설정이 실제 Java 코드와 어떻게 상호작용하는지 설명합니다.

## 전체적인 흐름

1.  **Gradle (`build.gradle.kts` 또는 `build.gradle`)**: 프로젝트에 필요한 라이브러리(의존성)를 정의하고 다운로드합니다.
2.  **Spring Boot**: Gradle이 다운로드한 라이브러리를 기반으로 애플리케이션을 **자동으로 설정**합니다.
3.  **`application.yaml`**: Spring Boot의 자동 설정을 **사용자 정의**하여 세부 동작을 변경합니다.
4.  **Java 코드**: 위에서 설정된 환경 위에서 실제 비즈니스 로직을 수행합니다.

---

## 1. Gradle: 의존성 관리자

-   `build.gradle.kts` 파일의 `dependencies` 블록에 우리가 사용할 기능들을 선언합니다. (예: `spring-boot-starter-web`)
-   **역할**:
    -   `spring-boot-starter-web` 같은 '스타터' 의존성을 선언하면, Spring Boot는 웹 애플리케이션에 필요한 수많은 라이브러리(Tomcat 서버, Spring MVC 등)를 **자동으로 가져옵니다.**
    -   즉, 개발자는 복잡한 라이브러리 조합을 신경 쓸 필요 없이, "웹 개발이 필요해" 라는 선언만 하면 됩니다.
-   **Java 코드에 미치는 영향**:
    -   Gradle이 가져온 라이브러리의 클래스들(예: `@RestController`, `@GetMapping`)을 Java 코드에서 사용할 수 있게 됩니다.
    -   이 라이브러리들이 클래스패스(classpath)에 존재해야 Spring Boot의 자동 설정이 동작합니다.

## 2. Spring Boot: 자동 설정 (Auto-configuration)

-   **시작점**: 메인 클래스의 `@SpringBootApplication` 어노테이션.
-   **역할**:
    -   Gradle이 가져온 라이브러리들을 감지하고, 그에 맞춰 필요한 Bean(객체)들을 **자동으로 생성하고 설정**합니다.
    -   예를 들어, `spring-boot-starter-web`이 있으면 내장 Tomcat 서버를 띄우고, 웹 요청을 처리할 준비를 합니다.
    -   `spring-boot-starter-data-jpa`와 H2 데이터베이스 라이브러리가 있으면, 별도 설정 없이도 메모리 기반의 데이터베이스 연결을 자동으로 설정해 줍니다.
-   **Java 코드에 미치는 영향**:
    -   개발자가 `@Autowired`를 사용하여 Spring Boot가 자동으로 생성해준 Bean(예: `JdbcTemplate`, `EntityManager`)을 Java 코드에 주입받아 바로 사용할 수 있습니다.
    -   복잡한 설정 클래스를 직접 만들지 않아도 애플리케이션의 핵심 기능들이 동작합니다.

## 3. `application.yaml`: 세부 설정 변경

-   **경로**: `src/main/resources/application.yaml`
-   **역할**:
    -   Spring Boot 자동 설정의 **기본값을 덮어쓰고** 원하는 값으로 변경합니다.
    -   예를 들어, 자동 설정된 내장 서버의 포트를 8080에서 9000으로 변경하거나(`server.port: 9000`), 연결할 데이터베이스의 주소와 계정 정보를 지정할 수 있습니다.
-   **Java 코드에 미치는 영향**:
    -   `application.yaml`에 정의된 값들은 Spring 환경의 프로퍼티가 됩니다.
    -   Java 코드에서는 `@Value("${property.name}")` 어노테이션을 사용해 특정 프로퍼티 값을 직접 가져와 사용하거나, `@ConfigurationProperties`를 이용해 여러 프로퍼티를 객체로 묶어서 사용할 수 있습니다.
    -   이를 통해 코드 변경 및 재배포 없이, 설정 파일만 수정하여 애플리케이션의 동작을 유연하게 변경할 수 있습니다.

## 요약

-   **Gradle**이 재료(라이브러리)를 준비합니다.
-   **Spring Boot**는 준비된 재료를 보고 자동으로 요리(애플리케이션 설정)를 합니다.
-   **`application.yaml`**은 요리의 레시피(설정)를 입맛에 맞게 살짝 바꿉니다.
-   **Java 코드**는 완성된 요리를 가져다 멋진 플레이팅(비즈니스 로직)을 합니다.
