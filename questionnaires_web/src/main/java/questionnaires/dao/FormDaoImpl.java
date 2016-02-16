package questionnaires.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import questionnaires.domain.Form;
import questionnaires.extras.DateUtilities;
import questionnaires.extras.ReportTitle;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
@Repository("formDao")
@Transactional
public class FormDaoImpl implements FormDao {
    private static Logger log = Logger.getLogger(FormDaoImpl.class);

    @Autowired(required = true)
    private SessionFactory factory;

    @Autowired(required = true)
    @Value( "${rowsOnPage}" )
    private int rowsOnPage;


    public FormDaoImpl() {
    }

    @Override
    public Long create(Form form) {
        try{
            return (Long) factory.getCurrentSession().save(form);
        } catch (Exception ex){
            log.info("Error execute create(): " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Form read(Long id) {
        return (Form) factory.getCurrentSession().get(Form.class, id);
    }

    @Override
    public boolean update(Form form) {
        try{
            factory.getCurrentSession().update(form);
            return true;
        } catch (Exception ex){
            log.error("Error execute update(): " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Form form) {
        try{
            factory.getCurrentSession().delete(form);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    @Override
    public List<Form> findAll() {
        return factory.getCurrentSession().createQuery("FROM Form").list();
    }

    @Override
    public Form getByTitleId(Long userId, Long titleId) {
        try{
            Query query = factory.getCurrentSession().createQuery("FROM Form where FORM_TITLE_ID=?"); // and CREATOR_ID=?");
            query.setParameter(0, titleId);
//        query.setParameter(1, userId);
            List<Form> forms = query.list();

            if (forms == null || forms.size() == 0) {
                return null;
            }
            return forms.get(0);
        } catch (Exception ex){
            log.info("Error execute getByTitleId(): " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Form> findFormByTitleId(Long userId, Long id) {
        try{
            Query query = factory.getCurrentSession().createQuery("FROM Form fm" +
                    " where fm.formTitleId=:formTitleId" +
                    " and fm.creatorId=:creatorId" +
                    " Order by fm.createDate desc, fm.id desc");
            query.setParameter("formTitleId", id);
            query.setParameter("creatorId", userId);
            List<Form> forms = query.list();
            if (forms == null || forms.size() == 0){
                return null;
            }
            return query.list();
        } catch (Exception ex){
            log.error("Error execute getByTitleId(): " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Form> findFormByTitleIdPortion(Long userId, ReportTitle reportTitle) {
        if (reportTitle.getFormTitle() == null) return null;
        try{
            StringBuilder sb = bldSqlCondByTitleIdPortion(reportTitle);
            Query queryCount = factory.getCurrentSession().createQuery("Select count(*) " + sb.toString());
            Query queryList = factory.getCurrentSession().createQuery(sb.toString() +
                    " Order by fm.createDate desc, fm.id desc");

            queryCount.setParameter("creatorId", userId);
            queryList.setParameter("creatorId", userId);
            queryCount.setParameter("formTitleId", reportTitle.getFormTitle().getId());
            queryList.setParameter("formTitleId", reportTitle.getFormTitle().getId());
            //--Period
            if (! reportTitle.isDateAll()) {
                if (reportTitle.getDateFrom() != null){
                    Date dateFrom = new DateUtilities(reportTitle.getDateFrom()).getDateStartDay();
                    queryCount.setParameter("dateFrom", dateFrom);
                    queryList.setParameter("dateFrom", dateFrom);
                }
                if (reportTitle.getDateTo() != null){
                    Date dateTo = new DateUtilities(reportTitle.getDateTo()).getDateEndDay();
                    queryCount.setParameter("dateTo", dateTo);
                    queryList.setParameter("dateTo", dateTo);
                }
            }
            //--Portion
            int firstRes = 0;
            if (reportTitle.getNumPage() != null && reportTitle.getNumPage() > 0){
                firstRes = (int)((reportTitle.getNumPage() - 1) * rowsOnPage +
                        reportTitle.getNumPage() - 1);
            }
            queryList.setFirstResult(firstRes);
            queryList.setMaxResults(rowsOnPage);

            List<Object> cntFormsList = queryCount.list();
            if (cntFormsList != null && cntFormsList.size() > 0){
                long pages = (long)((long)cntFormsList.get(0) / rowsOnPage - 0.5);
                reportTitle.setPages(pages);
                log.error("findFormByTitleIdPortion(): pages=" + pages);
            } else {
                reportTitle.setPages(0L);
            }
            return queryList.list();
        } catch (Exception ex){
            log.info("Error execute findFormByTitleIdPortion(): " + ex.getMessage());
            return null;
        }
    }

    private StringBuilder bldSqlCondByTitleIdPortion(ReportTitle reportTitle){
        StringBuilder sb = new StringBuilder("From Form fm Where fm.creatorId=:creatorId" +
                " and fm.formTitleId=:formTitleId");
        if (! reportTitle.isDateAll()) {
            if (reportTitle.getDateFrom() != null){
                sb.append(" and fm.createDate>=:dateFrom");
            }
            if (reportTitle.getDateTo() != null){
                sb.append(" and fm.createDate<=:dateTo");
            }
        }
        return sb;
    }
}
