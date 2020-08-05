package com.osn.locadora.services;

import org.springframework.mail.SimpleMailMessage;

import com.osn.locadora.domain.Cliente;
import com.osn.locadora.domain.Reserva;

public interface EmailService {

	void sendOrderConfirmationEmail(Reserva obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendEmailMsgClient(Cliente obj, String msg);
	
}
