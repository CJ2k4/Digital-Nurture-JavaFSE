import React, { Component } from 'react';
import Cart from './Cart';

// Exercise 7: OnlineShopping holds an array of 5 cart items and loops
// through them, passing data to the Cart component via props.
class OnlineShopping extends Component {
  constructor(props) {
    super(props);
    this.cartItems = [
      { itemname: 'Laptop', price: 55000 },
      { itemname: 'Headphones', price: 2500 },
      { itemname: 'Keyboard', price: 1200 },
      { itemname: 'Mouse', price: 800 },
      { itemname: 'Monitor', price: 12000 },
    ];
  }

  render() {
    return (
      <div>
        <h1>Online Shopping Cart</h1>
        <table border="1" cellPadding="8">
          <thead>
            <tr>
              <th>Item Name</th>
              <th>Price (₹)</th>
            </tr>
          </thead>
          <tbody>
            {this.cartItems.map((item, index) => (
              <Cart key={index} itemname={item.itemname} price={item.price} />
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

export default OnlineShopping;
