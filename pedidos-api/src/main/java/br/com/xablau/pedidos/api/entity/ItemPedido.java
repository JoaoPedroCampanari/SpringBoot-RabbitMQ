package br.com.xablau.pedidos.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemPedido {
    @JsonProperty("id")
    private UUID id = UUID.randomUUID();
    @JsonProperty("produto")
    private Produto produto;
    @JsonProperty("quantidade")
    private Integer quantidade;

    @Override
    public String toString() {
        return "ItemPedido{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                '}';
    }
}
