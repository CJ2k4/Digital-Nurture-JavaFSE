import React, { Component } from 'react';

// Exercise 17: fetch user details from the RandomUser API in
// componentDidMount() and display title, first name and image.
class Getuser extends Component {
  constructor(props) {
    super(props);
    this.state = { person: null, loading: true };
  }

  async componentDidMount() {
    try {
      const response = await fetch('https://api.randomuser.me/');
      const data = await response.json();
      this.setState({ person: data.results[0], loading: false });
    } catch (error) {
      this.setState({ loading: false });
      alert('Error fetching user: ' + error.message);
    }
  }

  render() {
    const { person, loading } = this.state;

    if (loading) {
      return <p>Loading user...</p>;
    }
    if (!person) {
      return <p>No user found.</p>;
    }

    return (
      <div>
        <h2>User Details</h2>
        <p>Title: {person.name.title}</p>
        <p>First Name: {person.name.first}</p>
        <img src={person.picture.large} alt={person.name.first} />
      </div>
    );
  }
}

export default Getuser;
