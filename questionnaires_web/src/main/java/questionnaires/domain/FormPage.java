package questionnaires.domain;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
@Entity
@Table(name = "FORM_PAGE")
public class FormPage {
    @Id
    @SequenceGenerator(name = "sequence_form_page", sequenceName = "SEQ_FORM_PAGE_ID",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_form_page")
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREATOR_ID", length = 15)     //NUMBER(15, 0),
    private Long creatorId;

    @Column(name = "LAST_EDITOR_ID", length = 15)// NUMBER(15, 0),
    private Long lastEditorId;

    @Column(name = "COMMENTS", length = 255)      // VARCHAR2(255 BYTE),
    private String comments;

    @Column(name = "NAME", length = 255)           //VARCHAR2(255 BYTE),
    private String name;

    @Column(name = "NOM", length = 3)            //NUMBER(3, 0) DEFAULT ((0)),
    private Long num;

    @Column(name = "FORM_TITLE_ID", length = 15)  //NUMBER(15, 0),
    private Long formTitleId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "FORM_TITLE_ID")
//    @NotFound(action = NotFoundAction.IGNORE)
//    private FormTitle formTitle;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "formPage")
//    private Set<FormBody> formBodies = new HashSet<>();

    public FormPage() {
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

    public Long getFormTitleId() {
        return formTitleId;
    }

    public void setFormTitleId(Long formTitleId) {
        this.formTitleId = formTitleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "FormPage{" +
                "id=" + id +
                ", num=" + num +
                ", name='" + name + '\'' +
                ", formTitleId=" + getFormTitleId() +
                ", comments='" + comments + '\'' +
                ", lastEditorId=" + lastEditorId +
                ", creatorId=" + creatorId +
                '}';
    }
}
