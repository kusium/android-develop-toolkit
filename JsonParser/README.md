AndroidJsonParser
===========================

A parser can transform JSONObject to Java bean, available for Android or common Java project which uses [JSONObject](https://github.com/douglascrockford/JSON-java).

#####input jsonStr:
<pre>
[
    {
        "name": "hello",
        "age": 1,
        "address": {
            "address": "number 1",
            "postcode": 434000
        },
        "datas": [
            {
                "width": 480,
                "length": 800,
                "shape": "retangle sape"
            },
            {
                "width": 540,
                "length": 960,
                "shape": "cycle sape"
            }
        ]
    }
]
</pre>

#####output: 
<pre>[Info [name=hello, age=1, address=Address [address=number 1, postcode=434000], datas=[Data [length=800, width=480, shape=retangle sape], Data [length=960, width=540, shape=cycle sape]]]]
</pre>

#####Java bean for jsonStr:
<pre>
public class Info implements Serializable{
	private static final long serialVersionUID = -7128975465639889745L;
	public 	String name;
	public Integer age;
	public Address address;
	public List<Data> datas;
	
	public Info() {
		super();
	}

	@Override
	public String toString() {
		return "Info [name=" + name + ", age=" + age + ", address=" + address + ", datas=" + datas + "]";
	}
}
</pre>

<pre>
public class Address implements Serializable {
	private static final long serialVersionUID = -5953347429635753009L;
	public String address;
	public Integer postcode;

	public Address() {
		super();
	}

	@Override
	public String toString() {
		return "Address [address=" + address + ", postcode=" + postcode + "]";
	}
}
</pre>

<pre>
public class Data implements Serializable {
	private static final long serialVersionUID = -2155648856259566537L;
	public Integer length;
	public Integer width;
	public String shape;
	
	public Data() {
		super();
	}
	
	@Override
	public String toString() {
		return "Data [length=" + length + ", width=" + width + ", shape=" + shape + "]";
	}
}
</pre>

