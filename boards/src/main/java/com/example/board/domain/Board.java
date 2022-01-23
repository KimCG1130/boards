package com.example.board.domain;


import lombok.*;

import javax.persistence.*;


//객체와 테이블 매핑 : @Entity, @Table
//
//기본 키 매핑 : @Id
//
//필드와 컬럼 매핑 : @Column
//
//연관관계 매핑 : @ManyToOne, @JoinColumn


@Entity//@Entity가 붙은 클래스는 JPA가 관리하는 것으로, 엔티티라고 불림, 테이블과의 매핑
@AllArgsConstructor(access = AccessLevel.PROTECTED)//모든 필드 값을 파라미터로 받는 생성자를 만듦,lombok
@NoArgsConstructor(access = AccessLevel.PROTECTED)//파라미터가 없는 기본 생성자를 생성,lombok
@Getter//자동으로 getter 생성( 유사제품 setter있음)


public class Board {

    @Id//기본기매핑,아래꺼 쓰지않으면 직접할당, 아래꺼쓰면 자동으로 기본키 매핑. identitiy는 db에 권한위임
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="board_id")//필드와 컬럼매핑, 즉 컬럼상 board_id와 id라는 변수가 짝을 이룬다
    private Integer id;

    @Column(nullable = false, columnDefinition = "varchar(20)")
    private String title;

    @Column(name="user_name", nullable = false, columnDefinition = "varchar(12)")
    private String username;

    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String content;

    @Builder//얘도 롬복,빌드 패턴을 사용하여 객체를 생성할 수 있으며 객체 생성 후 setter를 통한 접근을 제한 할수 있어서 불변형 객체를 만들 수 있다.>
    // 표현 방법을 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 나타냄>객체들마다 들어가야할 인자가 각각 다를 때 유연하게 사용
    public Board(String title, String username, String content){
        this.title = title;
        this.username = username;
        this.content = content;
    }

}

//JPA란
//Java Persistence API:orm기술을 표준으로 사용하는 인터페이스 모음,JPA는 반복적인 CRUD SQL을 처리, 간단히 말하자면 애플리케이션과 JDBC 사이에서 동작 징검다리 역할(명령문을 내리면 쿼리문으로 바꿔서 쏨),
// 대표 jpa: 하이버 네이트
// ORM(Object-Relational Mapping):Class와 RDB(Relational DataBase)의 테이블을 매핑(연결)한다는 뜻이며, 기술적으로는 어플리케이션의 객체를 RDB 테이블에 자동으로 영속화 해주는 것
//1. 생산성
//        JPA를 사용하면 자바 컬렉션에 저장하듯이 JPA에게 저장할 객체를 전달하면 된다.
//
//        지루하고 반복적인 코드를 개발자가 직접 작성하지 않아도 되며, DDL문도 자동으로 생성해주기 때문에 데이터베이스 설계 중심을 객체 설계 중심으로 변경할 수 있다.
//
//        2. 유지보수
//        필드를 하나만 추가해도 관련된 SQL과 JDBC 코드를 전부 수행해야 했지만 JPA는 이를 대신 처리해주기 때문에 개발자가 유지보수해야하는 코드가 줄어든다.
//
//        3. 패러다임의 불일치 해결
//        JPA는 연관된 객체를 사용하는 시점에 SQL을 전달할 수 있고, 같은 트랜잭션 내에서 조회할 때 동일성도 보장하기 때문에 다양한 패러다임의 불일치를 해결한다.
//
//        4. 성능
//        애플리케이션과 데이터베이스 사이에서 성능 최적화 기회를 제공한다.
//
//        같은 트랜잭션안에서는 같은 엔티티를 반환하기 때문에 데이터 베이스와의 통신 횟수를 줄일 수 있다. 또한, 트랜잭션을 commit하기 전까지 메모리에 쌓고 한번에 SQL을 전송한다.
//
//        5. 데이터 접근 추상화와 벤더 독립성
//        RDB는 같은 기능이라도 벤더마다 사용법이 다르기 때문에 처음 선택한 데이터베이스에 종속되고 변경이 어렵다. JPA는 애플리케이션과 데이터베이스 사이에서 추상화된 데이터 접근을 제공하기 때문에 종속이 되지 않도록한다.
//
//        만약 DB가 변경되더라도 JPA에게 알려주면 간단하게 변경이 가능하다.
//
//
