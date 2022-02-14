package com.rest.RESTAPI.dtos;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RESTdto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    //@NotNull(message = "CPF is mandatory")
    @CPF(message = "CPF is mandatory")
    private String cpf;

    @NotNull(message = "CEP is mandatory")
    private int cep;

    @NotBlank(message = "Sex is mandatory")
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
