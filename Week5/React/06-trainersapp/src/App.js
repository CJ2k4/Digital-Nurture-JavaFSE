import React from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Home from './Home';
import TrainersList from './Trainerlist';
import TrainerDetail from './TrainerDetails';
import TrainersMock from './TrainersMock';

// Exercise 6: routing with BrowserRouter, Routes, Route and Link.
//   /                 -> Home
//   /trainers         -> TrainersList
//   /trainers/:id     -> TrainerDetail
function App() {
  return (
    <BrowserRouter>
      <div>
        <h1>Cognizant Academy</h1>
        <nav>
          <Link to="/">Home</Link> | <Link to="/trainers">Trainers</Link>
        </nav>
        <hr />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/trainers" element={<TrainersList trainers={TrainersMock} />} />
          <Route path="/trainers/:id" element={<TrainerDetail />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
