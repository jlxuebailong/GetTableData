package org.ageadoc.gtd.cfg.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CFG_TABLE")
public class CfgTable {

    private Long id;
    private String tableName;
    private String tableDesc;
    private String packageName;
    private String className;

    private Set<CfgTableColumn> cfgTableColumns = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TBL_ID", nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "TBL_NAME", nullable = true)
    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Column(name = "TBL_DESC", nullable = true)
    public String getTableDesc() {
        return this.tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }

    @Column(name = "PKG_NAME", nullable = true)
    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Column(name = "CLS_NAME", nullable = true)
    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "table")
    public Set<CfgTableColumn> getCfgTableColumns() {
        return cfgTableColumns;
    }

    public void setCfgTableColumns(Set<CfgTableColumn> cfgTableColumns) {
        this.cfgTableColumns = cfgTableColumns;
    }
}