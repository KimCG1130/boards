package com.example.board.service;

import com.example.board.Dto.BoardDto;
import com.example.board.domain.Board;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;// 초기값이 저장되면 최종적인 값이 되어 프로그램 실행 도중에 수정을 할 수 없다


    @Transactional//데이터베이스의 상태를 변경하는 작업 또는 한번에 수행되어야 하는 연산
    public BoardDto create(BoardDto dto){
        Board createdBoard = dto.toEntity();

        return boardRepository.save(createdBoard).toDto();
    }



    public List<BoardDto> getList(){

        return boardRepository.findAllByOrderByCreatedAtDesc().stream().map(board -> board.toDto())//board를 dto로 변환 stream.map:list
                .collect(Collectors.toList());
//        return boardRepository.findAll().stream().map(board -> board.toDto())//board를 dto로 변환 stream.map:list
//                .collect(Collectors.toList());
    }

    public BoardDto getOne(Integer id){

        return boardRepository.findById(id).get().toDto();
    }



}
