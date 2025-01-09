package br.com.xablaum.pedidos.processamento.repository;

import br.com.xablaum.pedidos.processamento.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
