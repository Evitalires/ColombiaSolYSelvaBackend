package com.ColombiaSolySelva.ColombiaSolYSelva.repository;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.cliente;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.detallepedido;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdetallepedidoRepository extends JpaRepository<detallepedido, Long> {
    List<detallepedido> findByPedido_Cliente(cliente cliente);
    List<detallepedido> findByPedido(pedidos pedido);

}
