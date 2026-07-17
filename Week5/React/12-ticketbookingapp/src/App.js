import React, { Component } from 'react';
import { GuestPage, UserPage, LoginButton, LogoutButton } from './components';

// Exercise 12: conditional rendering based on login state.
class App extends Component {
  constructor(props) {
    super(props);
    this.state = { isLoggedIn: false };
    this.handleLogin = this.handleLogin.bind(this);
    this.handleLogout = this.handleLogout.bind(this);
  }

  handleLogin() {
    this.setState({ isLoggedIn: true });
  }

  handleLogout() {
    this.setState({ isLoggedIn: false });
  }

  render() {
    const { isLoggedIn } = this.state;

    // Element variable used to hold the button to render.
    let button;
    if (isLoggedIn) {
      button = <LogoutButton onClick={this.handleLogout} />;
    } else {
      button = <LoginButton onClick={this.handleLogin} />;
    }

    return (
      <div>
        <h1>Flight Ticket Booking</h1>
        {isLoggedIn ? <UserPage /> : <GuestPage />}
        {button}
      </div>
    );
  }
}

export default App;
