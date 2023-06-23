import { Container } from "react-bootstrap";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import MyNavbar from "./components/MyNavbar";
import CustomerPage from "./components/CustomerPage";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import LoginPage from "./components/LoginPage";
import RegisterPage from "./components/RegisterPage";
function App() {
  return (
    <div className="App">
      <Container fluid>
        <MyNavbar />
        <BrowserRouter>
          <Routes>
            <Route path="/register" element={<RegisterPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/customers" element={<CustomerPage />} />
          </Routes>
        </BrowserRouter>
      </Container>
    </div>
  );
}

export default App;
