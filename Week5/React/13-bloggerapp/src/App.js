import React, { Component } from 'react';
import { BookDetails, BlogDetails, CourseDetails } from './details';

// Exercise 13: implement conditional rendering in as many ways as possible.
class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showBook: true,
      showBlog: true,
      showCourse: true,
    };
  }

  render() {
    const { showBook, showBlog, showCourse } = this.state;

    // Way 1: if/else with an element variable.
    let bookSection;
    if (showBook) {
      bookSection = <BookDetails />;
    } else {
      bookSection = <p>Book details are hidden.</p>;
    }

    return (
      <div>
        <h1>Blogger App — Conditional Rendering</h1>

        {/* Way 1: element variable (if/else) */}
        {bookSection}

        {/* Way 2: ternary operator */}
        {showBlog ? <BlogDetails /> : <p>Blog details are hidden.</p>}

        {/* Way 3: logical && (short-circuit) */}
        {showCourse && <CourseDetails />}

        {/* Way 4: IIFE with switch-like logic */}
        {(() => {
          if (!showBook && !showBlog && !showCourse) {
            return <p>Nothing to display.</p>;
          }
          return null;
        })()}
      </div>
    );
  }
}

export default App;
