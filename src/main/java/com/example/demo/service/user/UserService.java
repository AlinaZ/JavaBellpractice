package com.example.demo.service.user;

import com.example.demo.view.SuccessView;
import com.example.demo.view.user.*;
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
    SuccessView add(@Valid UserSaveView user);

    /**
     * Получить список users
     *
     * @return {@Person}
     */
    List<UserListOutView> userFilter (@Valid UserListInView view);

    /**
     * Получить user по идентификатору
     * @param id
     * @return
     */
    UserView getUserById(Long id);

    /**
     * Обновить user по идентификатору
     * @param user
     */
    SuccessView update(@Valid UserView user);

}
