import React, { Component } from 'react';

// Exercise 16: React form with validation on change (handle) and on submit.
//  - Name must have at least 5 characters
//  - Email must contain '@' and '.'
//  - Password must have at least 8 characters
class Register extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: '',
      email: '',
      password: '',
      errors: { name: '', email: '', password: '' },
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  validateField(name, value) {
    switch (name) {
      case 'name':
        return value.length >= 5 ? '' : 'Name should have at least 5 characters';
      case 'email':
        return value.includes('@') && value.includes('.') ? '' : 'Email should contain @ and .';
      case 'password':
        return value.length >= 8 ? '' : 'Password should have at least 8 characters';
      default:
        return '';
    }
  }

  // Validation through the event handler (onChange).
  handleChange(event) {
    const { name, value } = event.target;
    const errorMsg = this.validateField(name, value);
    this.setState((prev) => ({
      [name]: value,
      errors: { ...prev.errors, [name]: errorMsg },
    }));
  }

  // Validation through the submit handler.
  handleSubmit(event) {
    event.preventDefault();
    const errors = {
      name: this.validateField('name', this.state.name),
      email: this.validateField('email', this.state.email),
      password: this.validateField('password', this.state.password),
    };
    this.setState({ errors });

    const isValid = !errors.name && !errors.email && !errors.password;
    if (isValid) {
      alert('Registration successful for ' + this.state.name);
    } else {
      alert('Please fix the validation errors before submitting.');
    }
  }

  render() {
    const { errors } = this.state;
    return (
      <div>
        <h2>Register</h2>
        <form onSubmit={this.handleSubmit}>
          <div>
            <label>Name: </label>
            <input type="text" name="name" value={this.state.name} onChange={this.handleChange} />
            <span style={{ color: 'red' }}> {errors.name}</span>
          </div>
          <br />
          <div>
            <label>Email: </label>
            <input type="text" name="email" value={this.state.email} onChange={this.handleChange} />
            <span style={{ color: 'red' }}> {errors.email}</span>
          </div>
          <br />
          <div>
            <label>Password: </label>
            <input
              type="password"
              name="password"
              value={this.state.password}
              onChange={this.handleChange}
            />
            <span style={{ color: 'red' }}> {errors.password}</span>
          </div>
          <br />
          <button type="submit">Register</button>
        </form>
      </div>
    );
  }
}

export default Register;
