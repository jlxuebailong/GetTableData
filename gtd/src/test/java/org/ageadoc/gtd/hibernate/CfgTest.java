package org.ageadoc.gtd.hibernate;

import junit.framework.TestCase;
import org.ageadoc.gtd.cfg.entity.CfgTable;
import org.ageadoc.gtd.cfg.entity.CfgTableColumn;
import org.ageadoc.gtd.cfg.entity.CfgTableRelJoin;
import org.ageadoc.gtd.cfg.entity.CfgTableRelation;
import org.ageadoc.gtd.hibernate.bean.ShortEmpInfo;
import org.ageadoc.gtd.hibernate.entity.*;
import org.ageadoc.gtd.hibernate.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class CfgTest extends TestCase{
    private SessionFactory sessionFactory;

    @Override
    protected void setUp() throws Exception {
        sessionFactory = SessionFactoryUtil.getSessionFactory();
    }

    @Override
    protected void tearDown() throws Exception {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    @SuppressWarnings({ "unchecked" })
    public void testCfgTableCreate() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CfgTable cfg = new CfgTable();
        cfg.setClassName("ClassB");
        cfg.setPackageName("Package.Name");
        cfg.setTableName("TableB");
        session.save(cfg);
        session.getTransaction().commit();
        session.close();
    }


    @SuppressWarnings({ "unchecked" })
    public void testCfgTableQuery() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from CfgTable" ).list();

        for ( CfgTable tbl : (List<CfgTable>) result ) {
            System.out.println( tbl.getClassName() );
            if(tbl.getId() == 1){

            }

            if(tbl.getId() == 2){
                CfgTableColumn col1 = new CfgTableColumn();
                col1.setColumnName("id");
                col1.setColumnType("bigint");
                col1.setColumnLength(null);
                col1.setPrimary(true);
                col1.setGenStrategy("2");
                col1.setNullable(false);
                col1.setClassFieldName("id");
                col1.setClassFileType("long");
                col1.setTable(tbl);
                session.saveOrUpdate(col1);

                CfgTableColumn col2 = new CfgTableColumn();
                col2.setColumnName("dept_num");
                col2.setColumnType("varchar");
                col2.setColumnLength(255);
                col2.setPrimary(false);
                col2.setGenStrategy(null);
                col2.setNullable(true);
                col2.setClassFieldName("deptNum");
                col2.setClassFileType("string");
                col2.setTable(tbl);
                session.saveOrUpdate(col2);

                CfgTableColumn col3 = new CfgTableColumn();
                col3.setColumnName("emp_name");
                col3.setColumnType("varchar");
                col3.setColumnLength(100);
                col3.setPrimary(false);
                col3.setGenStrategy(null);
                col3.setNullable(true);
                col3.setClassFieldName("empName");
                col3.setClassFileType("string");
                col3.setTable(tbl);
                session.saveOrUpdate(col3);
            }
            for(CfgTableColumn col : tbl.getCfgTableColumns()){
               System.out.println("\t" + col.getColumnName() + ", " + col.getId());
               //修改某一列的值
               if( col.getId() == 3 ){
                    System.out.println("\t-------"+col.getColumnType());
                    col.setColumnName("dept_num");
                    session.update(col);
               }
            }
        }
        session.getTransaction().commit();
        session.close();
    }


    public void testCfgTableColCreate() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CfgTableColumn cfg = new CfgTableColumn();
        cfg.setColumnName("ColB");
        cfg.setColumnLength(10);
        cfg.setColumnType("varchar");

        Query query = session.createQuery( "from CfgTable where tableName=:tableName" );
        query.setParameter("tableName", "TableA");
        Object object = query.getSingleResult();
        if(object != null){
            CfgTable table = (CfgTable)object;
            cfg.setTable(table);
        }
        session.save(cfg);
        session.getTransaction().commit();
        session.close();
    }

    public void testCfgTableColQuery() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from CfgTableColumn" ).list();
        for ( CfgTableColumn cfg : (List<CfgTableColumn>) result ) {
            System.out.println( cfg.getColumnName() + ", " + cfg.getTable().getTableName());
        }
        session.getTransaction().commit();
        session.close();
    }

    public void testCfgTableRelationCreate() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery( "from CfgTable where tableName=:tableName" );
        query.setParameter("tableName", "TableA");
        Object object = query.getSingleResult();
        CfgTable tableLeft = null;
        if(object != null){
            tableLeft = (CfgTable)object;
        }

        query = session.createQuery( "from CfgTable where tableName=:tableName" );
        query.setParameter("tableName", "TableB");
        object = query.getSingleResult();
        CfgTable tableRight = null;
        if(object != null){
            tableRight = (CfgTable)object;
        }

        CfgTableRelation cfg = new CfgTableRelation();
        cfg.setTableLeft(tableLeft);
        cfg.setTableRight(tableRight);
        cfg.setTypeLeft("1");
        cfg.setTypeRight("*");
        session.save(cfg);
        session.getTransaction().commit();
        session.close();
    }

    public void testCfgTableRelationQuery() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery( "from CfgTableRelation " );
        List<CfgTableRelation> list = query.list();
        for(CfgTableRelation rel : list){
            System.out.println(rel.getTableLeft().getTableName());
            System.out.println(rel.getTableRight().getTableName());
        }
        session.getTransaction().commit();
        session.close();
    }

    public void testCfgTableRelationJoinCreate() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery( "from CfgTableColumn where table.tableName=:tbname and columnName=:columnName" );
        query.setParameter("tbname", "TB_DEPART");
        query.setParameter("columnName", "dept_num");
        Object object = query.getSingleResult();
        CfgTableColumn columnLeft = null;
        if(object != null){
            columnLeft = (CfgTableColumn)object;
        }

        query = session.createQuery( "from CfgTableColumn where table.tableName=:tbname and columnName=:columnName" );
        query.setParameter("tbname", "TB_EMPLOYEE");
        query.setParameter("columnName", "dept_num");
        object = query.getSingleResult();
        CfgTableColumn columnRight = null;
        if(object != null){
            columnRight = (CfgTableColumn)object;
        }

        query = session.createQuery( "from CfgTableRelation where id=:id" );
        query.setParameter("id", 2L);
        object = query.getSingleResult();
        CfgTableRelation relation = null;
        if(object != null){
            relation = (CfgTableRelation)object;
        }

        CfgTableRelJoin cfg = new CfgTableRelJoin();
        cfg.setTableRelation(relation);
        cfg.setColumnLeft(columnLeft);
        cfg.setColumnRight(columnRight);
        session.saveOrUpdate(cfg);

        session.getTransaction().commit();
        session.close();
    }
}
