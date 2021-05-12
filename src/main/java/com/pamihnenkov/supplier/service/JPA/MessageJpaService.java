package com.pamihnenkov.supplier.service.JPA;

import com.pamihnenkov.supplier.model.Messages.Message;
import com.pamihnenkov.supplier.service.repository.MessageRepository;
import com.pamihnenkov.supplier.service.serviceInterfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MessageJpaService implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageJpaService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Set<Message> findAll() {
        return new HashSet<>(messageRepository.findAll());
    }

    @Override
    public Message findById(Long aLong) {
        return messageRepository.findById(aLong).orElse(null);
    }

    @Override
    public Message save(Message object) {
        return messageRepository.save(object);
    }

    @Override
    public void delete(Message object) {
        messageRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        messageRepository.deleteById(aLong);
    }
}
