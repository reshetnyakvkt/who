package questionnaires.service;

import org.springframework.util.DigestUtils;
import questionnaires.dao.UserDao;
import questionnaires.domain.User;
import questionnaires.exception.AuthenticationException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
@Service("authenticationService")
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final String C_PASS_SALT = "androschuk.a@gmail.com";
    private static final String C_USER_ID = "UserId";
    private static final String C_USER_NAME = "UserName";
    private static final String C_USER_IS_DEFINED = "UserIsDefined";
    private Map<Long, User> userMap;

    public static final Logger log = Logger.getLogger(AuthenticationServiceImpl.class);



    private MessageDigest md5 = null;

    @Autowired(required = true)
    private UserDao userDao;

    @Autowired(required = true)
    @Value( "${numberOfAttempts}" )
    private int numberOfAttempts;

    private int attempt=0;

    public AuthenticationServiceImpl() {
        log.info("/questionnaires/service/authenticate:create()");
        userMap = new HashMap<>();
        Locale.setDefault(Locale.ENGLISH);
        md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean authenticate(String login, String pass) throws AuthenticationException {
        attempt++;
        log.info("/questionnaires/service/authenticate:start(attempt=" + attempt + ")");
        /*
        //Переделать чтобы количество попыток обнулялось ч/з например 10 секунд
        if (attempt > numberOfAttempts) {
            throw new AuthenticationException("You have exceeded the maximum number of attempts");
        }*/

        User user = userDao.getUserByLogin(login);
        log.info("/questionnaires/service/authenticate:getUserByLogin(" + login + ")");
        if (user == null) return false;
        System.out.println(user);

        String passSalted = login.toUpperCase() + pass + C_PASS_SALT;

        md5.update(StandardCharsets.UTF_8.encode(passSalted));
        String passHashed = String.format("%032x", new BigInteger(1, md5.digest())).toUpperCase();

        System.out.println("md5(pass)=" + passHashed);
        System.out.println("(pass)=" + user.getPassword());

        return user.getPassword().equals(passHashed);
    }

    @Override
    public Long create(User user) {
        return userDao.create(user);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public User getUserById(Long userId) {
        return userDao.read(userId);
    }

    @Override
    public boolean getSesUserIsDefined(HttpSession session) {
        Object obj = session.getAttribute(C_USER_IS_DEFINED);
        return ( obj != null && ! obj.toString().isEmpty());
    }

    @Override
    public User getSesUser(HttpSession session) {
        Object obj_id = session.getAttribute(C_USER_ID);
        try{
            Long userId = (Long)obj_id;
            if (userId == null) return null;
            if (userMap.containsKey(userId)) {
                return userMap.get(userId);
            } else {
                User user = userDao.read(userId);
                if (user != null) userMap.putIfAbsent(user.getId(), user);
                return user;
            }
        } catch (Exception ex){
            log.info("Error getSesUserId(): " + ex.getMessage());
            return null;
        }
    }

    @Override
    public void setSesUserIsDefined(HttpSession session, boolean isDefined) {
        session.setAttribute(C_USER_IS_DEFINED, isDefined);
    }

    @Override
    public void setSesUser(HttpSession session, User user) {
        session.setAttribute(C_USER_ID, user.getId());
        session.setAttribute(C_USER_NAME, user.getLogin());
        userMap.putIfAbsent(user.getId(), user);
    }

    @Override
    public void removeSesParameters(HttpSession session, User user) {
        session.removeAttribute(C_USER_IS_DEFINED);
        session.removeAttribute(C_USER_ID);
        if (user != null){
            userMap.remove(user.getId());
        }
    }
}
