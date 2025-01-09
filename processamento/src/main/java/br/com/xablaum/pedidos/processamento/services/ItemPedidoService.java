package br.com.xablaum.pedidos.processamento.services;

import br.com.xablaum.pedidos.processamento.entity.ItemPedido;
import br.com.xablaum.pedidos.processamento.entity.Pedido;
import br.com.xablaum.pedidos.processamento.repository.ItemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }


    public List<ItemPedido> save(List<ItemPedido> itemPedidos) {
        return itemPedidoRepository.saveAll(itemPedidos);
    }

    public void save(ItemPedido item){
        itemPedidoRepository.save(item);
    }

    public void updateItemPedido(List<ItemPedido> itemPedidos, Pedido pedido) {
        for (ItemPedido item: itemPedidos){
            item.setPedido(pedido);
            this.save(item);
        }
    }
}
