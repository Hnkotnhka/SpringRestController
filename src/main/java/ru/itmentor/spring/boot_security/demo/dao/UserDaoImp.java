package ru.itmentor.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager manager;

   @Override
   public void create(User user, String[] selectedRoles) {
      manager.persist(createUser(user, selectedRoles));
   }

   @Override
   public User read(Long id) {
      return manager.find(User.class, id);
   }

   @Override
   public User getByName(String name) {
      return manager.createQuery("select u from User u where u.username = :name", User.class)
              .setParameter("name", name)
              .getSingleResult();
   }

   @Override
   public void update(User user, String[] selectedRoles) {
      manager.merge(createUser(user, selectedRoles));
   }

   @Override
   public void delete(User user) {
      manager.remove(manager.find(User.class, user.getId()));
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> getList() {
      return manager.createQuery("from User").getResultList();
   }

   @Override
   public List<Role> getRoles() {
      return manager.createQuery("from Role", Role.class).getResultList();
   }

   private User createUser(User user, String[] selectedRoles) {
      List<String> roleNames = Arrays.asList(selectedRoles);
      List<Role> allRoles = getRoles();

      for (Role role : allRoles) {
         if (roleNames.contains(role.getName())) {
            user.addRole(role);
         }
      }
      return user;
   }
}
