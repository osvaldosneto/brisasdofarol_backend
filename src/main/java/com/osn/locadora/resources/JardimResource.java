package com.osn.locadora.resources;

import com.osn.locadora.domain.Jardim;
import com.osn.locadora.services.JardimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/jardim")
public class JardimResource {

    @Autowired
    private JardimService jardimService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Jardim obj){
        jardimService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method =  RequestMethod.GET)
    public ResponseEntity<?> find(){
        Jardim jardim = jardimService.findLast();
        return ResponseEntity.ok().body(jardim);
    }

}
