package questionnaires.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import questionnaires.domain.ParamDict;
import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
@Repository("paramDictDao")
@Transactional
public class ParamDictDaoImpl implements ParamDictDao{
    private static Logger log = Logger.getLogger(ParamDictDaoImpl.class);
    @Autowired
    private SessionFactory factory;
    public ParamDictDaoImpl() {
    }

    @Override
    public Long create(ParamDict paramDict) {
        return (Long) factory.getCurrentSession().save(paramDict);
    }

    @Override
    public ParamDict read(Long id) {
        return (ParamDict) factory.getCurrentSession().get(ParamDict.class, id);
    }

    @Override
    public boolean update(ParamDict paramDict) {
        try{
            factory.getCurrentSession().update(paramDict);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    @Override
    public boolean delete(ParamDict paramDict) {
        try{
            factory.getCurrentSession().delete(paramDict);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    @Override
    public List<ParamDict> findAll() {
        return factory.getCurrentSession().createQuery("FROM ParamDict").list();
    }

    @Override
    public List<ParamDict> findAllByFormTitleId(Long id) {
        try{
            Query query = factory.getCurrentSession().createQuery("From ParamDict pd" +
                " join FormBody fb on fb.paramDictId=pd.id" +
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
