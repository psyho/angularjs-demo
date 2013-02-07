(ns angularjs.macros
  (:require [clojure.string :as string]))

(defmacro fn$ [args & body]
  (let [names (vec (map str args))]
    `(angularjs/add-$inject ~names (fn ~args ~@body))))

(defmacro defn$ [name args & body]
  `(def ~name (fn$ ~args ~@body)))

(defn atom-pair [[name value]]
  `(~name 
     (angularjs/scope-atom
       ~'$scope 
       ~(str name)
       ~value)))

(defmacro let$ [pairs & body]
  (let [atom-pairs (vec (mapcat atom-pair (partition 2 pairs)))]
    `(let ~atom-pairs ~@body)))

(defn camelize [s]
  (string/replace s #"-\w" (comp str last string/upper-case)))

(defn assignment [[name]]
  `(aset ~'$scope ~(camelize name) (angularjs/with-clj-args ~name)))

(defmacro letfn$ [fns & body]
  (let [assigns (map assignment fns)]
    `(letfn ~fns
       ~@assigns
       ~@body)))
