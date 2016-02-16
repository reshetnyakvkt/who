package questionnaires.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import questionnaires.dao.ParamTypeDao;
import questionnaires.domain.ParamType;

import java.util.List;

/**
 * Created by D2R2 on 27.10.2015.
 */
@Service("paramTypeService")
@Transactional
public class ParamTypeServiceImpl implements ParamTypeService{
    public static final Logger log = Logger.getLogger(ParamTypeServiceImpl.class);
    @Autowired(required = true)
    private ParamTypeDao paramTypeDao;

    public ParamTypeServiceImpl() {
    }

    @Override
    public List<ParamType> findAllByFormTitleId(Long id) {
        return paramTypeDao.findAllByFormTitleId(id);
    }
}
