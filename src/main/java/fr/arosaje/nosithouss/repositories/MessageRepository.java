package fr.arosaje.nosithouss.repositories;

import fr.arosaje.nosithouss.models.Message;
import fr.arosaje.nosithouss.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByReceiverId(Long id);
    List<Message> findAllBySenderId(Long id);

    List<Message> findAllBySender(User sender);
    List<Message> findAllByReceiverAndSender(User receiver, User sender);

}
