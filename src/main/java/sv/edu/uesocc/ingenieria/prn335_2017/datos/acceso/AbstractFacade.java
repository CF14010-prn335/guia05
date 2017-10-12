package sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public boolean create(T entity) {
         
        boolean salida = false;
        T e = this.crear(entity);
        if(e != null){
        salida =true;
    }
        return salida;
    }
    
    public T crear(T entity){
      T salida = null;
      try{
          EntityManager em = getEntityManager();
          if(em != null && entity != null){
              em.persist(entity);
              salida = entity;
          }
      }catch(Exception e){
          Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
      }
      return salida;
      }

    
    public boolean edit(T entity) {
      boolean salida = false;
        T e = this.crear(entity);
        if(e != null){
        salida =true;
    }
        return salida;
    }


    
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int inicio,int fin) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(fin);
        q.setFirstResult(inicio);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
