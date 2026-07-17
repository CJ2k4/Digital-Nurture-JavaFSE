import React from 'react';
import CalculateScore from './Components/CalculateScore';

// Exercise 3: invoke the CalculateScore functional component with props.
function App() {
  return (
    <div>
      <CalculateScore name="Charitra" school="Delhi Public School" total={450} goal={500} />
    </div>
  );
}

export default App;
