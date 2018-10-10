--questao 2
subListas :: [a] -> [[a]]
subListas l = [l]
--questao 3
--a
poli :: Integer -> Integer -> Integer -> (Integer -> Integer)
poli a b c = (\x -> a*(x^2) + b*x + c)
--b 
poliListas l = [poli a b c | (a, b, c) <- l]
--c
appListaPoli :: [Integer -> Integer] -> [Integer] -> [Integer]
appListaPoli funcL xl = [func x |func <- funcL, x <- xl]
--questao 4
--a
checarMatriz :: [[a]] -> Bool
checarMatriz matriz = not (elem False [(length x == length y)| x <- matriz, y <- matriz])
--b
permutarLinhas :: Int -> Int -> [[a]] -> [[a]]
permutarLinhas x y m = (take y ((take (x) m) ++ [m!!y] ++ (drop (x+1) m))) ++ [m!!x] ++ (drop (y+1) m)
--questao 5
checarSoma :: [Int] -> Bool
checarSoma [] = False
checarSoma l
    |(sum ([x|x <- l, mod x 2 /= 0])) > (sum ([x|x <- l, mod x 2 == 0]))  = True
    |otherwise = False
maiorSoma :: [[Int]] -> Int
maiorSoma [] = 0
maiorSoma l = maximum [(sum x) | x <- l]
filtrarEInserir :: [[Int]] -> Int -> ([[Int]], Int) 
filtrarEInserir [] n = ([], 0)
filtrarEInserir l n = ((filter (checarSoma) l), (maiorSoma (filter (checarSoma) l))*n)
--questao 6
intercalar :: [a] -> [a] -> Int -> [a]
intercalar [] [] n = []
intercalar l1 l2 n
    |(mod n 2 == 0) = [head l1] ++ (intercalar (tail l1) (tail l2) (n+1))
    |otherwise = [head l2] ++ (intercalar (tail l1) (tail l2) (n+1))
altMap :: (a -> b) -> (a -> b) -> [a] -> [b]
altMap f1 f2 l = intercalar (map (f1) l) (map (f2) l) 0
--questao 7
type Codigo = Int
data Voto = Presidente Codigo | Senador Codigo | Deputado Codigo | Branco deriving (Show)
type Urna = [Voto]
type Apuracao = [(Voto, Int)]
--letra a
instance Eq Voto where
    (Presidente cod1) == (Presidente cod2) = (cod1 == cod2)
    (Senador cod1) == (Senador cod2) = (cod1 == cod2)
    (Deputado cod1) == (Deputado cod2) = (cod1 == cod2)
    (Branco) == (Branco) = True
--letra b
totalVotos :: Urna -> Voto -> Int
totalVotos urna voto = sum([1 | x <- urna, voto == x])
--letra c
makeSetApu :: Apuracao -> Apuracao
makeSetApu [] = []
makeSetApu apu
    |elem (head apu) (tail apu) = makeSetApu (tail apu)
    |otherwise = [(head apu)] ++ (makeSetApu (tail apu))
apurar :: Urna -> Apuracao
apurar urna = makeSetApu [(voto, (totalVotos urna voto)) | voto <- urna]