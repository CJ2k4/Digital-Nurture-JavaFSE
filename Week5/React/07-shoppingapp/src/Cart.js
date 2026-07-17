import React, { Component } from 'react';

// Cart component receives itemname and price as props and displays them.
class Cart extends Component {
  render() {
    return (
      <tr>
        <td>{this.props.itemname}</td>
        <td>{this.props.price}</td>
      </tr>
    );
  }
}

export default Cart;
