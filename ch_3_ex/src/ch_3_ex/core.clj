; Exercise 4

(map inc [1 1 2 2])

(defn map-set
  "Documenation: "
  [f coll]
  (set (map f coll)))

(map-set inc [1 1 2 2])

