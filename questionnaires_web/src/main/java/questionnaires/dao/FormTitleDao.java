package questionnaires.dao;

import questionnaires.domain.FormTitle;
import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
public interface FormTitleDao {
    Long create(FormTitle formTitle);
    FormTitle read(Long id);
    boolean update(FormTitle formTitle);
    boolean delete(FormTitle formTitle);
    List<FormTitle> findAll();
    List<FormTitle> findByUserId(Long userId);
}
