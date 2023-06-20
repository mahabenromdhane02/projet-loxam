package com.javatechie.spring.okta.sso.dao;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Applicationinformations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idApp;
    private String nomApp;
    private String typeApp;
    private Integer lkpAnnu;
    private String referentApp;
    //@Column(nullable = false, unique = true, length = 100)
    private String urlApp;
    private String descApp;
    private Boolean actifApp;
    private Integer lotApp;
    private Date majLe;
    private String majPar;
    private String nomUrlpar;
    private boolean isfirstUrlpar;
    private boolean isDocIDUrlpar;
    private Integer orderUrlpar;

    private String valueUrlpar;


    @Override
    public String toString() {
        return "Applicationinformations{" +
                "idApp=" + idApp +
                '}';
    }



    public String getIdApp() {
        return idApp;
    }

    public void setIdApp(String idApp) {
        this.idApp = idApp;
    }

    public String getNomApp() {
        return nomApp;
    }

    public void setNomApp(String nomApp) {
        this.nomApp = nomApp;
    }

    public String getTypeApp() {
        return typeApp;
    }

    public void setTypeApp(String typeApp) {
        this.typeApp = typeApp;
    }

    public Integer getLkpAnnu() {
        return lkpAnnu;
    }

    public void setLkpAnnu(Integer lkpAnnu) {
        this.lkpAnnu = lkpAnnu;
    }

    public String getReferentApp() {
        return referentApp;
    }

    public void setReferentApp(String referentApp) {
        this.referentApp = referentApp;
    }

    public String getUrlApp() {
        return urlApp;
    }

    public void setUrlApp(String urlApp) {
        this.urlApp = urlApp;
    }

    public String getDescApp() {
        return descApp;
    }

    public void setDescApp(String descApp) {
        this.descApp = descApp;
    }

    public Boolean getActifApp() {
        return actifApp;
    }

    public void setActifApp(Boolean actifApp) {
        this.actifApp = actifApp;
    }

    public Integer getLotApp() {
        return lotApp;
    }

    public void setLotApp(Integer lotApp) {
        this.lotApp = lotApp;
    }

    public Date getMajLe() {
        return majLe;
    }

    public void setMajLe(Date majLe) {
        this.majLe = majLe;
    }

    public String getMajPar() {
        return majPar;
    }

    public void setMajPar(String majPar) {
        this.majPar = majPar;
    }

    public String getNomUrlpar() {
        return nomUrlpar;
    }

    public void setNomUrlpar(String nomUrlpar) {
        this.nomUrlpar = nomUrlpar;
    }

    public boolean getIsfirstUrlpar() {
        return isfirstUrlpar;
    }

    public void setIsfirstUrlpar(boolean isfirstUrlpar) {
        this.isfirstUrlpar = isfirstUrlpar;
    }

    public boolean getisDocIDUrlpar() {
        return isDocIDUrlpar;
    }

    public void setDocIDUrlpar(boolean docIDUrlpar) {
        this.isDocIDUrlpar = docIDUrlpar;
    }
    public Integer getOrderUrlpar() {
        return orderUrlpar;
    }

    public void setOrderUrlpar(Integer orderUrlpar) {
        this.orderUrlpar = orderUrlpar;
    }


    public String getValueUrlpar() {
        return valueUrlpar;
    }

    public void setValueUrlpar(String valueUrlpar) {
        this.valueUrlpar = valueUrlpar;
    }
}
