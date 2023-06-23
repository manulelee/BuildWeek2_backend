import { Col, Row, Button } from "react-bootstrap";

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
        <p>{new Date(customer.registrationDate).toLocaleDateString()}</p>
      </Col>
      <Col>
        <p>{new Date(customer.lastContactDate).toLocaleDateString()}</p>
      </Col>
      <Col>
        <p>{customer.annualIncome}</p>
      </Col>
      <Col>
        <p>{customer.category}</p>
      </Col>
      <Col>
        <Button>Altro</Button>
      </Col>
    </Row>
  );
}

export default CustomerLine;
