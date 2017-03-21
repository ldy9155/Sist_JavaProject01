package Management;

import java.io.Serializable;

public class MemberBean implements Serializable {
	String jTextID, jTextArea, jTextName, jTextPhone, jTextBirth, jTextEmail;

	public MemberBean() {

	}

	public MemberBean(String jTextID, String jTextArea, String jTextName, String jTextPhone, String jTextBirth,
			String jTextEmail) {
		this.jTextID = jTextID;
		this.jTextArea = jTextArea;
		this.jTextName = jTextName;
		this.jTextPhone = jTextPhone;
		this.jTextBirth = jTextBirth;
		this.jTextEmail = jTextEmail;
	}
	public String getjTextID() {
		return jTextID;
	}

	public void setjTextID(String jTextID) {
		this.jTextID = jTextID;
	}

	public String getjTextArea() {
		return jTextArea;
	}

	public void setjTextArea(String jTextArea) {
		this.jTextArea = jTextArea;
	}

	public String getjTextName() {
		return jTextName;
	}

	public void setjTextName(String jTextName) {
		this.jTextName = jTextName;
	}

	public String getjTextPhone() {
		return jTextPhone;
	}

	public void setjTextPhone(String jTextPhone) {
		this.jTextPhone = jTextPhone;
	}

	public String getjTextBirth() {
		return jTextBirth;
	}

	public void setjTextBirth(String jTextBirth) {
		this.jTextBirth = jTextBirth;
	}

	public String getjTextEmail() {
		return jTextEmail;
	}

	public void setjTextEmail(String jTextEmail) {
		this.jTextEmail = jTextEmail;
	}

}
