package br.com.suritec.desafio.service.dto;

import br.com.suritec.desafio.domain.Cliente;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode
@Builder
@ToString
public class ClienteDTO {


    @NotNull
    @CPF
    @Size(min = 1, max = 11)
    private String cpf;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        this(cliente.getCpf());
    }

    public ClienteDTO(@NotNull String cpf) {
        this.cpf = cpf;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
