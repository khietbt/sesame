import {Admin, EditGuesser, ListGuesser, Resource, ShowGuesser,} from "react-admin";
import {Layout} from "./Layout";
import {dataProvider} from "./dataProvider";
import {keycloakAuthProvider} from "ra-keycloak";
import keycloak from "./authentication/keycloak";

const authProvider = keycloakAuthProvider(keycloak);

export const App = () => {
  return (
    <Admin
      layout={Layout}
      dataProvider={dataProvider}
      authProvider={authProvider}
    >
      <Resource
        name="user"
        list={ListGuesser}
        edit={EditGuesser}
        show={ShowGuesser}
      />
    </Admin>
  );
}
