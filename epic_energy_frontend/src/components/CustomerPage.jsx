import {
  Button,
  Col,
  Container,
  Dropdown,
  DropdownButton,
  Form,
  Modal,
  Row,
} from "react-bootstrap";
import CustomerList from "./CustomerList";
import ModalCustomer from "./ModalCustomer";
import ModalInvoice from "./ModalInvoice";
import { useState } from "react";

function CustomerPage({ onSearch }) {
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

  const handleSubmit = (e) => {
    e.preventDefault();
    onSearch(filters);
  };

  return (
    <Container className="my-4 mx-2">
      <h1 className="mb-5">Registro Clienti</h1>
      <Row className="mx-1">
        <Col>
          <ModalCustomer />
          <ModalInvoice />
        </Col>
        <Col className="d-flex ">
          <DropdownButton id="dropdown-OrdinaPer" title="Ordina per">
            <Dropdown.Item href="#/action-1">Nome</Dropdown.Item>
            <Dropdown.Item href="#/action-2">Fatturato Annuale</Dropdown.Item>
            <Dropdown.Item href="#/action-3">Data Inserimento</Dropdown.Item>
            <Dropdown.Item href="#/action-3">
              Data ultimo contatto
            </Dropdown.Item>
            <Dropdown.Item href="#/action-3">Provincia</Dropdown.Item>
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
                <Form.Group className="mb-3" controlId="LegalName">
                  <Form.Label>Nome</Form.Label>
                  <Form.Control type="text" placeholder="Cerca per nome" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="fatturatoAnnuale">
                  <Form.Label>Nome</Form.Label>
                  <Form.Control type="range" placeholder="Cerca per nome" />
                </Form.Group>
                <Row>
                  <Col>
                    <Form.Group controlId="minAnnualIncome">
                      <Form.Label>Min Annual Income</Form.Label>
                      <Form.Control
                        type="number"
                        name="minAnnualIncome"
                        value={filters.minAnnualIncome}
                        onChange={handleChange}
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group controlId="minAnnualIncome">
                      <Form.Label>Max Annual Income</Form.Label>
                      <Form.Control
                        type="number"
                        name="maxAnnualIncome"
                        value={filters.maxAnnualIncome}
                        onChange={handleChange}
                      />
                    </Form.Group>
                  </Col>
                </Row>
              </Form>
            </Modal.Body>
            <Modal.Footer>
              <Button variant="secondary" onClick={handleClose}>
                Chiudi
              </Button>
              <Button variant="primary" onClick={handleClose}>
                Applica
              </Button>
            </Modal.Footer>
          </Modal>
        </Col>
      </Row>

      <CustomerList />
    </Container>
  );
}

export default CustomerPage;
