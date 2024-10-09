package io.github.khietbt.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class SesameAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        var converter = new SesameJwtGrantedAuthorityConverter();
        var authorities = converter.convert(jwt);

        return new JwtAuthenticationToken(jwt, authorities);
    }
}
