package com.saricanziya.expensetrack.service;

import com.saricanziya.expensetrack.entity.User;
import com.saricanziya.expensetrack.exception.ItemAlreadyExistsException;
import com.saricanziya.expensetrack.exception.ResourceNotFoundException;
import com.saricanziya.expensetrack.model.UserModel;
import com.saricanziya.expensetrack.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserModel userModel) {
        if(userRepository.existsByEmail(userModel.getEmail()))
            throw new ItemAlreadyExistsException("User is already registerede for this email: " + userModel.getEmail());

        User newUser = new User();

        // adı ve tipi aynı olan nesneleri kopyalar. Mapping işlemini kolaylaştırır
        // (source, target) olarak parametre alır.
        BeanUtils.copyProperties(userModel, newUser);
        return userRepository.save(newUser);


    }

    @Override
    public User getUser(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + id));
    }

    @Override
    public User updateUser(User user, Long id) throws ResourceNotFoundException {
        User existingUser = getUser(id);


        existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
        existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
        existingUser.setPassword(user.getPassword() != null ? user.getPassword() : existingUser.getPassword());
        existingUser.setAge(user.getAge() != null ? user.getAge() : existingUser.getAge());

        return userRepository.save(existingUser);
    }
}
