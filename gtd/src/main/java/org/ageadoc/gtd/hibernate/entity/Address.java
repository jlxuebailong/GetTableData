package org.ageadoc.gtd.hibernate.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
public class Address {

    private Integer addrId;
    private String address;
    private NewEmployee employee;

    @Id
    @SequenceGenerator(name = "sequence-generator",  sequenceName = "SEQ_ADDRESS", allocationSize = 1)
    @GeneratedValue(generator = "sequence-generator", strategy=GenerationType.SEQUENCE)
    @Column(name = "ADDRID", nullable = false)
    public Integer getAddrId() {
        return this.addrId;
    }

    public void setAddrId(Integer addrId) {
        this.addrId = addrId;
    }

    @Column(name = "ADDRESS", nullable = true, length = 250, columnDefinition = "nvarchar")
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMP_ID", nullable = false, referencedColumnName="EMP_NUM")
    public NewEmployee getEmployee(){
        return  this.employee;
    }

    public void setEmployee(NewEmployee employee){
        this.employee = employee;
    }
}