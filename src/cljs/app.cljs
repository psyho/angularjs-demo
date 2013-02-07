(ns app
  (:require projects
            [angularjs :as ng]))

(def app (ng/module "project" []))
(ng/controller app "Hello" projects/hello-controller)
