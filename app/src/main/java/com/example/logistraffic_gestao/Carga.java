package com.example.logistraffic_gestao;

import java.sql.Timestamp;

public class Carga {

    private Integer id;
    private String nome;
    private String tipo;
    private String cais;
    private String matricula;
    private Integer motorista_id;
    private Integer loja_id;
    //private String data_carga;

public Carga(Integer id,String nome,String tipo,String cais,String matricula,Integer motorista_id,Integer loja_id/*String data_carga*/){
    this.id = id;
    this.nome=nome;
    this.tipo=tipo;
    this.cais=cais;
    this.matricula=matricula;
    this.motorista_id=motorista_id;
    this.loja_id=loja_id;
   // this.data_carga=data_carga;





}

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCais(String cais) {
        this.cais = cais;
    }
/*
    public void setData_carga(String data_carga) {
        this.data_carga = data_carga;
    }

 */

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getLoja_id() {
        return loja_id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCais() {
        return cais;
    }

    public String getMatricula() {
        return matricula;
    }

    public Integer getMotorista_id() {
        return motorista_id;
    }
/*
    public String getData_carga() {
        return data_carga;
    }

 */

}

