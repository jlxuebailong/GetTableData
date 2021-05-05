package org.ageadoc.gtd.cfg.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "CFG_TABLECOL")
public class CfgTableColumn {

    private Long id;
    private CfgTable table;
    private String columnName;
    private String isPrimary;
    private String columnType;
    private Integer columnLength;
    private String defaultValue;
    private String columnDesc;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COL_ID", nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TBL_ID", nullable = true)
    public CfgTable getTable() {
        return this.table;
    }

    public void setTable(CfgTable table) {
        this.table = table;
    }

    @Column(name = "COL_NAME", nullable = true)
    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Column(name = "COL_PRIMARY", nullable = true, length = 1, columnDefinition = "char")
    public String getIsPrimary() {
        return this.isPrimary;
    }

    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary;
    }

    @Column(name = "COL_TYPE", nullable = true)
    public String getColumnType() {
        return this.columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    @Column(name = "COL_LEN", nullable = true)
    public Integer getColumnLength() {
        return this.columnLength;
    }

    public void setColumnLength(Integer columnLength) {
        this.columnLength = columnLength;
    }

    @Column(name = "COL_DEFVAL", nullable = true)
    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Column(name = "COL_DESC", nullable = true)
    public String getColumnDesc() {
        return this.columnDesc;
    }

    public void setColumnDesc(String columnDesc) {
        this.columnDesc = columnDesc;
    }



}