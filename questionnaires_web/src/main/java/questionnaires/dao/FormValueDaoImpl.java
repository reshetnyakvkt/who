package questionnaires.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import questionnaires.domain.FormValue;
import questionnaires.extras.ReportTitle;

import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
@Repository("formValueDao")
@Transactional
public class FormValueDaoImpl implements FormValueDao {
    private static Logger log = Logger.getLogger(FormValueDaoImpl.class);
    @Autowired
    private SessionFactory factory;

    @Autowired(required = true)
    @Value( "${rowsOnPage}" )
    private int rowsOnPage;

    public FormValueDaoImpl() {
    }

    @Override
    public Long create(FormValue formValue) {
        return (Long) factory.getCurrentSession().save(formValue);
    }

    @Override
    public FormValue read(Long id) {
        return (FormValue) factory.getCurrentSession().get(FormValue.class, id);
    }

    @Override
    public boolean update(FormValue formValue) {
        try{
            factory.getCurrentSession().update(formValue);
            return true;
        } catch (Exception ex){
            log.info("Error execute update(): " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(FormValue formValue) {
        try{
            factory.getCurrentSession().delete(formValue);
            return true;
        } catch (Exception ex){
            log.info("Error execute delete(): " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<FormValue> findAll() {
        return factory.getCurrentSession().createQuery("FROM FormValue").list();
    }

    @Override
    public List<FormValue> findByFormId(Long id) {
        try{
            Query query = factory.getCurrentSession().createQuery("FROM FormValue "
                + " where FORM_ID = ?");
            query.setParameter(0, id);
            return query.list();
        } catch (Exception ex){
            log.info("Error execute findByFormId(): " + ex.getMessage());
            return null;
        }
    }

    @Override
    public FormValue getLastFormValue() {
        log.info("getLastFormValue - start");
        try{
            Query query = factory.getCurrentSession().createQuery("FROM FormValue fv"
                    + " Where fv.lastEditDate != null"
                    + " Order by fv.lastEditDate desc").setFirstResult(0)
                    .setMaxResults(2);

            List<FormValue> formValueList = query.list();
            if (formValueList != null && formValueList.size() > 0) {
                log.info("getLastFormValue - formValueList.size()=" + formValueList.size());
                return formValueList.get(0);
            } else {
                log.info("getLastFormValue - result is empty");
                return null;
            }
        } catch (Exception ex){
            log.info("Error execute getLastFormValue(): " + ex.getMessage());
            return null;
        }
    }
}
