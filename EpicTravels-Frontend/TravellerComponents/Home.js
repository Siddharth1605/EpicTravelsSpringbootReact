import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import TravellerService from '../Service/TravellerService';
import './login.css';
import 'bootstrap/dist/css/bootstrap.css';
import SwapHorizTwoToneIcon from '@mui/icons-material/SwapHorizTwoTone';

const Home=()=> {
  const nav = useNavigate();
  const [searchQuery, setSearchQuery] = useState('');
  const [travellers, setTravellers] = useState([]);
  const [dummy, setDummy] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [itemsPerPage, setItemsPerPage] = useState(3);

  const handleSearch = async () => {
    try {
      const response = await fetch(`http://localhost:8080/traveller/items?query=${searchQuery}`);
      const data = await response.json();
      setTravellers(data);
    } catch (error) {
      console.error('Error searching items:', error);
    }
  };

  useEffect(() => {
      getAllTravellers();
  }, [])

  const getAllTravellers = () => {
      TravellerService.getAllTravellers().then((response) => {
          setTravellers(response.data)
          console.log(response.data);
      }).catch(error =>{
          console.log(error);
      })
  }

  const deleteTraveller = (travellerId) => {
    if(window.confirm("Sure to Delete?")){
     TravellerService.deleteTraveller(travellerId).then((response) =>{
      getAllTravellers();

     }).catch(error =>{
         console.log(error);
     })
    }
  }
  const deleteAllTraveller = () => {
    if(window.confirm("Sure to Delete All Travellers?")){
     TravellerService.deleteAllTraveller().then((response) =>{
      getAllTravellers();

     }).catch(error =>{
         console.log(error);
     })
    }
  }
  const LogoutHandler=()=>{
    if(window.confirm("Sure to Logout?")){
      nav("/")
    }
  }

  const tableStyle = {
    borderCollapse: 'collapse',
    border: '1px solid black',
  };

  const handleChange = (event) => {
    const query = event.target.value;
    setSearchQuery(event.target.value);
    if (query === '') {
      setTravellers(dummy);
    } else {
      handleSearch();
    }
  };

  const handleSort = async (column) => {
    try {
      const response = await fetch(`http://localhost:8080/traveller/sorting?sort=${column}`);
      const data = await response.json();
      setTravellers(data);
    } catch (error) {
      console.error('Error sorting items:', error);
    }
  };

  const handleUpdate = (id) => {
    TravellerService.deleteTraveller(id)
      .then((response) => {
        getAllTravellers();
      })
      .catch((error) => {
        console.log(error);
      });
  };

  // Pagination
  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentItems = travellers.slice(indexOfFirstItem, indexOfLastItem);

  const pageNumbers = [];
  for (let i = 1; i <= Math.ceil(travellers.length / itemsPerPage); i++) {
    pageNumbers.push(i);
  }

  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  return (
    <div id="container" style={{backgroundColor:'white', padding:'15px'}}>
      <h1 style={{ color: 'black' }}>Epic Travels</h1>
      <Link to="/add">
        <button id="addbtn" className="btn btn-info btn-lg">
          Add Travellers
        </button>
      </Link>
      <button id="viewbtn" className="btn btn-danger btn-lg" onClick={deleteAllTraveller}>
        Delete All Travellers
      </button>
      <Link to="/mode/data">
        <SwapHorizTwoToneIcon />
      </Link>
      <div>
        <input
          type="text"
          value={searchQuery}
          onChange={handleChange}
          placeholder="Search"
          title="Searching any element using jpa query"
          className="search"
        />
      </div>
      <table id="sidtable">
        <thead style={tableStyle}>
          <tr>
            <th title='Sort Id Table' onClick={() => handleSort('id')} style={tableStyle}>
              Id
            </th>
            <th onClick={() => handleSort('name')} style={tableStyle}>
              Name
            </th>
            <th onClick={() => handleSort('age')} style={tableStyle}>
              Age
            </th>
            <th onClick={() => handleSort('arrival')} style={tableStyle}>
              Arrival
            </th>
            <th onClick={() => handleSort('departure')} style={tableStyle}>
              Departure
            </th>
            <th onClick={() => handleSort('budget')} style={tableStyle}>
              Package
            </th>
            <th style={tableStyle}>Actions</th>
          </tr>
        </thead>
        <tbody style={tableStyle}>
          {currentItems.map((traveller) => (
            <tr key={traveller.id} style={tableStyle}>
              <td style={tableStyle}> {traveller.id}</td>
              <td style={tableStyle}> {traveller.name}</td>
              <td style={tableStyle}> {traveller.age}</td>
              <td style={tableStyle}> {traveller.arrival}</td>
              <td style={tableStyle}> {traveller.departure}</td>
              <td style={tableStyle}> {traveller.budget}</td>
             <td  ><Link  to={`/update/${traveller.id}`}><button id="actions" className='btn btn-outline-warning btn-sm'>Update</button></Link> 
             <button id="actions"  className='btn btn-outline-danger btn-sm' style={{marginTop:'5%'}} onClick = {() => deleteTraveller(traveller.id)}
            > Delete!</button></td>
            
      </tr>
    ))}
              </tbody>
            </table>
            <div className="pagination-container">
            <div className="page-input">
              <label htmlFor="pageNumber">Page Number: </label>
              <input
                type="number"
                id="pageNumber"
                min="1"
                max={Math.ceil(travellers.length / itemsPerPage)}
                value={currentPage}
                onChange={(e) => setCurrentPage(Number(e.target.value))}
              />
            </div>
            <div className="page-input">
              <label htmlFor="pageSize">Page Size: </label>
              <input
                type="number"
                id="pageSize"
                min="0"
                max={travellers.length}
                value={itemsPerPage}
                onChange={(e) => setItemsPerPage(Number(e.target.value))}
              />
            </div>
        </div>
    <button id="logout" className='btn btn-primary btn-lg' onClick={LogoutHandler}>Logout</button>
        
    </div>
  )
}

export default Home