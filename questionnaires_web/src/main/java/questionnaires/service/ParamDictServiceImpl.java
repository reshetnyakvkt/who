package questionnaires.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import questionnaires.dao.ParamDictDao;
import questionnaires.domain.ParamDict;

import java.util.List;

/**
 * Created by D2R2 on 27.10.2015.
 */
@Service("paramDictService")
@Transactional
public class ParamDictServiceImpl implements ParamDictService{
    public static final Logger log = Logger.getLogger(ParamDictServiceImpl.class);

    @Autowired(required = true)
    private ParamDictDao paramDictDao;
    public ParamDictServiceImpl() {
    }

    @Override
    public List<ParamDict> findAllByFormTitleId(Long id) {
        return paramDictDao.findAllByFormTitleId(id);
    }
}
