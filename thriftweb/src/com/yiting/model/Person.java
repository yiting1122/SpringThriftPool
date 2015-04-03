package com.yiting.model;

/**
 * Created by Administrator on 2015/4/3.
 */
public class Person {
	private int age;
	private String id;

	public Person(int age, String id) {
		this.age = age;
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
