package com.luxoft.chatbot.echo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="properties")
@Getter
@Setter
@NoArgsConstructor
public class BotProperty {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String botUserName;

    @Column(name = "token")
    private String botToken;

    public BotProperty(String botUserName, String botToken) {
        this.botUserName = botUserName;
        this.botToken = botToken;
    }
}
