package org.zerock.web_workbook_b01.service;

import org.zerock.web_workbook_b01.dto.BoardDto;
import org.zerock.web_workbook_b01.dto.PageRequestDto;
import org.zerock.web_workbook_b01.dto.PageResponseDto;

public interface BoardService {
    Long register(BoardDto boardDto);

    BoardDto readOne(Long bno);

    void modify(BoardDto boardDto);

    void remove(Long bno);

    PageResponseDto<BoardDto> list(PageRequestDto pageRequestDto);
}
