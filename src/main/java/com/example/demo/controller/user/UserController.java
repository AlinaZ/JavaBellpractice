package com.example.demo.controller.user;

import com.example.demo.service.user.UserService;
import com.example.demo.view.SuccessView;
import com.example.demo.view.user.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "UserController", description = "Управляет информацией об организациях")
@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)

public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получить список сотрудников
     * по параметрам
     * “officeId”:””, //обязательный параметр
     * “firstName”:””,
     * “lastName”:””,
     * “middleName”:””,
     * “position”,””,
     * “docCode”:””,
     * “citizenshipCode”:””
     *
     * @return
     */
    @ApiOperation(value = "Получить список сотрудников по параметрам", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping(value = "/list", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    List<UserListOutView> users(@RequestBody UserListInView view) {
        return userService.userFilter(view);
    }

    /**
     * Получить сотрудника по Id
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Получить сотрудника по Id", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody
    UserView getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * Обновить существующег сотрудника
     *
     * @param user
     */
    @ApiOperation(value = "Обновить сотрудника", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping(value = "/update", consumes = "application/json")
    public @ResponseBody
    SuccessView update(@RequestBody UserUpdView user) {
        return userService.update(user);
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
    @PostMapping(value = "/save", consumes = "application/json")
    public @ResponseBody
    SuccessView addNewOrg(@RequestBody UserSaveView user) {
        return userService.add(user);
    }
}
