package com.allthelucky.jsonutils.sample.info;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = -5953347429635753009L;
	public String address;
	public Integer postcode;

	public Address() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPostcode() {
		return postcode;
	}

	@Override
	public String toString() {
		return "Address [address=" + address + ", postcode=" + postcode + "]";
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
}
