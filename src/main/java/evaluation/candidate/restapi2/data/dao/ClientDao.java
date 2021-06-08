/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluation.candidate.restapi2.data.dao;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Raymond
 */
@Entity
@Table(schema = "assessment_schema", name = "client")
@JsonPropertyOrder("id, firstname, lastname, mobilenumber, idnumber, physicaladdress")
public class ClientDao implements Serializable {

    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = true)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "firstname")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "lastname")
    private String lastName;
    @Size(max = 45)
    @Column(name = "mobilenumber")
    private String mobileNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "idnumber")
    private String idnumber;
    @Size(max = 255)
    @Column(name = "physicaladdress")
    private String physicalAddress;

    public ClientDao() {
    }

    public ClientDao(Integer id) {
        this.id = id;
    }

    public ClientDao(String firstName, String lastName, String mobileNumber, String idnumber, String physicalAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.idnumber = idnumber;
        this.physicalAddress = physicalAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getIDNumber() {
        return idnumber;
    }

    public void setIDNumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientDao)) {
            return false;
        }
        ClientDao other = (ClientDao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evaluation.candidate.jpa.Client[ id=" + id + ", "
                + "firstname = "+ firstName +", lastname = " + lastName + ", "
                + "mobilenumber = " + mobileNumber + ", idnumber = " + idnumber + ", "
                + "physicaladdress = " + physicalAddress + " ]";
    }
    
}
