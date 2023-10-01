import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import TravellerService from '../Service/TravellerService';

const AddOrUpdate=()=> {
  const nav=useNavigate();
  const[name,setname]=useState('');
  const[age,setAge]=useState('');
  const[arrival,setArrival]=useState('');
  const[departure,setDeparture]=useState('');
  const[budget,setBudget]=useState('');
  const {id} = useParams();
    const saveOrUpdateTraveller = (e) => {
      e.preventDefault();
      if(age.length==0||budget.length==0||departure.length==0||name.length==0||arrival.length==0){
        alert("Enter All fields")
      }
      else if(age<18||age>65){
        alert("Enter Correct Age!")
      }
      else{
      if (window.confirm("Confirm Details!") == true) {
        e.preventDefault();
          const traveller = {id, name, age,arrival,departure,budget}
          if(id){
              TravellerService.updateTraveller(id, traveller).then((response) => {
                  nav('/data')
              }).catch(error => {
                  console.log(error)
              })
                                            
          }else{
              TravellerService.createTraveller(traveller).then((response) =>{
                  console.log(response.data)
                  nav('/data');
              }).catch(error => {
                  console.log(error)
              })
          }
        }
      }
    } 
  
      useEffect(() => {
          TravellerService.getTravellerById(id).then((response) =>{
              setname(response.data.name)
              setAge(response.data.age)
              setArrival(response.data.arrival)
              setDeparture(response.data.departure)
              setBudget(response.data.budget)
          }).catch(error => {
              console.log(error)
          })
      }, [])
      const title = () => {

        if(id){
            return <h1>Update Traveller</h1>
        }else{
            return <h1>Add Traveller</h1>
        }
    }
  return (
    <div id="body">
    <div className="signup-form">
    <div className="container">
      <div className="header">
        {title()}
        <p>Enter Traveller Details</p>
      </div>
      <form>
        <div className="input">
          <input type="text" placeholder="Traveller name" value={name} onChange={(e)=>setname(e.target.value)}  />
        </div>
        <div className="input">
          <input type="number" placeholder="Traveller Age" value={age} pattern="[0-9]+"  onChange={(e)=>setAge(e.target.value)}/>
        </div>
        <div className="input">
          <input type="text" placeholder="Traveller Arrival" value={arrival}  
                     onChange={(e)=>setArrival(e.target.value)} />
        </div>
        <div className="input">
          <input type="text" placeholder="Traveller Departure" value={departure} onChange={(e)=>setDeparture(e.target.value)}/>
        </div>
        <div className="input">
          <input type="text" placeholder="Traveller Budget" value={budget}    onChange={(e)=>setBudget(e.target.value)} />
        </div>
        
        <input onClick={saveOrUpdateTraveller} className="e-signup-btn" type="submit" value="Submit" />
      <Link to="/data">  <button className="e-cancel-btn" >Cancel </button></Link>
        </form>
    </div>
  </div>
    </div>
  )
}

export default AddOrUpdate