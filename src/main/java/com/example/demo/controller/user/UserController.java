package com.example.demo.controller.user;

import com.example.demo.service.user.UserService;
import com.example.demo.view.user.UserView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value="UserController", description="Управляет информацией об организациях")
@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)

public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получить список всех сотрудников
     * @return
     */
    @ApiOperation(value = "Получить список всех сотрудников", httpMethod = "GET")
    @GetMapping("/list")
    public List<UserView> users() {
        return userService.users();
    }

    /**
     * Получить сотрудника по Id
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Получить сотрудника по Id", httpMethod = "GET")
    @GetMapping(value = "/{id}", headers = "Accept=application/json")
    public UserView getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * Обновить существующ сотрудника (по Id)
     *
     * @param user
     * @param id
     */
    @ApiOperation(value = "Обновить офис по Id", httpMethod = "POST")
    @PostMapping(value = "/{id}", headers = "Accept=application/json")
    public void updateUserById(@RequestBody UserView user, @PathVariable Long id) {
        userService.update(user, id);
    }

    /**
     * Добавить нового сотрудника
     *
     * @param user
     */
    @ApiOperation(value = "Добавить нового сотрудника", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/save")
    public void addNewOrg(@RequestBody UserView user) {
        userService.add(user);
    }
}
