package questionnaires.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import questionnaires.domain.EnumDictionary;

import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
@Repository("enumDictionaryDao")
@Transactional
public class EnumDictionaryDaoImpl implements EnumDictionaryDao {
    private static Logger log = Logger.getLogger(EnumDictionaryDaoImpl.class);
    @Autowired
    private SessionFactory factory;
    public EnumDictionaryDaoImpl() {
    }

    @Override
    public Long create(EnumDictionary enumDictionary) {
        return (Long) factory.getCurrentSession().save(enumDictionary);
    }

    @Override
    public EnumDictionary read(Long id) {
        return (EnumDictionary) factory.getCurrentSession().get(EnumDictionary.class, id);
    }

    @Override
    public boolean update(EnumDictionary enumDictionary) {
        try{
            factory.getCurrentSession().update(enumDictionary);
            return true;
        } catch (Exception ex){
            log.info("Error execute update: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(EnumDictionary enumDictionary) {
        try{
            factory.getCurrentSession().delete(enumDictionary);
            return true;
        } catch (Exception ex){
            log.info("Error execute delete: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<EnumDictionary> findAll() {
        return factory.getCurrentSession().createQuery("FROM EnumDictionary").list();
    }

    @Override
    public List<EnumDictionary> findAllByFormTitleId(Long id) {
        try{
            Query query = factory.getCurrentSession().createQuery("From EnumDictionary" +
                    " join EnumDictionary.id as FormBody " +
                    " join FormPage fp on fp.id=fb.formPageId" +
                    " where FormPage.formTitleId = ?");
            query.setParameter(0, id);

            return (List<EnumDictionary>)query.list();
        } catch (Exception ex){
            log.info("Error execute findAllByFormTitleId: " + ex.getMessage());
            return null;
        }
    }
}
