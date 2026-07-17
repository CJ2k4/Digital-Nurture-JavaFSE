import React from 'react';

// Guest page: can only browse flight details.
export function GuestPage() {
  return (
    <div>
      <h2>Flight Details (Guest View)</h2>
      <p>Please log in to book tickets.</p>
    </div>
  );
}

// User page: logged-in users can book tickets.
export function UserPage() {
  return (
    <div>
      <h2>Welcome back!</h2>
      <p>You can now book your tickets.</p>
      <button>Book Ticket</button>
    </div>
  );
}

export function LoginButton({ onClick }) {
  return <button onClick={onClick}>Login</button>;
}

export function LogoutButton({ onClick }) {
  return <button onClick={onClick}>Logout</button>;
}
