package org.zerock.web_workbook_b01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.web_workbook_b01.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
