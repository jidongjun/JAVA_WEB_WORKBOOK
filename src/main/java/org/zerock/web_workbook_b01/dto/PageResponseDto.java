package org.zerock.web_workbook_b01.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageResponseDto<E> {
    private int page;
    private int size;
    private int total;

    // 시작페이지번호
    private int start;
    // 끝페이지번호
    private int end;

    // 이전페이지의 존재여부
    private boolean prev;
    // 다음페이지의 존재여부
    private boolean next;

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDto(PageRequestDto pageRequestDto, List<E> dtoList, int total){
        if(total <= 0){
            return;
        }

        this.page = pageRequestDto.getPage();
        this.size = pageRequestDto.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end = (int)(Math.ceil(this.page / 10.0)) * 10;     // 화면에서의 마지막 번호

        this.start = this.end - 9;                              // 화면에서의 시작번호

        int last = (int)(Math.ceil((total/(double)size)));      // 데이터의 개수를 계산한 마지막 페이지 번호

        this.end = Math.min(end, last);

        this.prev = this.start > 1;

        this.next = total > this.end * this.size;
    }

}
