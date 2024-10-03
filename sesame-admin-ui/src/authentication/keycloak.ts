import Keycloak from 'keycloak-js';

const keycloak = new Keycloak({
  url: 'http://localhost:8080',
  realm: 'sesame',
  clientId: 'sesame-admin-ui-client'
});

try {
  await keycloak.init({
    onLoad: 'login-required'
  });

  keycloak.onTokenExpired = () => {
    keycloak.updateToken(10).then(r => {
      if (r) {
        console.log("Access token has been refreshed successfully");

        return;
      }

      console.error("Failed to refresh access token.")
    });
  }
} catch (error) {
  console.error('Failed to initialize keycloak adapter:', error);
}


export default keycloak;