package questionnaires.dao;

import org.springframework.stereotype.Repository;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import questionnaires.domain.LinkGroupUserForm;
import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
@Repository("linkGroupUserFormDao")
@Transactional
public class LinkGroupUserFormDaoImpl implements LinkGroupUserFormDao {
    private static Logger log = Logger.getLogger(LinkGroupUserFormDaoImpl.class);
    @Autowired
    private SessionFactory factory;

    public LinkGroupUserFormDaoImpl() {
    }

    @Override
    public Long create(LinkGroupUserForm linkGroupUserForm) {
        return (Long) factory.getCurrentSession().save(linkGroupUserForm);
    }

    @Override
    public LinkGroupUserForm read(Long id) {
        return (LinkGroupUserForm) factory.getCurrentSession().get(LinkGroupUserForm.class, id);
    }

    @Override
    public boolean update(LinkGroupUserForm linkGroupUserForm) {
        try{
            factory.getCurrentSession().update(linkGroupUserForm);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    @Override
    public boolean delete(LinkGroupUserForm linkGroupUserForm) {
        try{
            factory.getCurrentSession().delete(linkGroupUserForm);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    @Override
    public List<LinkGroupUserForm> findAll() {
        return factory.getCurrentSession().createQuery("FROM LinkGroupUserForm").list();
    }
}
