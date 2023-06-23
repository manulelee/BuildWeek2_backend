import "bootstrap/dist/css/bootstrap.min.css";
import { useState } from "react";
import { Button, Form } from "react-bootstrap";

function RegisterPage() {
  let RegisterDto = {
    firstname: "",
    lastname: "",
    username: "",
    email: "",
    password: "",
  };
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [pass, setPassword] = useState("");
  const [user, setUsername] = useState("");
  const handleSubmit = (event) => {
    event.preventDefault();
    register(RegisterDto);
  };

  const handleFirstName = (event) => {
    setFirstName(event.target.value);
  };
  const handleLastName = (event) => {
    setLastName(event.target.value);
  };
  const handleEmail = (event) => {
    setEmail(event.target.value);
  };
  const handlePassword = (event) => {
    setPassword(event.target.value);
  };

  const handleUsername = (event) => {
    setUsername(event.target.value);
  };

  const register = async (RegisterDto) => {
    RegisterDto.firstname = firstName;
    RegisterDto.lastname = lastName;
    RegisterDto.username = user;
    RegisterDto.email = email;
    RegisterDto.password = pass;
    try {
      let response = await fetch("http://localhost:8080/api/auth/register", {
        method: "POST",
        body: JSON.stringify(RegisterDto),
        headers: {
          "Content-Type": "application/json",
        },
      });
      if (response.status === 201) {
        window.location.replace("/login");
      }
    } catch (error) {
      console.log("ERRORE: " + error);
    }
  };

  return (
    <div className="py-5 bg-dark" style={{ display: "block", height: "100vh", vhposition: "initial" }}>
      <img
        src="https://epicode.com/wp-content/uploads/2022/06/EPICODE-2.0-LOGO-15.png"
        style={{ width: "30%" }}
        alt="epicode Logo"
      ></img>
      <p className="text-light">New user? Register now on Epic Energy ⚡️</p>

      <Form onSubmit={handleSubmit} className="mt-5">
        <Form.Group className="mb-3 w-25 mx-auto" controlId="firstName" onChange={handleFirstName}>
          <Form.Control type="text" required placeholder="insert first name.." />
        </Form.Group>
        <Form.Group className="mb-3 w-25 mx-auto" controlId="lastName" onChange={handleLastName}>
          <Form.Control type="text" required placeholder="insert last name.." />
        </Form.Group>
        <Form.Group className="mb-3 w-25 mx-auto" controlId="email" onChange={handleEmail}>
          <Form.Control type="email" required placeholder="insert email.." />
        </Form.Group>
        <Form.Group className="mb-3 w-25 mx-auto" controlId="username" onChange={handleUsername}>
          <Form.Control type="text" required placeholder="insert username.." />
        </Form.Group>
        <Form.Group className="mb-3 w-25 mx-auto" controlId="password" onChange={handlePassword}>
          <Form.Control type="password" required placeholder="insert password.." />
        </Form.Group>
        <Button type="submit" className="btn btn-dark btn-outline-success my-2 w-25 ">
          Register
        </Button>
      </Form>
    </div>
  );
}
export default RegisterPage;
