package questionnaires.service;

import questionnaires.domain.FormTitle;

import java.util.List;

/**
 * Created by D2R2 on 04.11.2015.
 */
public interface FormTitleService {
    FormTitle read(Long id);
    List<FormTitle> findAll();
    List<FormTitle> findByUserId(Long userId);
}
