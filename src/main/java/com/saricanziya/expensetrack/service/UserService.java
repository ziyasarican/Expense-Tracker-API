package com.saricanziya.expensetrack.service;

import com.saricanziya.expensetrack.entity.User;
import com.saricanziya.expensetrack.model.UserModel;

public interface UserService {

    User createUser(UserModel userModel);

    User getUser(Long id);

    User updateUser(User user, Long id);
}
