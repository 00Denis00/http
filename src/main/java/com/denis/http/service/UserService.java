package com.denis.http.service;

import com.denis.http.model.User;
import com.denis.http.repository.UserRepository;
import com.denis.http.repository.hibernate.HibernateUserRepositoryImpl;

import java.util.List;

public class UserService
{
    UserRepository userRepository = new HibernateUserRepositoryImpl(){};

    public User findById(Integer id)
    {
        return userRepository.findById(id);
    }
    public void delete(User user)
    {
        userRepository.delete(user);
    }
    public List<User> findAll()
    {
        return userRepository.findAll();
    }
    public User save(User user)
    {
        return userRepository.save(user);
    }
    public User update(User user)
    {
        return userRepository.update(user);
    }
}
