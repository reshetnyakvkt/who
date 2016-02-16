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
@Table(name = "ENUM_DICT")
public class EnumDictionary {
    @Id
    @SequenceGenerator(name = "sequence_enum_dict", sequenceName = "SEQ_ENUM_DICT_ID",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_enum_dict")
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREATOR_ID", length = 15)     //NUMBER(15, 0),
    private Long creatorId;

    @Column(name = "LAST_EDITOR_ID", length = 15)// NUMBER(15, 0),
    private Long lastEditorId;

    @Column(name = "LAST_EDIT_DATE")
    private Date lastEditDate;

    @Column(name = "COMMENTS", length = 255)      // VARCHAR2(255 BYTE),
    private String comments;

    @Column(name = "NAME", length = 255)          // VARCHAR2(255 BYTE),
    private String name;

    @Column(name = "PARENT_ID", length = 15)     // NUMBER(15, 0),
    private Long parentId;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "enumDictionary")
//    private Set<FormBody> formBodies = new HashSet<>();

    public EnumDictionary() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

//    public Set<FormBody> getFormBodies() {
//        return formBodies;
//    }
//
//    public void setFormBodies(Set<FormBody> formBodies) {
//        this.formBodies = formBodies;
//    }
}
