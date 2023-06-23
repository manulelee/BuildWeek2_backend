import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import { Dropdown } from 'react-bootstrap';

function ModalCustomer() {
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
      <Button variant="primary" onClick={handleShow} >
        Customers
      </Button>

      <Modal show={show} onHide={handleClose}>
        
          <Modal.Title>Customer Sign in</Modal.Title>
          <Modal.Body>
          <Form noValidate validated={validated} onSubmit={handleSubmit}>
      <Row className="m-5">
        <Form.Group as={Col} md="4" controlId="validationCustom01">
          <Form.Label>Name</Form.Label>
          <Form.Control
            required
            type="text"
            placeholder="First name"
          />
        </Form.Group>
        <Form.Group as={Col} md="4" controlId="validationCustom02">
          <Form.Label>Legal Name</Form.Label>
          <Form.Control
            required
            type="text"
            placeholder="Last name"
          />
        </Form.Group>
        <Form.Group as={Col} md="4"controlId="validationCustomUsername">
          <Form.Label>Email</Form.Label>
          <InputGroup hasValidation>
            <Form.Control
              type="email"
              placeholder="name.lastname@gmail.com"
              aria-describedby="inputGroupPrepend"
              required
            />
          </InputGroup>
        </Form.Group>
        <Form.Group as={Col} md="4" controlId="validationCustomUsername">
          <Form.Label>Pec</Form.Label>
          <InputGroup hasValidation>
            <Form.Control
              type="email"
              placeholder="name.lastname@pec.com"
              aria-describedby="inputGroupPrepend"
              required
            />
          </InputGroup>
        </Form.Group>
        <Form.Group as={Col} md="4" controlId="validationCustom02">
          <Form.Label>Phone</Form.Label>
          <Form.Control
            required
            type="number"
            placeholder="+39 3366998844"
          />
        </Form.Group>
        <Form.Group as={Col} md="4" controlId="validationCustom02">
          <Form.Label>Date Registration</Form.Label>
          <Form.Control
            required
            type="date"
            placeholder="dd/mm/yyyy"
          />
        </Form.Group>
        <Form.Group as={Col} md="4" controlId="validationCustom02">
          <Form.Label>Last Contact Date</Form.Label>
          <Form.Control
            required
            type="date"
            placeholder="dd/mm/yyyy"
          />
        </Form.Group>
        <Form.Group as={Col} md="4" controlId="validationCustom02">
          <Form.Label>Annual Income €</Form.Label>
          <Form.Control
            required
            type="number"
            placeholder="0000"
          />
        </Form.Group>
        <Form.Group as={Col} md="4" controlId="validationCustom02">
          <Form.Label>Contact Email</Form.Label>
          <Form.Control
            required
            type="Email"
            placeholder=""
          />
        </Form.Group>
        <Form.Group as={Col} md="4" controlId="validationCustom02">
          <Form.Label> Contact Name</Form.Label>
          <Form.Control
            required
            type="text"
            placeholder=""
          />
        </Form.Group>
        <Form.Group as={Col} md="4" controlId="validationCustom02">
          <Form.Label> Contact Last Name</Form.Label>
          <Form.Control
            required
            type="text"
            placeholder=""
          />
        </Form.Group>
        <Form.Group as={Col} md="4" controlId="validationCustom02">
          <Form.Label>Contact Phone</Form.Label>
          <Form.Control
            required
            type="number"
            placeholder=""
          /> 
          </Form.Group>
           <Form.Group as={Col} md="4" controlId="validationCustom02">
          <Form.Label> Address</Form.Label>
          <Form.Control
            required
            type="text"
            placeholder="Sede Legale"
          />
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
          <Button variant="primary" type='submit'>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default ModalCustomer;