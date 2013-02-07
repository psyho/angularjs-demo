(ns users
  (:require [angularjs :as ng])
  (:use-macros [angularjs.macros :only [defn$ let$ letfn$]]))

(defn remove-user [users user]
  (remove #{user} users))

(defn$ user-list-controller [$scope] 
  (let$ [users []
         newUser {:name ""}]
    (letfn$ [(add [] 
                  (ng/swap! users conj @newUser)
                  (ng/reset! newUser {:name ""}))
             (remove [u]
                     (ng/swap! users remove-user u))])))
