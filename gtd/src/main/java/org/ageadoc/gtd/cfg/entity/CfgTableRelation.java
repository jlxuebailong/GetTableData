package org.ageadoc.gtd.cfg.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CFG_RELATION")
public class CfgTableRelation {

    private Long id;
    private CfgTable tableLeft;
    private CfgTable tableRight;
    private String relationDesc;
    private String typeLeft;
    private String typeRight;
    private String fetchType;

    private Set<CfgTableRelJoin> tableRelJoins = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REL_ID", nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "REL_LEFT", nullable = true)
    public CfgTable getTableLeft() {
        return this.tableLeft;
    }

    public void setTableLeft(CfgTable tableLeft) {
        this.tableLeft = tableLeft;
    }

    @ManyToOne
    @JoinColumn(name = "REL_RIGHT", nullable = true)
    public CfgTable getTableRight() {
        return this.tableRight;
    }

    public void setTableRight(CfgTable tableRight) {
        this.tableRight = tableRight;
    }

    @Column(name = "REL_DESC", nullable = true)
    public String getRelationDesc() {
        return this.relationDesc;
    }

    public void setRelationDesc(String relationDesc) {
        this.relationDesc = relationDesc;
    }

    @Column(name = "TYPE_LEFT", nullable = true, length = 1, columnDefinition = "char")
    public String getTypeLeft() {
        return this.typeLeft;
    }

    public void setTypeLeft(String typeLeft) {
        this.typeLeft = typeLeft;
    }

    @Column(name = "TYPE_RIGHT", nullable = true, length = 1, columnDefinition = "char")
    public String getTypeRight() {
        return this.typeRight;
    }

    public void setTypeRight(String typeRight) {
        this.typeRight = typeRight;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tableRelation")
    public Set<CfgTableRelJoin> getTableRelJoins() {
        return tableRelJoins;
    }

    @Column(name = "REL_FETCHTYPE", nullable = true, length = 1, columnDefinition = "char")
    public String getFetchType() {
        return fetchType;
    }

    public void setFetchType(String fetchType) {
        this.fetchType = fetchType;
    }

    public void setTableRelJoins(Set<CfgTableRelJoin> tableRelJoins) {
        this.tableRelJoins = tableRelJoins;
    }
}