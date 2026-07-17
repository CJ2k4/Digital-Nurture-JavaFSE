import React, { useContext } from 'react';
import ThemeContext from './ThemeContext';
import './theme.css';

// Retrieves the theme from context (no longer via props) using useContext.
function EmployeeCard({ employee }) {
  const theme = useContext(ThemeContext);

  return (
    <div className="employee-card">
      <p>
        <strong>{employee.name}</strong> — {employee.designation}
      </p>
      {/* Theme name from context drives the button className. */}
      <button className={theme}>View Profile</button>
    </div>
  );
}

export default EmployeeCard;
