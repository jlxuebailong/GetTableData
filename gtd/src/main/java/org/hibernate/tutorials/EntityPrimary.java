package org.hibernate.tutorials;

import javax.persistence.GenerationType;

public class EntityPrimary extends EntityField{
    private Boolean isPrimary = true;
    private GenerationType strategyType;

    public EntityPrimary(ColumnType columnType, String name, String columnName, GenerationType strategyType){
        super(columnType, name, columnName, false);
        this.isPrimary = true;
        this.strategyType = strategyType;
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
}
