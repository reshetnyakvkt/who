package questionnaires.dao;

import questionnaires.domain.LinkGroupUserForm;

import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
public interface LinkGroupUserFormDao {
    Long create(LinkGroupUserForm linkGroupUserForm);
    LinkGroupUserForm read(Long id);
    boolean update(LinkGroupUserForm linkGroupUserForm);
    boolean delete(LinkGroupUserForm linkGroupUserForm);
    List<LinkGroupUserForm> findAll();
}
