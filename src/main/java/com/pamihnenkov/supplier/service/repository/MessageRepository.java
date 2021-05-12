package com.pamihnenkov.supplier.service.repository;

import com.pamihnenkov.supplier.model.Messages.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {


}
