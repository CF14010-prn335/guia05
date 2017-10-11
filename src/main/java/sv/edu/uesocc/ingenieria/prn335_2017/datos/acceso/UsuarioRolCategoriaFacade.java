/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.UsuarioRolCategoria;

/**
 *
 * @author root
 */
@Stateless
public class UsuarioRolCategoriaFacade extends AbstractFacade<UsuarioRolCategoria> implements UsuarioRolCategoriaFacadeLocal {

    @PersistenceContext(unitName = "sv.edu.uesocc.ingenieria.prn335_2017_Posts_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioRolCategoriaFacade() {
        super(UsuarioRolCategoria.class);
    }
    
}
