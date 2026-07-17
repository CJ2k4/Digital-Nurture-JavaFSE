import React, { Component } from 'react';

// Exercise 15: controlled form with a textbox (name) and textarea (complaint).
// On submit, generates a reference number and shows it in an alert box.
class ComplaintRegister extends Component {
  constructor(props) {
    super(props);
    this.state = { employeeName: '', complaint: '' };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  }

  handleSubmit(event) {
    event.preventDefault();
    // Generate a simple reference number for follow-ups.
    const referenceNumber = 'REF-' + Math.floor(100000 + Math.random() * 900000);
    alert(
      `Complaint registered successfully!\n` +
        `Employee: ${this.state.employeeName}\n` +
        `Your reference number is: ${referenceNumber}`
    );
    this.setState({ employeeName: '', complaint: '' });
  }

  render() {
    return (
      <div>
        <h2>Raise a Complaint</h2>
        <form onSubmit={this.handleSubmit}>
          <div>
            <label>Employee Name: </label>
            <input
              type="text"
              name="employeeName"
              value={this.state.employeeName}
              onChange={this.handleChange}
              required
            />
          </div>
          <br />
          <div>
            <label>Complaint: </label>
            <br />
            <textarea
              name="complaint"
              rows="4"
              cols="40"
              value={this.state.complaint}
              onChange={this.handleChange}
              required
            />
          </div>
          <br />
          <button type="submit">Submit Complaint</button>
        </form>
      </div>
    );
  }
}

export default ComplaintRegister;
