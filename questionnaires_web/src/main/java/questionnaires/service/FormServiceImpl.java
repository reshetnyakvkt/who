package questionnaires.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import questionnaires.dao.*;
import questionnaires.domain.Form;
import questionnaires.domain.FormBody;
import questionnaires.domain.FormTitle;
import questionnaires.domain.FormValue;
import questionnaires.extras.Pair;

import java.util.*;

/**
 * Created by IntelliJ IDEA
 * User: reashetnyak_viktor
 * Date: 23.10.2015
 */
@Service("formService")
@Transactional
public class FormServiceImpl implements FormService {
    public static final Logger log = Logger.getLogger(FormServiceImpl.class);

    @Autowired(required = true)
    private FormDao formDao;

    @Autowired(required = true)
    private FormValueDao formValueDao;

    @Autowired(required = true)
    private FormBodyDao formBodyDao;

    public FormServiceImpl() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Override
    public Form getFormById(Long id) {
        return formDao.read(id);
    }

    @Override
    public Long createForm(Form form) {
        return formDao.create(form);
    }

    @Override
    public Form getEditFormByTitleId(Long userId, Long titleId) {
        Form form = formDao.getByTitleId(userId, titleId);
        return form;
    }

    @Override
    public List<Form> findFormAll() {
        List<Form> res = formDao.findAll();
        return res;
    }

    @Override
    public List<Form> findFormByTitleId(Long userId, Long id) {
        return formDao.findFormByTitleId(userId, id);
    }

    @Override
    public boolean createFormValues(Long userId, String queryString) {
        log.info("createFormValues()queryString: " + queryString);
        List<Pair<Long, String>> queryParameters = new ArrayList<>();
        String[] parameters = queryString.split("&");
        Long formTitleId = -1L;
        for (String parameter : parameters) {
            String[] keyValuePair = parameter.split("=");

            if (keyValuePair[0].equals("title_id")){
                try{
                    formTitleId = Long.valueOf(keyValuePair[1]);
                } catch(Exception ex){
                    log.info("createFormValues()/error get formTitleId:" + ex.getMessage());
                    return false;
                }
            } else {
                String str = keyValuePair[0];
                if (isInt(str)){
                    queryParameters.add(new Pair(Long.valueOf(keyValuePair[0]),
                            keyValuePair.length == 1 ? "" : keyValuePair[1]));
                }
            }
        }
        if (formTitleId == -1) return false;

        Form form = new Form(userId, formTitleId);
        Long formId = formDao.create(form);
        FormBody formBody;
        try{
            for (int i = 0; i < queryParameters.size(); i++){
                formBody = formBodyDao.read(queryParameters.get(i).getLeft());

                FormValue formValue = new FormValue(userId, formId, formTitleId,
                        formBody.getFormPageId(), formBody.getId());
                formValue.setValStr(queryParameters.get(i).getRight());
                formValueDao.create(formValue);
            }
        } catch(Exception ex){
            log.info("createFormValues()/error save FormValue: " + ex.getMessage());
            return false;
        }
        return true;
    }

    static boolean isInt(String s)  // assuming integer is in decimal number system
    {
        for(int a=0;a<s.length();a++)
        {
            if(a==0 && s.charAt(a) == '-') continue;
            if( !Character.isDigit(s.charAt(a)) ) return false;
        }
        return true;
    }
}
