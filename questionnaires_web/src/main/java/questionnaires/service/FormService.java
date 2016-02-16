package questionnaires.service;

import questionnaires.domain.*;

import java.util.List;
import java.util.Map;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
public interface FormService {
    Long createForm(Form form);
    Form getFormById(Long id);
    Form getEditFormByTitleId(Long userId, Long titleId);
    List<Form> findFormAll();
    List<Form> findFormByTitleId(Long userId, Long id);
    boolean createFormValues(Long userId, String queryString);
}
