package io.github.khietbt.miscellaneous;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuditorAwareImpl implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null
      || !authentication.isAuthenticated()
      || authentication instanceof AnonymousAuthenticationToken) {
      return Optional.empty();
    }

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    return Optional.ofNullable(userDetails.getUsername());

  }
}
