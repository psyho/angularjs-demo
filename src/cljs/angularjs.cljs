(ns angularjs)

(defn add-$inject [$inject f]
  (aset f "$inject" (to-array (map str $inject)))
  f)

(defn update-atom-on-scope-change [atom]
  (fn [n] 
    (let [new-value (js->clj n :keywordize-keys true)]
      (when (not= @atom new-value)
        (reset! atom new-value)))))

(defn update-scope-on-atom-change [$scope field]
  (fn [k r o n] 
    (let [new-value (clj->js n)]
      (when (not= (aget $scope field) new-value)
        (aset $scope field (clj->js n))))))

(defn scope-synced-atom [$scope name value]
  (let [at (atom value)]
    (aset $scope name (clj->js @at))
    (add-watch at :update-$scope (update-scope-on-atom-change $scope name))
    (.$watch $scope name (update-atom-on-scope-change at) true)
    at))

(defn module
  ([name] 
   (.module js/angular name))
  ([name dependencies] 
   (.module js/angular name (to-array dependencies))))

(defn controller [app name ctrl] 
  (.controller app name ctrl))
