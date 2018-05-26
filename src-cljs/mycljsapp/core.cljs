(ns mycljsapp.core
  (:require [reagent.core :as reagent]))

;(enable-console-print!)

(def verwalls [[5 5] [5 6] [5 7] [5 8] [5 9] [5 10] [11 5] [11 6] [11 7] [11 8] [11 9] [11 10] [7 7] [8 7] [6 6]])

(def horwalls [[5 5] [6 5] [7 5] [8 5] [9 5] [10 5] [5 11] [6 11] [7 11] [8 11] [9 11] [10 11] [8 8] [7 7] [8 10]])

(defonce state (reagent/atom {:centerx 7 :centery 7}))

(defn is-in? [an-item a-seq]
  (some #{an-item} a-seq))

(defn cell [x y]
  (let [realx (- x (- 9 (:centerx @state)))
        realy (- y (- 9 (:centery @state)))]
    [:td {:style {:background-image
                  (clojure.string/join "," (remove nil? [(if (= [9 9] [x y]) "url(img/center.png)" nil)

                                                         (if (is-in? [realx realy] verwalls) "url(img/wall-left.png)" nil)
                                                         (if (is-in? [(inc realx) realy] verwalls) "url(img/wall-right.png)" nil)
                                                         (if (is-in? [realx realy] horwalls) "url(img/wall-up.png)" nil)
                                                         (if (is-in? [realx (inc realy)] horwalls) "url(img/wall-down.png)" nil)

                                                         (if (and (is-in? [realx realy] verwalls) (is-in? [realx realy] horwalls)) "url(img/wall-corner-up-left.png)" nil)
                                                         (if (and (is-in? [realx realy] verwalls) (is-in? [realx (inc realy)] horwalls)) "url(img/wall-corner-down-left.png)" nil)
                                                         (if (and (is-in? [(inc realx) realy] verwalls) (is-in? [realx realy] horwalls)) "url(img/wall-corner-up-right.png)" nil)
                                                         (if (and (is-in? [(inc realx) realy] verwalls) (is-in? [realx (inc realy)] horwalls)) "url(img/wall-corner-down-right.png)" nil)

                                                         (if (and (is-in? [realx realy] verwalls) (is-in? [realx (dec realy)] verwalls)) "url(img/wall-left-continues-up.png)" nil)
                                                         (if (and (is-in? [realx realy] verwalls) (is-in? [realx (inc realy)] verwalls)) "url(img/wall-left-continues-down.png)" nil)
                                                         (if (and (is-in? [(inc realx) realy] verwalls) (is-in? [(inc realx) (dec realy)] verwalls)) "url(img/wall-right-continues-up.png)" nil)
                                                         (if (and (is-in? [(inc realx) realy] verwalls) (is-in? [(inc realx) (inc realy)] verwalls)) "url(img/wall-right-continues-down.png)" nil)

                                                         (if (and (is-in? [realx realy] horwalls) (is-in? [(dec realx) realy] horwalls)) "url(img/wall-up-continues-left.png)" nil)
                                                         (if (and (is-in? [realx realy] horwalls) (is-in? [(inc realx) realy] horwalls)) "url(img/wall-up-continues-right.png)" nil)
                                                         (if (and (is-in? [realx (inc realy)] horwalls) (is-in? [(dec realx) (inc realy)] horwalls)) "url(img/wall-down-continues-left.png)" nil)
                                                         (if (and (is-in? [realx (inc realy)] horwalls) (is-in? [(inc realx) (inc realy)] horwalls)) "url(img/wall-down-continues-right.png)" nil)
                                                         ]))}}]))

(defn page [] [:table {:id "grid" :style {:border-collapse :collapse} :cellspacing "0"}
               [:tr (cell 1 1) (cell 2 1) (cell 3 1) (cell 4 1) (cell 5 1) (cell 6 1) (cell 7 1) (cell 8 1) (cell 9 1) (cell 10 1) (cell 11 1) (cell 12 1) (cell 13 1) (cell 14 1) (cell 15 1) (cell 16 1) (cell 17 1)]
               [:tr (cell 1 2) (cell 2 2) (cell 3 2) (cell 4 2) (cell 5 2) (cell 6 2) (cell 7 2) (cell 8 2) (cell 9 2) (cell 10 2) (cell 11 2) (cell 12 2) (cell 13 2) (cell 14 2) (cell 15 2) (cell 16 2) (cell 17 2)]
               [:tr (cell 1 3) (cell 2 3) (cell 3 3) (cell 4 3) (cell 5 3) (cell 6 3) (cell 7 3) (cell 8 3) (cell 9 3) (cell 10 3) (cell 11 3) (cell 12 3) (cell 13 3) (cell 14 3) (cell 15 3) (cell 16 3) (cell 17 3)]
               [:tr (cell 1 4) (cell 2 4) (cell 3 4) (cell 4 4) (cell 5 4) (cell 6 4) (cell 7 4) (cell 8 4) (cell 9 4) (cell 10 4) (cell 11 4) (cell 12 4) (cell 13 4) (cell 14 4) (cell 15 4) (cell 16 4) (cell 17 4)]
               [:tr (cell 1 5) (cell 2 5) (cell 3 5) (cell 4 5) (cell 5 5) (cell 6 5) (cell 7 5) (cell 8 5) (cell 9 5) (cell 10 5) (cell 11 5) (cell 12 5) (cell 13 5) (cell 14 5) (cell 15 5) (cell 16 5) (cell 17 5)]
               [:tr (cell 1 6) (cell 2 6) (cell 3 6) (cell 4 6) (cell 5 6) (cell 6 6) (cell 7 6) (cell 8 6) (cell 9 6) (cell 10 6) (cell 11 6) (cell 12 6) (cell 13 6) (cell 14 6) (cell 15 6) (cell 16 6) (cell 17 6)]
               [:tr (cell 1 7) (cell 2 7) (cell 3 7) (cell 4 7) (cell 5 7) (cell 6 7) (cell 7 7) (cell 8 7) (cell 9 7) (cell 10 7) (cell 11 7) (cell 12 7) (cell 13 7) (cell 14 7) (cell 15 7) (cell 16 7) (cell 17 7)]
               [:tr (cell 1 8) (cell 2 8) (cell 3 8) (cell 4 8) (cell 5 8) (cell 6 8) (cell 7 8) (cell 8 8) (cell 9 8) (cell 10 8) (cell 11 8) (cell 12 8) (cell 13 8) (cell 14 8) (cell 15 8) (cell 16 8) (cell 17 8)]
               [:tr (cell 1 9) (cell 2 9) (cell 3 9) (cell 4 9) (cell 5 9) (cell 6 9) (cell 7 9) (cell 8 9) (cell 9 9) (cell 10 9) (cell 11 9) (cell 12 9) (cell 13 9) (cell 14 9) (cell 15 9) (cell 16 9) (cell 17 9)]
               [:tr (cell 1 10) (cell 2 10) (cell 3 10) (cell 4 10) (cell 5 10) (cell 6 10) (cell 7 10) (cell 8 10) (cell 9 10) (cell 10 10) (cell 11 10) (cell 12 10) (cell 13 10) (cell 14 10) (cell 15 10) (cell 16 10) (cell 17 10)]
               [:tr (cell 1 11) (cell 2 11) (cell 3 11) (cell 4 11) (cell 5 11) (cell 6 11) (cell 7 11) (cell 8 11) (cell 9 11) (cell 10 11) (cell 11 11) (cell 12 11) (cell 13 11) (cell 14 11) (cell 15 11) (cell 16 11) (cell 17 11)]
               [:tr (cell 1 12) (cell 2 12) (cell 3 12) (cell 4 12) (cell 5 12) (cell 6 12) (cell 7 12) (cell 8 12) (cell 9 12) (cell 10 12) (cell 11 12) (cell 12 12) (cell 13 12) (cell 14 12) (cell 15 12) (cell 16 12) (cell 17 12)]
               [:tr (cell 1 13) (cell 2 13) (cell 3 13) (cell 4 13) (cell 5 13) (cell 6 13) (cell 7 13) (cell 8 13) (cell 9 13) (cell 10 13) (cell 11 13) (cell 12 13) (cell 13 13) (cell 14 13) (cell 15 13) (cell 16 13) (cell 17 13)]
               [:tr (cell 1 14) (cell 2 14) (cell 3 14) (cell 4 14) (cell 5 14) (cell 6 14) (cell 7 14) (cell 8 14) (cell 9 14) (cell 10 14) (cell 11 14) (cell 12 14) (cell 13 14) (cell 14 14) (cell 15 14) (cell 16 14) (cell 17 14)]
               [:tr (cell 1 15) (cell 2 15) (cell 3 15) (cell 4 15) (cell 5 15) (cell 6 15) (cell 7 15) (cell 8 15) (cell 9 15) (cell 10 15) (cell 11 15) (cell 12 15) (cell 13 15) (cell 14 15) (cell 15 15) (cell 16 15) (cell 17 15)]
               [:tr (cell 1 16) (cell 2 16) (cell 3 16) (cell 4 16) (cell 5 16) (cell 6 16) (cell 7 16) (cell 8 16) (cell 9 16) (cell 10 16) (cell 11 16) (cell 12 16) (cell 13 16) (cell 14 16) (cell 15 16) (cell 16 16) (cell 17 16)]
               [:tr (cell 1 17) (cell 2 17) (cell 3 17) (cell 4 17) (cell 5 17) (cell 6 17) (cell 7 17) (cell 8 17) (cell 9 17) (cell 10 17) (cell 11 17) (cell 12 17) (cell 13 17) (cell 14 17) (cell 15 17) (cell 16 17) (cell 17 17)]
               ])

(defn handle-keydown [e]
  (if (= 87 (.-keyCode e)) (if (not (is-in? [(:centerx @state) (:centery @state)] horwalls)) (swap! state update-in [:centery] dec)))
  (if (= 83 (.-keyCode e)) (if (not (is-in? [(:centerx @state) (inc (:centery @state))] horwalls)) (swap! state update-in [:centery] inc)))
  (if (= 65 (.-keyCode e)) (if (not (is-in? [(:centerx @state) (:centery @state)] verwalls)) (swap! state update-in [:centerx] dec)))
  (if (= 68 (.-keyCode e)) (if (not (is-in? [(inc (:centerx @state)) (:centery @state)] verwalls)) (swap! state update-in [:centerx] inc))))

(.addEventListener js/document "keydown" handle-keydown)

(reagent/render-component [page] (.getElementById js/document "app"))

