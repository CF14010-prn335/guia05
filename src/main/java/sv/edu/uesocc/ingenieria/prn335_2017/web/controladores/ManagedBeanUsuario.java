package sv.edu.uesocc.ingenieria.prn335_2017.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.UsuarioFacadeLocal;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.Usuario;


@Named(value = "managedBeanUsuario")
@ViewScoped
public class ManagedBeanUsuario implements Serializable {
    
@EJB
private UsuarioFacadeLocal user;

    public ManagedBeanUsuario() {

    }
    
    
     private LazyDataModel<Usuario> modelo;   
     
   @EJB
     private UsuarioFacadeLocal ufl;
     
    private Usuario registro;
        private boolean btnadd = true;
        
         @PostConstruct
        private void inicio(){
            
            try {
                this.modelo = new LazyDataModel<Usuario>() {
                    @Override
                    public Object getRowKey(Usuario object){
                        if(object != null){
                            return object.getIdUsuario();
                        }
                        return null;
                    }
                    @Override
                    public Usuario getRowData(String rowKey){
                        if(rowKey != null && !rowKey.isEmpty() && this.getWrappedData() != null){
                            try {
                                Integer buscado = new Integer(rowKey);
                                for(Usuario reg : (List<Usuario>)getWrappedData()){
                                    if(reg.getIdUsuario().compareTo((buscado))==0){
                                        return reg;
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Error "+ e);
                            }
                        }
                        return null;
                    }
                    @Override
                    public List<Usuario> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
                        
                        List<Usuario> salida= new ArrayList();
                        try {
                            if(ufl != null){
                                this.setRowCount(ufl.count());
                                salida=ufl.findRange(first,pageSize);
                                
                            }
                        } catch (Exception e) {
                            System.out.println("Error "+e);
                        }
                            return salida;
                        
                    }
};
            } catch (Exception e) {
                System.out.println("Error "+e);
            }
            
        }
        public void btnNuevo(){
            this.registro=new Usuario();
           
            
            this.btnadd = !this.btnadd;
        }
        public void guardarRegistro(){
            try {
                
                if(this.registro != null && this.ufl != null){
                    
                                      
                    if(this.ufl.create(registro)){
                        this.btnadd=!this.btnadd;
                        
                    }
                }
            } catch (Exception e) {
                System.out.println("Error "+e);
            }
        }

    public LazyDataModel<Usuario> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<Usuario> modelo) {
        this.modelo = modelo;
    }

    public Usuario getRegistro() {
        return registro;
    }

    public void setRegistro(Usuario registro) {
        this.registro = registro;
    }

    public boolean isBtnadd() {
        return btnadd;
    }

    public void setBtnadd(boolean btnadd) {
        this.btnadd = btnadd;
    }
      
}
