package io.github.khietbt.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class SesameAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Autowired
    private SesameJwtGrantedAuthorityConverter converter;

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        return new JwtAuthenticationToken(jwt, this.converter.convert(jwt));
    }
}
