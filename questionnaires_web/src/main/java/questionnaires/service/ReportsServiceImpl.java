package questionnaires.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import questionnaires.dao.FormDao;
import questionnaires.dao.FormTitleDao;
import questionnaires.dao.FormValueDao;
import questionnaires.domain.Form;
import questionnaires.domain.FormTitle;
import questionnaires.extras.ReportForm;
import questionnaires.extras.ReportTitle;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by D2R2 on 03.11.2015.
 */
@Service("reportsService")
@Transactional
public class ReportsServiceImpl implements ReportsService {
    private static final String C_REP_TITLE_ID = "ReportTitleId";
    private static final String C_REP_DATE_FROM = "ReportDateFrom";
    private static final String C_REP_DATE_TO = "ReportDateTo";
    private static final String C_REP_DATE_ALL = "ReportDateAll";

    public static final Logger log = Logger.getLogger(ParamTypeServiceImpl.class);
    @Autowired(required = true)
    private FormValueDao formValueDao;

    @Autowired(required = true)
    private FormDao formDao;

    @Autowired(required = true)
    private FormTitleDao formTitleDao;

    public ReportsServiceImpl() {
    }

    @Override
    public List<ReportForm> findReportForm(Long userId, ReportTitle reportTitle) {
        List<Form> formList = formDao.findFormByTitleIdPortion(userId, reportTitle);
        if (formList == null) return null;
        List<ReportForm> reportFormList = new ArrayList<>();
        for (Form form : formList){
            ReportForm reportForm = new ReportForm(form, "");
            reportForm.setShortValue(formValueDao.findByFormId(form.getId()));
            reportFormList.add(reportForm);
        }
        return reportFormList;
    }

    public ReportTitle getReportTitle(HttpSession session){
        ReportTitle reportTitle = new ReportTitle();
        reportTitle.setDateFrom((Date) session.getAttribute(C_REP_DATE_FROM));
        reportTitle.setDateFrom((Date) session.getAttribute(C_REP_DATE_TO));
        Object obj_date_all = session.getAttribute(C_REP_DATE_ALL);
        if (obj_date_all != null){
            reportTitle.setDateAll((boolean) obj_date_all);
        } else {
            reportTitle.setDateAll(false);
        }
        Long formTitleId = (Long) session.getAttribute(C_REP_TITLE_ID);
        FormTitle formTitle = null;
        if (formTitleId != null){
            formTitle = formTitleDao.read(formTitleId);
        }
        reportTitle.setFormTitle(formTitle);

        return reportTitle;
    }
}
