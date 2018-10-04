--questao1 dado x e y retorne o mdc etre os dois (algoritmo de euclides)
mdc :: Int -> Int -> Int
mdc a 0 = a
mdc a b = mdc b (mod a b)
--questao2 dado um inteiro x verifique se x eh primo
primo :: Int -> Bool
primo n = elem n (primoList([2..n]))
primoList :: [Int]-> [Int]
primoList list
    |list == ([x | x <-list, ((mod x (head list)) /= 0  && (head list) /= x) || head list == x]) = list
    |otherwise = primoList ([x | x <-list, ((mod x (head list)) /= 0  && (head list) /= x) || head list == x])
--questao 3
type Ponto = (Double, Double, Double)
distancia :: Ponto -> Ponto -> Double
distancia ((x1, y1, z1)) ((x2, y2, z2)) = sqrt ((x1 - x2)^2 + (y1 - y2)^2 + (z1 - z2)^2)
--questao 4
somaDosQuadrados = sum [a^2 |a <- [12, 22..1002]]
--questao 5
grid :: Int -> Int -> [(Int, Int)]
grid n1 n2 = [(a, b) | a <- [0..n1], b <- [0..n2]]
--questao 6
square :: Int -> [(Int, Int)]
square n = [(a, b) | (a, b) <- grid n n, a /= b]
--questao 7
merge :: Ord a => [a] ->[a] ->[a]
merge [] l2 = l2
merge l1 [] = l1
merge [] [] = []
merge (x:xs) (y:ys)
    |x < y = [x] ++ (merge (xs) (y:ys))
    |otherwise = [y] ++ (merge (x:xs) (ys))
--questao 8
msort :: Ord a => [a] -> [a]
msort [] = []
msort [a] = [a]
msort list = merge (msort l1) (msort l2) where (l1, l2) = halve list
halve :: [a] -> ([a],[a])
halve [a] = ([a], [])
halve list = (take (div (length list) 2) list, drop (div (length list) 2) list)
--questao 9
aplicaFuncoes :: [Int->Int] -> [Int] -> [[Int]]
aplicaFuncoes listaFunc lista = [map funcao lista| funcao <- listaFunc]
--questao 10
data DiasSemana = Domingo | Segunda | Terca | Quarta | Quinta | Sexta | Sabado deriving (Ord, Enum, Eq, Show)
ordenaUteis :: [DiasSemana] -> [DiasSemana]
ordenaUteis dias = msort dias
datasIguais :: [(DiasSemana,Int)] -> DiasSemana -> [Int]
datasIguais list diaEx = makeSetList (msort([dia | (diaSemana, dia) <- list, (diaSemana1, dia2) <- list, diaSemana == diaSemana1 && diaSemana  == diaEx]))
makeSetList :: [Int] -> [Int]
makeSetList [] = []
makeSetList l
    |elem (head l) (tail l) = makeSetList (tail l)
    |otherwise = [head l] ++ makeSetList (tail l)
imprimeMes :: DiasSemana -> [(Int, DiasSemana)]
imprimeMes diaEspec = imprimeMesAux 1 diaEspec
imprimeMesAux :: Int -> DiasSemana -> [(Int, DiasSemana)]
imprimeMesAux 30 dia = [(30, dia)]
imprimeMesAux n diaSemana
    |diaSemana == Sabado = [(n, Sabado)] ++ (imprimeMesAux (n + 1) (Domingo))
    |otherwise = [(n, diaSemana)] ++ (imprimeMesAux (n+1) (succ diaSemana))