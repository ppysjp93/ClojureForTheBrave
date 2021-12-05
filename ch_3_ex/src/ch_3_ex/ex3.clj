(ns ch-3-ex.ex3
  (:gen-class))


; Exercise 3

(defn dec-maker
  "Documenation: "
  [number]
  (defn returned-function
    "Documenation: "
    [x]
    (- x number)))

(defn dec-maker2
  "Documenation: "
  [number]
  (fn [x]
    (- x number)))

(def dec3 (dec-maker2 3))

(dec3 5)
