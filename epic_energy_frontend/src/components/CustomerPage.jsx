import { Container, Row } from "react-bootstrap";
import CustomerList from "./CustomerList";
import ModalCustomer from "./ModalCustomer";

function CustomerPage() {
  return (
    <Container className="my-4 mx-2">
      <h1 className="mb-5">Registro Clienti</h1>
      <CustomerList />
      <Row className="mx-1">
        <ModalCustomer action="POST" />
      </Row>
    </Container>
  );
}

export default CustomerPage;
