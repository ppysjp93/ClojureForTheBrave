(ns ch-3-ex.core
  (:gen-class))

(def asym-hobbit-body-parts [{:name "head" :size 3}
			     {:name "left-eye" :size 1}
			     {:name "left-ear" :size 1}
			     {:name "mouth" :size 1}
			     {:name "nose" :size 1}
			     {:name "neck" :size 2}
			     {:name "left-shoulder" :size 3}
			     {:name "left-upper-arm" :size 3}
			     {:name "chest" :size 10}
			     {:name "back" :size 10}
			     {:name "left-forearm" :size 3}
			     {:name "abdomen" :size 6}
			     {:name "left-kidney" :size 1}
			     {:name "left-hand" :size 2}
			     {:name "left-knee" :size 2}
			     {:name "left-thigh" :size 4}
			     {:name "left-lower-leg" :size 3}
			     {:name "left-achilles" :size 1}
			     {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})


(defn expand-body-parts
  "Expects a seq of map that have a :name and :size"
  [body-parts f]
  (reduce (fn [final-body-parts part]
	    (into final-body-parts (f part)))
	  []
	  body-parts))

(defn symmetrize-body-parts
  "Documenation: "
  [asym-body-parts]
  (expand-body-parts asym-body-parts 
		     (fn [part]
		       (matching-part part))))



(defn radialise-part?
  "Documenation: "
  [part]
  (let [radial-parts
        (hash-set "shoulder" "hand" "arm" "eye" "leg" "foot")]
    (contains? radial-parts (:name part))))

(defn gen-n-list
  "Documenation: "
  [n]
  (loop [iteration 1
         result []]
    (if (> iteration n)
      result
      (recur (inc iteration)
             (conj result iteration)))))

(defn radialise-n
  "Documenation: "
  [part n]
  (reduce (fn [final elem]
          (into final [{:name (str (:name part) "-" elem)
                        :size (:size part)}]))
        []
        (gen-n-list n)))


(defn radial-body-parts-n
  "Documenation: "
  [body-parts n]
  (expand-body-parts body-parts 
		     (fn [part]
		       (if (radialise-part? part)
                         (radialise-n part n)
                         [part]))))

(def body-parts [{:name "head" :size 3}
		 {:name "eye" :size 1}
		 {:name "ear" :size 1}
		 {:name "mouth" :size 1}
		 {:name "nose" :size 1}
		 {:name "neck" :size 2}
		 {:name "shoulder" :size 3}
		 {:name "arm" :size 3}
		 {:name "chest" :size 10}
		 {:name "back" :size 10}
		 {:name "abdomen" :size 6}
		 {:name "hand" :size 2}
		 {:name "leg" :size 1}
		 {:name "foot" :size 2}])

(radial-body-parts-n body-parts 8)





