package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Tu
 * Date: 1/8/13
 * Time: 3:42 PM
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "ValidateVerificationCodeRequest")
public class ValidateVerificationCodeRequest {
	@XmlElement(required = true)
	private String uid;
	@XmlElement(required = true)
	private String verificationCode;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
}
