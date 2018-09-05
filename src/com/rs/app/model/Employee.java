package com.rs.app.model;

import java.io.Serializable;

public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	private int empno;
	private String name;

	public Employee() {
	}

	public Employee(int empno, String name) {
		super();
		this.empno = empno;
		this.name = name;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", name=" + name + "]";
	}
}
