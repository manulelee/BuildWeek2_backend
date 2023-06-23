import { useEffect, useState } from "react";
import {
  Col,
  Dropdown,
  DropdownButton,
  ListGroup,
  ListGroupItem,
  Modal,
  Row,
} from "react-bootstrap";
import { Container } from "react-bootstrap";
import { Button } from "react-bootstrap";
import { Form } from "react-bootstrap";
import { useParams } from "react-router-dom";

function CustomerList() {
  const key =
    "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJzLmFuZHJvQGdtYWlsLmNvbSIsImlhdCI6MTY4NzUxMzgzNywiZXhwIjoxNjg4Mzc3ODM3fQ.qBng154OD71efE1ZMMj-e0pgUFclJ8SYhgxmnpiWfAAcs-hA9qfYQVE3EUqQxSk1";
  const params = useParams();
  const [customers, setCustomers] = useState([]);

  const [showFiltra, setShowFiltra] = useState(false);

  const handleClose = () => setShowFiltra(false);
  const handleShow = () => setShowFiltra(true);

  const [filters, setFilters] = useState({
    minAnnualIncome: "",
    maxAnnualIncome: "",
    minRegistrationDate: "",
    maxRegistrationDate: "",
    minLastContactDate: "",
    maxLastContactDate: "",
    name: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFilters((prevFilters) => ({
      ...prevFilters,
      [name]: value,
    }));
  };

  const getAllCustomers = async () => {
    const url = "http://localhost:8080/api/customers";

    try {
      const response = await fetch(url, {
        method: "GET",
        headers: {
          Authorization: "Bearer " + key,
          "Content-Type": "application/json",
        },
      });

      if (!response.ok) {
        throw new Error("Error retrieving customers");
      }

      const data = await response.json();
      setCustomers(data.content);
    } catch (error) {
      console.error(error);
    }
  };

  const getFilteredCustomers = async () => {
    let url = "http://localhost:8080/api/customers/filter";

    const queryParams = [];
    if (filters.minAnnualIncome) {
      queryParams.push(`minAnnualIncome=${filters.minAnnualIncome}`);
    }
    if (filters.maxAnnualIncome) {
      queryParams.push(`maxAnnualIncome=${filters.maxAnnualIncome}`);
    }
    if (filters.minRegistrationDate) {
      queryParams.push(`minRegistrationDate=${filters.minRegistrationDate}`);
    }
    if (filters.maxRegistrationDate) {
      queryParams.push(`maxRegistrationDate=${filters.maxRegistrationDate}`);
    }
    if (filters.minLastContactDate) {
      queryParams.push(`minLastContactDate=${filters.minLastContactDate}`);
    }
    if (filters.maxLastContactDate) {
      queryParams.push(`maxLastContactDate=${filters.maxLastContactDate}`);
    }
    if (filters.name) {
      queryParams.push(`legalName=${filters.name}`);
    }

    if (queryParams.length > 0) {
      url += "?" + queryParams.join("&");
    }

    try {
      const response = await fetch(url, {
        method: "GET",
        headers: {
          Authorization: "Bearer " + key,
          "Content-Type": "application/json",
        },
      });

      if (!response.ok) {
        throw new Error("Error retrieving customers");
      }

      const data = await response.json();
      setCustomers(data.content);
      console.log(data.content);
    } catch (error) {
      console.error(error);
    }
  };

  // Chiamare la funzione per ottenere tutti i clienti
  useEffect(() => {
    getAllCustomers();
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    getFilteredCustomers();
  };

  return (
    <Container>
      <Row className="mx-1">
        <Col className="d-flex ">
          <DropdownButton id="dropdown-OrdinaPer" title="Ordina per">
            <Dropdown.Item href="#/action-1">Nome</Dropdown.Item>
            <Dropdown.Item href="#/action-2">Fatturato Annuale</Dropdown.Item>
            <Dropdown.Item href="#/action-3">Data Inserimento</Dropdown.Item>
            <Dropdown.Item href="#/action-3">
              Data ultimo contatto
            </Dropdown.Item>
          </DropdownButton>
          <Button className="btn-secondary">↑</Button>
          <Button className="btn-secondary">↓</Button>
        </Col>
        <Col className="text-end">
          <Button variant="primary" onClick={handleShow}>
            Filtri
          </Button>
          <Modal show={showFiltra} onHide={handleClose} animation={false}>
            <Modal.Header closeButton>
              <Modal.Title>Filtri</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <Form onSubmit={handleSubmit}>
                <Form.Group controlId="minAnnualIncome">
                  <Form.Label>Min Annual Income</Form.Label>
                  <Form.Control
                    type="number"
                    name="minAnnualIncome"
                    value={filters.minAnnualIncome}
                    onChange={handleChange}
                  />
                </Form.Group>

                <Form.Group controlId="maxAnnualIncome">
                  <Form.Label>Max Annual Income</Form.Label>
                  <Form.Control
                    type="number"
                    name="maxAnnualIncome"
                    value={filters.maxAnnualIncome}
                    onChange={handleChange}
                  />
                </Form.Group>

                <Form.Group controlId="minRegistrationDate">
                  <Form.Label>Min Registration Date</Form.Label>
                  <Form.Control
                    type="date"
                    name="minRegistrationDate"
                    value={filters.minRegistrationDate}
                    onChange={handleChange}
                  />
                </Form.Group>

                <Form.Group controlId="maxRegistrationDate">
                  <Form.Label>Max Registration Date</Form.Label>
                  <Form.Control
                    type="date"
                    name="maxRegistrationDate"
                    value={filters.maxRegistrationDate}
                    onChange={handleChange}
                  />
                </Form.Group>

                <Form.Group controlId="minLastContactDate">
                  <Form.Label>Min Last Contact Date</Form.Label>
                  <Form.Control
                    type="date"
                    name="minLastContactDate"
                    value={filters.minLastContactDate}
                    onChange={handleChange}
                  />
                </Form.Group>
                <Form.Group controlId="maxLastContactDate">
                  <Form.Label>Max Last Contact Date</Form.Label>
                  <Form.Control
                    type="date"
                    name="maxLastContactDate"
                    value={filters.maxLastContactDate}
                    onChange={handleChange}
                  />
                </Form.Group>

                <Form.Group controlId="name">
                  <Form.Label>Name</Form.Label>
                  <Form.Control
                    type="text"
                    name="name"
                    value={filters.name}
                    onChange={handleChange}
                  />
                </Form.Group>

                <Button variant="primary" type="submit">
                  Search
                </Button>
              </Form>
            </Modal.Body>
          </Modal>
        </Col>
      </Row>
      <Row>
        <Col>
          <ListGroup className="border-3 border">
            {customers &&
              customers.map((customer) => (
                <ListGroupItem key={customer.vatNumber}>
                  <Row>
                    <Col>
                      <p>{customer.vatNumber}</p>
                    </Col>
                    <Col>
                      <p>{customer.legalName}</p>
                    </Col>
                  </Row>
                </ListGroupItem>
              ))}
          </ListGroup>
        </Col>
      </Row>
    </Container>
  );
}

export default CustomerList;
