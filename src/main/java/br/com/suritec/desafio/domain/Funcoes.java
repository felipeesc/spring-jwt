package br.com.suritec.desafio.domain;

public enum Funcoes {
    CONSULTAR (0L, "consultando cliente"),
    CADASTRAR (1L, "cadastrando usuário"),
    ATUALIZAR (2L, "atualizando cadastro"),
    REMOVER (3l, "removendo cliente"),
    BUSCAR (4L, "consultar todos cadastro"),
    BUSCAR_CPF (5L, "consultar por CPF");

    private Long codigo;
    private String descricao;

    Funcoes(Long codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Long getCodigo() { return codigo; }

    public void setCodigo(Long codigo) { this.codigo = codigo; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }


}
