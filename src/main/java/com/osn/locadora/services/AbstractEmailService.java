package com.osn.locadora.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.osn.locadora.domain.Cliente;
import com.osn.locadora.domain.Reserva;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(Reserva obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromReserva(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromReserva(Reserva obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Confirmação Reserva/Alteração");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void sendEmailMsgClient(Cliente obj, String msg) {
		SimpleMailMessage sm = prepareSimpleMailMessageToClient(obj, msg);
		sendEmail(sm);
		
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageToClient(Cliente obj, String msg) {
			SimpleMailMessage sm = new SimpleMailMessage();
			sm.setTo(obj.getEmail());
			sm.setFrom(sender);
			sm.setSubject("Menssagem Das Casinhas Brisas do Farol");
			sm.setSentDate(new Date(System.currentTimeMillis()));
			sm.setText(msg);
			return sm;
	}

}
