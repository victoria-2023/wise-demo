import axios from 'axios';
const client = axios.create({ baseURL: 'http://localhost:8080/api/payments' });
export const fetchAll     = ()     => client.get('/');
export const createPayment = payload => client.post('/', payload);
