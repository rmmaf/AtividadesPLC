c :: [Int] -> [Int]
c (x:xs)  =  [ x | (x,y) <- zip (x:xs) xs, x == y ]

d :: [Int] -> [Int]
d [x]       =  []
d (x:y:zs) | x==y       = x : d (y:zs)
           | otherwise  = d (y:zs)
--questao 2
g ::[Int] -> Bool
g l = not ((foldr (+) 0 (map (\x -> mod x 2) (filter (\x -> (x >= 0) && (x <= 100)) l))) > 0)
--questao 3
type Nome = String
type Pot = Int
data Lampada = Compacta Nome Pot | Incand Nome Pot
instance Show Lampada where
    show (Compacta nome pot) = "Compacta " ++ nome ++ " " ++ (show pot)
    show (Incand nome pot) = "Incand " ++ nome ++ " " ++ (show pot)
instance Eq Lampada where
    (Compacta n1 p1) == (Compacta n2 p2) = (n1 == n2) && (p2 == p1)
    (Incand n1 p1) == (Incand n2 p2) = (n1 == n2) && (p2 == p1)
    (Compacta n1 p1) == (Incand n2 p2) = False
    (Incand n1 p1) == (Compacta n2 p2) = False