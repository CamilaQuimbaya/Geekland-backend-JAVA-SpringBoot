/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Controllers;

import com.example.demo.Controllers.exceptions.NonexistentEntityException;
import com.example.demo.Models.Detallepagos;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detallepagos")
public class DetallepagosJpaController implements Serializable {

    public DetallepagosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    @PostMapping()
    public String create(@RequestBody  Detallepagos detallepagos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(detallepagos);
            em.getTransaction().commit();
            return "Detalle de pago creado con exito";
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallepagos detallepagos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            detallepagos = em.merge(detallepagos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detallepagos.getId();
                if (findDetallepagos(id) == null) {
                    throw new NonexistentEntityException("The detallepagos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallepagos detallepagos;
            try {
                detallepagos = em.getReference(Detallepagos.class, id);
                detallepagos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallepagos with id " + id + " no longer exists.", enfe);
            }
            em.remove(detallepagos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    @GetMapping("/all")
    public List<Detallepagos> findDetallepagosEntities() {
        return findDetallepagosEntities(true, -1, -1);
    }

    public List<Detallepagos> findDetallepagosEntities(int maxResults, int firstResult) {
        return findDetallepagosEntities(false, maxResults, firstResult);
    }

    private List<Detallepagos> findDetallepagosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallepagos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    @GetMapping("/{id}")
    public Detallepagos findDetallepagos(@PathVariable("id")  Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallepagos.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallepagosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallepagos> rt = cq.from(Detallepagos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
