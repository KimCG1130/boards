package com.example.board.Controller;

import com.example.board.Dto.BoardDto;
import com.example.board.repository.BoardRepository;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardRestController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @GetMapping("/api/boards")
    public List<BoardDto> getLists() {
        return boardService.getList();
    }


    @GetMapping("/api/boards/{id}")
    public BoardDto getOne(@PathVariable Integer id) {

        return boardService.getOne(id);
    }
//만약 뒤에 넘버형식이 아닌 파라메터의 값과 이름을 함께 전  달하는 방식으로 사용할경우 RequestParam쓰기
//pahtvariable 같은 경우  Rest api 에서 값을 호출 할 때 주로쓰인다고한다.


    @PostMapping("/api/boards")
    public BoardDto createBoard(@RequestBody BoardDto requestDto) {

        return boardService.create(requestDto);
    }
}

