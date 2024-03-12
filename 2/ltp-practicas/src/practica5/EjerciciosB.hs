module EjerciciosB where
    type Person = String
    type Book = String
    type Database = [(Person,Book)]

    exampleBase :: Database
    exampleBase = [("Alicia","El nombre de la rosa"),("Juan", "La hija del canibal"), ("Pepe","Odesa"),("Alicia", "La ciudad de las bestias")]

    obtain :: Database -> Person -> [Book]
    obtain dBase thisPerson = [book | (person,book) <- dBase, person == thisPerson]

    borrow :: Database -> Book -> Person -> Database
    borrow dBase book thisPerson = dBase ++ [(thisPerson, book)]

    return' :: Database -> (Person,Book) -> Database
    return' dBase (thisPerson, book) = [(x,y) | (x,y) <- dBase, (x,y) /= (thisPerson, book)]

    data Tree a = Leaf a | Branch (Tree a) (Tree a) deriving Show

    symetric :: Tree a -> Tree a
    symetric (Leaf a) = Leaf a
    symetric (Branch a b) = Branch (symetric b) (symetric a)

    listToTree :: [a] -> Tree a
    listToTree (x:[]) = Leaf x
    listToTree (x:xs) = Branch (listToTree [x]) (listToTree xs)

    treeToList :: Tree a -> [a]
    treeToList (Leaf a) = a:[]
    treeToList (Branch a b) = (treeToList a) ++ (treeToList b)

    data BinTreeInt = Void | Node Int BinTreeInt BinTreeInt deriving Show

    insTree :: Int -> BinTreeInt -> BinTreeInt
    insTree n Void = (Node n Void Void)
    insTree n (Node x a b)
        | n <= x = Node x (insTree n a) b
        | otherwise = Node x a (insTree n b)

    creaTree :: [Int] -> BinTreeInt
    creaTree (x:[]) = insTree x Void
    creaTree (x:xs) = insTree x (creaTree xs)

    treeElem :: Int -> BinTreeInt -> Bool
    treeElem _ Void = False
    treeElem n (Node x _ _) | x == n = True
    treeElem n (Node x a b)
        | n <= x = treeElem n a
        | otherwise = treeElem n b