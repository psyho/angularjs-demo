(defproject angularjs-demo "0.1.0-SNAPSHOT"
  :description "Example of using AngularJS with ClojureScript"
  :url "https://github.com/psyho/angularjs-demo"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.5"]]
  :plugins [[lein-cljsbuild "0.3.0"]
            [lein-ring "0.8.2"]]
  :hooks [leiningen.cljsbuild]
  :source-paths ["src/clj"]
  :cljsbuild { 
              :builds {
                       :main 
                       {:source-paths ["src/cljs"]
                        :compiler {:output-to "resources/public/js/application.js"
                                   :externs ["externs/angularjs-externs.js"]
                                   :optimizations :advanced
                                   :pretty-print false}
                        :jar true}

                       :debug 
                       {:source-paths ["src/cljs"]
                        :compiler {:output-to "resources/public/js/application.js"
                                   :externs ["externs/angularjs-externs.js"]
                                   :optimizations :whitespace
                                   :pretty-print true}
                        :jar true}}}
  :ring {:handler angularjs-demo.server/app})

