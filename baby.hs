import Data.List
--questao 1
s1 = (+2)
s2 = (>3)
funcaoQ1 x = map (s1) (filter (s2) x)
--questao 2
--letra a
viraSort :: [Int] -> [Int]
makeSet l = viraSort (Data.List.sort l)
viraSort [] = []
viraSort x
    |elem (head (x)) (tail (x)) = viraSort((tail x))
    |otherwise = [head x] ++ viraSort((tail x))
--letra b
unionB :: [Int] -> [Int] ->  [Int]
unionB x y = makeSet((makeSet (x)) ++ (makeSet (y)))
--letra c
mapSet f c = map (f) (makeSet(c))
--questao 3
type Texto = String
type Id = String
type DataHoraPub = Int
data Post = Post (Id, DataHoraPub) Texto deriving (Show, Eq)
data Thread = Nil | T Post (Thread)
--letra a
instance Show Thread where
show Nil = ""
show (T (Post (id, dhp) tex) thr) = "(" ++ id ++ " " ++ (Prelude.show dhp) ++ " " ++ tex ++ ")" ++ (Main.show thr)
--letra b
inserirPost :: Post -> Thread -> Thread
inserirPost post fred = T post fred
--letra c
threadToList :: Thread -> [Post]
threadToList Nil = []
threadToList (T post fred) = [post] ++ (threadToList fred)
-- letra d
listToThread :: [Post] -> Thread
listToThread [] = Nil
listToThread posts = T (head posts) (listToThread (tail posts))
--letra efilter (\(Post (a, b) t) -> (a == "Joao") && (b == 1)) lista
removerPost :: (Id, DataHoraPub) -> Thread -> Thread
removerPost (id, dataH) fred = listToThread (filter (\(Post (a, b) t) -> (a /= id) && (b /= dataH)) (threadToList fred))
--a = T(Post ("J", 1) "qwer") (T (Post ("M", 2) "asdf") Nil)
