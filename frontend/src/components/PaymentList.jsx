import { useEffect, useState } from 'react';
import { fetchAll } from '../api/payments';

export default function PaymentList() {
  const [payments, setPayments] = useState([]);

  useEffect(() => {
    fetchAll().then(res => setPayments(res.data));
  }, []);

  if (!payments.length) return <p>No payments yet.</p>;

  return (
    <table border="1" cellPadding="4">
      <thead>
        <tr>
          <th>ID</th><th>User</th><th>Amount</th><th>From</th><th>To</th><th>At</th>
        </tr>
      </thead>
      <tbody>
        {payments.map(p => (
          <tr key={p.id}>
            <td>{p.id}</td>
            <td>{p.userId}</td>
            <td>{p.amount}</td>
            <td>{p.from}</td>
            <td>{p.to}</td>
            <td>{new Date(p.createdAt).toLocaleString()}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
