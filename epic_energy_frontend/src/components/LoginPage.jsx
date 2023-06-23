import "bootstrap/dist/css/bootstrap.min.css";
import { useState } from "react";
import { Form } from "react-bootstrap";
import { Link } from "react-router-dom";

function LoginPage() {
  let loginDto = {
    username: "",
    password: "",
  };

  let token;

  const [pass, setPassword] = useState([]);
  const [user, setUsername] = useState([]);
  const handleSubmit = (event) => {
    event.preventDefault();
    loginDto.username = user;
    loginDto.password = pass;
  };

  const handlePassword = (event) => {
    setPassword(event.target.value);
  };

  const handleUsername = (event) => {
    setUsername(event.target.value);
  };

  const getToken = async (loginDto) => {
    try {
      let response = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        body: JSON.stringify(loginDto),
        headers: {
          "Content-Type": "application/json",
        },
      });
      if (response.ok) {
        let data = await response.json();
        console.log(data);
        token = data.accessToken;
        localStorage.setItem("token", "Bearer" + " " + token);
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className="modal show my-5" style={{ display: "block", height: "100vh", vhposition: "initial" }}>
      <img src="https://epicode.com/wp-content/uploads/2022/06/EPICODE-2.0-LOGO-15.png" style={{ width: "30%" }}></img>
      <p>Welcome on Epic Energy ⚡️</p>

      <Form onSubmit={handleSubmit} className="mt-5">
        <Form.Group className="mb-3 w-25 mx-auto" controlId="username" onChange={handleUsername}>
          <Form.Control type="text" placeholder="insert username.." />
        </Form.Group>
        <Form.Group className="mb-3 w-25 mx-auto" controlId="password" onChange={handlePassword}>
          <Form.Control type="password" placeholder="insert password.." />
        </Form.Group>
        <button
          type="submit"
          className="btn btn-outline-warning my-2 w-25 "
          onClick={() => {
            getToken(loginDto);
            //   window.location.replace("/");
            <Link to="/"></Link>;
          }}
        >
          Login
        </button>
      </Form>

      <button
        type="submit"
        className="btn btn-outline-success my-2 w-25 "
        onClick={() => {
          // window.location.replace("/register");
        }}
      >
        Register now!
      </button>
    </div>
  );
}
export default LoginPage;
