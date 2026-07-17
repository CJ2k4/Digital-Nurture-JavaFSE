import React from 'react';

// Exercise 9 (part 1): use map() to render an array of players and
// arrow functions + filter() to show players with scores below 70.
function ListofPlayers() {
  const players = [
    { name: 'Rohit', score: 85 },
    { name: 'Dhawan', score: 65 },
    { name: 'Kohli', score: 90 },
    { name: 'Rahul', score: 55 },
    { name: 'Pant', score: 72 },
    { name: 'Hardik', score: 40 },
    { name: 'Jadeja', score: 68 },
    { name: 'Bumrah', score: 15 },
    { name: 'Shami', score: 25 },
    { name: 'Chahal', score: 30 },
    { name: 'Ashwin', score: 45 },
  ];

  // Arrow function used to filter scores below 70.
  const belowSeventy = players.filter((player) => player.score < 70);

  return (
    <div>
      <h2>All Players (using map)</h2>
      <ul>
        {players.map((player, index) => (
          <li key={index}>
            {player.name} - {player.score}
          </li>
        ))}
      </ul>

      <h2>Players with score below 70 (using arrow function + filter)</h2>
      <ul>
        {belowSeventy.map((player, index) => (
          <li key={index}>
            {player.name} - {player.score}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ListofPlayers;
