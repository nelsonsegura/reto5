package com.ciclo3.service;

import com.ciclo3.model.Message;
import com.ciclo3.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAllMessage() {
        return (List<Message>) messageRepository.getAllMessage();
    }
    public Optional<Message> getMessageById(Integer id) {
        return messageRepository.getMessageById(id);
    }
    public Message saveMessage(Message m) {
        if (m.getIdMessage() == null){
            return messageRepository.saveMessage(m);
        }else{
            Optional<Message> ms = messageRepository.getMessageById(m.getIdMessage());
            if (ms.isEmpty()){
                return messageRepository.saveMessage(m);
            }else{
                return m;
            }
        }
    }
    public Message updateMessage(Message message) {
        if (message.getIdMessage() != null) {
            Optional<Message> e = messageRepository.getMessageById(message.getIdMessage());
            if (!e.isEmpty()) {
                if (message.getMessageText() != null) {
                    e.get().setMessageText(message.getMessageText());
                }
                messageRepository.saveMessage(e.get());
                return e.get();
            }
        }
        return message;
    }
    public boolean deleteMessage(Integer id){
        Boolean d = getMessageById(id).map(message -> {
            messageRepository.deleteMessage(message);
            return true;
        }).orElse(false);
        return d;
    }

}
