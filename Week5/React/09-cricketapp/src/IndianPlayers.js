import React from 'react';

// Exercise 9 (part 2): destructuring and array merge (spread) features of ES6.
function IndianPlayers() {
  const players = ['Sachin', 'Virat', 'Rohit', 'Dhoni', 'Rahul', 'Pant'];

  // Destructuring: pick specific "odd" and "even" positioned players.
  const [player1, player2, player3, player4] = players;
  const oddTeamPlayers = [player1, player3]; // 1st and 3rd
  const evenTeamPlayers = [player2, player4]; // 2nd and 4th

  // Merge feature of ES6 (spread operator).
  const T20players = ['Suryakumar', 'Ishan'];
  const RanjiTrophyPlayers = ['Sarfaraz', 'Mayank'];
  const mergedPlayers = [...T20players, ...RanjiTrophyPlayers];

  return (
    <div>
      <h2>Indian Players</h2>
      <h3>Odd Team Players (destructuring)</h3>
      <p>{oddTeamPlayers.join(', ')}</p>

      <h3>Even Team Players (destructuring)</h3>
      <p>{evenTeamPlayers.join(', ')}</p>

      <h3>Merged Players — T20 + Ranji Trophy (merge/spread)</h3>
      <p>{mergedPlayers.join(', ')}</p>
    </div>
  );
}

export default IndianPlayers;
