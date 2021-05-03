package org.ageadoc.gtd.hibernate;

import javax.persistence.GenerationType;

public class EntityPrimary extends EntityField{
    private Boolean isPrimary = true;
    private GenerationType strategyType;
    private String sequenceName;

    public EntityPrimary(ColumnType columnType, String name, String columnName, GenerationType strategyType, String sequenceName){
        super(columnType, name, columnName, false);
        this.isPrimary = true;
        this.strategyType = strategyType;
        this.sequenceName = sequenceName;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
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
