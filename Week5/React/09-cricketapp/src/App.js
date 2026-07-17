import React from 'react';
import ListofPlayers from './ListofPlayers';
import IndianPlayers from './IndianPlayers';

// Display one of the two components based on a flag using a simple if/else.
function App() {
  const flag = true;

  let content;
  if (flag) {
    content = <ListofPlayers />;
  } else {
    content = <IndianPlayers />;
  }

  return (
    <div>
      <h1>Cricket App</h1>
      {content}
    </div>
  );
}

export default App;
