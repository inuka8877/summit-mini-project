import axios from 'axios';

import config from './config';

const HOST = config.backendHost;

export async function getProducts() {
  try {
    const response = await axios.get(`${HOST}/api/products`);
    return response.data;
  } catch (err) {
    console.log(err);
    return await Promise.reject('Failed to get products list!');
  }
}
