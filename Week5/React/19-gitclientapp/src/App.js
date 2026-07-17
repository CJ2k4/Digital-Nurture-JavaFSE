import React, { Component } from 'react';
import GitClient from './GitClient';

// Exercise 19: use GitClient to fetch and display a user's repositories.
class App extends Component {
  constructor(props) {
    super(props);
    this.state = { user: 'techiesyed', repos: [], loading: true };
  }

  componentDidMount() {
    const client = new GitClient();
    client
      .getRepositories(this.state.user)
      .then((repos) => this.setState({ repos, loading: false }))
      .catch(() => this.setState({ loading: false }));
  }

  render() {
    const { user, repos, loading } = this.state;
    return (
      <div>
        <h1>GitHub Repositories for {user}</h1>
        {loading ? (
          <p>Loading...</p>
        ) : (
          <ul>
            {repos.map((name) => (
              <li key={name}>{name}</li>
            ))}
          </ul>
        )}
      </div>
    );
  }
}

export default App;
