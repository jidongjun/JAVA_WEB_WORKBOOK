package org.zerock.web_workbook_b01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.web_workbook_b01.dto.BoardDto;
import org.zerock.web_workbook_b01.dto.PageRequestDto;
import org.zerock.web_workbook_b01.dto.PageResponseDto;
import org.zerock.web_workbook_b01.service.BoardService;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDto pageRequestDto, Model model) {
        PageResponseDto<BoardDto> responseDto = boardService.list(pageRequestDto);
        log.info(responseDto);
        model.addAttribute("responseDto", responseDto);
    }
}
