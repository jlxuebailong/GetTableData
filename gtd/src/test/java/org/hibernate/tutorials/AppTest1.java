package org.hibernate.tutorials;

import static org.junit.Assert.assertTrue;

import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest1 extends TestCase{
    private SessionFactory sessionFactory;

    @Override
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @Override
    protected void tearDown() throws Exception {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    @SuppressWarnings({ "unchecked" })
    public void testCreate() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save( new Department( "TPP","", "4F" ) );
        session.getTransaction().commit();
        session.close();
    }



    @SuppressWarnings({ "unchecked" })
    public void testQuery1() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from Department where deptId=10" ).list();
        for ( Department dept : (List<Department>) result ) {
            System.out.println( "Department (" + dept.getDeptId() + ") : " + dept.getName()  + "," + dept.getEmployees().size());
            for(Employee emp : dept.getEmployees()){
                System.out.println("Employee ("+emp.getEmpName()+", "+emp.getEmpNo() +")");
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings({ "unchecked" })
    public void testUpdate() {
         Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from Department where deptId=42" ).list();
        for ( Department dept : (List<Department>) result ) {
            System.out.println( "Department (" + dept.getDeptId() + ") : " + dept.getName() );
            dept.setDeptNo("D42");
            session.saveOrUpdate(dept);
        }
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings({ "unchecked" })
    public void testDel() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from Department where deptId=45" ).list();
        for ( Department dept : (List<Department>) result ) {
            System.out.println( "Department (" + dept.getDeptId() + ") : " + dept.getName() );
            session.delete(dept);
        }
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings({ "unchecked" })
    public void testQuery2() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String sql = "Select e from " + Employee.class.getName() + " e "
                + " order by e.empName, e.empNo ";

        // Create Query object.
        Query<Employee> query = session.createQuery(sql);

        // Execute query.
        List<Employee> employees = query.getResultList();

        for (Employee emp : employees) {
            System.out.println("Emp: " + emp.getEmpNo() + " : "
                    + emp.getEmpName());
        }

        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings({ "unchecked" })
    public void testQuery3() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String sql = "Select e from " + Employee.class.getName() + " e "
                + " where e.department.deptNo=:deptNo order by e.empName, e.empNo ";

        // Create Query object.
        Query<Employee> query = session.createQuery(sql);

        query.setParameter("deptNo", "D10");
        // Execute query.
        List<Employee> employees = query.getResultList();

        for (Employee emp : employees) {
            System.out.println("Emp: " + emp.getEmpNo() + " : "
                    + emp.getEmpName());
        }

        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings({ "unchecked" })
    public void testQuery4() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String sql = "Select e.empId, e.empNo, e.empName from "
                + Employee.class.getName() + " e ";

        // Create Query object.
        Query<Object[]>  query = session.createQuery(sql);

        // Execute query.
        List<Object[]> datas = query.getResultList();

        for (Object[] emp : datas) {
            System.out.println("Emp Id: " + emp[0]);
            System.out.println("    Emp No: " + emp[1]);
            System.out.println("    Emp Name: " + emp[2]);
        }



        session.getTransaction().commit();
        session.close();
    }
}
