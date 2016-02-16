package questionnaires.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import questionnaires.domain.ParamType;

import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
@Repository("paramTypeDao")
public class ParamTypeDaoImpl implements ParamTypeDao {
    private static Logger log = Logger.getLogger(ParamTypeDaoImpl.class);
    @Autowired
    private SessionFactory factory;

    @Override
    public Long create(ParamType paramType) {
        return (Long) factory.getCurrentSession().save(paramType);
    }

    @Override
    public ParamType read(Long id) {
        return (ParamType) factory.getCurrentSession().get(ParamType.class, id);
    }

    @Override
    public boolean update(ParamType paramType) {
        try{
            factory.getCurrentSession().update(paramType);
            return true;
        } catch (Exception ex){
            log.info("Error execute update(): " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(ParamType paramType) {
        try{
            factory.getCurrentSession().delete(paramType);
            return true;
        } catch (Exception ex){
            log.info("Error execute delete(): " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<ParamType> findAll() {
        return factory.getCurrentSession().createQuery("FROM ParamType").list();
    }

    @Override
    public List<ParamType> findAllByFormTitleId(Long id) {
        try{
            Query query = factory.getCurrentSession().createQuery("From ParamType pt" +
                    " join FormBody fb on fb.paramDictId=pt.id" +
                    " join FormPage fp on fp.id=fb.formPageid" +
                    " where FORM FORM_TITLE_ID = ?");
            query.setParameter(0, id);

            return query.list();
        } catch (Exception ex){
            log.info("Error execute findAllByFormTitleId: " + ex.getMessage());
            return null;
        }
    }
}
