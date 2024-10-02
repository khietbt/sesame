import {httpClient} from "ra-keycloak";
import keycloak from "./keycloak.ts";

export const dataProvider = {
  getList: async (resource: any, params: any) => {
    const client = httpClient(keycloak);
    const number = 0;
    const size = 1;

    const url = `http://localhost:8004/users?number=${number}&size=${size}`;

    return client(url, {})
      .then(
        ({json}) => ({
          data: json.content,
          total: json.page.totalPages
        })
      )
  },
  getOne: async (resource: string, params: any) => {
    const {id} = params;

    const client = httpClient(keycloak);

    const url = `http://localhost:8004/${resource}/${id}`;

    return client(url, {})
      .then(({json}) => (
        {
          data: json
        }
      ))
  }
}