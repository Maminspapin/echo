package com.luxoft.chatbot.echo.dao;

import com.luxoft.chatbot.echo.entity.BotProperties;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PropertyDAOImpl implements PropertyDAO {

    private final EntityManager entityManager;

    public PropertyDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public BotProperties getBotProperty() {
        return entityManager.find(BotProperties.class, 1);
    }
}
