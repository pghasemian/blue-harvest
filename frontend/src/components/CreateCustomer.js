import React, { useState } from 'react';
import axios from 'axios';

const CreateCustomer = () => {
    const [firstName, setFirstName] = useState('');
    const [surName, setSurName] = useState('');
    const [responseMessage, setResponseMessage] = useState('');
    const [customerInfo, setCustomerInfo] = useState(null); // To store the result

    const handleCreateCustomer = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/customers/create', {
                firstName: firstName,
                surName: surName,
            });
            setCustomerInfo(response.data); // Assuming the response includes the customer data
            setResponseMessage('Customer created successfully');
        } catch (error) {
            setResponseMessage('Error creating customer');
            console.error(error);
        }
    };

    return (
        <div className="container">
            <h2>Create New Customer</h2>
            <input
                type="text"
                placeholder="First name"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
            />
            <input
                type="text"
                placeholder="surname"
                value={surName}
                onChange={(e) => setSurName(e.target.value)}
            />
            <button onClick={handleCreateCustomer}>Create Customer</button>


            {customerInfo && (
                <div className="table-container">

                    <p>{responseMessage}</p>
                </div>
            )}
        </div>
    );
};

export default CreateCustomer;