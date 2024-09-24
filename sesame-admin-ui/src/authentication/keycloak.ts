import Keycloak from 'keycloak-js';

const keycloak = new Keycloak({
  url: 'http://localhost:8080',
  realm: 'sesame',
  clientId: 'sesame-admin-ui-client'
});

try {
  const authenticated = await keycloak.init({
    onLoad: 'login-required'
  });
  console.log(`User is ${authenticated ? 'authenticated' : 'not authenticated'}`);
} catch (error) {
  console.error('Failed to initialize keycloak adapter:', error);
}

export default keycloak;