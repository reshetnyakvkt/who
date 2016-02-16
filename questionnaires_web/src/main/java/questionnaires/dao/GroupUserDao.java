package questionnaires.dao;

import questionnaires.domain.GroupUser;
import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
public interface GroupUserDao {
    Long create(GroupUser groupUser);
    GroupUser read(Long id);
    boolean update(GroupUser groupUser);
    boolean delete(GroupUser groupUser);
    List<GroupUser> findAll();
}
