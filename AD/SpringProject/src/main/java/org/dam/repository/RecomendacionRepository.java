package org.dam.repository;

import jakarta.transaction.Transactional;
import org.dam.modelo.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RecomendacionRepository extends JpaRepository<Recomendacion, Long> {
}
