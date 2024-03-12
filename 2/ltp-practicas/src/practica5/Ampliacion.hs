module Amplicacion where
    repeated :: Int -> [Int] -> Int
    repeated _ [] = 0
    repeated n (x:xs)
        | n == x = 1 + repeated n xs
        | otherwise = repeated n xs

    concat' :: [[a]] -> [a]
    concat' [] = []
    concat' (x:xs) = x ++ concat' (xs) -- alba

    square :: Int -> Int
    square x = x * x

    data BinTreeInt = Void | Node Int BinTreeInt BinTreeInt deriving Show

    dupElem :: BinTreeInt -> BinTreeInt
    dupElem Void = Void
    dupElem (Node n a b) = Node (2*n) (dupElem a) (dupElem b)

    data Tree a = Branch a (Tree a) (Tree a) | Null deriving Show

    countProperty :: (a -> Bool) -> (Tree a) -> Int
    countProperty _ Null = 0
    countProperty f (Branch n a b) = (countProperty f a) + (countProperty f b) + (if f n then 1 else 0)