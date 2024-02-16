package fr.arosaje.nosithouss.repositories;

import fr.arosaje.nosithouss.models.MessageRequestGuard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageGuardRepository extends JpaRepository<MessageRequestGuard, Long>{

}
