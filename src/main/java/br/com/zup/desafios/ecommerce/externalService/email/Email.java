package br.com.zup.desafios.ecommerce.externalService.email;

public interface Email {

    /**
     *
     * @param from - email da origem
     * @param to - email do detino
     * @param subject - assunto do email
     * @param body - corpo do email
     */
    void send(String from, String to, String subject, String body);
}
