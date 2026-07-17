import React, { Component } from 'react';

// Exercise 11 (part 1): demonstrates event handling, `this` binding,
// passing arguments to handlers, and synthetic events.
class EventExamples extends Component {
  constructor(props) {
    super(props);
    this.state = { counter: 0 };
    this.increment = this.increment.bind(this);
    this.decrement = this.decrement.bind(this);
    this.sayHello = this.sayHello.bind(this);
  }

  increment() {
    this.setState({ counter: this.state.counter + 1 });
  }

  decrement() {
    this.setState({ counter: this.state.counter - 1 });
  }

  sayHello() {
    alert('Hello! Welcome to Event Handling in React.');
  }

  // The Increase button invokes multiple methods.
  handleIncreaseClick() {
    this.increment();
    this.sayHello();
  }

  sayWelcome(msg) {
    alert(msg);
  }

  // Synthetic event handler.
  handlePress(e) {
    alert('I was clicked. Event type: ' + e.type);
  }

  render() {
    return (
      <div>
        <h2>Event Handling Examples</h2>
        <h3>Counter: {this.state.counter}</h3>
        <button onClick={() => this.handleIncreaseClick()}>Increment</button>{' '}
        <button onClick={this.decrement}>Decrement</button>
        <br />
        <br />
        <button onClick={() => this.sayWelcome('welcome')}>Say Welcome</button>{' '}
        <button onClick={(e) => this.handlePress(e)}>Synthetic Click (onPress)</button>
      </div>
    );
  }
}

export default EventExamples;
