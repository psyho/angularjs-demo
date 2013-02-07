(ns debug
  (:require [clojure.string :as string]))

(defn log [& msg]
  (.log js/console (string/join " " msg)))

(defn p 
  ([obj] (log (pr-str obj)))
  ([name obj] (log name (pr-str obj))))
