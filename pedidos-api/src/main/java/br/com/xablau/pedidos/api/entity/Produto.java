package br.com.xablau.pedidos.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Produto {
    @JsonProperty("id")
    private UUID id = UUID.randomUUID();
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("valor")
    private Double valor;

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                '}';
    }
}
