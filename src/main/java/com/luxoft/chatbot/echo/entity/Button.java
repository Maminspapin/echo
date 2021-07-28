package com.luxoft.chatbot.echo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "button")
@Getter
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "keyboard_button",
            joinColumns = @JoinColumn(name = "button_id"),
            inverseJoinColumns = @JoinColumn(name = "keyboard_id")
    )
    private List<KeyBoard> keyBoardProperties;

}
