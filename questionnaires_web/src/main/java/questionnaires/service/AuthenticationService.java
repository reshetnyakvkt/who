package questionnaires.service;

import questionnaires.domain.User;
import questionnaires.exception.AuthenticationException;

import javax.servlet.http.HttpSession;

/**
 * Created by Admin on 22.10.2015.
 */
public interface AuthenticationService {
    boolean authenticate(String login, String pass) throws AuthenticationException;
    Long create(User user);
    User getUserByLogin(String login);
    User getUserById(Long userId);
    boolean getSesUserIsDefined(HttpSession session);
    void setSesUserIsDefined(HttpSession session, boolean isDefined);
    User getSesUser(HttpSession session);
    void setSesUser(HttpSession session, User user);
    void removeSesParameters(HttpSession session, User user);
}
