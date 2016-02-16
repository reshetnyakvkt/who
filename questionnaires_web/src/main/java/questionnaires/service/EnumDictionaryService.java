package questionnaires.service;

import questionnaires.domain.EnumDictionary;

import java.util.List;

/**
 * Created by D2R2 on 27.10.2015.
 */
public interface EnumDictionaryService{
    List<EnumDictionary> findAllByFormTitleId(Long id);
}
