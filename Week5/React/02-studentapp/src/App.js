import React from 'react';
import Home from './Components/Home';
import About from './Components/About';
import Contact from './Components/Contact';

// Exercise 2: invoke Home, About and Contact components together.
function App() {
  return (
    <div>
      <h1>Student Management Portal</h1>
      <Home />
      <About />
      <Contact />
    </div>
  );
}

export default App;
