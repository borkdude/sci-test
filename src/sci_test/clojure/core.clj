(ns sci-test.impl.clojure.core
  {:no-doc true}
  (:refer-clojure :exclude [future read read-string])
  (:require [sci.core :as sci]
            [sci.impl.namespaces :refer [copy-core-var]]))

(defn time*
  "Evaluates expr and prints the time it took.  Returns the value of
  expr."
  [_ _ expr]
  `(let [start# (. System (nanoTime))
         ret# ~expr]
     (prn (str "Elapsed time: " (/ (double (- (. System (nanoTime)) start#)) 1000000.0) " msecs"))
     ret#))

(def data-readers (sci/new-dynamic-var '*data-readers* nil))

(def core-extras
  {'file-seq (copy-core-var file-seq)
   'agent (copy-core-var agent)
   'send (copy-core-var send)
   'send-off (copy-core-var send-off)
   'promise (copy-core-var promise)
   'deliver (copy-core-var deliver)
   'shutdown-agents (copy-core-var shutdown-agents)
   'slurp (copy-core-var slurp)
   'spit (copy-core-var spit)
   'time (with-meta time* {:sci/macro true})
   'Throwable->map (copy-core-var Throwable->map)
   'compare-and-set! (copy-core-var compare-and-set!)
   '*data-readers* data-readers
   'default-data-readers default-data-readers
   'xml-seq (copy-core-var xml-seq)})