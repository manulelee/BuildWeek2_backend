import { Col, Row } from "react-bootstrap";
import ModalCustomer from "./ModalCustomer";

function CustomerLine({ customer }) {
  return (
    <Row>
      <Col>
        <p>{customer.vatNumber}</p>
      </Col>
      <Col>
        <p>{customer.legalName}</p>
      </Col>
      <Col>
        <ModalCustomer customer={customer}></ModalCustomer>
      </Col>
    </Row>
  );
}

export default CustomerLine;
