package questionnaires.service;

import questionnaires.domain.FormBody;

import java.util.List;

/**
 * Created by D2R2 on 26.10.2015.
 */
public interface FormBodyService {
    List<FormBody> findAll();
    List<FormBody> findAllByFormTitleId(Long formTitleId);
    Long createFormBody(FormBody formBody);
    FormBody getFormById(Long id);
}
