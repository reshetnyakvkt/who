package questionnaires.dao;

import questionnaires.domain.FormPage;
import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
public interface FormPageDao {
    Long create(FormPage formPage);
    FormPage read(Long id);
    boolean update(FormPage formPage);
    boolean delete(FormPage formPage);
    List<FormPage> findAll();
    List<FormPage> findByTitle(Long id);
}
