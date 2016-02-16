package questionnaires.service;

import questionnaires.domain.ParamDict;

import java.util.List;

/**
 * Created by D2R2 on 27.10.2015.
 */
public interface ParamDictService {
    List<ParamDict> findAllByFormTitleId(Long id);
}
