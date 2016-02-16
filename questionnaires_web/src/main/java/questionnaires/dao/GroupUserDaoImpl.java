package questionnaires.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import questionnaires.domain.GroupUser;
import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
*/
@Repository("groupUserDao")
@Transactional
public class GroupUserDaoImpl implements GroupUserDao {
    private static Logger log = Logger.getLogger(GroupUserDaoImpl.class);
    @Autowired
    private SessionFactory factory;

    @Override
    public Long create(GroupUser groupUser) {
        return (Long) factory.getCurrentSession().save(groupUser);
    }

    @Override
    public GroupUser read(Long id) {
        return (GroupUser) factory.getCurrentSession().get(GroupUser.class, id);
    }

    @Override
    public boolean update(GroupUser groupUser) {
        try{
            factory.getCurrentSession().update(groupUser);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    @Override
    public boolean delete(GroupUser groupUser) {
        try{
            factory.getCurrentSession().delete(groupUser);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    @Override
    public List<GroupUser> findAll() {
        return factory.getCurrentSession().createQuery("FROM GroupUser").list();
    }
}
