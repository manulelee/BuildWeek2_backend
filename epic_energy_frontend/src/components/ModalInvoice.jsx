import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import { Dropdown } from 'react-bootstrap';

function ModalInvoice() {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        Invoices
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Invoices</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Date</Form.Label>
              <Form.Control
                type="date"
                placeholder="dd/mm/yyyy"
                autoFocus
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Amount €</Form.Label>
              <Form.Control
                type="number"
                placeholder="dd/mm/yyyy"
                autoFocus
              />
            </Form.Group>
          </Form>
          <Dropdown className="d-inline mx-2" autoClose="true">
        <Dropdown.Toggle id="dropdown-autoclose-true">
          State
        </Dropdown.Toggle>
        <Dropdown.Menu>
            <ul>
          <li>ACTIVE</li>
          <li>VOID</li>
          <li>PAID</li>
            </ul>
        </Dropdown.Menu>
      </Dropdown>
      <Dropdown className="d-inline mx-2">
        <Dropdown.Toggle id="dropdown-autoclose-true">
          Customers
        </Dropdown.Toggle>
        <Dropdown.Menu>
            <ul>
          <li><Dropdown.Item href="#"></Dropdown.Item></li>
          <li><Dropdown.Item href="#"></Dropdown.Item></li>
          <li><Dropdown.Item href="#"></Dropdown.Item></li>
            </ul>
        </Dropdown.Menu>
      </Dropdown>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleClose}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default ModalInvoice;