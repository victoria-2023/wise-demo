import { useState } from 'react';
import { createPayment } from '../api/payments';

export default function PaymentForm({ onCreated }) {
  const [form, setForm] = useState({ userId: '', amount: '', from: '', to: '' });
  const [error, setError] = useState(null);

  const handleSubmit = async e => {
    e.preventDefault();
    setError(null);
    try {
      await createPayment({
        userId: form.userId,
        amount: parseFloat(form.amount),
        from: form.from,
        to: form.to,
      });
      setForm({ userId: '', amount: '', from: '', to: '' });
      onCreated();
    } catch (err) {
      setError(err.toString());
    }
  };

  return (
    <div>
      {error && <p style={{ color: 'red' }}>ERROR: {error}</p>}
      <form onSubmit={handleSubmit}>
        <input
          placeholder="User ID"
          value={form.userId}
          onChange={e => setForm({ ...form, userId: e.target.value })}
          required
        />
        <input
          placeholder="Amount"
          type="number"
          step="0.01"
          value={form.amount}
          onChange={e => setForm({ ...form, amount: e.target.value })}
          required
        />
        <input
          placeholder="From"
          value={form.from}
          onChange={e => setForm({ ...form, from: e.target.value })}
          required
        />
        <input
          placeholder="To"
          value={form.to}
          onChange={e => setForm({ ...form, to: e.target.value })}
          required
        />
        <button type="submit">Create Payment</button>
      </form>
    </div>
  );
}
