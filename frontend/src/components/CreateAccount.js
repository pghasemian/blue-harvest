import React, { useState } from 'react';
import axios from 'axios';

const CreateAccount = () => {
    const [customerId, setCustomerId] = useState('');
    const [initialCredit, setInitialCredit] = useState('');
    const [responseMessage, setResponseMessage] = useState('');
    const [accountInfo, setAccountInfo] = useState(null); // To store the result

    const handleCreateAccount = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/accounts/create', {
                customerId: Number(customerId),
                initialCredit: Number(initialCredit),
            });
            setAccountInfo(response.data); // Assuming the response includes the account data
            setResponseMessage(`Account created successfully`);
        } catch (error) {
            setResponseMessage('Error creating account');
            console.error(error);
        }
    };

    return (
        <div className="container">
            <h2>Create New Account</h2>
            <input
                type="number"
                placeholder="Customer ID"
                value={customerId}
                onChange={(e) => setCustomerId(e.target.value)}
            />
            <input
                type="number"
                placeholder="Initial Credit"
                value={initialCredit}
                onChange={(e) => setInitialCredit(e.target.value)}
            />
            <button onClick={handleCreateAccount}>Create Account</button>
            <p>{responseMessage}</p>

            {accountInfo && (
                <div className="table-container">
                    <h3>Account Information</h3>
                    <table>
                        <thead>
                        <tr>
                            <th>Account ID</th>
                            <th>Customer ID</th>
                            <th>Initial Credit</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>{accountInfo.id}</td>
                            <td>{accountInfo.customerId}</td>
                            <td>{accountInfo.balance}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
};

export default CreateAccount;
