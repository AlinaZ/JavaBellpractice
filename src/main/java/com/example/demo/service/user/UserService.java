package com.example.demo.service.user;

import com.example.demo.view.user.UserView;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис
 */
@Validated
public interface UserService {

    /**
     * Добавить user в БД
     *
     * @param user
     */
    void add(@Valid UserView user);

    /**
     * Получить список users
     *
     * @return {@Person}
     */
    List<UserView> users();

    /**
     * Получить user по идентификатору
     * @param id
     * @return
     */
    UserView getUserById(Long id);

    /**
     * Обновить user по идентификатору
     * @param user
     * @param id
     */
    void update(@Valid UserView user,Long id);

}
