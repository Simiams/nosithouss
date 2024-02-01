package fr.arosaje.nosithouss.repositories;

import fr.arosaje.nosithouss.models.Flag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlagRepository extends JpaRepository<Flag, Long> {

    Flag findByKey(String key);
}
