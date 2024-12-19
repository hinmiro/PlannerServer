package org.example.plannerserver.repository;

import org.example.plannerserver.entity.ApplicationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationDataRepository extends JpaRepository<ApplicationData, Long> {
}
