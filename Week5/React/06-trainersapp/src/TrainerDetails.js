import React from 'react';
import { useParams } from 'react-router-dom';
import TrainersMock from './TrainersMock';

// Retrieves the "id" parameter from the URL using the useParams hook,
// queries the mock data and displays the matching trainer's details.
function TrainerDetail() {
  const { id } = useParams();
  const trainer = TrainersMock.find((t) => t.trainerId === Number(id));

  if (!trainer) {
    return <h3>Trainer not found</h3>;
  }

  return (
    <div>
      <h2>Trainer Details</h2>
      <p><strong>Trainer Id:</strong> {trainer.trainerId}</p>
      <p><strong>Name:</strong> {trainer.name}</p>
      <p><strong>Email:</strong> {trainer.email}</p>
      <p><strong>Phone:</strong> {trainer.phone}</p>
      <p><strong>Technology:</strong> {trainer.technology}</p>
      <p><strong>Skills:</strong> {trainer.skills.join(', ')}</p>
    </div>
  );
}

export default TrainerDetail;
