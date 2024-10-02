import {Datagrid, EditButton, List, Pagination, ShowButton, TextField} from "react-admin";

const UserPagination = () => <Pagination rowsPerPageOptions={[1, 5, 10, 25, 50, 100]}/>;

export const UserList = (
  <List pagination={<UserPagination/>}>
    <Datagrid>
      <TextField source="id"/>
      <TextField source="name"/>
      {/*<DateField source="published_at"/>*/}
      {/*<ReferenceManyCount label="Nb comments" reference="comments" target="post_id" link/>*/}
      {/*<BooleanField source="commentable" label="Com."/>*/}
      {/*<NumberField source="nb_views" label="Views"/>*/}
      <>
        <EditButton/>
        <ShowButton/>
      </>
    </Datagrid>

  </List>
);