import React, { useState } from 'react';
import axios from 'axios';

const CustomerInfo = () => {
    const [customerId, setCustomerId] = useState('');
    const [customerInfo, setCustomerInfo] = useState(null);
    const [errorMessage, setErrorMessage] = useState('');

    const handleGetInfo = async () => {
        if (!customerId || isNaN(customerId)) {
            setErrorMessage('Please enter a valid numeric Customer ID.');
            return;
        }

        try {
            const response = await axios.get(`http://localhost:8080/api/accounts/info/${customerId}`);
            setCustomerInfo(response.data);
            setErrorMessage('');  // Clear any previous error message
        } catch (error) {
            setErrorMessage('Error fetching customer info.');
            console.error('Error fetching customer info', error);
        }
    };

    const handleCustomerIdChange = (e) => {
        const value = e.target.value;
        if (!isNaN(value) || value === '') {
            setCustomerId(value);  // Update only if it's a valid number or empty
        }
    };

    return (
        <div className="container">
            <h2>Get Customer Info</h2>
            <input
                type="number"
                placeholder="Customer ID"
                value={customerId}
                onChange={handleCustomerIdChange}
                min="1"
                required
            />
            <button onClick={handleGetInfo}>Get Info</button>
            {errorMessage && <p className="error">{errorMessage}</p>}

            {customerInfo && (
                <div className="customer-info">
                    <h3>Customer Info</h3>
                    <table>
                        <tbody>
                        <tr>
                            <th>Name:</th>
                            <td>{customerInfo.firstName}</td>
                        </tr>
                        <tr>
                            <th>Surname:</th>
                            <td>{customerInfo.surName}</td>
                        </tr>
                        <tr>
                            <th>Balance:</th>
                            <td>{customerInfo.balance}</td>
                        </tr>
                        </tbody>
                    </table>

                    <h4>Transactions</h4>
                    {customerInfo.transactions.length > 0 ? (
                        <table>
                            <thead>
                            <tr>
                                <th>Transaction ID</th>
                                <th>Amount</th>
                            </tr>
                            </thead>
                            <tbody>
                            {customerInfo.transactions.map((transaction, index) => (
                                <tr key={index}>
                                    <td>{transaction.id}</td>
                                    <td>{transaction.amount}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    ) : (
                        <p>No transactions found.</p>
                    )}
                </div>
            )}
        </div>
    );
};

export default CustomerInfo;
