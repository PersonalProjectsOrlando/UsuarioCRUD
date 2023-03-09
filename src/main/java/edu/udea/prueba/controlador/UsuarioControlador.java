package edu.udea.prueba.controlador;

import edu.udea.prueba.business.GestorUsuario;
import edu.udea.prueba.models.ObjetoRespuesta;
import edu.udea.prueba.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UsuarioControlador {
    @Autowired
 private GestorUsuario gestorUsuario;
   @GetMapping("/usuarios")
   public ResponseEntity<ArrayList<Usuario>> getUsuarios(){
          return new ResponseEntity<>(gestorUsuario.getUsuarios(), HttpStatus.ACCEPTED);
   }
    //Metodo con QueryParam
   @GetMapping("/usuario")
    public ResponseEntity<Object> getUsuario(@RequestParam String nombreUsuario){
       try {
           Usuario usuario=gestorUsuario.getUsuario(nombreUsuario);
           return new ResponseEntity<>(usuario,HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }

   }


    //Metodo con PathParam
    @GetMapping("/usuario/{id}")
    public ResponseEntity<String> getUsuarioPath(@PathVariable String id){
       return new ResponseEntity<>("pathParams "+id,HttpStatus.OK);
    }
    //Create
    @PostMapping("/usuario")
    public ResponseEntity<String> postUsuario(@RequestBody Usuario usuario){
       try{
           String user=gestorUsuario.setUsuario(usuario);
           return new ResponseEntity<>(user,HttpStatus.OK);
       }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }
    //Update
    @PutMapping("/usuario/{nombre}")
    public ResponseEntity<ObjetoRespuesta> putUsuario(@RequestBody Usuario usuarioUpdate, @PathVariable String nombre){

        try {
            Usuario usuarioBd = gestorUsuario.updateUsuarioAll(usuarioUpdate, nombre);
            return new ResponseEntity<>(new ObjetoRespuesta("Actualizacion exitosa",usuarioBd),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ObjetoRespuesta(e.getMessage(),null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/usuario/{nombre}")
    public ResponseEntity<ObjetoRespuesta> patchUsuario(@RequestBody Usuario usuarioUpdate,@PathVariable String nombre ){

        try {
            Usuario usuarioBd = gestorUsuario.updateUsuario(usuarioUpdate, nombre);
            return new ResponseEntity<>(new ObjetoRespuesta("Actualizacion exitosa",usuarioBd),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ObjetoRespuesta(e.getMessage(),null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/usuario/{nombre}")
    public ResponseEntity<ObjetoRespuesta> deleteUsuario(@PathVariable String nombre){
        try{
            String mensaje= gestorUsuario.deleteUsuario(nombre);
            return new ResponseEntity<>(new ObjetoRespuesta(mensaje, null), HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(new ObjetoRespuesta(e.getMessage(),null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
