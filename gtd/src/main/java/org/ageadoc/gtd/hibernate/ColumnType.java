package org.ageadoc.gtd.hibernate;

public enum ColumnType {
    CHAR,
    NVARCHAR,
    VARCHAR,
    INT,
    BIGINT,
    IMAGE;

    private ColumnType() {
    }

    public static ColumnType convert(String type){
        ColumnType ct = ColumnType.VARCHAR;
        if(type == null) {
            return ct;
        }
        type = type.trim().toUpperCase();

        if(type.equals("CHAR")){
            ct = ColumnType.CHAR;
        }else if(type.equals("NVARCHAR")){
            ct = ColumnType.NVARCHAR;
        }else if(type.equals("INT")){
            ct = ColumnType.INT;
        }else if(type.equals("BIGINT")){
            ct = ColumnType.BIGINT;
        }else if(type.equals("IMAGE")){
            ct = ColumnType.IMAGE;
        }
        return ct;
    }
}
