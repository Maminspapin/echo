package com.luxoft.chatbot.echo.dao;

import com.luxoft.chatbot.echo.entity.Button;
import org.springframework.data.repository.CrudRepository;

public interface ButtonRepository extends CrudRepository<Button, Integer> {

    Button findButtonByName(String name);
}
