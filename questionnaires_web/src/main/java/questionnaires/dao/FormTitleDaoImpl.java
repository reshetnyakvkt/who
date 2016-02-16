package questionnaires.dao;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import questionnaires.domain.FormTitle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 Created by IntelliJ IDEA
 User: reashetnyak_viktor
 Date: 23.10.2015
 */
@Repository("formTitleDao")
@Transactional
public class FormTitleDaoImpl implements FormTitleDao {
    private static Logger log = Logger.getLogger(FormTitleDaoImpl.class);
    @Autowired
    private SessionFactory factory;

    public FormTitleDaoImpl() {
    }

    @Override
    public Long create(FormTitle formTitle) {
        return (Long) factory.getCurrentSession().save(formTitle);
    }

    @Override
    public FormTitle read(Long id) {
        return (FormTitle) factory.getCurrentSession().get(FormTitle.class, id);
    }

    @Override
    public boolean update(FormTitle formTitle) {
        try{
            factory.getCurrentSession().update(formTitle);
            return true;
        } catch (Exception ex){
            log.error("Error execute update: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(FormTitle formTitle) {
        try{
            factory.getCurrentSession().delete(formTitle);
            return true;
        } catch (Exception ex){
            log.error("Error execute delete: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<FormTitle> findAll() {
            return factory.getCurrentSession().createQuery("FROM FormTitle").list();
    }

    @Override
    public List<FormTitle> findByUserId(Long userId) {
        try {
            return factory.getCurrentSession().createQuery("FROM FormTitle ft"
                    //" INNER JOIN LinkGroupUserForm lgf ON ft.id = lgf.formTitleId"
//                    " JOIN GroupUser gu ON gu.id = lgf.groupUserId" +
//                    " JOIN LinkGroupUser lgu ON lgu.groupUserId = gu.id"
//                    " WHERE lgu.userId=:userId"
                    )
//                    .setParameter("userId", userId)
                    .list();
//            if (resAll == null) return null;
//            List<FormTitle> resUnq = new ArrayList<>();
//            boolean isExists;
//            for (FormTitle formTitle : (List<FormTitle>)resAll){
//                if (resUnq.size() != 0){
//                    isExists = resUnq.contains(formTitle);
//                } else {
//                    isExists = false;
//                }
//                if (!isExists){
//                    resUnq.add(formTitle);
//                }
//            }
//            return resUnq;

            //uniqueResult()
            /*
            return factory.getCurrentSession().createQuery("FROM FormTitle as ft Where " +
                    " EXISTS elements(From LinkGroupUser as lgu \n" +
                    "  JOIN GroupUser as gu ON gu.id = lgu.groupUserId \n" +
                    "  JOIN LinkGroupUserForm lgf ON gu.id = lgf.groupUserId  \n" +
                    "  WHERE lgf.formTitleId = ft.ID AND lgu.userId=:userId)")
                    .setParameter("userId", userId).list();
            */
        } catch (Exception ex){
            log.error("Error execute findByUserId: ", ex);
            return null;
        }
    }
}
