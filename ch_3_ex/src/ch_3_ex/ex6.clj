(defn symmetrize-body-parts
  "Documenation: "
  [asym-body-parts]
  (expand-body-parts asym-body-parts 
		     (fn [part]
		       (matching-part part))))

(defn radial-body-parts-n
  "Documenation: "
  [body-parts n]
  (expand-body-parts body-parts 
		     (fn [part]
		       (if (radialise-part? part)
                         (radialise-n part n)
                         [part]))))
