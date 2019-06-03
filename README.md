# resolve-wren

Shopping Cart Total

### Steps

- Provided json file is read from resources dir and converted to a clojure data structure.
- Then that data structure is parsed to create a map of product prices and volume prices, where a key of each product in the map is id value.
- Shopping cart is represented as a sequence of item ids of arbitrary ordering.
- The shopping cart is passed to `cart-total` function along with the product price map.

#### Example

```clojure
(let [products (:treats (slurp-products "products.json"))
      prod-map (product-map products)
      cart [3 1 2 4 4]]
  (println (cart-total cart prod-map)))
```

### Tests

```bash
lein test
```
