package com.ciclo3.controller;

import com.ciclo3.model.Message;
import com.ciclo3.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Message")
@CrossOrigin(origins = "*")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/all")
    public List<Message> getAllMessage() {
        return (List<Message>) messageService.getAllMessage();
    }
    @GetMapping("/{id}")
    public Optional<Message> getMessageById(@PathVariable Integer id) {
        return messageService.getMessageById(id);
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Message saveMessage(@RequestBody Message m) {
        return messageService.saveMessage(m);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED )
    public Message updateMessage(@RequestBody Message m) {
        return messageService.updateMessage(m);
    };
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteMessage(@PathVariable Integer id){
        return messageService.deleteMessage(id);
    }
}
