package com.rest.RESTAPI.services;

import com.rest.RESTAPI.models.RESTModel;
import com.rest.RESTAPI.repositories.RESTRepositorie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RESTservices {

    private final RESTRepositorie repositorie;

    public RESTservices(RESTRepositorie repositorie) {
        this.repositorie = repositorie;
    }

    @Transactional
    public RESTModel save(RESTModel model){
        return repositorie.save(model);
    }
    
}
