import { Container } from 'react-bootstrap';
import './App.css';
import CustomerList from './components/CustomerList';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <Container></Container>
        <p>Salve</p>
        <CustomerList></CustomerList>
      </header>
    </div>
  );
}

export default App;
