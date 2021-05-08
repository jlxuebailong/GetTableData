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
        for ( CfgTable cfg : (List<CfgTable>) result ) {
            System.out.println( cfg.getClassName() );
            for(CfgTableColumn col : cfg.getCfgTableColumns()){
               System.out.println("\t" + col.getColumnName() );
               if(col.getColumnName().equals("ColA")){
                    System.out.println("\t-------"+col.getColumnType());
                    col.setPrimary(true);
                    col.setGenStrategy("2");
                    col.setNullable(false);
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
        Query query = session.createQuery( "from CfgTableColumn where columnName=:columnName" );
        query.setParameter("columnName", "ColA");
        Object object = query.getSingleResult();
        CfgTableColumn columnLeft = null;
        if(object != null){
            columnLeft = (CfgTableColumn)object;
        }

        query = session.createQuery( "from CfgTableColumn where columnName=:columnName" );
        query.setParameter("columnName", "ColB");
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
        session.save(cfg);
        session.getTransaction().commit();
        session.close();
    }
}
