/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitec.org.mongounitec;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@CrossOrigin
public class ControladorMensajito {
    
    @Autowired RepositorioMensajito repoMensa;
    //aqui a continuacion van las 5 operaciones basicas con la entidad 
    
    //Metodo Get: Buscar Todos
    @RequestMapping (value="/mensajito", method=RequestMethod.GET , headers = {"Accept=application/json"})
    
    public ArrayList <Mensajito> obtenerTodos() throws Exception{ return (ArrayList <Mensajito>) repoMensa.findAll();
    }
//metodo Get: Buscar por Id
    @RequestMapping(value="/mensajito/{id}", method=RequestMethod.GET, headers= {"Accept=application/json"})
public Mensajito obtenerPorId (@PathVariable String id)
throws Exception {
return repoMensa.findOne (id);
}

//Metodo POST: guardarversion para clientes variables (web y desktop)
@RequestMapping(value="/mensajito/{titulo}/{cuerpo}",method = RequestMethod.POST, headers = {"Accept=application/json"})
public Estatus guardarMensajito(@PathVariable String titulo, @PathVariable String cuerpo) throws Exception{
    repoMensa.save(new Mensajito (titulo,cuerpo));
    Estatus estatus=new Estatus();
    estatus.setSuccess(true);
    return estatus;
}
//metodo POST: guardar, pero es una version mas pura y efectiva 
@RequestMapping(value="/mensajito ", method=RequestMethod.POST, headers = {"Accept=application/json"})
public Estatus guardarMensajitoMejorado(@RequestBody String json)
        throws Exception{
    //Transfromamos el json a objeto java 
    ObjectMapper maper=new ObjectMapper();
    Mensajito mensa=maper.readValue(json, Mensajito.class);
    repoMensa.save(mensa);
    Estatus es=new Estatus();
    es.setSuccess(true);
    return es;
}

}



