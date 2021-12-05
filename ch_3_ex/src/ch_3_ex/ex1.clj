(ns ch-3-ex.ex1 
  (:gen-class))

(defn use-str
  "Use str function"
  [person]
  (str "Hello " person))

(defn use-vector
  "Use str function"
  [person]
  (vector "Hello " person))

(defn use-list
  "Use str function"
  [person]
  (list "Hello " person))

(defn use-hash-map
  "Use str function"
  [person]
  {:person person})

(defn use-hash-set
  "Use str function"
  [person]
  (set ["Sam" person]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (println (use-str "Sam"))
  (println (use-vector "Sam"))
  (println (use-list "Sam"))
  (println ((use-hash-map "Sam") :person))
  (println (use-hash-set "Sam")))

(-main)
