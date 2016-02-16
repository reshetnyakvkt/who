package questionnaires.dao;

import questionnaires.domain.ParamDict;

import java.util.List;

/**
 * Created by D2R2 on 23.10.2015.
 */
public interface ParamDictDao {
    Long create(ParamDict paramDict);
    ParamDict read(Long id);
    boolean update(ParamDict paramDict);
    boolean delete(ParamDict paramDict);
    List<ParamDict> findAll();
    List<ParamDict> findAllByFormTitleId(Long id);
}
