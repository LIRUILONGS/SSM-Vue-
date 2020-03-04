package com.liruilong.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;

public class LogType {
    private Integer id;

    private String logtypemsg;

    @JsonFormat(pattern = "yyyy-MM-dd ", timezone ="GMT+8" )
    private Date adddate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogtypemsg() {
        return logtypemsg;
    }

    public void setLogtypemsg(String logtypemsg) {
        this.logtypemsg = logtypemsg == null ? null : logtypemsg.trim();
    }

    public Date getAdddate() {
        return adddate;
    }
    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    public LogType(String logtypemsg) {
        this.logtypemsg = logtypemsg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()){ return false;}
        LogType logType = (LogType) o;
        return Objects.equals(logtypemsg, logType.logtypemsg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logtypemsg);
    }
    @Override
    public String toString() {
        return "LogType{" +
                "id=" + id +
                ", logtypemsg='" + logtypemsg + '\'' +
                '}';
    }
}