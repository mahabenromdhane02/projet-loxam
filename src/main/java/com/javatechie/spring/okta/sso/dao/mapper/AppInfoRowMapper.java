package com.javatechie.spring.okta.sso.dao.mapper;

import com.javatechie.spring.okta.sso.dao.Applicationinformations;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AppInfoRowMapper implements RowMapper<Applicationinformations> {
    @Override
    public Applicationinformations mapRow(ResultSet rs, int rowNum) throws SQLException {
       Applicationinformations  applicationinformations= new Applicationinformations();
       applicationinformations.setIdApp(rs.getString("idApp"));
        applicationinformations.setNomApp(rs.getString("nomApp"));
        applicationinformations.setTypeApp(rs.getString("typeApp"));
        applicationinformations.setLkpAnnu(rs.getInt("lkpAnnu"));
        applicationinformations.setReferentApp(rs.getString("referentApp"));
        applicationinformations.setUrlApp(rs.getString("urlApp"));
        applicationinformations.setDescApp(rs.getString("descApp"));
        applicationinformations.setActifApp(rs.getBoolean("actifApp"));
        applicationinformations.setLotApp(rs.getInt("lotApp"));
        applicationinformations.setMajLe(rs.getDate("majLe"));
        applicationinformations.setMajPar(rs.getString("majPar"));
        applicationinformations.setNomUrlpar(rs.getString("nomUrlpar"));
        applicationinformations.setValueUrlpar(rs.getString("valueUrlpar"));
        applicationinformations.setIsfirstUrlpar(rs.getBoolean("isfirstUrlpar"));
        applicationinformations.setDocIDUrlpar(rs.getBoolean("isDocIDUrlpar"));
        applicationinformations.setOrderUrlpar(rs.getInt("orderUrlpar"));
        return applicationinformations;
    }
}