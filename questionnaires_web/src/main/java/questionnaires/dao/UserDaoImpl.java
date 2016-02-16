package questionnaires.dao;

import questionnaires.domain.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 */
@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {
    private static Logger log = Logger.getLogger(UserDaoImpl.class);
    @Autowired
    private SessionFactory factory;

    public UserDaoImpl() {
    }

    public UserDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Long create(User user) {
        return (Long) factory.getCurrentSession().save(user);
    }

    @Override
    public User read(Long id) {
        return (User) factory.getCurrentSession().get(User.class, id);
    }

    @Override
    public User getUserByLogin(String login) {
        Query query = factory.getCurrentSession().createQuery("FROM User u where Upper(u.login)=:login").
                setParameter("login", login.toUpperCase());
        List<User> users = query.list();
        if (users == null | users.size() == 0){
            return null;
        }
        return (User)query.list().get(0);
    }

    @Override
    public boolean update(User user) {
        try{
            factory.getCurrentSession().update(user);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    @Override
    public boolean delete(User user) {
        try{
            factory.getCurrentSession().delete(user);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    @Override
    public List<User> findAll() {
        return factory.getCurrentSession().createQuery("FROM User").list();
    }
}
