package br.com.xablaum.pedidos.processamento.entity;




import br.com.xablaum.pedidos.processamento.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    private UUID id = UUID.randomUUID();
    private String cliente;
    @Column(name = "itens")
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itemPedidos = new ArrayList<>();
    @Column(name = "valor_total")
    private Double valorTotal;
    @Column(name = "email_notificacao")
    private String emailNotificacao;
    @Enumerated(EnumType.STRING)
    private Status status = Status.EM_PROCESSAMENTO;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
