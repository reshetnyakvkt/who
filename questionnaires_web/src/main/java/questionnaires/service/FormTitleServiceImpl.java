package questionnaires.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import questionnaires.dao.FormTitleDao;
import questionnaires.domain.FormTitle;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: reashetnyak_viktor
 * Date: 04.11.2015
 */

@Service("formTitleService")
@Transactional
public class FormTitleServiceImpl implements FormTitleService {
    public static final Logger log = Logger.getLogger(FormTitleServiceImpl.class);

    @Autowired(required = true)
    private FormTitleDao formTitleDao;

    public FormTitleServiceImpl() {
    }

    @Override
    public FormTitle read(Long id) {
        return formTitleDao.read(id);
    }

    @Override
    public List<FormTitle> findAll() {
        return formTitleDao.findAll();
    }

    @Override
    public List<FormTitle> findByUserId(Long userId) {
        return formTitleDao.findByUserId(userId);
    }

}
