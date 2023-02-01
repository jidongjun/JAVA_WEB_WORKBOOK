package org.zerock.web_workbook_b01.repository;

import java.util.stream.IntStream;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.web_workbook_b01.domain.Board;
import org.zerock.web_workbook_b01.repository.BoardRepository;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Board board = Board.builder()
                .title("title...." + i)
                .content("content...." + i)
                .writer("user " + (i % 10))
                .build();

            Board result = boardRepository.save(board);
            log.info("BNO : " + result.getBno());
        });
    }
}
