import React, { Component } from 'react';
import ThemeContext from './ThemeContext';
import EmployeesList from './EmployeesList';

// Exercise 14: the App provides the theme via ThemeContext.Provider instead
// of passing it down through props. A toggle button flips light/dark.
class App extends Component {
  constructor(props) {
    super(props);
    this.state = { theme: 'dark' };
    this.toggleTheme = this.toggleTheme.bind(this);
  }

  toggleTheme() {
    this.setState((prev) => ({ theme: prev.theme === 'dark' ? 'light' : 'dark' }));
  }

  render() {
    return (
      <ThemeContext.Provider value={this.state.theme}>
        <div>
          <h1>Employee Management ({this.state.theme} theme)</h1>
          <button onClick={this.toggleTheme}>Toggle Theme</button>
          <EmployeesList />
        </div>
      </ThemeContext.Provider>
    );
  }
}

export default App;
