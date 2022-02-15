package com.rest.RESTAPI.services;

import com.rest.RESTAPI.models.RESTModel;
import com.rest.RESTAPI.repositories.RESTRepositorie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public boolean existsByCPF(String cpf){
        return repositorie.existsBycpf(cpf);
    }

    public List<RESTModel> findAll(){
        List<RESTModel> list =  repositorie.findAll();
        for (RESTModel model: list) {
            String cpf = model.getCpf();
            model.setCpf(cpf.substring(0,3) + ".***.***-" + cpf.substring(9,11));
        }
        return list;
    }

    public Optional<RESTModel> findById(UUID id){
        return repositorie.findById(id);
    }

    @Transactional
    public void delete(RESTModel model){
        repositorie.delete(model);
    }
}
