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
import CustomerLine from "./CustomerLine";

function CustomerList() {
  const key = localStorage.getItem("token");
  const [customers, setCustomers] = useState([]);

  const [showFiltra, setShowFiltra] = useState(false);

  const [filters, setFilters] = useState({
    minAnnualIncome: "",
    maxAnnualIncome: "",
    minRegistrationDate: "",
    maxRegistrationDate: "",
    minLastContactDate: "",
    maxLastContactDate: "",
    name: "",
  });

  const [sortingField, setSortingField] = useState("legalName");
  const [sortingDirection, setSortingDirection] = useState("ASC");

  const handleSortingChange = (field, direction) => {
    setSortingField(field);
    setSortingDirection(direction);
  };

  const handleClose = () => setShowFiltra(false);
  const handleShow = () => setShowFiltra(true);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFilters((prevFilters) => ({
      ...prevFilters,
      [name]: value,
    }));
  };

  const getAllCustomers = async () => {
    const url = "http://localhost:8080/api/customers?sort=legalName,ASC";

    try {
      const response = await fetch(url, {
        method: "GET",
        headers: {
          Authorization: key,
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
    let url = `http://localhost:8080/api/customers/filter?sort=${sortingField},${sortingDirection}`;

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
      url += "&" + queryParams.join("&");
    }

    try {
      const response = await fetch(url, {
        method: "GET",
        headers: {
          Authorization: key,
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

  useEffect(() => {
    getFilteredCustomers();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [filters, sortingField, sortingDirection]);

  return (
    <Container>
      <Row className="mx-1">
        <Col className="d-flex ">
          <DropdownButton id="dropdown-OrdinaPer" title="Ordina per">
            <Dropdown.Item
              onClick={() => handleSortingChange("legalName", "ASC")}
            >
              Nome
            </Dropdown.Item>
            <Dropdown.Item
              onClick={() => handleSortingChange("annualIncome", "ASC")}
            >
              Fatturato Annuale
            </Dropdown.Item>
            <Dropdown.Item
              onClick={() => handleSortingChange("registrationDate", "ASC")}
            >
              Data Inserimento
            </Dropdown.Item>
            <Dropdown.Item
              onClick={() => handleSortingChange("lastContactDate", "ASC")}
            >
              Data ultimo contatto
            </Dropdown.Item>
          </DropdownButton>
          <Button
            className="btn-secondary"
            onClick={() => handleSortingChange(sortingField, "ASC")}
          >
            ↓
          </Button>
          <Button
            className="btn-secondary"
            onClick={() => handleSortingChange(sortingField, "DESC")}
          >
            ↑
          </Button>
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
              <Form>
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

                <Button variant="primary">Apply</Button>
              </Form>
            </Modal.Body>
          </Modal>
        </Col>
      </Row>
      <Row>
        <Col>
          <ListGroup className="border-3 border">
            <Row>
              <Col>IVA</Col>
              <Col>Nome Legale</Col>
              <Col>Data di Registrazione</Col>
              <Col>Ultimo Contatto</Col>
              <Col>Reddito Annuale</Col>
              <Col>Categoria</Col>
              <Col></Col>
            </Row>
            {customers &&
              customers.map((customer) => (
                <ListGroupItem key={customer.vatNumber}>
                  <CustomerLine customer={customer} />
                </ListGroupItem>
              ))}
          </ListGroup>
        </Col>
      </Row>
    </Container>
  );
}

export default CustomerList;
