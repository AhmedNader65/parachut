
package com.mrerror.parachut.Models.DetailsOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Delivery implements Serializable {

    private final static long serialVersionUID = 8813985491406299170L;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("national_id")
    @Expose
    private Object nationalId;
    @SerializedName("machine_number")
    @Expose
    private Object machineNumber;
    @SerializedName("license_number")
    @Expose
    private Object licenseNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getNationalId() {
        return nationalId;
    }

    public void setNationalId(Object nationalId) {
        this.nationalId = nationalId;
    }

    public Object getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(Object machineNumber) {
        this.machineNumber = machineNumber;
    }

    public Object getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(Object licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

}
