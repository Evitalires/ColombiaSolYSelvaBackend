package com.ColombiaSolySelva.ColombiaSolYSelva.repository;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.detallepedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdetallepedidoRepository extends JpaRepository<detallepedido, Long> {
}
