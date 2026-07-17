import React, { Component } from 'react';

// Exercise 8: use constructor + state to store entry/exit counts.
// Two buttons increment the respective counters.
class CountPeople extends Component {
  constructor(props) {
    super(props);
    this.state = {
      entrycount: 0,
      exitcount: 0,
    };
    this.UpdateEntry = this.UpdateEntry.bind(this);
    this.UpdateExit = this.UpdateExit.bind(this);
  }

  UpdateEntry() {
    this.setState({ entrycount: this.state.entrycount + 1 });
  }

  UpdateExit() {
    this.setState({ exitcount: this.state.exitcount + 1 });
  }

  render() {
    return (
      <div>
        <h1>Mall Entry / Exit Tracker</h1>
        <h2>Number of people who entered the mall: {this.state.entrycount}</h2>
        <h2>Number of people who exited the mall: {this.state.exitcount}</h2>
        <button onClick={this.UpdateEntry}>Login</button>{' '}
        <button onClick={this.UpdateExit}>Exit</button>
      </div>
    );
  }
}

export default CountPeople;
