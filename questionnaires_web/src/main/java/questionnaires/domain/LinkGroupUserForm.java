package questionnaires.domain;

import javax.persistence.*;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
@Entity
@Table(name = "LINK_GU_FORM")
public class LinkGroupUserForm {
    @Id
    @SequenceGenerator(name = "sequence_link_gu_form", sequenceName = "SEQ_LINK_GU_FORM_ID",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_link_gu_form")
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREATOR_ID", length = 15)     //NUMBER(15, 0),
    private Long creatorId;

    @Column(name = "LAST_EDITOR_ID", length = 15)// NUMBER(15, 0),
    private Long lastEditorId;

    @Column(name = "COMMENTS", length = 255)      // VARCHAR2(255 BYTE),
    private String comments;

    @Column(name = "GROUP_USER_ID", length = 15)// NUMBER(15, 0),
    private Long groupUserId;

    @Column(name = "FORM_TITLE_ID", length = 15)// NUMBER(15, 0),
    private Long formTitleId;

    public LinkGroupUserForm() {
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

    public Long getGroupUserId() {
        return groupUserId;
    }

    public void setGroupUserId(Long groupUserId) {
        this.groupUserId = groupUserId;
    }

    public Long getFormTitleId() {
        return formTitleId;
    }

    public void setFormTitleId(Long formTitleId) {
        this.formTitleId = formTitleId;
    }
}
