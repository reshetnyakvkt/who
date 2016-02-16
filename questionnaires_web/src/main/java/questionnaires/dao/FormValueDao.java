package questionnaires.dao;

import questionnaires.domain.FormValue;
import questionnaires.extras.ReportTitle;

import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
public interface FormValueDao {
    Long create(FormValue formValue);
    FormValue read(Long id);
    FormValue getLastFormValue();
    boolean update(FormValue formValue);
    boolean delete(FormValue formValue);
    List<FormValue> findAll();
    List<FormValue> findByFormId(Long id);
}
