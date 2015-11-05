package com.ng.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @see 邮箱账号和密码
 * @author 王麟腾
 * @since 2015-11-04
 */
public class SmtpAuthor extends Authenticator {
	private String userName;
	private String password;

	public SmtpAuthor() {
	}

	public SmtpAuthor(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}
