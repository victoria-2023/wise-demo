import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import PaymentForm from './components/PaymentForm';
import PaymentList from './components/PaymentList';
import reportWebVitals from './reportWebVitals';

function App() {
  const [refreshKey, setRefreshKey] = React.useState(0);

  return (
    <div style={{ padding: 20 }}>
      <h1>Wise Demo Payments</h1>
      <PaymentForm onCreated={() => setRefreshKey(k => k + 1)} />
      <hr />
      <PaymentList key={refreshKey} />
    </div>
  );
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

reportWebVitals();
