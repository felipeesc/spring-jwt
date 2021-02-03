package br.com.suritec.desafio.resource.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * Classe utilitária para a criação de HTTP headers.
 */
public final class HeaderUtil {

    private static final Logger LOG = LoggerFactory.getLogger(HeaderUtil.class);

    private static final String APPLICATION_NAME = "suritecApp";

    private HeaderUtil() {
    }

    /**
     * Crie os alertas.
     *
     * @param mensagem   mensagem.
     * @param parametros parametros.
     * @return o {@link HttpHeaders} com os alertas.
     */
    public static HttpHeaders createAlert(String mensagem, String parametros) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-suritecApp-alert", mensagem);
        headers.add("X-suritecApp-params", parametros);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".created", param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".updated", param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".deleted", param);
    }

    /**
     * Criar um alerta de falha.
     *
     * @param nomeEntidade   o nome da entidade.
     * @param chaveErro      a chave do erro.
     * @param mensagemPadrao a mensagem padrão.
     * @return o {@link HttpHeaders} com os alertas.
     */
    public static HttpHeaders createFailureAlert(String nomeEntidade, String chaveErro,
                                                 String mensagemPadrao) {
        LOG.error("O processamento da entidade falhou, {}", mensagemPadrao);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-suritecApp-error", "error." + chaveErro);
        headers.add("X-suritecApp-params", nomeEntidade);
        return headers;
    }
}
