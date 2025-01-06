package br.com.xablau.pedidos.notificacao.entity;



import br.com.xablau.pedidos.notificacao.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedido {
    @JsonProperty("id")
    private UUID id = UUID.randomUUID();
    @JsonProperty("cliente")
    private String cliente;
    @JsonProperty("itemPedidos")
    private List<ItemPedido> itemPedidos = new ArrayList<>();
    @JsonProperty("valorTotal")
    private Double valorTotal;
    @JsonProperty("emailNotificacao")
    private String emailNotificacao;
    private Status status = Status.EM_PROCESSAMENTO;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private LocalDateTime dataHora = LocalDateTime.now();

    public UUID getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItemPedidos() {
        return itemPedidos;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public String getEmailNotificacao() {
        return emailNotificacao;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente='" + cliente + '\'' +
                ", itemPedidos=" + itemPedidos +
                ", valorTotal=" + valorTotal +
                ", emailNotificacao='" + emailNotificacao + '\'' +
                ", status=" + status +
                ", dataHora=" + dataHora +
                '}';
    }
}
