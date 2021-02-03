package br.com.suritec.desafio.domain;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@MappedSuperclass
public abstract class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 1, max = 20)
    @Column(name = "nome", length = 20, nullable = false)
    private String nome;

    @NotBlank
    @Email
    @Size(min = 1, max = 100)
    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Size(min = 1, max = 20)
    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @NotBlank
    @CPF
    @Size(min = 1, max = 11)
    @Column(length = 11, unique = true, nullable = false)
    private String cpf;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code", referencedColumnName = "user_code")
    private User user;

    @Embedded
    private Endereco endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public User getUsuario() {
        return user;
    }

    public void setUsuario(User user) {
        this.user = user;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}

