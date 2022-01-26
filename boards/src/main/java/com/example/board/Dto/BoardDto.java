package com.example.board.Dto;


import com.example.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.xml.ws.BindingType;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
        private Integer id;
        private String title;
        private String username;
        private String content;
        private LocalDateTime createdAt;

        public Board toEntity() {

                return Board.builder()
                        .id(this.id)
                        .content(this.content)
                        .username(this.username)
                        .title(this.title)
                        .createdAt(this.createdAt)
                        .build();
        }

}



