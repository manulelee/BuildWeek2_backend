import { Container } from 'react-bootstrap';
import './App.css';
import CustomerList from './components/CustomerList';
import ModalCustomer from './components/ModalCustomer';
import ModalInvoice from './components/ModalInvoice';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <Container></Container>
        <p>Salve</p>
        <CustomerList></CustomerList>
        <ModalCustomer></ModalCustomer>
        <ModalInvoice></ModalInvoice>
      </header>
    </div>
  );
}

export default App;
