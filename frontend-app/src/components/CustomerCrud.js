import axios from "axios"
import { useState, useEffect }  from 'react'

function CustomerCrud()
{
    const[customerId,setId] = useState('');
    const[customerName,setName] = useState("");
    const[customerAddress,setAddress] = useState("");
    const[mobile,setMobile] = useState("");
    const[customers,setUsers] = useState([]);

    useEffect(() =>{
        (async() => await Load())();
    },[]);



    async function Load(){
        const result = await axios.get(
            "http://localhost:8084/api/v1/customer/getAllCustomer");
            setUsers(result.data);
            console.log(result.data);
    }

    async function save(event)
    {
        event.preventDefault();
        try
        {
            await axios.post("http://localhost:8084/api/v1/customer/save",
            {
                customerName: customerName,
                customerAddress: customerAddress,
                mobile: mobile
            });
            alert('Customer Registration Successfully');
            setId("");
            setName("");
            setAddress("");
            setMobile("")
            Load();
        }
        catch(err)
        {
            alert("User Registration Failed")
        }
    }

    async function editEmployee(customers)
    {
        setName(customers.customerName);
        setAddress(customers.customerAddress);
        setMobile(customers.mobile)
        setId(customers.customerId)
    }


    async function DeleteCustomer(customerId)
    {
        await axios.delete("http://localhost:8084/api/v1/customer/deleteCustomer/" + customerId);
        alert("Customer deleted successfully");
        Load();
    }

    async function update(event)
    {
        event.preventDefault();

        try
        {
            await axios.post("http://localhost:8084/api/v1/customer/update",
            {
                customerId: customerId,
                customerName: customerName,
                customerAddress: customerAddress,
                mobile: mobile
            });
            alert("Registration Updatedddd");
            setId("");
            setName("");
            setAddress("");
            setMobile("");
            Load();
        }
        catch(err)
        {
            alert("User registration failed")
        }
    }
    return(
        <div>
            <h1>Customer details</h1>
            <div className="container mt-4">
                <form>
                    <div className="form-group">
                        <input type="text" className="form-control" id="customerId" hidden
                        value={customerId}
                        onChange={(event)=>
                            {
                            setId(event.target.value);
                        }} />
                        <label>Customer name</label>
                        <input type="text" className="form-control" id="customerName" 
                        value={customerName}
                        onChange={(event)=>{
                            setName(event.target.value);
                        }} 
                        />
                        </div>

                        <div className="form-group">
                        <label>Customer address</label>
                        <input type="text" className="form-control" id="customeraddress" 
                        value={customerAddress}
                        onChange={(event)=>{
                            setAddress(event.target.value);
                        }} />
                        </div>

                        <div className="form-group">
                        <label>Customer mobile</label>
                        <input type="text" className="form-control" id="mobile" 
                        value={mobile}
                        onChange={(event)=>{
                            setMobile(event.target.value);
                        }} />
                        </div>
                    <div>
                        <button className="btn btn-primary mt-4" onClick={save}>Register</button>
                        <button className="btn btn-warning mt-4" onClick={update}>Update</button>
                    </div>
                </form>
            </div>

        <table className ="table table-dark" align='center'>
            <thead>
                <tr>
                    <th scope='col'>Customer Id</th>
                    <th scope='col'>Customer Name</th>
                    <th scope='col'>Customer Address</th>
                    <th scope='col'>Mobile</th>

                    <th scope='col'>Option</th>
                </tr>
            </thead>
            {customers.map(function fn(customer){
                return(
                    <tbody>
                        <tr>
                        <th scope='row'>{customer.customerId}</th>
                        <td>{customer.customerName}</td>
                        <td>{customer.customerAddress}</td>
                        <td>{customer.mobile}</td>
                        <td>
                            <button type='button' className="btn btn-warning" onClick={() =>editEmployee(customer)}>Edit</button>
                            <button type='button' className="btn btn-danger" onClick={() =>DeleteCustomer(customer.customerId)}>Delete</button>
                        </td>
                        </tr>
                    </tbody>
                );
            })}
        </table>
        </div>
    );
}
export default CustomerCrud;
