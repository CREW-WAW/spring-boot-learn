# Spring Boot ==> Clean Architecture

## **E**nvironment

* JPA
* QueryDSL
* Lombok
* Swagger

## Gradle

```bash
[build.gradle]
buildscript {
    ext {
        queryDslVersion = "5.0.0"
    }
}

...
...

dependencies {
    ...
    ...
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'

    implementation "com.querydsl:querydsl-jpa:${queryDslVersion}"
    implementation "com.querydsl:querydsl-apt:${queryDslVersion}"

    implementation 'org.springdoc:springdoc-openapi-ui:1.6.14'
    ...
    ...
}

// querydsl 적용
def querydslDir = "${rootProject.buildDir}/generated/qclass"

querydsl {
    jpa = true
    querydslSourcesDir = querydslDir
}

sourceSets {
    main.java.srcDir querydslDir
}

configurations {
    querydsl.extendsFrom compileClasspath
}

compileQuerydsl {
    options.annotationProcessorPath = configurations.querydsl
}

```

## API Docs

- [Swagger UI](http://localhost:8085/swagger-ui/index.html)