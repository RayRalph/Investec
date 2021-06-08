/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluation.candidate.restapi2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import evaluation.candidate.restapi2.data.dao.ClientDao;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Raymond
 */
@ApiModel(description = "Client represents a single human being (a man, woman or child).")

public class ClientCreate implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("mobilenumber")
    private String mobileNumber;
    @JsonProperty("idnumber")
    private String idnumber;
    @JsonProperty("physicaladdress")
    private String physicalAddress;

    public ClientCreate() {
    }

    public ClientCreate(String firstName, String lastName, String mobileNumber, String idnumber, String physicalAddress) {
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

    public static ClientCreate mapEntityToModel(ClientDao client) {
        ClientCreate dto = new ClientCreate();
        dto.setId(client.getId());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setMobileNumber(client.getMobileNumber());
        dto.setIDNumber(client.getIDNumber());
        dto.setPhysicalAddress(client.getPhysicalAddress());

        return dto;
    }

    public static ClientDao mapModelToEntity(ClientCreate client) {
        ClientDao dao = new ClientDao();
        dao.setId(client.getId());
        dao.setFirstName(client.getFirstName());
        dao.setLastName(client.getLastName());
        dao.setMobileNumber(client.getMobileNumber());
        dao.setIDNumber(client.getIDNumber());
        dao.setPhysicalAddress(client.getPhysicalAddress());
        System.out.println(dao.toString());
        return dao;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClientCreate client = (ClientCreate) o;
        return Objects.equals(this.id, client.id)
                && Objects.equals(this.firstName, client.firstName)
                && Objects.equals(this.lastName, client.lastName)
                && Objects.equals(this.mobileNumber, client.mobileNumber)
                && Objects.equals(this.idnumber, client.idnumber)
                && Objects.equals(this.physicalAddress, client.physicalAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, mobileNumber, idnumber, physicalAddress);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class client {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    firstname: ").append(toIndentedString(firstName)).append("\n");
        sb.append("    lastname: ").append(toIndentedString(lastName)).append("\n");
        sb.append("    mobilenumber: ").append(toIndentedString(mobileNumber)).append("\n");
        sb.append("    idnumber: ").append(toIndentedString(idnumber)).append("\n");
        sb.append("    physicaladdress: ").append(toIndentedString(physicalAddress)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
