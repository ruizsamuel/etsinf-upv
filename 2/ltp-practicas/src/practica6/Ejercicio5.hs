module Ejercicio5 (Queue, empty, enqueue, dequeue, first, isEmpty, size) where
    data Queue a = EmptyQueue | Item a (Queue a)
    empty = EmptyQueue
    enqueue x EmptyQueue = Item x EmptyQueue
    enqueue x (Item a q) = Item a (enqueue x q)
    dequeue (Item _ q) = q
    first (Item a _) = a
    isEmpty EmptyQueue = True
    isEmpty _ = False
    size EmptyQueue = 0
    size (Item _ q) = 1 + size q

    instance (Eq a) => Eq (Queue a) where
        EmptyQueue == EmptyQueue = True
        EmptyQueue == (Item a q) = False
        (Item a q) == EmptyQueue = False
        (Item a q) == (Item b w) | ((a == b) && (q == w)) = True
                                 | otherwise = False
        q /= w = not(q == w)