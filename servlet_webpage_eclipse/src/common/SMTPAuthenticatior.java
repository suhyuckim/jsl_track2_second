package common;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticatior extends Authenticator{
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		// naver 메일 사용할경우 : naver id, pw
		return new PasswordAuthentication("obull123","aimtop3165@!");
	}
}
