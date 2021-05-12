package org.ageadoc.gtd.cfg.gen;

import org.ageadoc.gtd.cfg.entity.CfgTable;
import org.ageadoc.gtd.cfg.entity.CfgTableColumn;
import org.ageadoc.gtd.hibernate.ColumnType;
import org.ageadoc.gtd.hibernate.EntityField;
import org.ageadoc.gtd.hibernate.entity.Department;
import org.ageadoc.gtd.hibernate.entity.Employee;
import org.ageadoc.gtd.hibernate.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenEntity {

    private Config conf = null;
    private SessionFactory sessionFactory;

    public GenEntity(){
        this.conf = new Config();
        this.sessionFactory = SessionFactoryUtil.getSessionFactory();
    }

    private void execute(){
        Session session = this.sessionFactory.openSession();
        //session.beginTransaction();
        List result = session.createQuery( "from CfgTable" ).list();
        for ( CfgTable tbl : (List<CfgTable>) result ) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("PackageName", tbl.getPackageName());
            map.put("TableName",tbl.getTableName());
            map.put("ClassName", tbl.getClassName());

            List<EntityField> fieldList = new ArrayList<>();
            System.out.println( "CfgTable (" + tbl.getTableName() + ") : " + tbl.getClassName()  + "," + tbl.getCfgTableColumns().size());
            for(CfgTableColumn col : tbl.getCfgTableColumns()){
                System.out.println("CfgTableColumn ("+col.getColumnName()+", "+col.getColumnLength() +")");
                String colType = col.getColumnType();
                String clsFieldName = col.getClassFieldName();
                String columnName = col.getColumnName();
                Boolean nullable = col.getNullable();
                Integer colLen = col.getColumnLength();
                EntityField field = new EntityField(ColumnType.convert(colType), clsFieldName, columnName, nullable);
                field.setLength(colLen);
                fieldList.add(field);
            }
            map.put("fieldList", fieldList);
            System.out.println(map);
        }
        //session.getTransaction().commit();
        session.close();

    }

    public static void main(String[] args) {
        GenEntity gen = new GenEntity();
        gen.execute();
    }

}
