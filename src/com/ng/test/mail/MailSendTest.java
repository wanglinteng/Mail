package com.ng.test.mail;

import com.ng.mail.MailContent;
import com.ng.mail.MailSender;

public class MailSendTest {

	private static String fromMail = "testmailpublic@163.com";
	private static String fromMailPasswd = "zhengwei";
	private static String toMail = "924214972@qq.com";
	private static String mailServerHost = "smtp.163.com";
	private static String mailServerPort = "25";
	private static String nick1 = "大能国际集团（沈阳运营中心）";
	private static String nick2 = "大能国际集团（东京开发团队）";
	private static String nick3 = "大能国际集团（亚太研究中心）";
	private static String nick4 = "大能国际集团（未来研究中心）";

	public static void main(String[] args) {
		sendText();
		sendHtmlAttach();
		sendHtml();
		sendAttach();
	}

	// 发送html+附件
	private static Boolean sendHtmlAttach() {
		MailContent mailInfo = new MailContent();
		mailInfo.setMailServerHost(mailServerHost);
		mailInfo.setMailServerPort(mailServerPort);
		mailInfo.setValidate(true);
		mailInfo.setUserName(fromMail);
		mailInfo.setPassword(fromMailPasswd);
		mailInfo.setFromAddress(fromMail);
		mailInfo.setToAddress(toMail);
		mailInfo.setNick(nick1);
		mailInfo.setSubject("HTML+附件类信息");
		// html内容
		String htmlContent = 
		"<table style='border:solid 1px #add9c0;'>"
		+ "<tr>"
				+ "<th>标题</th>" + "<th>时间</th>"+ "<th>内容</th>"
		+ "</tr>"
		+ "<tr>"
			+ "<td>1111</td>" + "<td>1111</td>" + "<td>1111</td>"
		+ "</tr>"
		+ "</table>";
		mailInfo.setContent(htmlContent);
		// 附件
		mailInfo.setAttachFileNames(new String[] { "F:\\welcome.txt" });
		Boolean result = MailSender.sendHtmlMail(mailInfo);
		return result;
	}
	//发送html
	private static Boolean sendHtml(){
		MailContent mailInfo = new MailContent();
		mailInfo.setMailServerHost(mailServerHost);
		mailInfo.setMailServerPort(mailServerPort);
		mailInfo.setValidate(true);
		mailInfo.setUserName(fromMail);
		mailInfo.setPassword(fromMailPasswd);
		mailInfo.setFromAddress(fromMail);
		mailInfo.setToAddress(toMail);
		mailInfo.setNick(nick2);
		mailInfo.setSubject("网页类信息");
		// html内容
		String htmlContent = 
		"<table style='border:solid 1px #add9c0;'>"
		+ "<tr>"
				+ "<th>aaaa</th>" + "<th>bbbb</th>"+ "<th>cccc</th>"
		+ "</tr>"
		+ "<tr>"
			+ "<td>1111</td>" + "<td>2222</td>" + "<td>3333</td>"
		+ "</tr>"
		+ "</table>";
		mailInfo.setContent(htmlContent);
		Boolean result = MailSender.sendHtmlMail(mailInfo);
		return result;
	}
	//发送附件
	private static Boolean sendAttach(){
		MailContent mailInfo = new MailContent();
		mailInfo.setMailServerHost(mailServerHost);
		mailInfo.setMailServerPort(mailServerPort);
		mailInfo.setValidate(true);
		mailInfo.setUserName(fromMail);
		mailInfo.setPassword(fromMailPasswd);
		mailInfo.setFromAddress(fromMail);
		mailInfo.setToAddress(toMail);
		mailInfo.setNick(nick3);
		mailInfo.setSubject("附件类信息");
		// 附件
		mailInfo.setAttachFileNames(new String[] { "F:\\welcome.txt" });
		Boolean result = MailSender.sendHtmlMail(mailInfo);
		return result;
	}
	//发送文本信息
	private static Boolean sendText(){
		MailContent mailInfo = new MailContent();
		mailInfo.setMailServerHost(mailServerHost);
		mailInfo.setMailServerPort(mailServerPort);
		mailInfo.setValidate(true);
		mailInfo.setUserName(fromMail);
		mailInfo.setPassword(fromMailPasswd);
		mailInfo.setFromAddress(fromMail);
		mailInfo.setToAddress(toMail);
		mailInfo.setNick(nick4);
		mailInfo.setSubject("文本类信息");
		mailInfo.setContent("本次验证码：【1597986】，非本人操作，请忽略！");
		Boolean result = MailSender.sendTextMail(mailInfo);
		return result;
	}


}
