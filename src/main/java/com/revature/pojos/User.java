package com.revature.pojos;

import java.util.Objects;

public class User {
    private Integer  USER_ID;
    private String  FIRSTNAME;
    private String  LASTNAME;
    private String  USERNAME;
    private String  PASSWORD;
	private static boolean admin;


	public User(Integer USER_ID, String FIRSTNAME, String LASTNAME, String USERNAME,String PASSWORD, boolean admin) {
		this.USER_ID = USER_ID;
		this.FIRSTNAME = FIRSTNAME;
		this.LASTNAME = LASTNAME;
		this.USERNAME= USERNAME;
		this.PASSWORD = PASSWORD;
         this.admin  =  admin;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
    public static void setUser(int id, boolean b) {
    }

	public Integer getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(Integer uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getFIRSTNAME() {
		return FIRSTNAME;
	}
	public void setFIRSTNAME(String fFIRSTNAME) {

		this.FIRSTNAME = fFIRSTNAME;
	}
	public String getLASTNAME() {

		return LASTNAME;
	}
	public void setLASTNAME(String lLASTNAME) {

		LASTNAME = lLASTNAME;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uUSERNAME) {

		USERNAME = uUSERNAME;
	}
	public String getPASSWORD() {

		return PASSWORD;
	}
	public void setPASSWORD(String pPASSWORD) {

		PASSWORD = pPASSWORD;
	}
	public static boolean isAdmin() {
		return admin;
	}

	public static void setAdmin(boolean admin) {
		User.admin = admin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(FIRSTNAME, LASTNAME, PASSWORD, USERNAME, USER_ID);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(FIRSTNAME, other.FIRSTNAME) && Objects.equals(LASTNAME, other.LASTNAME)
				&& Objects.equals(PASSWORD, other.PASSWORD) && Objects.equals(USERNAME, other.USERNAME)
				&& Objects.equals(USER_ID, other.USER_ID);
	}
	@Override
	public String toString() {
		return "User [USER_ID=" + USER_ID + ", FIRSTNAME=" + FIRSTNAME + ", LASTNAME=" + LASTNAME + ", USERNAME="
				+ USERNAME + ", PASSWORD=" + PASSWORD + "]";
	}


	public boolean login(String password) {
		return false;
	}

}
