package cz.o2.tvs.db;


import cz.o2.tvs.stb.servlet.RequestSTB;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class NG_STB_ServiceFacade {
    
    Logger _LOG = Logger.getLogger(NG_STB_ServiceFacade.class.getName());
    
    private final EntityManager em;

    public NG_STB_ServiceFacade() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrjServletRequestSTB");
        em = emf.createEntityManager();
    }

    /**
     * All changes that have been made to the managed entities in the
     * persistence context are applied to the database and committed.
     */
    public void commitTransaction() {
        final EntityTransaction entityTransaction = em.getTransaction();
        if (!entityTransaction.isActive()) {
            entityTransaction.begin();
        }
        entityTransaction.commit();
    }

    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public <T> T persistEntity(T entity) {
        em.persist(entity);
        commitTransaction();
        return entity;
    }

    public <T> T mergeEntity(T entity) {
        entity = em.merge(entity);
        commitTransaction();
        return entity;
    }

    public RegisterFw persistRegisterFw(RegisterFw registerFw) {
        em.persist(registerFw);
        commitTransaction();
        return registerFw;
    }

    /** <code>select o from CurrentStbVersionFw o</code> */
    public List<CurrentStbVersionFw> getCurrentStbVersionFwFindAll() {
        return em.createNamedQuery("CurrentStbVersionFw.findAll", CurrentStbVersionFw.class).getResultList();
    }

    /** <code>select o from RegisterFw o</code> */
    public List<RegisterFw> getRegisterFwFindAll() {
        return em.createNamedQuery("RegisterFw.findAll", RegisterFw.class).getResultList();
    }

    /** <code>select o from Model o</code> */
    public List<Model> getModelFindAll() {
        return em.createNamedQuery("Model.findAll", Model.class).getResultList();
    }

    /** <code>select o from ImageStb o</code> */
    public List<ImageStb> getImageStbFindAll() {
        return em.createNamedQuery("ImageStb.findAll", ImageStb.class).getResultList();
    }

    /** <code>select o from CategoryStb o</code> */
    public List<Category> getCategoryFindAll() {
        return em.createNamedQuery("Category.findAll", Category.class).getResultList();
    }

    public int getCountSTBbyMAC() {
        String sqlString = "select count(DISTINCT MAC) from stb;";
        Long result = (Long) em.createNativeQuery(sqlString).getSingleResult();
        return result.intValue();
    }

    public void updateSTB(Stb entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        _LOG.info("AFTER commit MAC = "+entity.getMac());
    }

    public Map getMapSTB() {

        List<Stb> listStb = em.createNamedQuery("Stb.findAll", Stb.class).getResultList();
        Map<Integer, Stb> result;
        result = (Map<Integer, Stb>) listStb.stream().collect(Collectors.toMap(Stb::getId, (p) -> p));

        return result;
    }
}
