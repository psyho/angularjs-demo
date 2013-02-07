(ns angularjs-demo.server
  (:use compojure.core)
  (:require [compojure.route :as route]
            [ring.util.response :as response]))

(defroutes app
  (GET "/" [] (response/redirect "/index.html"))
  (route/resources "/")
  (route/not-found "<h1>Page not found</h1>"))
