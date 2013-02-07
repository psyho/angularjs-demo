(ns projects
  (:use-macros [angularjs.macros :only [defn$ let$ letfn$]]))

(defn hello [{:keys [name]}]
  (js/alert (str "Hi, " name "!")))

(defn$ hello-controller [$scope] 
  (let$ [user {:name "Adam"}]
    (letfn$ [(say-hello [] (hello @user))])))
