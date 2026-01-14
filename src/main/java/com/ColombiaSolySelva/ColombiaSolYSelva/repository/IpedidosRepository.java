package com.ColombiaSolySelva.ColombiaSolYSelva.repository;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpedidosRepository extends JpaRepository<pedidos, Long> {
}
