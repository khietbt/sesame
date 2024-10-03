import {Admin, EditGuesser, Resource,} from "react-admin";
import {Layout} from "./Layout";
import {dataProvider} from "./authentication/dataProvider.ts";
import {UserList} from "./resources/sesame-user-service/user";
import {UserShow} from "./resources/sesame-user-service/user/UserShow.tsx";
import {authProvider} from "./authentication/authProvider.ts";

export const App = () => {
  return (
    <Admin
      layout={Layout}
      dataProvider={dataProvider("http://localhost:8003")}
      authProvider={authProvider}
    >
      <Resource
        name="sesame-user-service:users"
        options={{label: "Users"}}
        list={UserList}
        edit={EditGuesser}
        show={UserShow}
      />
    </Admin>
  );
}
