package org.ageadoc.gtd.hibernate;

public class EntityField {
    private ColumnType columnType;
    private String name;
    private String columnName;
    private Integer length;
    private Boolean nullable = true;

    public EntityField(ColumnType columnType, String name, String columnName, Boolean nullable){
        this.columnType = columnType;
        this.name = name;
        this.columnName = columnName;
        this.nullable = nullable;
    }

    public EntityField(ColumnType columnType, Integer len, String name, String columnName, Boolean nullable){
        this.columnType = columnType;
        this.length = len;
        this.name = name;
        this.columnName = columnName;
        this.nullable = nullable;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }

    public String getType() {
        String type = "String";
        switch (columnType.ordinal()){
            case 0:
            case 1:
            case 2:
                break;
            case 3:
                type = "Integer";
                break;
            case 4:
                type = "Long";
                break;
            default:
                type = columnType.name();
        }
        return type;
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
