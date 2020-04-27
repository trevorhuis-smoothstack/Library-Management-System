package com.ss.lms.models;

import java.io.Serializable;

public class Borrower implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer cardNo;
    private String name;
    private String address;
    private String phone;

    public Borrower(Integer cardNo, String name, String address, String phone) {
        this.cardNo = cardNo;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Borrower() {
	}

	public Borrower(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
	}

	public Integer getCardNo() {
        return cardNo;
    }

    public void setCardNo(Integer cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((cardNo == null) ? 0 : cardNo.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Borrower other = (Borrower) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (cardNo == null) {
            if (other.cardNo != null)
                return false;
        } else if (!cardNo.equals(other.cardNo))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Borrower [address=" + address + ", cardNo=" + cardNo + ", name=" + name + ", phone=" + phone + "]";
    }
}