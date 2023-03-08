package org.zerock.web_workbook_b01.service;

import static org.junit.jupiter.api.Assertions.*;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.web_workbook_b01.dto.BoardDto;
import org.zerock.web_workbook_b01.dto.PageRequestDto;
import org.zerock.web_workbook_b01.dto.PageResponseDto;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {
        log.info(boardService.getClass().getName());

        BoardDto boardDto = BoardDto.builder().title("Sample Title....")
            .content("Sample Content...")
            .writer("user00")
            .build();

        Long bno = boardService.register(boardDto);
        log.info("bno : " + bno);
    }

    @Test
    public void testModify() {
        BoardDto boardDto = BoardDto.builder()
            .bno(101L)
            .title("update Title 101....")
            .content("Update Content 101...")
            .build();

        boardService.modify(boardDto);
    }

    @Test
    public void testList(){
        PageRequestDto pageRequestDto = PageRequestDto.builder()
            .type("tcw")
            .keyword("1")
            .page(1)
            .size(10)
            .build();

        PageResponseDto<BoardDto> responseDto = boardService.list(pageRequestDto);
        log.info(responseDto);
    }
}