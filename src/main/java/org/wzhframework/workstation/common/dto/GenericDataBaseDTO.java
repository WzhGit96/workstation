package org.wzhframework.workstation.common.dto;

/**
 * @author wzh
 * @since 2023/2/24
 */
public class GenericDataBaseDTO extends GenericDTO{
    private String sql;

    private String parameter;

    private int total;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "GenericDataBaseDTO{" +
                "sql='" + sql + '\'' +
                ", parameter='" + parameter + '\'' +
                ", total=" + total +
                '}';
    }
}
