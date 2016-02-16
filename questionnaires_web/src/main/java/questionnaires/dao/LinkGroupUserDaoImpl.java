package questionnaires.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import questionnaires.domain.LinkGroupUser;

import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
@Repository("linkGroupUserDao")
@Transactional
public class LinkGroupUserDaoImpl implements LinkGroupUserDao {
    private static Logger log = Logger.getLogger(GroupUserDaoImpl.class);
    @Autowired
    private SessionFactory factory;

    public LinkGroupUserDaoImpl() {
    }

    @Override
    public Long create(LinkGroupUser linkGroupUser) {
        return (Long) factory.getCurrentSession().save(linkGroupUser);
    }

    @Override
    public LinkGroupUser read(Long id) {
        return (LinkGroupUser) factory.getCurrentSession().get(LinkGroupUser.class, id);
    }

    @Override
    public boolean update(LinkGroupUser linkGroupUser) {
        try{
            factory.getCurrentSession().update(linkGroupUser);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    @Override
    public boolean delete(LinkGroupUser linkGroupUser) {
        try{
            factory.getCurrentSession().delete(linkGroupUser);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    @Override
    public List<LinkGroupUser> findAll() {
        return factory.getCurrentSession().createQuery("FROM LinkGroupUser").list();
    }
}
