package org.zerock.web_workbook_b01.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zerock.web_workbook_b01.domain.Board;
import org.zerock.web_workbook_b01.dto.BoardDto;
import org.zerock.web_workbook_b01.dto.PageRequestDto;
import org.zerock.web_workbook_b01.dto.PageResponseDto;
import org.zerock.web_workbook_b01.repository.BoardRepository;

@Service
@Log4j2
@RequiredArgsConstructor
// 스프링은 해당 객체를 감싸는 별도의 클래스를 생성해 내는데 간혹 여러번의 데이터베이스
// 연결이 있을 수도 있음
@Transactional
public class BoardServiceImpl implements BoardService {

    private final ModelMapper modelMapper;

    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDto boardDto) {
        Board board = modelMapper.map(boardDto, Board.class);
        Long bno = boardRepository.save(board).getBno();
        return bno;
    }

    @Override
    public BoardDto readOne(Long bno) {
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        BoardDto boardDto = modelMapper.map(board, BoardDto.class);

        return boardDto;
    }

    @Override
    public void modify(BoardDto boardDto) {
        Optional<Board> result = boardRepository.findById(boardDto.getBno());
        Board board = result.orElseThrow();
        board.change(boardDto.getTitle(), boardDto.getContent());
        boardRepository.save(board);
    }

    @Override
    public void remove(Long bno) {
        boardRepository.deleteById(bno);
    }

    @Override
    public PageResponseDto<BoardDto> list(PageRequestDto pageRequestDto) {
        String[] types = pageRequestDto.getTypes();
        String keyword = pageRequestDto.getKeyword();
        Pageable pageable = pageRequestDto.getPageable("bno");

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);
        List<BoardDto> dtoList = result.getContent().stream()
            .map(board -> modelMapper.map(board, BoardDto.class)).toList();

        return PageResponseDto.<BoardDto>withAll()
            .pageRequestDto(pageRequestDto)
            .dtoList(dtoList)
            .total((int)result.getTotalElements())
            .build();
    }
}
