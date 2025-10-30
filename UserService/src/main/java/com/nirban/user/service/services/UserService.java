package com.nirban.user.service.services;

import com.nirban.user.service.dto.requestDto.UserDto;
import com.nirban.user.service.entities.User;
import com.nirban.user.service.repositories.UserRepository;

import java.util.List;

public interface UserService {

    //user operations

    //create
    User saveUser(UserDto user);

    //get all user
    List<User> getAllUser();

    //get single user of given userId

    User getUser(String userId);

    //TODO: delete
    void deleteUser(String userId);

    //TODO: update
    User updateUser(String userId, User user);


}
