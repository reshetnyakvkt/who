package questionnaires.domain;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
@Entity
@Table(name = "FORM")
public class Form {
    @Id
    @SequenceGenerator(name = "sequence_form", sequenceName = "SEQ_FORM_ID",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_form")
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREATOR_ID", length = 15)     //NUMBER(15, 0),
    private Long creatorId;

    @Column(name = "CREATE_DATE", length = 15)
    private Date createDate;

    @Column(name = "LAST_EDITOR_ID", length = 15)// NUMBER(15, 0),
    private Long lastEditorId;

    @Column(name = "LAST_EDIT_DATE")
    private Date lastEditDate;

    @Column(name = "COMMENTS", length = 255)      // VARCHAR2(255 BYTE),
    private String comments;

    @Column(name = "FORM_TITLE_ID", length = 15)// NUMBER(15, 0),
    private Long formTitleId;

    @Column(name = "FORM_STATE", length = 3)
    private Long formState;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "form")
//    private Set<FormValue> formValues = new HashSet<>();

    public Form() {
        this.createDate = Calendar.getInstance().getTime();
    }

    public Form(Long creatorId, Long formTitleId) {
        this();
        this.creatorId = creatorId;
        this.lastEditorId = creatorId;
        this.formTitleId = formTitleId;
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

    public Date getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

//    public void addFormValue(FormValue formValue) {
//        formValues.add(formValue);
//    }

    public Long getFormTitleId() {
        return formTitleId;
    }

    public void setFormTitleId(Long formTitleId) {
        this.formTitleId = formTitleId;
    }

    public Long getFormState() {
        return formState;
    }

    public void setFormState(Long formState) {
        this.formState = formState;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    //    public Set<FormValue> getFormValues() {
//        return formValues;
//    }
//
//    public void setFormValues(Set<FormValue> formValues) {
//        this.formValues = formValues;
//    }


//    public Set<FormValue> getFormValues() {
//        return formValues;
//    }

    @Override
    public String toString() {
        String res = "Form{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", createDate=" + createDate +
                ", comments='" + comments + '\'' +
                ", lastEditorId=" + lastEditorId +
                ", lastEditDate=" + lastEditDate +
                ", formTitleId=" + formTitleId +
                ", formState=" + formState +
                ',';
//        if (formValues == null){
//            res += "formValues is null";
//        } else {
//            res += "formValues.size=" + formValues.size();
//            res += "{" + formValues.toString() + "}";
//        }
        res += '}';
        return res;
    }
}
