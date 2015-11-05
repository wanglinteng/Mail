package com.ng.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {
	/**
	 * @see 以文本格式发送邮件
	 * @param mailInfo
	 *            待发邮件信息
	 * @return Boolean (true-成功 false-失败)
	 * @since 2015-11-04
	 */
	public static boolean sendTextMail(MailContent mailInfo) {
		// 判断是否需要身份认证
		SmtpAuthor authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new SmtpAuthor(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者昵称+地址
			String nick = null;
			try {
				// 防止中文乱码
				nick = javax.mail.internet.MimeUtility.encodeText(mailInfo.getNick());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			Address from = new InternetAddress(nick + "<" + mailInfo.getFromAddress() + ">");
			// 设置邮件消息的发送者信息
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 邮件正文
			if (mailInfo.getContent() != null) {
				// 创建一个包含HTML内容的MimeBodyPart
				MimeBodyPart body = new MimeBodyPart();
				body.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
				mainPart.addBodyPart(body);
			}
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * @see 以HTML格式发送邮件
	 * @param mailInfo
	 *            待发邮件信息
	 * @return Boolean (true-成功 false-失败)
	 * @since 2015-11-04
	 */
	public static boolean sendHtmlMail(MailContent mailInfo) {
		// 判断是否需要身份认证
		SmtpAuthor authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new SmtpAuthor(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者昵称+地址
			String nick = null;
			try {
				// 防止中文乱码
				nick = javax.mail.internet.MimeUtility.encodeText(mailInfo.getNick());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			Address from = new InternetAddress(nick + "<" + mailInfo.getFromAddress() + ">");
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();

			// 邮件正文
			if (mailInfo.getContent() != null) {
				// 创建一个包含HTML内容的MimeBodyPart
				MimeBodyPart body = new MimeBodyPart();
				body.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
				mainPart.addBodyPart(body);
			}

			// 含有附件
			String[] attachFileNames = mailInfo.getAttachFileNames();
			if (attachFileNames != null && attachFileNames.length > 0) {
				for (String fileName : attachFileNames) {
					MimeBodyPart attach = new MimeBodyPart();
					// 获得数据源
					FileDataSource fds = new FileDataSource(fileName);
					// 得到附件本身、文件名并至入BodyPart
					attach.setDataHandler(new DataHandler(fds));
					attach.setFileName(fds.getName());
					mainPart.addBodyPart(attach);
				}
			}

			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}