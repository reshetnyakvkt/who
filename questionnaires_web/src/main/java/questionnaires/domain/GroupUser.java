package questionnaires.domain;

import javax.persistence.*;

/**
  Created by IntelliJ IDEA
  User: reashetnyak_viktor
  Date: 23.10.2015
 */
@Entity
@Table(name = "GROUP_USER")
public class GroupUser {
  @Id
  @SequenceGenerator(name = "sequence_group_user", sequenceName = "SEQ_GROUP_USER_ID",
          allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_group_user")
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

  public GroupUser() {
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
}
