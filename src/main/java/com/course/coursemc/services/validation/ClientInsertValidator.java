package com.course.coursemc.services.validation;

import com.course.coursemc.domain.enums.TipoClient;
import com.course.coursemc.dto.ClientNewDTO;
import com.course.coursemc.resources.exceptions.FieldMessage;
import com.course.coursemc.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
    @Override
    public void initialize(ClientInsert ann) {
    }

    @Override
    public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getTipo().equals(TipoClient.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if(objDto.getTipo().equals(TipoClient.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
