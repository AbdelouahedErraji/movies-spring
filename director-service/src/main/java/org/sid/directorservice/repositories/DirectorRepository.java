package org.sid.directorservice.repositories;

import org.sid.directorservice.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}
