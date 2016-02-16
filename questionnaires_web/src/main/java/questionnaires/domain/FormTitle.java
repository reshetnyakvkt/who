package questionnaires.domain;

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
@Table(name = "FORM_TITLE")
public class FormTitle {
    @Id
    @SequenceGenerator(name = "sequence_form_title", sequenceName = "SEQ_FORM_TITLE_ID",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_form_title")
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREATOR_ID", length = 15)     //NUMBER(15, 0),
    private Long creatorId;

    @Column(name = "LAST_EDITOR_ID", length = 15)// NUMBER(15, 0),
    private Long lastEditorId;

    @Column(name = "COMMENTS", length = 255)      // VARCHAR2(255 BYTE),
    private String comments;

    @Column(name = "NAME", length = 255)          // VARCHAR2(255 BYTE)
    private String name;

    @Column(name = "DATE_ACTIVE_FROM") //DATE,
    private Date dateActiveFrom;

    @Column(name = "DATE_ACTIVE_TO")   //DATE,
    private Date dateActiveTo;

    @Column(name = "SUBJECT_FORM_ID")  //NUMBER(15, 0),
    private Long subjectFromId;

    @Column(name = "IS_ACTIVE")        //NUMBER(1, 0) DEFAULT ((0)),
    private Integer isActive;

    @Column(name = "EDIT_DATE")        //DATE,
    private Date editDate;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "formTitle")
//    private Set<FormValue> formValues = new HashSet<>();
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "formTitle")
//    private Set<FormPage> formPages = new HashSet<>();

    public FormTitle() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateActiveFrom() {
        return dateActiveFrom;
    }

    public void setDateActiveFrom(Date dateActiveFrom) {
        this.dateActiveFrom = dateActiveFrom;
    }

    public Date getDateActiveTo() {
        return dateActiveTo;
    }

    public void setDateActiveTo(Date dateActiveTo) {
        this.dateActiveTo = dateActiveTo;
    }

    public Long getSubjectFromId() {
        return subjectFromId;
    }

    public void setSubjectFromId(Long subjectFromId) {
        this.subjectFromId = subjectFromId;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

//    public Set<FormValue> getFormValues() {
//        return formValues;
//    }
//
//    public void setFormValues(Set<FormValue> formValues) {
//        this.formValues = formValues;
//    }

//    public Set<FormPage> getFormPages() {
//        return formPages;
//    }
//
//    public void setFormPages(Set<FormPage> formPages) {
//        this.formPages = formPages;
//    }

    @Override
    public String toString() {
        String res = "FormTitle{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", lastEditorId=" + lastEditorId +
                ", comments='" + comments + '\'' +
                ", name='" + name + '\'' +
                ", dateActiveFrom=" + dateActiveFrom +
                ", dateActiveTo=" + dateActiveTo +
                ", subjectFromId=" + subjectFromId +
                ", isActive=" + isActive +
                ", editDate=" + editDate +
                ", "
                ;

//        if (formValues == null){
//            res += "formValues is null";
//        } else {
//            res += "formValues.size=" + formValues.size();
//        }
//        res += ", ";
//        if (formPages == null){
//            res += "formPages is null";
//        } else {
//            res += "formPages.size=" + formPages.size();
//        }
        res += '}';
        return res;
    }
}
