package questionnaires.dao;

import questionnaires.domain.Form;
import questionnaires.extras.ReportTitle;

import java.util.Date;
import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
public interface FormDao {
    Long create(Form form);
    Form read(Long id);
    Form getByTitleId(Long userId, Long titleId);
    boolean update(Form form);
    boolean delete(Form form);
    List<Form> findAll();
    List<Form> findFormByTitleId(Long userId, Long id);
    List<Form> findFormByTitleIdPortion(Long userId, ReportTitle reportTitle);
}
