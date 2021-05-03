package org.hibernate.tutorials.entity;

import javax.persistence.*;

@Entity
@Table(name = "new_employees", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_num" }) } )
public class NewEmployeeEntity {

    private Integer idNum;
    private String fname;
    private String minit;
    private String nnn;
    private String lname;

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



}