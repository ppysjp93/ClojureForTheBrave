(ns fwpd.core
  (:gen-class))

(def filename "suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int
  "Documenation: "
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn convert
  "Documenation: "
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(parse (slurp filename))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(def first-row (first (parse (slurp filename))))

(def first-row-vector (map vector first-row))

(def first-row-map (map vector vamp-keys first-row))



(defn glitter-filter
  "Documenation: "
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(def records (mapify (parse (slurp filename))))

(def first-record (first records))

(glitter-filter 3 records)

;; Ex_1

(map :name (glitter-filter 3 records))

;; Ex_2

(def new-suspect {:name "Sam" :glitter-index 10})

(defn append
  "Documenation: "
  [records new-suspect]
  (into () (into () (conj (into [] records) new-suspect))))

;; Here I am utilizing the fact that conj with a vector adds to the end of
;; of the vector. I then use the double into get everything back in order.
;; I'm sure there must be a better way of doing this. 

(defn append
  "Documenation: "
  [records new-suspect]
  (into () (reverse (conj (into [] records) new-suspect))))

;; I think the above implementation makes a bit more sense even though
;; we haven't technically come across how to use reverse. 


;; Ex 3


(defn is-name-present?
  "Documenation: "
  [record]
  (contains? record :name))

(defn is-glitter-index-pressent?
  "Documenation: "
  [record]
  (contains? record :glitter-index))

(def validators {:name is-name-present?
                   :glitter-index is-glitter-index-pressent?})


(defn validate
  "Documenation: "
  [validate-funcs record]
  (and ((:name validate-funcs) record)
       ((:glitter-index validate-funcs) record)))

(defn append
  "Documenation: "
  [records new-suspect]
  (defn append
    "Documenation: "
    [records new-suspect]
    (into () (reverse (conj (into [] records) new-suspect))))
  (if (validate validators new-suspect)
    (append records new-suspect)
    records))

;; Ex 4


;; Something like this will be needed and on each record to be
;; processed


(str (:name first-record) "," (:glitter-index first-record) "\n")

;; I'm also thinking that reduce will be neeeded with an initial empty
;; string and then build it up. 


(reduce str "" ["hello" "sam"])

(reduce (fn [final record]
          (into final (identity record)))
        []
        records)



(clojure.string/join "\n"
                     (map (fn [record]
                            (str (:name record) ","
                                 (:glitter-index record)))
                          records))
