import { Col, ListGroupItem, Row } from "react-bootstrap";

function Customer (customer) {
    return (
        <ListGroupItem>
            <Row>
                <Col>
                 <p>{customer.legal_name}</p>
                </Col>
                <Col>
                <p>{customer.vat_number}</p>
                </Col>
            </Row>
        </ListGroupItem>
    )
}

export default Customer;