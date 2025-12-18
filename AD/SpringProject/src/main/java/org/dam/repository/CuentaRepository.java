package org.dam.repository;

import jakarta.transaction.Transactional;
import org.dam.modelo.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
}
