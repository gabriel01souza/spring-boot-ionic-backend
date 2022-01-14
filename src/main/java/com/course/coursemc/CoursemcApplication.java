package com.course.coursemc;

import com.course.coursemc.domain.Categoria;
import com.course.coursemc.domain.City;
import com.course.coursemc.domain.Client;
import com.course.coursemc.domain.Endereco;
import com.course.coursemc.domain.Payment;
import com.course.coursemc.domain.PaymentComBoleto;
import com.course.coursemc.domain.PaymentComCard;
import com.course.coursemc.domain.Pedido;
import com.course.coursemc.domain.Product;
import com.course.coursemc.domain.State;
import com.course.coursemc.domain.enums.EstadoPayment;
import com.course.coursemc.domain.enums.TipoClient;
import com.course.coursemc.repositories.CategoriaRepository;
import com.course.coursemc.repositories.CityRepository;
import com.course.coursemc.repositories.ClientRepository;
import com.course.coursemc.repositories.EnderecoRepository;
import com.course.coursemc.repositories.PaymentRepository;
import com.course.coursemc.repositories.PedidoRepository;
import com.course.coursemc.repositories.ProductRepository;
import com.course.coursemc.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CoursemcApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    public static void main(String[] args) {
        SpringApplication.run(CoursemcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria categoria1 = new Categoria(null, "Informática");
        Categoria categoria2 = new Categoria(null, "Escritório");

        Product product1 = new Product(null, "Computer", 2000.00);
        Product product2 = new Product(null, "Impressora", 800.00);
        Product product3 = new Product(null, "Mouse", 80.00);

        categoria1.getProducts().addAll(Arrays.asList(product1, product2, product3));
        categoria2.getProducts().addAll(Arrays.asList(product2));

        product1.getCategorias().addAll(Arrays.asList(categoria1));
        product2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
        product3.getCategorias().addAll(Arrays.asList(categoria1));

        State state1 = new State(null, "Minas Gerais");
        State state2 = new State(null, "São Paulo");

        categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
        productRepository.saveAll(Arrays.asList(product1, product2, product3));

        City city1 = new City(null, "Uberlândia", state1);
        City city2 = new City(null, "São Paulo", state2);
        City city3 = new City(null, "Campinas", state2);

        state1.getCities().addAll(Arrays.asList(city1));
        state2.getCities().addAll(Arrays.asList(city2, city3));

        stateRepository.saveAll(Arrays.asList(state1, state2));
        cityRepository.saveAll(Arrays.asList(city1, city2,city3));

        Client client1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoClient.PESSOAFISICA);

        client1.getTelefones().addAll(Arrays.asList("2763323", "938378393"));

        Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", client1, city1);
        Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", client1, city2);

        client1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));

        clientRepository.saveAll(Arrays.asList(client1));
        enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), client1, endereco1);
        Pedido pedido2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), client1, endereco2);

        Payment payment1 = new PaymentComCard(null, EstadoPayment.QUITADO, pedido1, 6);
        pedido1.setPayment(payment1);

        Payment payment2 = new PaymentComBoleto(null, EstadoPayment.PENDENTE, pedido2, sdf.parse("20/10/2017 00:00"), null);
        pedido2.setPayment(payment2);

        client1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));

        pedidoRepository.saveAll(Arrays.asList(pedido1,pedido2));
        paymentRepository.saveAll(Arrays.asList(payment1, payment2));

    }
}
