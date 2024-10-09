package io.github.khietbt.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SesameJwtGrantedAuthorityConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        var claims = jwt.getClaims();
        var realmAccess = (Map<String, Object>) claims.get("realm_access");
        var realmRoles = (List<String>) realmAccess.get("roles");

        var resourceAccess = (Map<String, Map<String, List<String>>>) claims.get("resource_access");
        var clientRoles = (List<String>) resourceAccess.get("sesame-client").get("roles");

        return Stream.concat(realmRoles.stream(), clientRoles.stream()).map(
                s -> (GrantedAuthority) () -> s
        ).collect(Collectors.toSet());
    }
}
