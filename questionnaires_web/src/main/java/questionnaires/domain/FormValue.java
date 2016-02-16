package questionnaires.domain;

import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
@Entity
@Table(name = "FORM_VALUE")
public class FormValue {
    @Id
    @SequenceGenerator(name = "sequence_form_value", sequenceName = "SEQ_FORM_VALUE_ID",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_form_value")
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREATOR_ID", length = 15)     //NUMBER(15, 0),
    private Long creatorId;

    @Column(name = "LAST_EDITOR_ID", length = 15) // NUMBER(15, 0),
    private Long lastEditorId;

    @Column(name = "COMMENTS", length = 255)      // VARCHAR2(255 BYTE),
    private String comments;

    @Column(name = "VAL_STR", length = 500) //          VARCHAR2(500 BYTE),
    private String valStr;

    @Column(name = "LAST_EDIT_DATE")
    private Date lastEditDate;

    @Column(name = "FORM_TITLE_ID", length = 15) //   NUMBER(15, 0),
    private Long formTitleId;

    @Column(name = "FORM_PAGE_ID", length = 15) //     NUMBER(15, 0),
    private Long formPageId;

    @Column(name = "FORM_BODY_ID", length = 15) //     NUMBER(15, 0),
    private Long formBodyId;

    @Column(name = "FORM_ID", length = 15) //          NUMBER(15, 0),
    private Long formId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="FORM_ID")
//    private Form form;

//    @ManyToOne
//    @JoinColumn(name="FORM_TITLE_ID")
//    private FormTitle formTitle;
//
//    @ManyToOne
//    @JoinColumn(name="FORM_PAGE_ID")
//    private FormPage formPage;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="FORM_BODY_ID")
//    @NotFound(action = NotFoundAction.IGNORE)
//    private FormBody formBody;

    public FormValue() {
    }

    public FormValue(Long creatorId, Long formId, Long formTitleId, Long formPageId, Long formBodyId) {
        this.creatorId = creatorId;
        this.lastEditorId = creatorId;
        this.formTitleId = formTitleId;
        this.formPageId = formPageId;
        this.formId = formId;
        this.formBodyId = formBodyId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getLastEditorId() {
        return lastEditorId;
    }

    public void setLastEditorId(Long lastEditorId) {
        this.lastEditorId = lastEditorId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public Long getFormBodyId() {
        return formBodyId;
    }

    public void setFormBodyId(Long formBodyId) {
        this.formBodyId = formBodyId;
    }

    public Date getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public String getValStr() {
        return valStr;
    }

    public void setValStr(String valStr) {
        this.valStr = valStr;
    }

    public Long getFormTitleId() {
        return formTitleId;
    }

    public void setFormTitleId(Long formTitleId) {
        this.formTitleId = formTitleId;
    }

    public Long getFormPageId() {
        return formPageId;
    }

    public void setFormPageId(Long formPageId) {
        this.formPageId = formPageId;
    }

    @Override
    public String toString() {
        String res = "FormValue{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", lastEditorId=" + lastEditorId +
                ", lastEditDate=" + lastEditDate + '\'' +
                ", formId=" + formId +
                ", comments='" + comments + '\'' +
                ", valStr='" + valStr + '\'' +
                ", formTitleId=" + formTitleId + '\'' +
                ", formPageId=" + formPageId + '\'' +
                ", formBodyId=" + formBodyId;
//        if (formTitle != null){
//            res += ", formTitle=" + formTitle;
//        } else {
//            res += ", formTitle is null";
//        }
//        if (formPage != null){
//            res += ", formPage=" + formPage;
//        } else {
//            res += ", formPage is null";
//        }
//        if (formBody != null){
//            res += ", formBody=" + formBody;
//        } else {
//            res += ", formBody is null";
//        }
        res += "}";
        return res;
    }
}
