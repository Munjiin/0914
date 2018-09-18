package org.zerock.domain;


import lombok.Data;

import java.util.Date;

@Data
public class BoardVO {
    private Integer bno;
    private String title, content,writer;
    private Date regdate, updatedate;

    private int viewcnt;

}
