package com.preview.preview.domain.service.user;

import com.preview.preview.domain.user.User;

import java.util.Optional;

public interface UserService {
    public Optional<User> findByIdPw(String id);


}
