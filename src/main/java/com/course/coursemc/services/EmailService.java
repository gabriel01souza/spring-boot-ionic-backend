package com.course.coursemc.services;

import com.course.coursemc.domain.Client;
import com.course.coursemc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(Client client, String newPass);
}
