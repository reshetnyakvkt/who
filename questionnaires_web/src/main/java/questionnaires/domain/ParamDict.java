package questionnaires.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
@Entity
@Table(name = "PARAM_DICT")
public class ParamDict {
    @Id
    @SequenceGenerator(name = "sequence_param_dict", sequenceName = "SEQ_PARAM_DICT_ID",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_param_dict")
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREATOR_ID", length = 15)   // NUMBER(15, 0),
    private Long creatorId;

    @Column(name = "LAST_EDITOR_ID", length = 15) // NUMBER(15, 0),
    private Long lastEditorId;

    @Column(name = "NAME", length = 255)      // VARCHAR2(255 BYTE),
    private String name;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paramDict")
//    private Set<FormBody> formBodies = new HashSet<>();

    public ParamDict() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastEditorId(Long lastEditorId) {
        this.lastEditorId = lastEditorId;
    }

    @Override
    public String toString() {
        return "ParamDict{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
