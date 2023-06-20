package com.javatechie.spring.okta.sso.security;

import java.io.IOException;
import java.util.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javatechie.spring.okta.sso.dao.Applicationinformations;
import com.javatechie.spring.okta.sso.dao.mapper.AppInfoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.UserList;

@Component
public class OktaSecurityFilter extends AbstractAuthenticationProcessingFilter {

    public static final String ID_USER = "IdUser";
    private Client client;
    private JdbcTemplate jdbcTemplate;


    protected OktaSecurityFilter(RequestMatcher requiresAuthenticationRequestMatcher, Client client, JdbcTemplate jdbcTemplate) {
        super(requiresAuthenticationRequestMatcher);
        this.client = client;
        this.jdbcTemplate = jdbcTemplate;
        // TODO Auto-generated constructor stub
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {
        String applicationCode = request.getParameter("app");


        if (applicationCode != null) {
            UserList clientlist = client.listUsers(request.getParameter("mail"), null, null, null, null);
            if (clientlist != null) {
                if (clientlist.single() != null) {
                    String docId = (String) clientlist.single().getProfile().get("docID");

                    List<Applicationinformations> applicationinformations = getUrlForwordFromApplicationCode(applicationCode);

                    if (applicationinformations.isEmpty()) {
                        // ERROR
                    }
                    StringBuilder modifiedUrlApp = new StringBuilder();
                    if (applicationinformations.size() == 1) {

                        boolean isDocIDUrlpar = applicationinformations.get(0).getisDocIDUrlpar();

                        if (isDocIDUrlpar) {
                            modifiedUrlApp.append(applicationinformations.get(0).getUrlApp()).append("?").append(applicationinformations.get(0).getNomUrlpar()).append("=").append(docId);

                        } else {
                            // ERREUR

                        }

                    } else {
                        Optional<Applicationinformations> firstApplicationinformation = applicationinformations.stream().filter(Applicationinformations::getIsfirstUrlpar).findFirst();

                        if (firstApplicationinformation.isPresent()) {
                            modifiedUrlApp.append(firstApplicationinformation.get().getUrlApp()).append("?").append(firstApplicationinformation.get().getNomUrlpar()).append("=").append(firstApplicationinformation.get().getNomUrlpar().equals(ID_USER) ? docId : firstApplicationinformation.get().getValueUrlpar());
                            for (Applicationinformations info : applicationinformations) {
                                if (!info.getNomUrlpar().equals(firstApplicationinformation.get().getNomUrlpar())) {
                                    //info.getValueUrlpar() != null &&
                                    modifiedUrlApp.append("&").append(info.getNomUrlpar()).append("=").append(info.getNomUrlpar().equals(ID_USER) ? docId : info.getValueUrlpar());
                                }
                            }
                        } else {
                            applicationinformations.sort((o1, o2) -> o1.getOrderUrlpar().compareTo(o2.getOrderUrlpar()));
                            StringBuilder urlParams = new StringBuilder();
                            String operator = "?";
                            for (Applicationinformations info : applicationinformations) {
                                //if (/*info.getValueUrlpar() != null || */ info.getNomUrlpar().equals(ID_USER)) {

                                urlParams.append(operator)
                                        .append(info.getNomUrlpar())
                                        .append("=")
                                        .append(info.getNomUrlpar().equals(ID_USER) ? docId : info.getValueUrlpar());
                                operator = "&";
                                //}
                            }
                            modifiedUrlApp.append(applicationinformations.get(0).getUrlApp()).append(urlParams.toString());
                        }


                        //response.sendRedirect(applicationinformations.get(0).getUrlApp() + docId);
                    }
                    response.sendRedirect(modifiedUrlApp.toString());


                }


            }
        }
        // TODO Auto-generated method stub
        return SecurityContextHolder.getContext().getAuthentication();
    }


    /*	private Applicationinformations findMinOrderUrlpar(List<Applicationinformations> applicationinformations) {
            // Logique pour trouver l'élément avec le min(orderUrlpar)
            Applicationinformations minOrderUrlpar = null;
            int minOrder = Integer.MAX_VALUE;

            for (Applicationinformations info : applicationinformations) {
                int order = info.getOrderUrlpar();
                if (order < minOrder) {
                    minOrder = order;
                    minOrderUrlpar = info;
                }
            }

            return minOrderUrlpar;
        }*/
    private List<Applicationinformations> getUrlForwordFromApplicationCode(String applicationCode) {
        String query = "SELECT BIFROST_DEV.dbo.APPLICATION.idApp, BIFROST_DEV.dbo.APPLICATION.nomApp, BIFROST_DEV.dbo.APPLICATION.typeApp, BIFROST_DEV.dbo.APPLICATION.lkpAnnu, BIFROST_DEV.dbo.APPLICATION.referentApp, BIFROST_DEV.dbo.APPLICATION.urlApp, BIFROST_DEV.dbo.APPLICATION.descApp, BIFROST_DEV.dbo.APPLICATION.actifApp, BIFROST_DEV.dbo.APPLICATION.lotApp, BIFROST_DEV.dbo.APPLICATION.majLe, BIFROST_DEV.dbo.APPLICATION.majPar, " +
                "BIFROST_DEV.dbo.URLPARAM.nomUrlpar, BIFROST_DEV.dbo.URLPARAM.isfirstUrlpar,  BIFROST_DEV.dbo.URLPARAM.valueUrlpar ,BIFROST_DEV.dbo.URLPARAM.isDocIDUrlpar, BIFROST_DEV.dbo.URLPARAM.orderUrlpar " +
                "FROM BIFROST_DEV.dbo.APPLICATION LEFT OUTER JOIN BIFROST_DEV.dbo.URLPARAM " +
                "ON (BIFROST_DEV.dbo.APPLICATION.idApp = BIFROST_DEV.dbo.URLPARAM.idApp) " +
                "WHERE BIFROST_DEV.dbo.APPLICATION.idApp= ?";
        List<Applicationinformations> applications = (List<Applicationinformations>) jdbcTemplate.query(
                query, new AppInfoRowMapper(), applicationCode);
        return applications;

    }

   @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }


}
