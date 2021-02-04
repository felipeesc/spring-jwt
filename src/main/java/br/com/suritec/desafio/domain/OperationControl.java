package br.com.suritec.desafio.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(schema = "domain", name = "operacao_controle")
public class OperationControl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "operation_code", sequenceName = "operation_code", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operation_code")
    private Long code;

    private LocalDateTime dataHora;

    private String usuarioAlteracao;

    private String funcaoExecutada;

    public OperationControl(String funcaoExecutada) {
        this.dataHora = LocalDateTime.now();
        this.funcaoExecutada = funcaoExecutada;
    }

    public OperationControl() {
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(String usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }

    public String getFuncaoExecutada() {
        return funcaoExecutada;
    }

    public void setFuncaoExecutada(String funcaoExecutada) {
        this.funcaoExecutada = funcaoExecutada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationControl that = (OperationControl) o;

        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        return "OperationControl{" +
                "code=" + code +
                '}';
    }
}
