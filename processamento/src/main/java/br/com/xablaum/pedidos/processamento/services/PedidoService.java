package br.com.xablaum.pedidos.processamento.services;

import br.com.xablaum.pedidos.processamento.entity.ItemPedido;
import br.com.xablaum.pedidos.processamento.entity.Pedido;
import br.com.xablaum.pedidos.processamento.repository.ItemPedidoRepository;
import br.com.xablaum.pedidos.processamento.repository.PedidoRepository;
import br.com.xablaum.pedidos.processamento.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final Logger logger = LoggerFactory.getLogger(PedidoService.class);
    private final PedidoRepository pedidoRepository;
    private final ProdutoService produtoService;
    private final ItemPedidoService itemPedidoService;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoService produtoService, ItemPedidoService itemPedidoService) {
        this.pedidoRepository = pedidoRepository;
        this.produtoService = produtoService;
        this.itemPedidoService = itemPedidoService;
    }

    public void salvarPedido(Pedido pedido){
        //salva o produto
        produtoService.save(pedido.getItemPedidos());
        //salva os itemPedidos
        List<ItemPedido> itemPedidos = itemPedidoService.save(pedido.getItemPedidos());
        //salva o pedido
        pedidoRepository.save(pedido);
        //associa os itensPedido ao pedido(necessario devido ao UUID random)
        itemPedidoService.updateItemPedido(itemPedidos, pedido);

        logger.info("Pedido salvo: {}", pedido.toString());
    }
}