package br.com.xablau.pedidos.notificacao.service;

import br.com.xablau.pedidos.notificacao.entity.Pedido;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmailPedidoCriado(Pedido pedido){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("pedidos-api@company.com");
        simpleMailMessage.setTo(pedido.getEmailNotificacao());
        simpleMailMessage.setSubject("Pedido de compra");
        simpleMailMessage.setText(this.gerarMensagemPedidoCriado(pedido));
        mailSender.send(simpleMailMessage);
    }

    public void enviarEmailPedidoProcessado(Pedido pedido){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("pedidos-api@company.com");
        simpleMailMessage.setTo(pedido.getEmailNotificacao());
        simpleMailMessage.setSubject("Pedido Processado");
        simpleMailMessage.setText(this.gerarMensagemPedidoProcessado(pedido));
        mailSender.send(simpleMailMessage);
    }

    private String gerarMensagemPedidoCriado(Pedido pedido) {
        String pedidoId = pedido.getId().toString();
        String cliente = pedido.getCliente();
        String valor = String.valueOf(pedido.getValorTotal());
        String status = pedido.getStatus().name();

        return String.format("Olá %s, seu pedido de n° %s foi realizado com sucesso no valor de %s.\nStatus: %s",cliente, pedidoId, valor, status);
    }

    private String gerarMensagemPedidoProcessado(Pedido pedido){
        String pedidoId = pedido.getId().toString();
        String cliente = pedido.getCliente();
        String valor = String.valueOf(pedido.getValorTotal());
        String status = pedido.getStatus().name();

        return String.format("Olá %s, seu pedido de n° %s foi processado no valor de %s, e nas proximas horas será enviado!\nStatus: %s",cliente, pedidoId, valor, status);
    }
}
