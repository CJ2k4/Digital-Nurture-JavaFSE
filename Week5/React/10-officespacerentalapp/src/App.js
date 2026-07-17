import React from 'react';

// Exercise 10: use JSX to create elements/attributes and render DOM.
// Rent is shown in red if below 60000, green if above 60000.
function App() {
  const officeSpaces = [
    {
      name: 'DBS',
      rent: 50000,
      address: 'Chennai, Tamil Nadu',
      image: 'https://picsum.photos/id/1011/200/120',
    },
    {
      name: 'Regus',
      rent: 65000,
      address: 'Bengaluru, Karnataka',
      image: 'https://picsum.photos/id/1015/200/120',
    },
    {
      name: 'WeWork',
      rent: 72000,
      address: 'Hyderabad, Telangana',
      image: 'https://picsum.photos/id/1016/200/120',
    },
  ];

  const heading = <h1>Office Space, at Affordable Range</h1>;

  return (
    <div>
      {heading}
      {officeSpaces.map((office, index) => (
        <div key={index} style={{ border: '1px solid #ccc', margin: '10px', padding: '10px' }}>
          <img src={office.image} alt={office.name} />
          <h2>Name: {office.name}</h2>
          <p style={{ color: office.rent < 60000 ? 'red' : 'green' }}>Rent: {office.rent}</p>
          <p>Address: {office.address}</p>
        </div>
      ))}
    </div>
  );
}

export default App;
