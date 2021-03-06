package org.ageadoc.gtd.hibernate;

public class EntityField {
    private ColumnType columnType;
    private String fieldName;
    private String columnName;
    private Integer length;
    private Boolean nullable = true;

    public EntityField(ColumnType columnType, String fieldName, String columnName, Boolean nullable){
        this.columnType = columnType;
        this.fieldName = fieldName;
        this.columnName = columnName;
        this.nullable = nullable;
    }

    public EntityField(ColumnType columnType, Integer len, String fieldName, String columnName, Boolean nullable){
        this.columnType = columnType;
        this.length = len;
        this.fieldName = fieldName;
        this.columnName = columnName;
        this.nullable = nullable;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }

    public String getFieldType() {
        String fieldType = "String";
        switch (columnType.ordinal()){
            case 0:
            case 1:
            case 2:
                break;
            case 3:
                fieldType = "Integer";
                break;
            case 4:
                fieldType = "Long";
                break;
            default:
                fieldType = columnType.name();
        }
        return fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
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

    @Override
    public String toString() {
        return "EntityField{" +
                "columnType=" + columnType +
                ", fieldName='" + fieldName + '\'' +
                ", fieldType='" + getFieldType() + '\'' +
                ", columnName='" + columnName + '\'' +
                ", length=" + length +
                ", nullable=" + nullable +
                '}';
    }
}
