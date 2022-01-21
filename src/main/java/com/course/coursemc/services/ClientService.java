package com.course.coursemc.services;

import com.course.coursemc.domain.City;
import com.course.coursemc.domain.Client;
import com.course.coursemc.domain.Endereco;
import com.course.coursemc.domain.enums.TipoClient;
import com.course.coursemc.dto.ClientDTO;
import com.course.coursemc.dto.ClientNewDTO;
import com.course.coursemc.repositories.ClientRepository;
import com.course.coursemc.repositories.EnderecoRepository;
import com.course.coursemc.services.exceptions.DataIntegrityException;
import com.course.coursemc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Client find(Integer id) {
        Optional<Client> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: "
                + id + ", tipo: " + Client.class.getName()));
    }

    @Transactional
    public Client insert(Client obj){
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public Client update(Client obj){
        Client newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }
    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas!");
        }
    }
    public List<Client> findAll(){
        return repo.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Client fromDTO(ClientDTO objDto) {
        return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
    }

    public Client fromDTO(ClientNewDTO objDto) {
        Client client = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoClient.toEnum(objDto.getTipo()));
        City city = new City(objDto.getCityId(), null, null);
        Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), client, city);
        client.getEnderecos().add(endereco);
        client.getTelefones().add(objDto.getTelefone1());
        if(objDto.getTelefone2() != null){
            client.getTelefones().add(objDto.getTelefone2());
        }
        if(objDto.getTelefone3() != null){
            client.getTelefones().add(objDto.getTelefone3());
        }
        return client;
    }

    private  void updateData(Client newObj, Client obj){
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
}

