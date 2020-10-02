(ns clojure1.core)

"rec 4"
(defn add_alphabet_to_word [started alphabet comb]
  (if (empty? alphabet)
     comb
     (let [firs (first alphabet) first_in_started (first started)]
     (if (not= firs first_in_started)
       (add_alphabet_to_word started (rest alphabet) (cons (cons firs started) comb))
       (add_alphabet_to_word started (rest alphabet) comb)))))

"rec 3"
  (defn add_new_combo [started alphabet tail]
    (let [combinations (add_alphabet_to_word (first started) alphabet ()) ]
      (if (empty? (rest started))
        (concat tail combinations)
        (add_new_combo (rest started) alphabet (concat tail combinations)))))

"rec 2"
  (defn main_rec [started n alphabet]
    (if (= n 0)
      started
      (main_rec (add_new_combo started alphabet ()) (- n 1) alphabet)))

"rec 1"
  (defn create_started_list [alphabet tail]
    (if (empty? (rest alphabet))
      (cons (list (first alphabet)) tail)
      (create_started_list (rest alphabet) (cons (list (first alphabet)) tail))))

  (defn resolution [n alphabet]
    (let [started (create_started_list alphabet ())]
      (if (= n 1)
        started
        (main_rec started (- n 1) alphabet))))

  (defn -main
    "Задан набор символов и число n.
    Опишите функцию, которая возвращает
    список всех строк длины n,
    состоящих из этих символов и
    не содержащих двух одинаковых
    символов, идущих подряд."
    [& args]
    "решение для args = list n"
    (println (resolution (Integer/parseInt (last args)) (drop-last args))))