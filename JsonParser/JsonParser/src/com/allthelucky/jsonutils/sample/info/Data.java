package com.allthelucky.jsonutils.sample.info;
import java.io.Serializable;

public class Data implements Serializable {

	private static final long serialVersionUID = -2155648856259566537L;
	public Integer length;
	public Integer width;
	public String shape;

	@Override
	public String toString() {
		return "Data [length=" + length + ", width=" + width + ", shape=" + shape + "]";
	}

}
