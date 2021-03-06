(ns mycljsapp.core
  (:require [reagent.core :as reagent]))

(def verwalls [[4 4] [4 5] [4 6] [4 7] [4 8] [4 9] [4 10]
               [5 5] [5 6] [5 7] [5 8] [5 10]
               [11 5] [11 6] [11 7] [11 8] [11 9] [11 10]
               [7 9] [7 8] [7 7] [8 7] [8 9] [9 8] [6 6] [7 6]])

(def horwalls [[4 4] [5 4] [6 4] [7 4] [8 4] [9 4] [10 4]
               [5 5] [6 5] [7 5] [8 5] [9 5] [10 5]
               [4 11] [5 11] [6 11] [7 11] [8 11] [9 11] [10 11]
               [6 6] [8 8] [7 7] [8 9]])

(def radius 4)
(def diameter (+ 1 (* 2 radius)))

(defonce state (reagent/atom {:centerx 7 :centery 7}))

(defn is-in? [an-item a-seq]
  (some #{an-item} a-seq))

(defn cell [x y]
  (let [realx (- x (- radius (:centerx @state)))
        realy (- y (- radius (:centery @state)))
        images (remove
                 nil?
                 [(if (= [radius radius] [x y]) "url(img/center.png)" nil)

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
                  ])
        style (if (empty? images)
                {}
                {:style {:background-image (clojure.string/join "," images)}})]
    [:td (merge {:key (str "td-" x "-" y)} style)]))

(defn page [] [:table
               {:id "grid" :style {:border-collapse :collapse :border "1px solid black"} :cellSpacing "0"}
               [:tbody
                (doall (for [y (range diameter)]
                         [:tr {:key (str "tr-" y)}
                          (doall (for [x (range diameter)]
                                   ^{:key (str "cell-" x "-" y)}
                                   [cell x y]))]))]])

(defn handle-keydown [e]
  (if (= 38 (.-keyCode e)) (if (not (is-in? [(:centerx @state) (:centery @state)] horwalls)) (swap! state update-in [:centery] dec)))
  (if (= 40 (.-keyCode e)) (if (not (is-in? [(:centerx @state) (inc (:centery @state))] horwalls)) (swap! state update-in [:centery] inc)))
  (if (= 37 (.-keyCode e)) (if (not (is-in? [(:centerx @state) (:centery @state)] verwalls)) (swap! state update-in [:centerx] dec)))
  (if (= 39 (.-keyCode e)) (if (not (is-in? [(inc (:centerx @state)) (:centery @state)] verwalls)) (swap! state update-in [:centerx] inc))))

(.addEventListener js/document "keydown" handle-keydown)

(reagent/render-component [page] (.getElementById js/document "app"))
