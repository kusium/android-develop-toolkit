package com.allthelucky.jsonutils.sample.info;
import java.io.Serializable;
import java.util.List;


public class Info implements Serializable{

	private static final long serialVersionUID = -7128975465639889745L;
	public 	String name;
	public Integer age;
	public Address address;
	public List<Data> datas;
	
	public Info() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Data> getDates() {
		return datas;
	}

	public void setDates(List<Data> datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "Info [name=" + name + ", age=" + age + ", address=" + address + ", datas=" + datas + "]";
	}

}
