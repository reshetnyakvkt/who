package questionnaires.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import questionnaires.dao.EnumDictionaryDao;
import questionnaires.domain.EnumDictionary;

import java.util.List;
import java.util.Locale;

/**
 * Created by D2R2 on 27.10.2015.
 */
@Service("enumDictionaryService")
@Transactional
public class EnumDictionaryServiceImpl implements EnumDictionaryService{
    public static final Logger log = Logger.getLogger(EnumDictionaryServiceImpl.class);
    @Autowired(required = true)
    private EnumDictionaryDao enumDictionaryDao;

    public EnumDictionaryServiceImpl() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Override
    public List<EnumDictionary> findAllByFormTitleId(Long id) {
        return enumDictionaryDao.findAllByFormTitleId(id);
    }
}
