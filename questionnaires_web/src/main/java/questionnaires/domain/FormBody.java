package questionnaires.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
@Entity
@Table(name = "FORM_BODY")
public class FormBody {
    @Id
    @SequenceGenerator(name = "sequence_form_body", sequenceName = "SEQ_FORM_BODY_ID",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_form_body")
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREATOR_ID", length = 15)     //NUMBER(15, 0),
    private Long creatorId;

    @Column(name = "LAST_EDITOR_ID", length = 15)// NUMBER(15, 0),
    private Long lastEditorId;

    @Column(name = "COMMENTS", length = 255)      // VARCHAR2(255 BYTE),
    private String comments;

    @Column(name = "NOM", length = 3)           //  NUMBER(3, 0),
    private Integer num;

    @Column(name = "EDIT_DATE")                  //  DATE,
    private Date editDate;

//    @Column(name = "PARAM_DICT_ID", length = 15) //  NUMBER(15, 0),
//    private Long paramDictId;
//
//    @Column(name = "PARAM_TYPE_ID", length = 15) //  NUMBER(15, 0),
//    private Long paramTypeIdId;
//
//    @Column(name = "ENUM_DICT_ID", length = 15)  //  NUMBER(15, 0),
//    private Long enumDictId;
//
    @Column(name = "FORM_PAGE_ID", length = 15)  //  NUMBER(15, 0),
    private Long formPageId;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="FORM_PAGE_ID")
//    @NotFound(action = NotFoundAction.IGNORE)
//    private FormPage formPage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="PARAM_DICT_ID")
    private ParamDict paramDict;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="PARAM_TYPE_ID")
    private ParamType paramType;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="ENUM_DICT_ID")
//    @NotFound(action = NotFoundAction.IGNORE)
//    private EnumDictionary enumDictionary;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "formBody")
//    private Set<FormValue> formValues = new HashSet<>();

    public FormBody() {
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

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getFormPageId() {
        return formPageId;
    }

    public void setFormPageId(Long formPageId) {
        this.formPageId = formPageId;
    }

//    public Long getEnumDictId() {
//        if (enumDictionary != null){
//            return enumDictionary.getId();
//        } else {
//            return null;
//        }
//    }
//
//    public Long getParamTypeId() {
//        if (paramType != null){
//            return paramType.getId();
//        } else {
//            return null;
//        }
//    }

//    public Set<FormValue> getFormValues() {
//        return formValues;
//    }
//
//    public void setFormValues(Set<FormValue> formValues) {
//        this.formValues = formValues;
//    }

    public void setParamDict(ParamDict paramDict) {
        this.paramDict = paramDict;
    }

    public ParamDict getParamDict() {
        return paramDict;
    }

    public void setParamType(ParamType paramType) {
        this.paramType = paramType;
    }

    public ParamType getParamType() {
        return paramType;
    }

//    public EnumDictionary getEnumDictionary() {
//        return enumDictionary;
//    }

//    public FormPage getFormPage() {
//        return formPage;
//    }
//
//    public void setFormPage(FormPage formPage) {
//        this.formPage = formPage;
//    }

//
//    public void setEnumDictionary(EnumDictionary enumDictionary) {
//        this.enumDictionary = enumDictionary;
//    }

    @Override
    public String toString() {
        String res = "FormBody{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", lastEditorId=" + lastEditorId +
                ", comments='" + comments + '\'' +
                ", editDate=" + editDate +
                ", num=" + num +
//                ", paramDictId=" + getParamDictId() +
//                ", paramTypeId=" + getParamTypeId() +
//                ", enumDictId=" + getEnumDictId() +
                ", formPageId=" + getFormPageId();
//        if (formValues != null){
//            res += ", formValues.size=" + formValues.size();
//        } else {
//            res += ", formValues is null";
//        }
        if (paramDict != null){
            res += ", paramDict=" + paramDict;
        } else {
            res += ", paramDict is null";
        }
        if (paramType != null){
            res += ", paramType=" + paramType;
        } else {
            res += ", paramType is null";
        }
//        if (enumDictionary != null){
//            res += ", enumDictionary=" + enumDictionary;
//        } else {
//            res += ", enumDictionary is null";
//        }

        res +=  '}';


        return res;
    }
}
