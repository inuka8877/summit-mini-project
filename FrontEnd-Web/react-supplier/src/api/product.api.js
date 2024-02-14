import axios from 'axios';

import config from './config';

const HOST = config.backendHost;

export async function getProducts({supplierId}) {
  try {
    const response = await axios.get(`${HOST}/api/products?supplierId=${supplierId}`);
    return response.data;
  } catch (err) {
    console.log(err);
    return await Promise.reject('Failed to get products list!');
  }
}
