package questionnaires.extras;

import questionnaires.domain.Form;
import questionnaires.domain.FormValue;
import java.util.Date;
import java.util.List;

public class ReportForm {
    private Long formTitleId;
    private Long formId;
    private String shortValues;
    private Date createDate;

    public ReportForm() {
    }

    public ReportForm(Form form, String shortValues) {
        this.formTitleId = form.getFormTitleId();
        this.formId = form.getId();
        this.createDate = form.getCreateDate();
        this.shortValues = shortValues;
    }

    public void setShortValue(List<FormValue> formValueList){
        if (formValueList == null) return;
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < formValueList.size(); i++){
            FormValue formValue = formValueList.get(i);
            if (formValue.getValStr() != null && !formValue.getValStr().isEmpty()){
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(formValue.getValStr());
            }
            if (sb.length() > 150){
                sb.append("…");
                break;
            }
        }
        shortValues = sb.toString();
    }

    public Long getFormTitleId() {
        return formTitleId;
    }

    public void setFormTitleId(Long formTitleId) {
        this.formTitleId = formTitleId;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public String getShortValues() {
        return shortValues;
    }

    public void setShortValues(String shortValues) {
        this.shortValues = shortValues;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
