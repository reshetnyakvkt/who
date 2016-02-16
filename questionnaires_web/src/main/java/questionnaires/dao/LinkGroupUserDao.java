package questionnaires.dao;

import questionnaires.domain.LinkGroupUser;
import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
public interface LinkGroupUserDao {
    Long create(LinkGroupUser linkGroupUser);
    LinkGroupUser read(Long id);
    boolean update(LinkGroupUser linkGroupUser);
    boolean delete(LinkGroupUser linkGroupUser);
    List<LinkGroupUser> findAll();
}
