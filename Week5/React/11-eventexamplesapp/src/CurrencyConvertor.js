import React, { Component } from 'react';

// Exercise 11 (part 2): convert Indian Rupees to Euro on button click.
class CurrencyConvertor extends Component {
  constructor(props) {
    super(props);
    this.state = { rupees: 0, euro: null };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(e) {
    this.setState({ rupees: e.target.value });
  }

  handleSubmit(e) {
    e.preventDefault();
    // Approx conversion rate: 1 Euro = 90 Rupees.
    const euro = (this.state.rupees / 90).toFixed(2);
    this.setState({ euro });
  }

  render() {
    return (
      <div>
        <h2>Currency Convertor (INR to Euro)</h2>
        <form onSubmit={this.handleSubmit}>
          <label>Enter amount in Rupees: </label>
          <input type="number" value={this.state.rupees} onChange={this.handleChange} />{' '}
          <button type="submit">Convert</button>
        </form>
        {this.state.euro !== null && (
          <h3>
            ₹{this.state.rupees} = €{this.state.euro}
          </h3>
        )}
      </div>
    );
  }
}

export default CurrencyConvertor;
