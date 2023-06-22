import { useState } from "react";
import { Col, ListGroup, ListGroupItem, Row } from "react-bootstrap";
import { Container } from "react-bootstrap";
import { useParams } from "react-router-dom";

function CustomerList() {
  const params = useParams();
  const [customers, setCustomers] = useState([]);

  const getAllCustomers = async () => {
    const token =
      "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJzLmFuZHJvQGdtYWlsLmNvbSIsImlhdCI6MTY4NzQ0NTk2OCwiZXhwIjoxNjg4MzA5OTY4fQ.JPY4y5wJBg8-OW_PBUO5XfJBvWzK0ma-Kec8FEZ0USw8zsw28LFgmpXXLMN_GXJi";
    const url = "http://localhost:8080/api/customers/all";

    try {
      const response = await fetch(url, {
        method: "GET",
        headers: {
          Authorization: token,
          "Content-Type": "application/json",
        },
      });

      if (!response.ok) {
        throw new Error("Error retrieving customers");
      }

      const data = await response.json();
      console.log(data); // Puoi fare qualcosa con i dati dei clienti qui
    } catch (error) {
      console.error(error);
    }
  };

  // Chiamare la funzione per ottenere tutti i clienti
  getAllCustomers();

  return (
    <Container>
      <Row>
        <Col>
          <ListGroup className="border-3 border">
            <ListGroupItem className="border-1 border">
              <Row>
                <Col>
                  <p>Nome 1</p>
                </Col>
                <Col>
                  <p>partita iva 1</p>
                </Col>
              </Row>
            </ListGroupItem>
            <ListGroupItem className="border-1 border">
              <Row>
                <Col>
                  <p>Nome 1</p>
                </Col>
                <Col>
                  <p>partita iva 1</p>
                </Col>
              </Row>
            </ListGroupItem>
            {/*{customers &&
        customers.map((customer) => <ListGroupItem key={customer.vat_number} customer={customer} />)}*/}
          </ListGroup>
        </Col>
      </Row>
    </Container>
  );
}

export default CustomerList;
