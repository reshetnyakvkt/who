package questionnaires.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import questionnaires.dao.FormDao;
import questionnaires.dao.FormValueDao;
import questionnaires.domain.Form;
import questionnaires.domain.FormBody;
import questionnaires.domain.FormValue;
import questionnaires.extras.ReportTitle;

import java.util.*;

/**
 * Created by IntelliJ IDEA
 * User: reashetnyak_viktor
 * Date: 26.10.2015
 */
@Service("formValueService")
@Transactional
public class FormValueServiceImpl implements FormValueService{
    public static final Logger log = Logger.getLogger(FormValueServiceImpl.class);

    @Autowired(required = true)
    private FormValueDao formValueDao;

    @Autowired(required = true)
    private FormDao formDao;

    public FormValueServiceImpl() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Override
    public List<FormValue> findByFormId(Long id) {
        return formValueDao.findByFormId(id);
    }

    @Override
    public List<FormValue> fillNotExistsByFormBody(Long creatorId, Long id, Long formTitleId, List<FormBody> formBodyList) {
        try{
        List<FormValue> res = findByFormId(id);
        if (res != null){
            res = new ArrayList<>();
        }
        ArrayList<Long> formBodyIds = new ArrayList<>();
        for(FormValue formValue : res){
            formBodyIds.add(formValue.getFormBodyId());
        }
        for (int i=0; i < formBodyList.size(); i++){
            if (formBodyIds.indexOf(formBodyList.get(i).getId()) < 0){
                FormValue newFormValue = new FormValue(creatorId, id, formTitleId,
                        formBodyList.get(i).getFormPageId(), formBodyList.get(i).getId());
                formValueDao.create(newFormValue);
                res.add(newFormValue);
            }
        }
        return res;
        } catch (Exception ex){
            log.info("Error execute fillNotExistsByFormBody(): " + ex.getMessage());
            return null;
        }
    }

    @Override
    public FormValue read(Long id) {
        return formValueDao.read(id);
    }

    @Override
    public boolean update(FormValue formValue) {
        return formValueDao.update(formValue);
    }

    @Override
    public FormValue getLastFormValue() {
        return formValueDao.getLastFormValue();
    }

    @Override
    public List<FormValue> findByReportTitle(Long creatorId, ReportTitle reportTitle) {
        List<Form> formList = formDao.findFormByTitleIdPortion(creatorId, reportTitle);
        if (formList == null || formList.size() == 0) return null;
        List<FormValue> res = new ArrayList<>();
        List<FormValue> formValueList;
        for (Form form : formList){
            formValueList = findByFormId(form.getId());
            if (formValueList != null || formValueList.size() > 0) {
                res.addAll(formValueList);
            }
        }
        return res;
    }
}
