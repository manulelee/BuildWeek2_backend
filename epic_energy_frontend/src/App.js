import { Container } from "react-bootstrap";
import "./App.css";
import CustomerList from "./components/CustomerList";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import LoginPage from "./components/LoginPage";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <BrowserRouter>
          <Routes>
            <Route path="/login" element={<LoginPage />}></Route>
          </Routes>
        </BrowserRouter>
        {/* <Container></Container>
        <p>Salve</p>
  <CustomerList></CustomerList>*/}
      </header>
    </div>
  );
}

export default App;
