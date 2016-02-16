package questionnaires.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import questionnaires.domain.FormBody;
import questionnaires.domain.FormPage;

import java.util.ArrayList;
import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
@Repository("formBodyDao")
@Transactional
public class FormBodyDaoImpl implements FormBodyDao {
    private static Logger log = Logger.getLogger(FormBodyDaoImpl.class);
    @Autowired
    private SessionFactory factory;

    @Autowired
    private FormPageDao formPageDao;

    public FormBodyDaoImpl() {
    }

    @Override
    public Long create(FormBody formBody) {
        return (Long) factory.getCurrentSession().save(formBody);
    }

    @Override
    public FormBody read(Long id) {
        return (FormBody) factory.getCurrentSession().get(FormBody.class, id);
    }

    @Override
    public boolean update(FormBody formBody) {
        try{
            factory.getCurrentSession().update(formBody);
            return true;
        } catch (Exception ex){
            log.info("Error execute update: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(FormBody formBody) {
        try{
            factory.getCurrentSession().delete(formBody);
            return true;
        } catch (Exception ex){
            log.info("Error execute delete: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<FormBody> findAll() {
        return factory.getCurrentSession().createQuery("FROM FormBody").list();
    }

    @Override
    public List<FormBody> findAllByFormTitleId(Long id) {
        try{
            List<FormPage> formPageList = formPageDao.findByTitle(id);
            if (formPageList == null || formPageList.size() == 0){
                return null;
            }
            List<FormBody> resFormBodyList = new ArrayList<>();
            List<FormBody> formBodyList;
            for (FormPage formPage : formPageList){
                formBodyList = factory.getCurrentSession()
                        .createQuery("FROM FormBody fb where fb.formPageId=?")
                        .setParameter(0, formPage.getId()).list();
                if (formBodyList != null){
                    resFormBodyList.addAll(formBodyList);
                }
            }
            return resFormBodyList;
        } catch (Exception ex){
            log.info("Error execute findAllByFormTitleId: " + ex.getMessage());
            return null;
        }
    }
}
