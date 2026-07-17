import React from 'react';
import '../Stylesheets/mystyle.css';

// Function component that accepts Name, School, Total and goal as props
// and calculates the average score of a student.
function CalculateScore(props) {
  const { name, school, total, goal } = props;
  const average = (total / goal).toFixed(2);

  return (
    <div className="score-card">
      <h1>Score Calculator</h1>
      <h2>Name: {name}</h2>
      <p>School: {school}</p>
      <p>Total Marks: {total}</p>
      <p>Goal: {goal}</p>
      <h3>Average Score: {average}</h3>
    </div>
  );
}

export default CalculateScore;
