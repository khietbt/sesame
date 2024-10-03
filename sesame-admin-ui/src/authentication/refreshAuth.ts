import keycloak from "./keycloak.ts";

export const refreshAuth = async () => {
  if (keycloak.isTokenExpired(120)) {
    await keycloak.updateToken(120);
  }
}