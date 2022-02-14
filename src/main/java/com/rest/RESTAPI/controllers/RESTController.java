package com.rest.RESTAPI.controllers;

import com.rest.RESTAPI.ExternalAPIS.CEP;
import com.rest.RESTAPI.dtos.RESTdto;

import com.rest.RESTAPI.models.RESTModel;
import com.rest.RESTAPI.services.RESTservices;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class RESTController {

    final RESTservices resTservices;

    public RESTController(RESTservices resTservices) {
        this.resTservices = resTservices;
    }

    @PostMapping()
    public ResponseEntity<Object> saveRegister(@RequestBody @Valid RESTdto resTdto){
        //validate
        if (resTservices.existsByCPF(resTdto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: this cpf have a register");
        }
        var modelREST = new RESTModel();

        BeanUtils.copyProperties(resTdto, modelREST);
        modelREST.setTimestamp(LocalDateTime.now(ZoneId.of("UTC")));

        // faz a consulta em uma api externa para capturar os dados
        var modelCEP = CEP.consult(modelREST.getCep());

        if(modelCEP != null){
        modelREST.setCity(modelCEP.getLocalidade());
        modelREST.setDistrict(modelCEP.getLogradouro());
        modelREST.setStates(modelCEP.getUf());
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: The cep is not valid");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(resTservices.save(modelREST));
    }

    @GetMapping()
    public ResponseEntity<List<RESTModel>> getAllModels(){
        return ResponseEntity.status(HttpStatus.OK).body(resTservices.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneModel(@PathVariable(value = "id")UUID id){
        Optional<RESTModel> model = resTservices.findById(id);
        if(model.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("register not found");
        }else{
            return ResponseEntity.status(HttpStatus.FOUND).body(model.get());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteModel(@PathVariable(value = "id")UUID id){
        Optional<RESTModel> model = resTservices.findById(id);
        if(model.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("register not found");
        }
        resTservices.delete(model.get());
        return ResponseEntity.status(HttpStatus.OK).body("Sucess deleted");

    }
}

