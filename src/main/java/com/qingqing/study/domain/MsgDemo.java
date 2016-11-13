package com.qingqing.study.domain;

/**
 * Created by xuya on 2016/11/13.
 */
public class MsgDemo {

    private Long sequenceNo;

    private String text;

    public Long getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Long sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return "sequenceNo:" + sequenceNo + ", text:" + text;
    }
}
