(ns angularjs
  (:refer-clojure :exclude [swap! reset!]))

(defn add-$inject [$inject f]
  (aset f "$inject" (to-array (map str $inject)))
  f)

(deftype ScopeAtom [$scope name]
  IDeref
  (-deref [self] (.get self))
  
  Object
  (get [_] (js->clj (aget $scope name) :keywordize-keys true))
  (set [_ new-value] (aset $scope name (clj->js new-value)) new-value))

(defn reset! [a new-value]
  "Sets the value of atom to newval without regard for the
  current value. Returns newval."
  (.set a new-value))

(defn swap!
  "Atomically swaps the value of atom to be:
  (apply f current-value-of-atom args). Note that f may be called
  multiple times, and thus should be free of side effects.  Returns
  the value that was swapped in."
  ([a f]
     (reset! a (f (.get a))))
  ([a f x]
     (reset! a (f (.get a) x)))
  ([a f x y]
     (reset! a (f (.get a) x y)))
  ([a f x y z]
     (reset! a (f (.get a) x y z)))
  ([a f x y z & more]
     (reset! a (apply f (.get a) x y z more))))

(defn scope-atom [$scope name value]
  (let [atom (ScopeAtom. $scope name)]
    (reset! atom value)
    atom))

(defn with-clj-args [f]
  (fn [& args]
    (apply f (map #(js->clj % :keywordize-keys true) args))))

(defn module
  ([name] 
   (.module js/angular name))
  ([name dependencies] 
   (.module js/angular name (to-array dependencies))))

(defn controller [app name ctrl] 
  (.controller app name ctrl))
