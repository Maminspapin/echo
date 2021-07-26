package com.luxoft.chatbot.echo.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="properties")
@Getter
@NoArgsConstructor
public class BotProperties {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String botUserName;

    @Column(name = "token")
    private String botToken;
}
