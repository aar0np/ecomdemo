import _ from "lodash";

export const fetcher = (url) => fetch(url).then((res) => res.json());

export const getProducts = async () => {
  const response = await fetch("/mocks/products.json");
  const data = await response.json();
  return data.data;
};

export const getProduct = async (productId) => {
  const response = await fetch("/mocks/products.json");
  const data = await response.json();
  return _.find(data.data, { product_id: productId });
};
