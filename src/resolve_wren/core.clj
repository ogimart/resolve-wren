(ns resolve-wren.core
 (:require [cheshire.core :as json] 
           [clojure.java.io :as io]))

(defn slurp-products
  "Reads bakery products from json file on the classpath.
  Takes file name."
  [file-name]
  (json/parse-string (slurp (io/resource file-name)) true))

(defn product-map
  "Creates map of products where the key is id value.
  Takes a sequence of products."
  [products]
  (into {} (map (fn [p] {(:id p) p}) products)))

(defn item-total
  "Calculates total price of items of same category.
  Takes product id, number of items, and map of pruducts."
  [id n prod-map]
  (let [price (get-in prod-map [id :price])
        bulk (get-in prod-map [id :bulkPricing])]
    (if (nil? bulk)
      (* n price)
      (+ (* (quot n (:amount bulk)) (:totalPrice bulk))
         (* (rem n (:amount bulk)) price)))))

(defn cart-total
  "Calculates total of the cart.
  Takes cart as a sequence of item ids, and map of products."
  [cart prod-map]
  (let [freq (frequencies cart)
        total (map (fn [[id n]] (item-total id n prod-map)) freq)]
    (reduce + total)))
