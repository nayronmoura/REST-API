package com.rest.RESTAPI.repositories;

import com.rest.RESTAPI.models.RESTModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface RESTRepositorie extends JpaRepository<RESTModel, UUID> {
    boolean existsBycpf(String cpf);

}
