(ns app
  (:require users
            [angularjs :as ng]))

(def app (ng/module "project" []))
(ng/controller app "UserListController" users/user-list-controller)
