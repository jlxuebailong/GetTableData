package org.ageadoc.gtd.hibernate;

import static org.junit.Assert.assertTrue;

import junit.framework.TestCase;
import org.ageadoc.gtd.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.ageadoc.gtd.hibernate.bean.ShortEmpInfo;


import java.io.Serializable;
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
        Query<Department> query = session.createQuery( "from Department where deptNo=:deptNo" );
        query.setParameter("deptNo", "XX-D10");
        Department dept = query.getSingleResult();

        if (dept != null) {
            System.out.println( "Department (" + dept.getDeptId() + ") : " + dept.getName() );
            session.delete(dept);
        }
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

    @SuppressWarnings({ "unchecked" })
    public void testQuery5() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String sql = "Select new "+ ShortEmpInfo.class.getName()
                +"(e.empId, e.empNo, e.empName)" +
                " from " + Employee.class.getName() + " e ";

        Query<ShortEmpInfo> query = session.createQuery(sql);

        List<ShortEmpInfo> employees = query.getResultList();

        for (ShortEmpInfo emp : employees) {
            System.out.println("Emp: " + emp.getEmpNo() + " : "
                    + emp.getEmpName());
        }

        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings({ "unchecked" })
    public void testQuery6() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String sql = "Select max(e.empId) from " + Employee.class.getName() + " e ";
        Query<Number> query = session.createQuery(sql);
        Number value = query.getSingleResult();
        System.out.println(value);
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings({ "unchecked" })
    public void testPersist() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query<Department> query = session.createQuery( "from Department where deptNo=:deptNo" );
        query.setParameter("deptNo", "D10");
        Department department = query.getSingleResult();
        System.out.println(department);

        if(department != null) {
            Employee emp = new Employee();
            emp.setEmpName("Name New");
            emp.setEmpNo("E-NEW");
            emp.setJob("Coder");
            emp.setSalary(1000f);
            emp.setManager(null);
            emp.setHideDate(new Date());
            emp.setDepartment(department);

            session.persist(emp);
        }

        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings({ "unchecked" })
    public void testFlush() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query<Employee> query = session.createQuery( "from Employee where empNo=:empNo" );
        query.setParameter("empNo", "E-NEW");
        Employee employee = query.getSingleResult();
        System.out.println(employee);

        employee.setJob("Developer");
        System.out.println(employee.getJob());
        session.flush();

        employee.setJob("Manager");
        System.out.println(employee.getJob());

        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings({ "unchecked" })
    public void testTransientObject() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query<Employee> query = session.createQuery( "from Employee where empNo=:empNo" );
        query.setParameter("empNo", "E-NEW");
        Employee emp = query.getSingleResult();
        Timekeeper tk1 = new Timekeeper();

        tk1.setEmployee(emp);
        tk1.setInOut(Timekeeper.IN);
        tk1.setDateTime(new Date());

        /*System.out.println(session.contains(tk1));
        session.persist(tk1);
        System.out.println("- tk1.getTimekeeperId() = " + tk1.getTimekeeperId());

        System.out.println(session.contains(tk1));
        //主動刷新到數據庫
        session.flush();
        System.out.println("- tk1.getTimekeeperId() = " + tk1.getTimekeeperId());
        */

        // save() very similar to persist()
        // save() return ID, persist() return void.
        // Hibernate assign ID value to 'tk2', no action with DB
        // And return ID of 'tk2'.
        Serializable id = session.save(tk1);
        System.out.println("- id = " + id);
        session.flush();

        String timekeeperId = tk1.getTimekeeperId();
        System.out.println("- timekeeperId = " + timekeeperId);

        session.getTransaction().commit();
        session.close();
    }

    public void testNewEmployeeEntity1() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        NewEmployeeEntity entity = new NewEmployeeEntity();
        entity.setFname("yyds");
        entity.setLname("yyds");
        entity.setMinit("F");
        entity.setNnn("yyds");
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void testNewEmployeeEntity2() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from NewEmployeeEntity" ).list();
        for ( NewEmployeeEntity entity : (List<NewEmployeeEntity>) result ) {
            System.out.println( entity.getIdNum()+", Fname (" + entity.getFname() + ") : " + entity.getLname()  + "," + entity.getMinit());
        }
        session.getTransaction().commit();
        session.close();
    }

    public void testAddressEntity1() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Address address = new Address();
        address.setAddress("哥哥在客厅打游戏");
        session.persist(address);
        session.getTransaction().commit();
        session.close();
    }
    public void testAddressEntity2() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from Address" ).list();
        for ( Address entity : (List<Address>) result ) {
            System.out.println( entity.getAddrId()+"," + entity.getAddress());
        }
        session.getTransaction().commit();
        session.close();
    }
}
