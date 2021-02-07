package br.com.suritec.desafio.domain;

import com.google.common.base.Objects;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "domain", name = "cliente")
public final class Cliente extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "cliente_code", sequenceName = "cliente_code", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_code")
    private Long code;

    public Cliente(String nome, String email, String telefone, String cpf, Endereco endereco) {
        setNome(nome);
        setEmail(email);
        setPhone(telefone);
        setCpf(cpf);
        setEndereco(endereco);
    }

    public Long getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Clientes{" +
                "nome ='" + getCpf() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equal(code, cliente.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
