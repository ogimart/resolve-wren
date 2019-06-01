(ns resolve-wren.core-test
  (:require [clojure.test :refer :all]
            [resolve-wren.core :refer :all]))

(deftest bakery-test
  (testing "bakery cart total"
    (let [products (:treats (slurp-products "products.json"))
          prod-map (product-map products)
          cart-1 [3 1 1 1 1 2]
          cart-2 [3 3 3 3 3 3 3 3] 
          cart-3 [3 1 2 4 4]]
      (is (= 16.25 (cart-total cart-1 prod-map)))
      (is (=  8.50 (cart-total cart-2 prod-map)))
      (is (= 12.25 (cart-total cart-3 prod-map))))))
