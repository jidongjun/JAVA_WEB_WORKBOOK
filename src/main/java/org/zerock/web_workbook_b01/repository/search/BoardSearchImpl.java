package org.zerock.web_workbook_b01.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.web_workbook_b01.domain.Board;
import org.zerock.web_workbook_b01.domain.QBoard;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board = QBoard.board;                // Q도메인 객체
        JPQLQuery<Board> query = from(board);       // select.. from board
        query.where(board.title.contains("1"));     // where title like

        // paging
        this.getQuerydsl().applyPagination(pageable, query);
        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);       // select ... from board

        // 검색조건과 키워드가 있다면
        if ((types != null && types.length > 0) && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();               // querydsl을 이용할 때 '()'가 필요할 시       
            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));       // title like...
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));     // content like...
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));      // writer like...
                        break;
                }
            } // end for
            query.where(booleanBuilder);
        } // end if

        // bno > 0 조건
        query.where(board.bno.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);
        
        List<Board> list = query.fetch();
        
        long count = query.fetchCount();
        return new PageImpl<>(list, pageable, count);
    }
}
