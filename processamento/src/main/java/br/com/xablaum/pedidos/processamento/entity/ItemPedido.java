package br.com.xablaum.pedidos.processamento.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ItemPedido")
public class ItemPedido {
    @JsonProperty("id")
    @Id
    private UUID id = UUID.randomUUID();

    @JsonProperty("produto")
    @ManyToOne
    private Produto produto;

    @JsonProperty("quantidade")
    private Integer quantidade;

    @ManyToOne
    private Pedido pedido;

    @Override
    public String toString() {
        return "ItemPedido{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                '}';
    }
}
