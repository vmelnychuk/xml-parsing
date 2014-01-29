package org.training.vamel.model;

public class Employee {
	private int id;
	private int age;
	private String name;
	private String type;
	public Employee() {
		id = 0;
		age = 0;
		name = "";
		type = "";
	}
	public Employee(int id, String name, int age, String type) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "id: " + id + " name: " + name + " age: " + age + " type: " + type ;
	}
	
}
