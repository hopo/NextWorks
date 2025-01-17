package kr.or.nextit.member.service;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MemberVo implements Serializable {

	private String memId;
	private String memPwd;
	private String memName;
	private String memPhone;
	private String loginDate;
	private String privilCode;
	private String delAt;

	private boolean result;
	private String message;

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPwd() {
		return memPwd;
	}

	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getPrivilCode() {
		return privilCode;
	}

	public void setPrivilCode(String privilCode) {
		this.privilCode = privilCode;
	}

	public String getDelAt() {
		return delAt;
	}

	public void setDelAt(String delAt) {
		this.delAt = delAt;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// TODO : org.apache.commons.lang3.builder.ToStringBuilder;
	/*
	@Override
	public String toString() {
		return ToStringBuilder.refelctionToString(this);
	}
	*/

}
