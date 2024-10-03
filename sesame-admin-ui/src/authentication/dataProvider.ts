import {httpClient} from "ra-keycloak";
import keycloak from "./keycloak.ts";
import {addRefreshAuthToDataProvider} from "react-admin";
import {refreshAuth} from "./refreshAuth.ts";

const provider = (baseUrl: string) => ({
    getList: async (resource: string, params: any) => {
      const client = httpClient(keycloak);
      const {page, perPage} = params.pagination;
      const [serviceName, serviceResource] = resource.split(":");

      const url = `${baseUrl}/api/v1/${serviceName}/${serviceResource}?number=${page - 1}&size=${perPage}`;

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
      const [serviceName, serviceResource] = resource.split(":");

      const url = `${baseUrl}/api/v1/${serviceName}/${serviceResource}/${id}`;

      return client(url, {})
        .then(({json}) => (
          {
            data: json
          }
        ))
    }
  }
);

export const dataProvider = (baseUrl: string) => addRefreshAuthToDataProvider(provider(baseUrl), refreshAuth);