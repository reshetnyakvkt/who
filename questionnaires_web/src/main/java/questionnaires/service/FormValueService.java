package questionnaires.service;

import questionnaires.domain.FormBody;
import questionnaires.domain.FormValue;
import questionnaires.extras.ReportTitle;

import java.util.List;

/**
 * Created by D2R2 on 26.10.2015.
 */
public interface FormValueService {
    FormValue read(Long id);
    FormValue getLastFormValue();
    boolean update(FormValue formValue);
    List<FormValue> findByFormId(Long id);
    List<FormValue> fillNotExistsByFormBody(Long creatorId, Long id, Long formTitleId, List<FormBody> formBodyList);
    List<FormValue> findByReportTitle(Long creatorId, ReportTitle reportTitle);
}
