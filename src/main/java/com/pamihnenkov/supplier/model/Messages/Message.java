package com.pamihnenkov.supplier.model.Messages;

import com.pamihnenkov.supplier.model.BaseEntity;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Entity
@Table(name = "messages")
public class Message extends BaseEntity implements Serializable {

    @ManyToOne
    private ApplicationUser sender;
    @ManyToOne
    private ApplicationUser recipient;
    private String text;

    public Message(ApplicationUser sender) {
        this.sender = sender;
    }
}
