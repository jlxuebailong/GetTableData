package org.ageadoc.gtd.hibernate.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "new_employees")
public class NewEmployee implements Serializable {

    private Integer idNum;
    private String fname;
    private String minit;
    private String nnn;
    private String lname;
    private Integer empNum;
    private Set<Address> addresses = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_num", nullable = false)
    public Integer getIdNum() {
        return this.idNum;
    }

    public void setIdNum(Integer idNum) {
        this.idNum = idNum;
    }

    @Column(name = "fname", nullable = true)
    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    @Column(name = "minit", nullable = true, columnDefinition = "char")
    public String getMinit() {
        return this.minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    @Column(name = "nnn", nullable = true, columnDefinition = "char")
    public String getNnn() {
        return this.nnn;
    }

    public void setNnn(String nnn) {
        this.nnn = nnn;
    }

    @Column(name = "lname", nullable = false, length = 100)
    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @Column(name = "emp_num", nullable = false, length = 100)
    public Integer getEmpNum() {
        return this.empNum;
    }

    public void setEmpNum(Integer empNum) {
        this.empNum = empNum;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}