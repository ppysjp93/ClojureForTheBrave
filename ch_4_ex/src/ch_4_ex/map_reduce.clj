(ns ch-4-ex.core
  (:gen-class))

(defn my-map
  "Documenation: "
  [f coll]
  (reduce (fn [x y]
            (cons (f y) x))
          ()
          (reverse coll)))

(map inc [0 1 2 3 4])

(my-map inc [0 1 2 3 4])

; I wonder if there is a way of using 'into' which would be 
; the clojure way.

; So above is a close shot of what we are after
; and below is how we should be thinking of this problem
; my question is there a way of doing this without reverse? 

; (reduce f [1 2 3 4 5])

; Below is what reduce looks like abstractly

;(f (f (f (f 1 2) 3) 4) 5)

; (map f [1 2 3 4 5])

; Below is how we can think map work s

; ((f 1) (f 2) (f 3) (f 4) (f 5))


; We are trying to make reduce produce map

