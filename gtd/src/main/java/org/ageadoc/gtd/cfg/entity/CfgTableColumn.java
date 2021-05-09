package org.ageadoc.gtd.cfg.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "CFG_TABLECOL")
public class CfgTableColumn {

    private Long id;
    private CfgTable table;
    private String columnName;
    private Boolean isPrimary;
    private String columnType;
    private Integer columnLength;
    private String defaultValue;
    private String columnDesc;

    private String genStrategy;
    private String seqName;
    private Boolean isNullable;

    private String classFieldName;
    private String classFileType;


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
    @Type(type="yes_no")
    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    @Column(name = "COL_NULLABLE", nullable = true, length = 1, columnDefinition = "char")
    @Type(type="yes_no")
    public Boolean getNullable() {
        return isNullable;
    }

    public void setNullable(Boolean nullable) {
        isNullable = nullable;
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

    @Column(name = "COL_GENSTRA", nullable = true, columnDefinition = "char")
    public String getGenStrategy() {
        return genStrategy;
    }

    public void setGenStrategy(String genStrategy) {
        this.genStrategy = genStrategy;
    }

    @Column(name = "COL_SEQNAME", nullable = true)
    public String getSeqName() {
        return seqName;
    }

    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }

    @Column(name = "CLS_FIELDNAME", nullable = true)
    public String getClassFieldName() {
        return classFieldName;
    }

    public void setClassFieldName(String classFieldName) {
        this.classFieldName = classFieldName;
    }

    @Column(name = "CLS_TYPE", nullable = true)
    public String getClassFileType() {
        return classFileType;
    }

    public void setClassFileType(String classFileType) {
        this.classFileType = classFileType;
    }
}