package com.ctrip.framework.apollo.portal.spi.defaultimpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.ctrip.framework.apollo.portal.component.config.PortalConfig;
import com.ctrip.framework.apollo.portal.entity.bo.Email;
import com.ctrip.framework.apollo.portal.entity.bo.emai.EmailKeyValue;
import com.ctrip.framework.apollo.portal.spi.EmailService;
import com.ctrip.framework.apollo.portal.util.EmailUtils;
import com.ctrip.framework.apollo.portal.util.fegin.FeignUtils;
import com.ctrip.framework.apollo.portal.util.fegin.api.EmailApi;

public class EHIEmailService implements EmailService {
	@Autowired
	private PortalConfig portalConfig;

	@Override
	public void send(Email email) {
		String emailBodyBuilder = "<entry><content><![CDATA[<!DOCTYPE html>" + email.getBody()
				+ "]]></content></entry>";
		ArrayList<EmailKeyValue> list = new ArrayList<>();
		list.add(new EmailKeyValue("Title", email.getSubject()));
		list.add(new EmailKeyValue("Content", emailBodyBuilder));
		FeignUtils.getHttpApi(EmailApi.class, portalConfig.emailUrl()).sendEmail(EmailUtils.generateEmail(300044, "", list));
	}
}
