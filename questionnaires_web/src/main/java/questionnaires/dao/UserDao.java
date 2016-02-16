package questionnaires.dao;

import questionnaires.domain.User;

import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
public interface UserDao {
    Long create(User user);
    User getUserByLogin(String login);
    User read(Long id);
    boolean update(User user);
    boolean delete(User user);
    List<User> findAll();
}
