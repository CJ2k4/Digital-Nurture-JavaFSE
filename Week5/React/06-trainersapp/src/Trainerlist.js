import React from 'react';
import { Link } from 'react-router-dom';

// Accepts the trainers data as a prop and renders their names as links.
// Each name links to the TrainerDetail route, passing the trainer id.
function TrainersList({ trainers }) {
  return (
    <div>
      <h2>Trainers List</h2>
      <ul>
        {trainers.map((trainer) => (
          <li key={trainer.trainerId}>
            <Link to={`/trainers/${trainer.trainerId}`}>{trainer.name}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TrainersList;
