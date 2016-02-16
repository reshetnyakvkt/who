package questionnaires.domain;

import javax.persistence.*;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 22.10.2015
 */

@Entity
@Table(name = "S_USER")
public class User {
    @Id
    @SequenceGenerator(name = "sequence_s_user", sequenceName = "SEQ_S_USER",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_s_user")
    @Column(name = "ID")
    private Long id;

    @Column (name = "LOGIN", length = 25, nullable = false)
    private String login;

    @Column (name = "PASSWORD", length = 25, nullable = false)
    private String password;

    @Column (name = "ADMIN_USER", length = 2)
    private Integer permission;

    public User() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", permission=" + permission +
                '}';
    }
}
