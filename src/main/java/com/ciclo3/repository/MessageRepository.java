package com.ciclo3.repository;

import com.ciclo3.model.Message;
import com.ciclo3.repository.crud.MessageCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository

public class MessageRepository {
    @Autowired
    private MessageCrudRepository messageCrudRepository;

    public List<Message> getAllMessage() {
       return (List<Message>) messageCrudRepository.findAll();
    }
    public Optional<Message> getMessageById(Integer id) {
        return messageCrudRepository.findById(id);
    }
    public Message saveMessage(Message m) {
        return messageCrudRepository.save(m);
    }
    public void deleteMessage(Message m){
        messageCrudRepository.delete(m);
    }

}
