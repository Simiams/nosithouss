package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.dtos.responses.ContactRes;
import fr.arosaje.nosithouss.models.Contact;
import fr.arosaje.nosithouss.models.User;
import fr.arosaje.nosithouss.repositories.ContactRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final AuthService authService;

    public ContactService(ContactRepository contactRepository, AuthService authService) {
        this.contactRepository = contactRepository;
        this.authService = authService;
    }

    public void safeSaveContact(Contact contact) {
        Contact newContact = contactRepository.findByUserAndContactUser(contact.getUser(), contact.getContactUser());
        if (newContact == null)
            newContact = contactRepository.findByUserAndContactUser(contact.getContactUser(), contact.getUser());
        contactRepository.save(newContact != null ? newContact.bSetLastChat(contact.getLastChat()) : contact);
    }

    public List<ContactRes> getAllContact() {
        User user = authService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()); //todo global method
        return contactRepository.findAllByUserOrContactUser(user, user).stream().map(contact -> ContactRes.builder()
                .userName((contact.getContactUser().equals(user) ? contact.getUser() : contact.getContactUser()).getUsername())
                .lastChat(contact.getLastChat())
                .build())
                .toList();
    }
}
