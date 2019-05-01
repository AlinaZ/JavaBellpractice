package com.example.demo.dao.user;

import com.example.demo.model.User;

import java.util.List;

/**
 * DAO для работы с User
 */
public interface UserDao {
    /**
     * Получить все объекты User
     *
     * @return
     */
    List<User> all();

    /**
     * Получить User по идентификатору
     *
     * @param id
     * @return
     */
    User loadById(Long id);

    User loadByFullName(String firstName, String lastName, String middleName);

    /**
     * Сохранить User
     *
     * @param user
     */
    void save(User user);

    /**
     * Обновить User  по идентификатору
     * @param user
     * @param id
     */
    void update (User user, Long id);
}
