package com.luxoft.chatbot.echo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "keyboard")
@Getter
@NoArgsConstructor
public class KeyBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "keyboard_button",
            joinColumns = @JoinColumn(name = "keyboard_id"),
            inverseJoinColumns = @JoinColumn(name = "button_id")
    )
    private List<Button> buttons;
}
