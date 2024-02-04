package fr.arosaje.nosithouss.repositories;

import fr.arosaje.nosithouss.models.Contact;
import fr.arosaje.nosithouss.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByUserAndContactUser(User user, User contact);

    List<Contact> findAllByUserOrContactUser(User user, User contact);
    List<Contact> findAllByUser(User user);
    List<Contact> findAllByContactUser(User contact);

    Contact findByUserOrContactUser(User user, User contactUser);
}
