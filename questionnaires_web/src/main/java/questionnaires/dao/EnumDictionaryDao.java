package questionnaires.dao;


import questionnaires.domain.EnumDictionary;

import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
public interface EnumDictionaryDao {
    Long create(EnumDictionary enumDictionary);
    EnumDictionary read(Long id);
    boolean update(EnumDictionary enumDictionary);
    boolean delete(EnumDictionary enumDictionary);
    List<EnumDictionary> findAll();
    List<EnumDictionary> findAllByFormTitleId(Long id);
}
