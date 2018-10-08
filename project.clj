(defproject org.yotsov/mycljsapp "0.1.0-SNAPSHOT"

  ; lein -v
  ; Leiningen 2.8.1 on Java 1.8.0_51 Java HotSpot(TM) 64-Bit Server VM

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.339"]
                 [reagent "0.8.1"]
                 [figwheel "0.5.16"]]
  :plugins [[lein-figwheel "0.5.16"]
            [lein-cljsbuild "1.1.7"]]

  :clean-targets ^{:protect false} ["target" "resources/public/js/compiled"]

  :cljsbuild {:builds [{:id "dev"
                        ; lein do clean, trampoline figwheel dev
                        :source-paths ["src-cljs/"]
                        :figwheel true
                        :compiler {:optimizations :none
                                   :output-to "resources/public/js/compiled/app.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :main "mycljsapp.core"
                                   :asset-path "js/compiled/out"}}

                       {:id "prod"
                        ; lein do clean, cljsbuild once prod
                        :source-paths ["src-cljs/"]
                        :compiler {:optimizations :advanced
                                   :output-to "resources/public/js/compiled/app.js"}}]}

  :figwheel {:css-dirs ["resources/public/css"]})
