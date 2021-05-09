package model;

public class Courier_dt {
	private int id;
	private String name;
	private int telno;
	private String company;
	private String vehical;
	private String email;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTelno() {
		return telno;
	}
	public void setTelno(int telno) {
		this.telno = telno;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getVehical() {
		return vehical;
	}
	public void setVehical(String vehical) {
		this.vehical = vehical;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ResearcherDt [id=" + id + ", name=" + name + ", Tel=" + telno + ", Company=" + company + ", vehical="
				+ vehical + ", email=" + email + "]";
	}

}
