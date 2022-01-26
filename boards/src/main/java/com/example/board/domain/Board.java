package com.example.board.domain;


import com.example.board.Dto.BoardDto;
import com.example.board.repository.BoardRepository;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


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


public class Board extends Timestamped {

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

//    @Column(name="created_at")
//    private LocalDateTime createdAt;

    @Builder//얘도 롬복,빌드 패턴을 사용하여 객체를 생성할 수 있으며 객체 생성 후 setter를 통한 접근을 제한 할수 있어서 불변형 객체를 만들 수 있다.>
    // 표현 방법을 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 나타냄>객체들마다 들어가야할 인자가 각각 다를 때 유연하게 사용
    //public Board(String title, String username, String content){
    public Board(String title, String content, String username, LocalDateTime createdAt, Integer id){
  //      this.id = id;
        this.title = title;
        this.username = username;
        this.content = content;
  //      this.createdAt = createdAt;
    }

    public BoardDto toDto() { //보드를 디티오로 바꿔서 반환해주는 메서드(자세한건 서비스에 기술되어있음)*
        return BoardDto.builder()
                .id(this.id)
                .content(this.content)
                .title(this.title)
                .username(this.username)
                .createdAt(this.getCreatedAt())
                .build();
    }



}
