package com.ColombiaSolySelva.ColombiaSolYSelva.repository;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IproductoRepository extends JpaRepository<producto, Long> {
}
