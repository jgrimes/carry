(defproject
  todomvc "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.8.51"]

                 [org.clojure/core.match "0.3.0-alpha4"]

                 [reagent "0.6.0-SNAPSHOT" :exclusions [cljsjs/react]]
                 [cljsjs/react-with-addons "15.0.2-0"]

                 [com.domkm/silk "0.1.2"]
                 [com.rpl/specter "0.10.0"]
                 [funcool/hodgepodge "0.1.4"]
                 [prismatic/schema "1.1.0"]

                 [binaryage/devtools "0.6.1"]]

  :pedantic? :abort

  :plugins [[lein-cljsbuild "1.1.2"]
            [lein-figwheel "0.5.0-6" :exclusions [org.clojure/clojure]]]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "resources/private" "target"]

  :profiles {:local-deps   {:dependencies [; required by carry-devtools:
                                           [cljsjs/jquery-ui "1.11.4-0"]
                                           [cljsjs/filesaverjs "1.1.20151003-0"]]}

             :clojars-deps {:dependencies [[carry "0.1.0"]
                                           [carry-reagent "0.1.0"]
                                           [carry-logging "0.1.0"]
                                           [carry-history "0.1.0"]
                                           [carry-schema "0.1.0"]

                                           ; required by carry-devtools:
                                           [cljsjs/jquery-ui "1.11.4-0"]
                                           [cljsjs/filesaverjs "1.1.20151003-0"]]}}

  :cljsbuild {:builds [{:id           "clojars-deps"
                        :source-paths ["src"
                                       "../../contrib/persistence/src"
                                       "../../contrib/devtools/src"]
                        :compiler     {:main                 app.core
                                       :asset-path           "js/compiled/clojars-deps/out"
                                       :output-to            "resources/public/js/compiled/frontend.js"
                                       :output-dir           "resources/public/js/compiled/clojars-deps/out"
                                       :source-map-timestamp true
                                       :compiler-stats       true
                                       :parallel-build       false}
                        :figwheel     {:on-jsload     "app.core/on-jsload"
                                       :before-jsload "app.core/before-jsload"}}

                       {:id           "local-deps"
                        :source-paths ["src"
                                       "../../src"
                                       "../../contrib/reagent/src"
                                       "../../contrib/history/src"
                                       "../../contrib/persistence/src"
                                       "../../contrib/logging/src"
                                       "../../contrib/devtools/src"
                                       "../../contrib/schema/src"]
                        :compiler     {:main                 app.core
                                       :asset-path           "js/compiled/local-deps/out"
                                       :output-to            "resources/public/js/compiled/frontend.js"
                                       :output-dir           "resources/public/js/compiled/local-deps/out"
                                       :source-map-timestamp true
                                       :compiler-stats       true
                                       :parallel-build       false}
                        :figwheel     {:on-jsload     "app.core/on-jsload"
                                       :before-jsload "app.core/before-jsload"}}

                       {:id           "min"
                        :source-paths ["src"
                                       "../../src"
                                       "../../contrib/reagent/src"
                                       "../../contrib/history/src"
                                       "../../contrib/persistence/src"
                                       "../../contrib/logging/src"
                                       "../../contrib/devtools/src"
                                       "../../contrib/schema/src"]
                        :compiler     {:main           app.core
                                       :output-to      "resources/public/js/compiled/frontend.js"
                                       :optimizations  :advanced
                                       :pretty-print   false
                                       :compiler-stats true
                                       :parallel-build false}}]}

  :aliases {"cljsbuild-min" ["with-profile" "+local-deps" "cljsbuild" "once" "min"]}

  :figwheel {:css-dirs ["resources/public/css"]}

  :hiera
  {:path          "ns-hierarchy.png"
   :vertical      false
   :show-external true
   :cluster-depth 1})
