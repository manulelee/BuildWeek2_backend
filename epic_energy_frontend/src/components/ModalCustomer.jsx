import { useState } from "react";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import { Dropdown } from "react-bootstrap";

function ModalCustomer({ customer }) {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const [validated, setValidated] = useState(false);

  const handleSubmit = (event) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    }

    setValidated(true);
  };

  return (
    <>
      {
        !customer.legalName && (
          <Button variant="primary" onClick={handleShow}>
            New Customer
          </Button>
        )
      }
      {
        customer.legalName && (
          <Button variant="primary" onClick={handleShow}>
            Edit Customer
          </Button>
        )
      }

      <Modal show={show} onHide={handleClose}>
        <Modal.Title>Customer Sign in</Modal.Title>
        <Modal.Body>
          <Form noValidate validated={validated} onSubmit={handleSubmit}>
            <Row className="m-5">
              <Form.Group as={Col} md="4" controlId="vatNumber">
                <Form.Label>VAT Number</Form.Label>
                <Form.Control required type="text" placeholder="VAT Number"
                  value={customer.vatNumber} />
              </Form.Group>
              <Form.Group as={Col} md="4" controlId="legalName">
                <Form.Label>Legalname</Form.Label>
                <Form.Control required type="text" placeholder="Legalname"
                  value={customer.legalName} />
              </Form.Group>
              <Form.Group as={Col} md="4" controlId="email">
                <Form.Label>Email</Form.Label>
                <InputGroup hasValidation>
                  <Form.Control
                    type="email"
                    placeholder="name.lastname@gmail.com"
                    aria-describedby="inputGroupPrepend"
                    required
                    value={customer.email}
                  />
                </InputGroup>
              </Form.Group>
              <Form.Group as={Col} md="4" controlId="pec">
                <Form.Label>Pec</Form.Label>
                <InputGroup hasValidation>
                  <Form.Control
                    type="email"
                    placeholder="name.lastname@pec.com"
                    aria-describedby="inputGroupPrepend"
                    required
                    value={customer.pec}
                  />
                </InputGroup>
              </Form.Group>
              <Form.Group as={Col} md="4" controlId="phone">
                <Form.Label>Phone</Form.Label>
                <Form.Control
                  required
                  placeholder="+39 3366998844"
                  value={customer.phone}
                />
              </Form.Group>
              <Form.Group as={Col} md="4" controlId="registrationDate">
                <Form.Label>Registration Date</Form.Label>
                <Form.Control required type="date" placeholder="dd/mm/yyyy"
                  value={customer.registrationDate}
                />
              </Form.Group>
              <Form.Group as={Col} md="4" controlId="lastContactDate">
                <Form.Label>Last Contact Date</Form.Label>
                <Form.Control required type="date" placeholder="dd/mm/yyyy"
                  value={customer.lastContactDate} />
              </Form.Group>
              <Form.Group as={Col} md="4" controlId="annualIncome">
                <Form.Label>Annual Income €</Form.Label>
                <Form.Control required type="number" placeholder="0000"
                  value={customer.annualIncome} />
              </Form.Group>
              <Form.Group as={Col} md="4" controlId="contactEmail">
                <Form.Label>Contact Email</Form.Label>
                <Form.Control
                  type="email"
                  placeholder="name.lastname@gmail.com"
                  aria-describedby="inputGroupPrepend"
                  required
                  value={customer.contactEmail}
                />
              </Form.Group>
              <Form.Group as={Col} md="4" controlId="contactName">
                <Form.Label> Contact Name</Form.Label>
                <Form.Control required type="text" placeholder="Firstname"
                  value={customer.contactName} />
              </Form.Group>
              <Form.Group as={Col} md="4" controlId="contactLastName">
                <Form.Label> Contact Last Name</Form.Label>
                <Form.Control required type="text" placeholder="Lastname"
                  value={customer.contactLastName} />
              </Form.Group>
              <Form.Group as={Col} md="4" controlId="contactPhone">
                <Form.Label>Contact Phone</Form.Label>
                <Form.Control
                  required
                  placeholder="+39 3366998844"
                  value={customer.contactPhone}
                />
              </Form.Group>
              <Form.Group as={Col} md="4" controlId="category">
                <Form.Label> Address</Form.Label>
                <Form.Control required type="text" placeholder="Sede Legale" />
              </Form.Group>
              <Form.Group as={Col} md="4" controlId="validationCustom02">
                <Form.Label> Address</Form.Label>
                <Form.Control
                  required
                  type="text"
                  placeholder="Sede Operativa"
                />
              </Form.Group>
              <Dropdown className="d-inline mx-2">
                <Dropdown.Toggle id="dropdown-autoclose-true">
                  Category
                </Dropdown.Toggle>
                <Dropdown.Menu>
                  <ul>
                    <li>PA</li>
                    <li>SAS</li>
                    <li>SPA</li>
                    <li>SRL</li>
                  </ul>
                </Dropdown.Menu>
              </Dropdown>
            </Row>
          </Form>
        </Modal.Body>

        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" type="submit">
            Save Changes
          </Button>
          <Button>Delete Customer</Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default ModalCustomer;
