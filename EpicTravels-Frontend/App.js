import './App.css';
import {BrowserRouter,Routes,Route} from 'react-router-dom';
import Signup from './TravellerComponents/Signup';
import Home from './TravellerComponents/Home';
import AddOrUpdate from './TravellerComponents/AddOrUpdate';
import Example from './TravellerComponents/Example';
import Login from './TravellerComponents/Login';
import Mhome from './TransportmodeComponents/Mhome';

function App() {
  return (
    <div className="App">
    <BrowserRouter>
    <Routes>
    <Route path="/" element={<Example />} />
    <Route path="/login" element={<Login />} />
    <Route path="/register" element ={<Signup />}></Route>
    <Route path="/data" element ={<Home />}></Route>
    <Route path="/add" element ={<AddOrUpdate />}></Route>
    <Route path="/update/:id" element ={<AddOrUpdate />}></Route>
    <Route path="/mode/data" element={<Mhome />} />
    </Routes>
    </BrowserRouter>
    </div>
  );
}
export default App;
