package questionnaires.dao;

import questionnaires.domain.ParamType;

import java.util.List;

/**
 * Created by Admin on 23.10.2015.
 */
public interface ParamTypeDao {
    Long create(ParamType paramType);
    ParamType read(Long id);
    boolean update(ParamType paramType);
    boolean delete(ParamType paramType);
    List<ParamType> findAll();
    List<ParamType> findAllByFormTitleId(Long id);
}
