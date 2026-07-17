import React, { Component } from 'react';

// Exercise 4: class-based component demonstrating the component life cycle.
// - constructor initializes state with an empty list of posts
// - loadPosts() uses the Fetch API to retrieve posts
// - componentDidMount() calls loadPosts() after the first render
// - componentDidCatch() reports any rendering errors as an alert
class Posts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
    };
  }

  loadPosts() {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then((response) => {
        if (!response.ok) {
          throw new Error('Failed to fetch posts');
        }
        return response.json();
      })
      .then((data) => {
        this.setState({ posts: data });
      })
      .catch((error) => {
        alert('Error while loading posts: ' + error.message);
      });
  }

  componentDidMount() {
    this.loadPosts();
  }

  componentDidCatch(error, info) {
    alert('An error occurred in the Posts component: ' + error);
  }

  render() {
    return (
      <div>
        <h1>List of Posts</h1>
        {this.state.posts.map((post) => (
          <div key={post.id} style={{ borderBottom: '1px solid #ccc', margin: '10px' }}>
            <h3>{post.title}</h3>
            <p>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}

export default Posts;
