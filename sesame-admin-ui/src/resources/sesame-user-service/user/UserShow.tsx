import {Show, SimpleShowLayout, TextField} from "react-admin";

export const UserShow = (
  <Show>
    <SimpleShowLayout>
      <TextField source="id"/>
      <TextField source="name"/>
      {/*<RichTextField source="body" />*/}
      {/*<DateField label="Publication date" source="published_at" />*/}
    </SimpleShowLayout>
  </Show>
)