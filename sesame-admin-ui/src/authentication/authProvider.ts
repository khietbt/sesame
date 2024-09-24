// import {AuthProvider} from "react-admin";
// import keycloakClient from "./keycloak.ts";
// import {KeycloakTokenParsed} from "keycloak-js";
// import jwt_decode from 'jwt-decode';
//
// /**
//  * This authProvider is only for test purposes. Don't use it in production.
//  */
// export const authProvider: AuthProvider = {
//   login: async () => {
//     return keycloakClient.login({
//       redirectUri: 'http://localhost:5173'
//     })
//   },
//
//   logout: async () => {
//     return keycloakClient.logout({redirectUri: 'http://localhost:5173'});
//   },
//   checkError: async () => Promise.resolve(),
//   checkAuth: async () => keycloakClient.authenticated && keycloakClient.token ? Promise.resolve()
//     : Promise.reject('Failed to obtain access token.'),
//
//   getPermissions: async () => {
//     return Promise.resolve(undefined);
//   },
//
//   getIdentity: () => {
//     if (keycloakClient.token) {
//       const decoded = jwt_decode<KeycloakTokenParsed>(keycloakClient.token);
//       const id = decoded.sub || '';
//       const fullName = decoded.preferred_username;
//       return Promise.resolve({id, fullName});
//     }
//     return Promise.reject('Failed to get identity.');
//   },
// };
//
// export default authProvider;
