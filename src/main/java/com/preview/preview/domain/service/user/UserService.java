package com.preview.preview.domain.service.user;

import com.preview.preview.domain.user.User;
import com.preview.preview.domain.web.dto.user.UserDto;

import java.util.Optional;

public interface UserService {
    public Optional<User> findByIdPw(String id);
    public UserDto signup(UserDto userDto);

}
