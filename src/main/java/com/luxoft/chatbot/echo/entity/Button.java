package com.luxoft.chatbot.echo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "button")
@Getter
@Setter
@NoArgsConstructor
public class Button {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "callback_text")
    private String callbackText;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "keyboard_id")
    private Keyboard keyboard;
}
