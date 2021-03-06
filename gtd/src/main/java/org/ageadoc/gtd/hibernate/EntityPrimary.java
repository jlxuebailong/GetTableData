package org.ageadoc.gtd.hibernate;

import javax.persistence.GenerationType;

public class EntityPrimary extends EntityField{
    private final Boolean isPrimary = Boolean.TRUE;
    private GenerationType strategyType;
    private String sequenceName;

    public EntityPrimary(ColumnType columnType, String name, String columnName, GenerationType strategyType, String sequenceName){
        super(columnType, name, columnName, false);
        this.strategyType = strategyType;
        this.sequenceName = sequenceName;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public GenerationType getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(GenerationType strategyType) {
        this.strategyType = strategyType;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }
}
