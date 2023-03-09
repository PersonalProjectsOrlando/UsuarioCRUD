package edu.udea.prueba.business;
import edu.udea.prueba.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class GestorUsuario {
    private ArrayList<Usuario> usuarios;
    public GestorUsuario(){
        this.usuarios = new ArrayList<>();
        this.usuarios.add(new Usuario("OrlandoB","oralbama","12345"));
        this.usuarios.add(new Usuario("PilarP","lupipa","6789"));

    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario(String nombre) throws Exception {
        //Usuario usuario = new Usuario();
        for(Usuario usuario: this.usuarios ){
            if(usuario.getNombre().equals(nombre)){
                return usuario;
            }
        }
        throw new Exception("Usuario No existe :(");
    }
    // Metodo para guardar un usuario
    public String setUsuario(Usuario usuario) throws Exception {
        try{
            getUsuario(usuario.getNombre());

        }catch(Exception e){
            this.usuarios.add(usuario);
            return "Creacion del usuario exitosa";
        }
        //Error si el usuario existe
        throw  new Exception("Usuario Existe");
    }
    public Usuario updateUsuario(Usuario usuario_update,String nombre) throws Exception{
        try{
            //Usamos la siguiente sentencia si no queremos usar como parametro Strin nombre de updateusuario()
            //Usuario usuarioBd=getUsuario(usuario_update.getNombre());
            Usuario usuarioBd=getUsuario(nombre);
            if(usuario_update.getNombre() != null && !usuario_update.getNombre().equals("")){
                usuarioBd.setNombre(usuario_update.getNombre());
            }
            if(usuario_update.getNombreUsuario() != null && !usuario_update.getNombreUsuario().equals("")){
                usuarioBd.setNombreUsuario(usuario_update.getNombreUsuario());
            }
            if(usuario_update.getPassword() != null && !usuario_update.getPassword().equals("")){
                usuarioBd.setPassword(usuario_update.getPassword());
            }
            return  usuarioBd;

        }catch(Exception e){
            throw new Exception("Usuario No existe, fallo actualizacion de datos");

        }
    }
    public Usuario updateUsuarioAll(Usuario usuarioUpdate, String nombre) throws Exception{
        try {
            Usuario usuarioBd=getUsuario(nombre);
            usuarioBd.setNombreUsuario(usuarioUpdate.getNombreUsuario());
            usuarioBd.setNombre(usuarioUpdate.getNombre());
            usuarioBd.setPassword(usuarioUpdate.getPassword());
            return usuarioBd;
        } catch (Exception e) {
            throw new Exception("Usuario No existe, fallo actualizacion de datos");
        }
    }
    public String deleteUsuario(String nombre) throws Exception {
        try{
            Usuario usuarioBd=getUsuario(nombre);
            this.usuarios.remove(usuarioBd);
            return "Usuario eliminado";

        }catch (Exception e){
            throw new Exception("Usuario no existe para eliminar");
        }

    }
}
