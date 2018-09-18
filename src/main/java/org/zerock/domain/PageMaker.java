package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

import static java.lang.Math.ceil;

@Getter
@ToString
public class PageMaker {
    private int total;
    private PageDTO pageDTO;
    private int start,end;
    private boolean prev,next;


    public PageMaker(int total, PageDTO pageDTO) {
        this.total = total;
        this.pageDTO = pageDTO;

        int tempEnd = (int) ceil(pageDTO.getPage() /10.0)*10;
        this.start = tempEnd-9;

        int realEnd=(int)Math.ceil(total/(double)pageDTO.getSize());
        this.end = realEnd < tempEnd?realEnd:tempEnd;

        this.prev = this.start != 1;
        this.next = this.end * pageDTO.getSize() < total;


    }
}
