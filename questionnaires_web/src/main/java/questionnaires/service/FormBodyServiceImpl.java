package questionnaires.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import questionnaires.dao.FormBodyDao;
import questionnaires.domain.FormBody;

import java.util.List;
import java.util.Locale;

/**
 * Created by D2R2 on 26.10.2015.
 */
@Service("formBodyService")
@Transactional
public class FormBodyServiceImpl implements FormBodyService {
    public static final Logger log = Logger.getLogger(FormBodyServiceImpl.class);

    @Autowired(required = true)
    private FormBodyDao formBodyDao;

    public FormBodyServiceImpl() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Override
    public List<FormBody> findAll() {
        return formBodyDao.findAll();
    }

    @Override
    public Long createFormBody(FormBody formBody) {
        return formBodyDao.create(formBody);
    }

    @Override
    public FormBody getFormById(Long id) {
        return formBodyDao.read(id);
    }

    @Override
    public List<FormBody> findAllByFormTitleId(Long formTitleId) {
        return formBodyDao.findAllByFormTitleId(formTitleId);
    }
}
