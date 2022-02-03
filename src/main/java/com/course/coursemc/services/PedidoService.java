package com.course.coursemc.services;

import com.course.coursemc.domain.Client;
import com.course.coursemc.domain.ItemPedido;
import com.course.coursemc.domain.PaymentComBoleto;
import com.course.coursemc.domain.Pedido;
import com.course.coursemc.domain.enums.EstadoPayment;
import com.course.coursemc.repositories.ItemPedidoRepository;
import com.course.coursemc.repositories.PaymentRepository;
import com.course.coursemc.repositories.PedidoRepository;
import com.course.coursemc.security.UserSS;
import com.course.coursemc.services.exceptions.AuthorizationException;
import com.course.coursemc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ItemPedidoRepository  itemPedidoRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private EmailService emailService;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: "
                + id + ", tipo: " + Pedido.class.getName()));
    }

    public Pedido insert(Pedido obj){
        obj.setId(null);
        obj.setInstante(new Date());
        obj.setClient(clientService.find(obj.getClient().getId()));
        obj.getPayment().setEstado(EstadoPayment.PENDENTE);
        obj.getPayment().setPedido(obj);
        if(obj.getPayment() instanceof PaymentComBoleto){
            PaymentComBoleto paymentComBoleto = (PaymentComBoleto) obj.getPayment();
            boletoService.preencherPaymentComBoleto(paymentComBoleto,obj.getInstante());
        }
        obj = repo.save(obj);
        paymentRepository.save(obj.getPayment());
        for(ItemPedido ip : obj.getItens()){
            ip.setDesconto(0.0);
            ip.setProduct(productService.find(ip.getProduct().getId()));
            ip.setPrice(ip.getProduct().getPrice());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        emailService.sendOrderConfirmationEmail(obj);
        System.out.println(obj);
        return obj;
    }

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        UserSS user = UserService.authenticated();
        if(user == null){
            throw new AuthorizationException("Acesso negado");
        }
        PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Client client = clientService.find(user.getId());
        return repo.findByClient(client, pageRequest);
    }
}
