package questionnaires.dao;

import questionnaires.domain.FormBody;

import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
public interface FormBodyDao {
    Long create(FormBody formBody);
    FormBody read(Long id);
    boolean update(FormBody formBody);
    boolean delete(FormBody formBody);
    List<FormBody> findAll();
    List<FormBody> findAllByFormTitleId(Long id);
}
