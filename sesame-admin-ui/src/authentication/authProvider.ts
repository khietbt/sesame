import {addRefreshAuthToAuthProvider, AuthProvider} from "react-admin";
import {keycloakAuthProvider} from "ra-keycloak";
import keycloak from "./keycloak.ts";
import {refreshAuth} from "./refreshAuth.ts";

export const authProvider: AuthProvider = addRefreshAuthToAuthProvider(keycloakAuthProvider(keycloak), refreshAuth);