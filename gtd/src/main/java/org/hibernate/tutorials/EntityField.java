package org.hibernate.tutorials;

public class EntityField {
    private String type;
    private String name;
    private String columnName;
    private Integer length;
    private Boolean nullable = true;

    public EntityField(String type, String name, String columnName, Boolean nullable){
        this.type = type;
        this.name = name;
        this.columnName = columnName;
        this.nullable = nullable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }
}
