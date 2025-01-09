package br.com.xablaum.pedidos.processamento.services;

import br.com.xablaum.pedidos.processamento.entity.ItemPedido;
import br.com.xablaum.pedidos.processamento.entity.Pedido;
import br.com.xablaum.pedidos.processamento.entity.Produto;
import br.com.xablaum.pedidos.processamento.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void save(List<ItemPedido> itemPedidos) {

        for (ItemPedido a:itemPedidos){
            produtoRepository.save(a.getProduto());
        }
    }
}
