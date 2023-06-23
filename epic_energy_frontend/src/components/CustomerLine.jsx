import { Col, Row } from "react-bootstrap";

function CustomerLine({ customer }) {
  return (
    <Row>
      <Col>
        <p>{customer.vatNumber}</p>
      </Col>
      <Col>
        <p>{customer.legalName}</p>
      </Col>
    </Row>
  );
}

export default CustomerLine;
