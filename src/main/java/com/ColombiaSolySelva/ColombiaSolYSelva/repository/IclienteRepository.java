package com.ColombiaSolySelva.ColombiaSolYSelva.repository;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IclienteRepository extends JpaRepository <cliente, Long> {
    cliente findBycorreoCliente(String correoCliente);
}
