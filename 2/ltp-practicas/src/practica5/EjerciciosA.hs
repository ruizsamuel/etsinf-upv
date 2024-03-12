module EjerciciosA where

    decBin :: Int -> [Int]
    decBin x =  if x < 2 then [x] else
                (mod x 2) : decBin (div x 2)
    
    binDec :: [Int] -> Int
    binDec (x:[]) = x
    binDec (x:xs) = x + binDec (xs) * 2

    divisors :: Int -> [Int]
    divisors x = [y | y <- [1..x], mod x y == 0]

    divisors' :: Int -> [Int]
    divisors' x = divisorsB x [1..x]

    divisorsB :: Int -> [Int] -> [Int]
    divisorsB _ [] = []
    divisorsB x (y:ys)
        | mod x y == 0 = y : divisorsB x ys
        | otherwise = divisorsB x ys

    member :: Int -> [Int] -> Bool
    member _ [] = False
    member x (y:ys)
        | x == y = True
        | otherwise = member x ys

    isPrime :: Int -> Bool
    isPrime x
        | x == 1 = True
        | otherwise = divisors x == [1,x]
    
    primes :: Int -> [Int]
    primes x = take x [y | y <- [1..], isPrime y]

    selectEven :: [Int] -> [Int]
    selectEven xs = [y | y <- xs, (mod y 2) == 0]

    selectEvenPos' :: [Int] -> [Int]
    selectEvenPos' [] = []
    selectEvenPos' (x:[]) = [x]
    selectEvenPos' (x:(y:xs)) = x:(selectEvenPos xs)

    selectEvenPos :: [Int] -> [Int]
    selectEvenPos xs = [xs !! z | z <- [0,2..length(xs) - 1]]

    ins :: Int -> [Int] -> [Int]
    ins x xs = [y | y <- xs, y <= x] ++ [x] ++ [y | y <- xs, y > x]

    iSort :: [Int] -> [Int]
    iSort [] = []
    iSort (x:xs) = (ins x (iSort xs))

    doubleAll :: [Int] -> [Int]
    doubleAll xs = map (*2) xs

    map' :: (a -> b) -> [a] -> [b]
    map' f xs = [f y | y <- xs]

    filter' :: (a -> Bool) -> [a] -> [a]
    filter' f xs = [y | y <- xs, f y]