package com.wzh.workstation.dto;

import com.wzh.workstation.entity.WorkProject;

import java.util.List;

public class WorkProjectRspDTO {
    private long total;

    private List<WorkProject> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<WorkProject> getRows() {
        return rows;
    }

    public void setRows(List<WorkProject> rows) {
        this.rows = rows;
    }
}
