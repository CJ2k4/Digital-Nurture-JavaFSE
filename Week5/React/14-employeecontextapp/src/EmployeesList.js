import React from 'react';
import EmployeeCard from './EmployeeCard';

// Theme name is no longer passed explicitly to the child EmployeeCard;
// it is read from context inside EmployeeCard.
function EmployeesList() {
  const employees = [
    { id: 1, name: 'Charitra Jain', designation: 'Software Engineer' },
    { id: 2, name: 'Meera Nair', designation: 'Team Lead' },
    { id: 3, name: 'Raghav Iyer', designation: 'Architect' },
  ];

  return (
    <div>
      <h2>Employees</h2>
      {employees.map((emp) => (
        <EmployeeCard key={emp.id} employee={emp} />
      ))}
    </div>
  );
}

export default EmployeesList;
