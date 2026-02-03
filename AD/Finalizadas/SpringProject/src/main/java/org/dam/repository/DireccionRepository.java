package org.dam.repository;

import jakarta.transaction.Transactional;
import org.dam.modelo.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
}
