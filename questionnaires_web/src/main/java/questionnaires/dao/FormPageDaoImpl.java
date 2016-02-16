package questionnaires.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import questionnaires.domain.FormPage;

import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
@Repository("formPageDao")
@Transactional
public class FormPageDaoImpl implements FormPageDao{
    private static Logger log = Logger.getLogger(FormPageDaoImpl.class);
    @Autowired
    private SessionFactory factory;
    public FormPageDaoImpl() {
    }

    @Override
    public Long create(FormPage formPage) {
        return (Long) factory.getCurrentSession().save(formPage);
    }

    @Override
    public FormPage read(Long id) {
        return (FormPage) factory.getCurrentSession().get(FormPage.class, id);
    }

    @Override
    public boolean update(FormPage formPage) {
        try{
            factory.getCurrentSession().update(formPage);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    @Override
    public boolean delete(FormPage formPage) {
        try{
            factory.getCurrentSession().delete(formPage);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    @Override
    public List<FormPage> findAll() {
        return factory.getCurrentSession().createQuery("FROM FormPage").list();
    }

    @Override
    public List<FormPage> findByTitle(Long id) {
        return factory.getCurrentSession().createQuery("FROM FormPage fp where fp.formTitleId=:formTitleId")
                .setParameter("formTitleId", id).list();
    }
}
