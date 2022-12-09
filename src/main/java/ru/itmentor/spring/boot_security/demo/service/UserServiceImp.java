package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.dao.UserDao;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void create(User user, String[] selectedRoles) {
        userDao.create(user, selectedRoles);
    }

    @Transactional()
    @Override
    public User read(Long id) {
        return userDao.read(id);
    }

    @Transactional()
    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Transactional
    @Override
    public void update(User user, String[] selectedRoles) {
        userDao.update(user, selectedRoles);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Transactional()
    @Override
    public List<User> getList() {
        return userDao.getList();
    }

    @Override
    public List<Role> getRoles() {
        return userDao.getRoles();
    }
}
