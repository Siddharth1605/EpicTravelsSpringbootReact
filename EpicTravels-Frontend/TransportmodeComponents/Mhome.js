import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import './Mlogin.css'
import 'bootstrap/dist/css/bootstrap.css';
import SwapHorizTwoToneIcon from '@mui/icons-material/SwapHorizTwoTone';

import TransportmodeService from '../Service/TransportmodeService';

const Mhome=()=> {
  const nav=useNavigate();

  const [transportmode, setTransportmode] = useState([])
  useEffect(() => {
      getAllTransportmode();
      console.log(transportmode)
  }, [])

  const getAllTransportmode = () => {
    TransportmodeService.getAllTransportmode().then((response) => {
        setTransportmode(response.data)
          console.log(response.data);
      }).catch(error =>{
          console.log(error);
      })
  }

  const LogoutHandler=()=>{
    if(window.confirm("Sure to Logout?")){
      nav("/")
    }
  }
  const tableStyle = {
    borderCollapse: 'collapse',
    border: '1px solid black',
  }
  return (
    <div id="container">
            <h1 style={{color:'black'}}>Epic Travels</h1>
    <Link to="/data"><SwapHorizTwoToneIcon titleAccess='Show the relationship'/></Link>
  
    <table >
              <thead style={tableStyle}>
                <tr>
                  <th style={tableStyle}>Id</th>
                  <th style={tableStyle}>Name</th>
                  <th style={tableStyle}>Mode</th>
                  <th style={tableStyle}>Travellerid</th>
                  <th style={tableStyle}>Traveller</th>
                </tr>
              </thead>
              <tbody style={tableStyle}>
              {
                transportmode.map(
                  modes => (
      <tr key={modes.id} style={tableStyle}>
             <td style={tableStyle}> {modes.id}</td>
             <td style={tableStyle}> {modes.name}</td>
             <td style={tableStyle}> {modes.mode}</td>
             <td style={tableStyle}> {modes.traveller.id}</td>
             <td style={tableStyle}> <ul>
             <li>Name: {modes.traveller.name}</li>
             <li>Arrival: {modes.traveller.arrival}</li>
             <li>Departure: {modes.traveller.departure}</li>
             <li>Budget: {modes.traveller.budget}</li>
             <li>Age: {modes.traveller.age}</li>
           </ul></td>
            
      </tr>
    ))}
              </tbody>
            </table>
    <button id="logout" className='btn btn-primary btn-lg' onClick={LogoutHandler}>Logout</button>
        
    </div>
  )
}

export default Mhome