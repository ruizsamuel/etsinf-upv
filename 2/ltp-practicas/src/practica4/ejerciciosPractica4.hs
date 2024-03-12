module EjerciciosPractica4 where
    import Data.Char
    import Factorial

    numCbetw2 :: Char -> Char -> Int
    numCbetw2 c d = if c == d then 0 else 
                    if d > c then ord (d) - ord (c) - 1 else 
                    ord (c) - ord (d) - 1

    addRange :: Int -> Int -> Int
    addRange x y =  if x == y then x else 
                    if x > y then y + addRange x (y + 1) else
                    x + addRange (x+1) y

    max' :: Ord a => a -> a -> a
    max' a b = if a > b then a else b

    leapyear :: Int -> Bool
    leapyear y = if (mod y 4) == 0 then
                    if (mod y 100) == 0 then 
                        if (mod y 400) == 0 then True else False 
                    else True 
                 else False

    daysAmonth :: Int -> Int -> Int
    daysAmonth m y = if m < 8 then
                        if m == 2 then
                            if leapyear y then 29 else 28
                        else if mod m 2 == 0 then 30 else 31
                     else if m <= 12 then
                        if mod m 2 == 0 then 31 else 30
                     else 0
    
    remainder :: Int -> Int -> Int
    remainder a b = if a >= 0 && b > 0 then 
                        if a >= b then remainder (a-b) b else a
                    else 0

    sumFacts :: Int -> Int
    sumFacts n = if n > 1 then fact n + sumFacts (n - 1) else 1 