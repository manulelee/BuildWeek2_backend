import { Container } from "react-bootstrap";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import MyNavbar from "./components/MyNavbar";
import CustomerPage from "./components/CustomerPage";

function App() {
  return (
    <div className="App">
      <Container fluid>
        <MyNavbar />
        <CustomerPage />
      </Container>
    </div>
  );
}

export default App;
