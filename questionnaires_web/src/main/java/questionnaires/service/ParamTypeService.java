package questionnaires.service;

import questionnaires.domain.ParamType;

import java.util.List;

/**
 * Created by D2R2 on 27.10.2015.
 */
public interface ParamTypeService {
    List<ParamType> findAllByFormTitleId(Long id);
}
