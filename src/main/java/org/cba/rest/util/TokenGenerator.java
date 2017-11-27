package org.cba.rest.util;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.cba.model.entities.Role;
import org.cba.model.entities.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TokenGenerator {
    public String generateToken(User user) throws JOSEException {
        List<String> roles = getRolesAsStringList(user);
        String issuer = "semester3project-fbbc";

        JWSSigner signer = new MACSigner(System.getenv("PROP_SECRET_TOKEN"));
        Date now = new Date();
        Date after7Days = new Date(now.getTime() + 1000 * 60 * 60 * 24 * 7);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .claim("username", user.getUsername())
                .claim("id", user.getId())
                .claim("roles", roles)
                .claim("issuer", issuer)
                .issueTime(now)
                .expirationTime(after7Days)
                .build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        signedJWT.sign(signer);
        return signedJWT.serialize();
    }

    private List<String> getRolesAsStringList(User user) {
        List<String> list = new ArrayList<>();
        for (Role role : user.getRoles()) {
            list.add(role.getName());
        }
        return list;
    }
}
