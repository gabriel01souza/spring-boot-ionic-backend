package com.course.coursemc.services;

import com.course.coursemc.domain.PaymentComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void preencherPaymentComBoleto(PaymentComBoleto paymentComBoleto, Date instancePedido){
        Calendar cal = Calendar.getInstance();
        cal.setTime(instancePedido);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        paymentComBoleto.setDataVencimento(cal.getTime());
    }
}
