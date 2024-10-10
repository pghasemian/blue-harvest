import React from 'react';
import './App.css';
import CreateAccount from './components/CreateAccount';
import CustomerInfo from './components/CustomerInfo';
import CreateCustomer from './components/CreateCustomer';
import logo from './logo.png';

function App() {
    return (
        <div className="app-container">
            <img src={logo} alt="Blue Harvest Logo" className="logo" />
            <h1>Blue Harvest</h1>
            <div className="outer-container">
                <CreateCustomer />
                <CreateAccount />
                <CustomerInfo />
            </div>
        </div>
    );
}

export default App;
