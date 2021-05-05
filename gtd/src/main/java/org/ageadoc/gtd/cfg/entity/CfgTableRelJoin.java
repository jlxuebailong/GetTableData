package org.ageadoc.gtd.cfg.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "CFG_RELJOIN")
public class CfgTableRelJoin {

    private Long id;
    private CfgTableRelation tableRelation;
    private CfgTableColumn columnLeft;
    private CfgTableColumn columnRight;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOIN_ID", nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "REL_ID", nullable = false)
    public CfgTableRelation getTableRelation() {
        return this.tableRelation;
    }

    public void setTableRelation(CfgTableRelation tableRelation) {
        this.tableRelation = tableRelation;
    }

    @ManyToOne
    @JoinColumn(name = "COL_LEFT", nullable = true)
    public CfgTableColumn getColumnLeft() {
        return this.columnLeft;
    }

    public void setColumnLeft(CfgTableColumn columnLeft) {
        this.columnLeft = columnLeft;
    }

    @ManyToOne
    @JoinColumn(name = "COL_RIGHT", nullable = true)
    public CfgTableColumn getColumnRight() {
        return this.columnRight;
    }

    public void setColumnRight(CfgTableColumn columnRight) {
        this.columnRight = columnRight;
    }



}