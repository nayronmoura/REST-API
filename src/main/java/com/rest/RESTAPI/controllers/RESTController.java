package com.rest.RESTAPI.controllers;

import com.rest.RESTAPI.ExternalAPIS.CEP;
import com.rest.RESTAPI.dtos.RESTdto;
import com.rest.RESTAPI.models.CEPModel;
import com.rest.RESTAPI.models.RESTModel;
import com.rest.RESTAPI.services.RESTservices;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;

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
        var modelREST = new RESTModel();
        var modelCEP = new CEPModel();

        BeanUtils.copyProperties(resTdto, modelREST);
        modelREST.setTimestamp(LocalDateTime.now(ZoneId.of("UTC")));

        modelCEP = CEP.consult(modelREST.getCep());
        modelREST.setCity(modelCEP.getLocalidade());
        modelREST.setDistrict(modelCEP.getLogradouro());
        modelREST.setStates(modelCEP.getUf());

        return ResponseEntity.status(HttpStatus.CREATED).body(resTservices.save(modelREST));
    }

}

