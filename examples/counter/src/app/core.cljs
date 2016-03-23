(ns app.core
  (:require [counter.core :as counter]
            [reagent-mvsa.core :as mvsa]
            [reagent.core :as r]))

(enable-console-print!)

(defn main
  []
  (let [app (mvsa/app counter/spec)
        [app-view-model app-view] (mvsa/connect-ui app counter/view-model counter/view)]
    (r/render app-view (.getElementById js/document "root"))
    (assoc app :view-model app-view-model)))

(def app (main))

;;;;;;;;;;;;;;;;;;;;;;;; Figwheel stuff
(defn before-jsload
  [])

(defn on-jsload
  []
  #_(. js/console clear))